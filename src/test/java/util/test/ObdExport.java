package util.test;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hm.common.util.CommonUtil;
import com.hm.common.util.DateUtil;
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
 * @date 上午11:11:04 2017年9月25日
 * @version 1.0
 * @describe
 */
public class ObdExport {

	public static void main(String[] args) throws Exception {
		// System.out.println(hexToYiHuo("8102000B4D2016100119497AE01109160b1324000000140"));
		generationData();

		// String content = "aad";
		//
		// String temp = test(content);
		//
		// System.out.println(content);
		// System.out.println(temp);
	}

	private static String test(String content) {
		String data = content;
		data += "nihao123";
		return data;
	}

	public static String hexToYiHuo(String content) {
		content = hexToYiHuoChange(content);
		String[] b = content.split(" ");
		int a = 0;
		for (int i = 0; i < b.length; i++) {
			a = a ^ Integer.parseInt(b[i], 16);
		}
		if (a < 10) {
			StringBuffer sb = new StringBuffer();
			sb.append("0");
			sb.append(a);
			return sb.toString();
		}

		String value = Integer.toHexString(a);

		return (value.length() != 2) ? ("0" + value) : value;
	}

	private static String hexToYiHuoChange(String content) {
		String str = "";
		for (int i = 0; i < content.length(); i++) {
			if (i % 2 == 0) {
				str += " " + content.substring(i, i + 1);
			} else {
				str += content.substring(i, i + 1);
			}
		}
		return str.trim();
	}

	public static void generationData() throws Exception {
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

		String[] collections = { "obdLoginDoc", "obdAlarmDescDoc", "obdAlarmUpsideDoc", "obdBaseStationDoc",
				"obdCarsUpsideDoc", "obdDormancyAwakenDoc", "obdDormancyEnterDoc", "obdDrivingBehaviorUpsideDoc",
				"obdMalfunctionCodeDoc" };

		String[] collectionTmps = { "登陆上行数据结构体", "报警描述", "报警数据", "基站定位数据", "车辆数据", "休眠唤醒数据", "休眠进入数据", "驾驶行为数据",
				"故障码数据" };

		for (int i = 0; i < collections.length; i++) {
			String collectionName = collections[i];
			JSONArray array = loadCollectionData(db, collectionName);
			FileUtil.writer("d:/obd/" + collectionTmps[i] + ".json", array.toString());
		}
	}

	private static JSONArray loadCollectionData(MongoDatabase db, String collectionName) {
		Date now = new Date();
		AggregateIterable<Document> iterable = db.getCollection(collectionName)
				.aggregate(Arrays.asList(Aggregates.match(Filters.and(Filters.eq("deviceId", "M201610011949"),
						Filters.ne("upsideId", null), Filters.gt("createTime", DateUtil.getCurrenBeforeDay(now)),
						Filters.lt("createTime", DateUtil.getCurrenBeforeDay(now))))));

		JSONArray items = new JSONArray();
		for (Document document : iterable) {
			String upsideId = document.getString("upsideId");

			JSONObject item = new JSONObject();
			item.put("上行数据报文", upsideData(db, upsideId));
			Map<String, String> downsideData = downsideData(db, upsideId);
			if (CommonUtil.isNotEmpty(downsideData)) {
				item.put("平台生成待转义下行报文", downsideData.get("geResponseData"));
				item.put("平台生成已转义下行报文", downsideData.get("responseTerminalData"));
				item.put("终端上报平台日期", downsideData.get("createTime"));
			}

			items.add(item);
		}

		return items;
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

	private static Map<String, String> downsideData(MongoDatabase db, String upsideId) {
		BasicDBObject doc = new BasicDBObject("upsideId", upsideId);

		MongoCollection<Document> collection = db.getCollection("OBD_DOWNSIDE_DATA");
		Document document = collection.find(doc).first();
		if (CommonUtil.isEmpty(document)) {
			return null;
		}

		Map<String, String> content = new HashMap<String, String>();
		String responseData = document.getString("responseData");
		content.put("geResponseData", responseData);
		if (!escapeE6orE7(responseData).equals(document.getString("escapeResponseData"))) {
			System.out.println("-------------------------->false");
		}

		content.put("responseTerminalData", escapeE6orE7(responseData));
		content.put("createTime", document.get("createTime").toString());
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
}
