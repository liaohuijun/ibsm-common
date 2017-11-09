package util.test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.hm.common.util.CommonUtil;
import com.hm.common.util.DateUtil;
import com.hm.common.util.FileUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;

/**
 * @author shishun.wang
 * @date 下午4:11:06 2017年8月15日
 * @version 1.0
 * @describe
 */
public class TestMongoDriver {

	public static Double lastTotalOilConsumption;
	public static Double lastTotalDrivingMileage;

	public static void main(String[] args) throws Exception {

		MongoClientOptions.Builder build = new MongoClientOptions.Builder();
		// 与数据最大连接数50
		build.connectionsPerHost(50);
		// 如果当前所有的connection都在使用中，则每个connection上可以有50个线程排队等待
		build.threadsAllowedToBlockForConnectionMultiplier(50);
		build.connectTimeout(1 * 60 * 1000);
		build.maxWaitTime(2 * 60 * 1000);
		MongoClientOptions options = build.build();
		MongoClient client = new MongoClient("10.28.16.89", options);

		// 获取数据库test,不存在的话，会自动建立该数据库
		MongoDatabase db = client.getDatabase("smzc");
		// 获取data集合，不存在的话，会自动建立该集合（相当于关系数据库中的数据表）

		List<String> list = Arrays.asList("2017-10-20", "2017-10-21", "2017-10-22", "2017-10-23", "2017-10-24",
				"2017-10-25", "2017-10-26", "2017-10-27", "2017-10-28", "2017-10-29", "2017-10-30", "2017-10-31",
				"2017-11-01", "2017-11-02");
		boolean hasTitle = true;
		for (String day : list) {
			loadData(db, day, hasTitle);
			if (hasTitle) {
				hasTitle = false;
			}
		}

		// 加载车辆注册数据
		loadRegData(db);
		// 加载车辆上报数据
		loadCarUpload(db);

	}

	private static void loadRegData(MongoDatabase db) throws Exception {
		String collectionName = "CBOBD_REGISTRATION_UPSIDE";
		AggregateIterable<Document> iterable = db
				.getCollection(
						collectionName)
				.aggregate(Arrays.asList(Aggregates.match(Filters.and(Filters.eq("deviceId", "13173900985"),
						Filters.and(
								Filters.gt("createTime", new Date(DateUtil.yyyymmddhhmmss2long("2017-10-24 00:00:00"))),
								Filters.lt("createTime",
										new Date(DateUtil.yyyymmddhhmmss2long("2017-12-01 23:59:59"))))))));

		StringBuilder builder = new StringBuilder();
		for (Document document : iterable) {
			loadCarUploadData(db, builder, document.get("upsideId").toString());
		}
		FileUtil.writer("d:/cobd/" + DateUtil.now4yyyyMMdd() + "_车辆注册平台上报数据.txt", builder.toString());
	}

	private static void loadCarUpload(MongoDatabase db) throws Exception {
		String collectionName = "CBOBD_LOCATION_INFO_UPSIDE";
		AggregateIterable<Document> iterable = db.getCollection(collectionName).aggregate(Arrays.asList(
				Aggregates.match(
						Filters.and(Filters.eq("deviceId", "13173900985"), Filters.and(Filters.ne("upsideId", null)),
								// Aggregates.match(Filters.and(Filters.eq("deviceId",
								// "13172500117"),
								Filters.and(Filters.gt("gpsTime", "2017-10-24 00:00:00"),
										Filters.lt("gpsTime", "2017-12-01 23:59:59")))),
				Aggregates.sort(new Document().append("gpsTime", -1))));

		StringBuilder builder = new StringBuilder();
		for (Document document : iterable) {
			loadCarUploadData(db, builder, document.get("upsideId").toString());
		}
		FileUtil.writer("d:/cobd/" + DateUtil.now4yyyyMMdd() + "_车辆上行上报数据.txt", builder.toString());
	}

	private static void loadCarUploadData(MongoDatabase db, StringBuilder builder, String upsideId) {
		String collectionName = "OBD_UPSIDE_DATA";
		AggregateIterable<Document> iterable = db.getCollection(collectionName)
				.aggregate(Arrays.asList(Aggregates.match(Filters.and(Filters.eq("_id", upsideId)))));

		for (Document document : iterable) {
			builder.append("终端上报数据日期:").append(DateUtil.yyyyMMddhhmm((Date) document.get("createTime")))
					.append(System.lineSeparator());
			builder.append("终端上报未转义报文:").append(document.get("originalData")).append(System.lineSeparator());
			builder.append("终端上报转义报文:").append(escape7D02or7D01(document.get("originalData").toString()))
					.append(System.lineSeparator());
		}

		FindIterable<Document> findIterable = db.getCollection("OBD_DOWNSIDE_DATA")
				.find(new BasicDBObject("upsideId", upsideId));
		for (Document document : findIterable) {
			String downData = document.get("responseData").toString();
			builder.append("终端下行未转义报文:").append(downData).append(System.lineSeparator());
			builder.append("终端下行转义报文:").append(escape7Eor7D(downData)).append(System.lineSeparator());
			if(!document.get("escapeResponseData").equals(escape7Eor7D(downData))){
				System.out.println("检测到数据非法");
			}
		}
		builder.append(System.lineSeparator());
	}

