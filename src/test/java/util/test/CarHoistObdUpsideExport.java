package util.test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.bson.Document;

import com.hm.common.util.CommonUtil;
import com.hm.common.util.FileUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;

/**
 * @author shishun.wang
 * @date 下午2:37:53 2017年10月17日
 * @version 1.0
 * @describe
 */
public class CarHoistObdUpsideExport {

	public static void main(String[] args) throws Exception {

		Date startTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2017-10-17 11:48:27");
		String[] deviceIds = {"M201612010177","M201610011933"};

		MongoClientOptions.Builder build = new MongoClientOptions.Builder();
		// 与数据最大连接数50
		build.connectionsPerHost(50);
		// 如果当前所有的connection都在使用中，则每个connection上可以有50个线程排队等待
		build.threadsAllowedToBlockForConnectionMultiplier(50);
		build.connectTimeout(1 * 60 * 1000);
		build.maxWaitTime(2 * 60 * 1000);
		MongoClientOptions options = build.build();
		MongoClient client = new MongoClient("10.28.16.89", options);
		MongoDatabase db = client.getDatabase("smzc");

		for (String deviceId : deviceIds) {
			getData(deviceId, startTime, db);
		}
	}

	/**
	 * @param deviceId
	 * @param startTime
	 * @param db
	 * @throws IOException
	 */
	private static void getData(String deviceId, Date startTime, MongoDatabase db) throws IOException {
		String[] collections = { "obdLoginDoc", "obdAlarmDescDoc", "obdAlarmUpsideDoc", "obdBaseStationDoc",
				"obdCarsUpsideDoc", "obdDormancyAwakenDoc", "obdDormancyEnterDoc", "obdDrivingBehaviorUpsideDoc",
				"obdMalfunctionCodeDoc" };
		
		String[] collectionTmps = { "登陆上行数据结构体", "报警描述", "报警数据", "基站定位数据", "车辆数据", "休眠唤醒数据", "休眠进入数据", "驾驶行为数据",
		"故障码数据" };

		for (int i = 0; i < collections.length; i++) {
			String collectionName = collections[i];

			StringBuffer content = new StringBuffer();
			
			AggregateIterable<Document> iterable = null;
			if(CommonUtil.isEmpty(startTime)){
				iterable = db.getCollection(collectionName)
						.aggregate(Arrays.asList(Aggregates.match(Filters.and(Filters.eq("deviceId", deviceId),
								Filters.ne("upsideId", null)))));
			}else{
				iterable = db.getCollection(collectionName)
						.aggregate(Arrays.asList(Aggregates.match(Filters.and(Filters.eq("deviceId", deviceId),
								Filters.ne("upsideId", null), Filters.gt("createTime", startTime)))));
			}


			for (Document document : iterable) {
				String upsideId = document.getString("upsideId");
				content.append("上行报文:" + upsideData(db, upsideId)).append(System.lineSeparator());
				content = downsideData(db, upsideId, content);
				content.append(System.lineSeparator()).append(System.lineSeparator());
				
			}
			
			FileUtil.writer("D:/var/"+deviceId+"/"+collectionTmps[i]+".txt", content.toString());
		}
	}

	private static StringBuffer downsideData(MongoDatabase db, String upsideId, StringBuffer content) {
		BasicDBObject doc = new BasicDBObject("upsideId", upsideId);

		MongoCollection<Document> collection = db.getCollection("OBD_DOWNSIDE_DATA");
		Document document = collection.find(doc).first();
		if (CommonUtil.isEmpty(document)) {
			return null;
		}

		String responseData = document.getString("responseData");
		content.append("响应未转义报文:" + responseData).append(System.lineSeparator());
		if (!escapeE6orE7(responseData).equals(document.getString("escapeResponseData"))) {
			System.out.println("-------------------------->false");
		}
		content.append("响应已转义报文:" + escapeE6orE7(responseData)).append(System.lineSeparator());
		content.append("终端上报数据时间" + document.get("createTime").toString()).append(System.lineSeparator());

		return content;
	}

	/**
	 * 转义E6、E7
	 * 
	 * @param content
	 * @return
	 */
	private static String escapeE6orE7(String content) {
		String data = content;
		data = data.substring(2).toUpperCase();// 去头部E7
		data = data.substring(0, data.length() - 2);// 去尾部E7

		String regex = "(.{2})";
		String dataTmp = data.replaceAll(regex, "$1 ");
		String[] datas = dataTmp.split("\\s+");

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < datas.length; i++) {
			if ("E7".equals(datas[i])) {
				builder.append("E602");
				continue;
			}
			if ("E6".equals(datas[i])) {
				builder.append("E601");
				continue;
			}
			builder.append(datas[i]);
		}
		return "E7" + builder.toString() + "E7";
	}

	private static String upsideData(MongoDatabase db, String upsideId) {
		BasicDBObject doc = new BasicDBObject("_id", upsideId);

		MongoCollection<Document> collection = db.getCollection("OBD_UPSIDE_DATA");
		Document document = collection.find(doc).first();
		if (CommonUtil.isEmpty(document)) {
			return null;
		}
		return document.getString("originalData");
	}
}
