package util.test;

import java.util.List;

import org.bson.Document;

import com.hm.common.util.CommonUtil;
import com.hm.common.util.StringUtil;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

/**
 * @author shishun.wang
 * @date 2017年10月14日 上午11:40:48
 * @version 1.0
 * @describe
 */
public class DiuQi {

	public static void main(String[] args) {
		/*
		 * System.out.println(CommonUtil.hexToYiHuo(
		 * "020000764D20161201017700FE110A0B01261B00040000000101DB1C73073B27690000000E0900000D2400A00000000000000000000000000000000000000000000011FFFFFF800091010000000C000000060000000000454200005342019865013D00C400300000000002211503E802D00000000C000000F0000A000E000F0010001E"
		 * ));
		 * 
		 * // FC String content =
		 * "E7020000764D2016120101770100110A0B01263A00040000000101DB1C73073B2769000000090800000D2400C80000000000000000000000000000000000000000000011FFFFFF800091010000000C000000060000000000454200005342019865013D00C400300000000002211503E802D00000000C000000F0000A000D0047001400F8CDE7";
		 * System.out.println(content); //
		 * System.out.println(escapeE601orE602(data)); //
		 * System.out.println(CommonUtil.hexToYiHuo(data));
		 * 
		 * // 检验数据准确性(原始报文校验) System.out.println(upsideDataVerify(content));
		 * System.out.println(content);
		 */

		MongoClientOptions.Builder build = new MongoClientOptions.Builder();
		// 与数据最大连接数50
		build.connectionsPerHost(50);
		// 如果当前所有的connection都在使用中，则每个connection上可以有50个线程排队等待
		build.threadsAllowedToBlockForConnectionMultiplier(50);
		build.connectTimeout(1 * 60 * 1000);
		build.maxWaitTime(2 * 60 * 1000);
		MongoClientOptions options = build.build();
		MongoClient client = new MongoClient("127.0.0.1", options);
		MongoDatabase db = client.getDatabase("ibsm-cms");

		FindIterable<Document> iterable = db.getCollection("data").find();
		for (Document document : iterable) {
			List<Document> docs = (List<Document>) document.get("upside_data");
			// System.out.println(docs.get(0).get("originalData"));
//			System.out.println(upsideDataVerify(docs.get(0).get("originalData") + ""));
		}
		System.out.println(upsideDataVerify(
				"E7020000764D2016100119497D1F1109190B333100B90100000001D235250633FF220000000000000006B327060000000000000000000000000000000000000000000011FFFBFF8000910100311AE9000424F205580A004C28640000674600005E011A00FA00D60000000001E22803B9036100311AE90005F686002100210024002E006E16E7"));
	}

	private static boolean upsideDataVerify(String content) {
		String data = content;
		data = data.substring(2).toUpperCase();// 去头部E7
		data = data.substring(0, data.length() - 2);// 去尾部E7

		String upsideVerify = data.substring(data.length() - 2, data.length());
		String localVerify = CommonUtil.hexToYiHuo(data.substring(0, data.length() - 2));// 去掉校验位，亦或运算数据
		System.out.println(upsideVerify + "--------->" + localVerify);
		return StringUtil.notDistinguishCaseEquals(upsideVerify, localVerify);
	}

	/**
	 * 转换E601、E602
	 * 
	 * @param content
	 * @return
	 */
	private static String escapeE601orE602(String content) {
		String data = content;
		data = data.substring(2).toUpperCase();// 去头部E7
		data = data.substring(0, data.length() - 2);// 去尾部E7

		String regex = "(.{2})";
		String dataTmp = data.replaceAll(regex, "$1 ");
		String[] datas = dataTmp.split("\\s+");

		String lastCode = "";
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < datas.length; i++) {
			if ("E602".equals(lastCode + datas[i])) {
				builder.delete(builder.length() - 2, builder.length());
				builder.append("E7");
				lastCode = datas[i];
				continue;
			}

			if ("E601".equals(lastCode + datas[i])) {
				builder.delete(builder.length() - 2, builder.length());
				builder.append("E6");
				lastCode = datas[i];
				continue;
			}

			lastCode = datas[i];
			builder.append(datas[i]);
		}
		return "E7" + builder.toString() + "E7";
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