	/**
	 * 转义7E、7D
	 * 
	 * @param content
	 * @return
	 */
	private static String escape7Eor7D(String content) {
		String data = content;
		data = data.substring(2).toUpperCase();// 去头部E7
		data = data.substring(0, data.length() - 2);// 去尾部E7

		String regex = "(.{2})";
		String dataTmp = data.replaceAll(regex, "$1 ");
		String[] datas = dataTmp.split("\\s+");

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < datas.length; i++) {
			if ("7D".equals(datas[i])) {
				builder.append("7D01");
				continue;
			}
			if ("7E".equals(datas[i])) {
				builder.append("7D02");
				continue;
			}
			builder.append(datas[i]);
		}
		return "7E" + builder.toString() + "7E";
	}

	/**
	 * 转换7D01、7D02
	 * 
	 * @param content
	 * @return
	 */
	private static String escape7D02or7D01(String content) {
		String data = content;
		data = data.substring(2).toUpperCase();// 去头部E7
		data = data.substring(0, data.length() - 2);// 去尾部E7

		String regex = "(.{2})";
		String dataTmp = data.replaceAll(regex, "$1 ");
		String[] datas = dataTmp.split("\\s+");

		String lastCode = "";
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < datas.length; i++) {
			if ("7D01".equals(lastCode + datas[i])) {
				builder.delete(builder.length() - 2, builder.length());
				builder.append("7D");
				lastCode = datas[i];
				continue;
			}

			if ("7D02".equals(lastCode + datas[i])) {
				builder.delete(builder.length() - 2, builder.length());
				builder.append("7E");
				lastCode = datas[i];
				continue;
			}

			lastCode = datas[i];
			builder.append(datas[i]);
		}
		return "7E" + builder.toString() + "7E";
	}

	private static void loadData(MongoDatabase db, String day, boolean hasTitle) {
		String collectionName = "CBOBD_LOCATION_INFO_UPSIDE";
		AggregateIterable<Document> iterable = db.getCollection(collectionName)
				.aggregate(Arrays.asList(
						Aggregates.match(Filters.and(Filters.eq("deviceId", "13173900985"),
								// Aggregates.match(Filters.and(Filters.eq("deviceId",
								// "13172500117"),
								Filters.and(Filters.gt("gpsTime", day + " 00:00:00"),
										Filters.lt("gpsTime", day + " 23:59:59")))),
						Aggregates.sort(new Document().append("gpsTime", -1)), Aggregates.limit(1)));
		for (Document document : iterable) {
			if (hasTitle) {
				System.out.println("GPS数据上报时间\t\t终端手机号\t\t总油耗(L)\t\t总里程(KM)\t当日行驶油耗(L)\t当日行驶里程(KM)\t数据报文");
			}
			String buffer = document.get("gpsTime") + "\t" + document.get("deviceId") + "\t"
					+ trace(Double.valueOf(document.get("totalOilConsumption") + "")) + "\t\t"
					+ trace(Double.valueOf(document.get("totalDrivingMileage") + "")) + "\t";
			{
				if (CommonUtil.isEmpty(lastTotalOilConsumption)) {
					buffer += "\t" + 0;
				} else {
					buffer += "\t"
							+ trace(Double.valueOf(document.get("totalOilConsumption") + "") - lastTotalOilConsumption);
				}
				lastTotalOilConsumption = Double.valueOf(document.get("totalOilConsumption") + "");
			}

			{
				if (CommonUtil.isEmpty(lastTotalDrivingMileage)) {
					buffer += "\t\t" + 0;
				} else {
					buffer += "\t\t"
							+ trace(Double.valueOf(document.get("totalDrivingMileage") + "") - lastTotalDrivingMileage);
				}
				lastTotalDrivingMileage = Double.valueOf(document.get("totalDrivingMileage") + "");
			}

			buffer += "\t\t" + document.get("originalData");
			System.out.println(buffer);
		}

	}

	private static double trace(double data) {
		BigDecimal b = new BigDecimal(data);
		return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
