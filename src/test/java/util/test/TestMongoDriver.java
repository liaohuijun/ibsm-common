package util.test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.hm.common.util.CommonUtil;
import com.hm.common.util.FileUtil;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.AggregateIterable;
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

		List<String> list = Arrays.asList("2017-08-24", "2017-08-25", "2017-08-26", "2017-08-27", "2017-08-28",
				"2017-08-29", "2017-08-30", "2017-08-31", "2017-09-01", "2017-09-02", "2017-09-03", "2017-09-04",
				"2017-09-05", "2017-09-06", "2017-09-07", "2017-09-08", "2017-09-09", "2017-09-10", "2017-09-11",
				"2017-09-12", "2017-09-13", "2017-09-14", "2017-09-15", "2017-09-16", "2017-09-17", "2017-09-18",
				"2017-09-19", "2017-09-20", "2017-09-21", "2017-09-22", "2017-09-23", "2017-09-24", "2017-09-25",
				"2017-09-26","2017-09-27","2017-09-28");
		boolean hasTitle = true;
		for (String day : list) {
			loadData(db, day, hasTitle);
			if (hasTitle) {
				hasTitle = false;
			}
		}

		String collectionName = "CBOBD_LOCATION_INFO_UPSIDE";
		AggregateIterable<Document> iterable = db.getCollection(collectionName)
				.aggregate(Arrays.asList(
						Aggregates.match(Filters.and(Filters.eq("deviceId", "13173300500"),
								Filters.and(Filters.gt("gpsTime", "2017-08-27 00:00:00"),
										Filters.lt("gpsTime", "2017-09-26 23:59:59")))),
						Aggregates.sort(new Document().append("gpsTime", -1))));
		StringBuffer buffer = new StringBuffer();
		for (Document document : iterable) {
			buffer.append(document.get("gpsTime") + "\t\t" + document.get("originalData") + "\n");
		}
		System.out.println(buffer.toString());
		FileUtil.writer("d:/test.txt", buffer.toString());
	}

	private static void loadData(MongoDatabase db, String day, boolean hasTitle) {
		String collectionName = "CBOBD_LOCATION_INFO_UPSIDE";
		AggregateIterable<Document> iterable = db.getCollection(collectionName)
				.aggregate(Arrays.asList(
						Aggregates.match(Filters.and(Filters.eq("deviceId", "13173300500"),
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
