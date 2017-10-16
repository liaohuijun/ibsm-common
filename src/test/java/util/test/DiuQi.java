package util.test;

import com.hm.common.util.CommonUtil;
import com.hm.common.util.StringUtil;

/**
 * @author shishun.wang
 * @date 2017年10月14日 上午11:40:48
 * @version 1.0
 * @describe
 */
public class DiuQi {

	public static void main(String[] args) {
		// FC
		String content = "E7020000764D2016120101770C89110A0F040E0D00480000000001DBA4DB073B781D000000210400000277029E0000000000000000000000000000000000000000000011FFFFFF80008B01000081630000147800000000004B2800007E3D01E60165006500C4023C0000000002090A03D002B70000816300001F12006B0036003D003B00FCFCE7";
		System.out.println(content);
		// System.out.println(escapeE601orE602(data));
		// System.out.println(CommonUtil.hexToYiHuo(data));

		// 检验数据准确性(原始报文校验)
		System.out.println(upsideDataVerify(content));
		System.out.println(content);

	}

	private static boolean upsideDataVerify(String content) {
		String data = content;
		data = data.substring(2).toUpperCase();// 去头部E7
		data = data.substring(0, data.length() - 2);// 去尾部E7

		String upsideVerify = data.substring(data.length() - 2, data.length());
		String localVerify = CommonUtil.hexToYiHuo(data);
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
