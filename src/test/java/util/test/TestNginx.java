package util.test;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hm.common.util.DateUtil;

/**
 * @author shishun.wang
 * @date 上午11:03:08 2017年8月17日
 * @version 1.0
 * @describe
 */
public class TestNginx {

	public static void main(String[] args) throws Exception {
//		HttpResponse response = HttpClientFactory.GET
//				.build("http://erpbasicgw.vvip-u.com/restful/v1.0/export/1/11?currentUserId=1&dataSource=erp")
//				.execute();
		// System.out.println(EntityUtils.toString(response.getEntity(),
		// HttpClientStatus.CHARACTER_ENCODING));

//		ServerResponse<PageInfo<ExportFileRecordBean>> parse = HttpClientResponseParse.parseGeneric(response,
//				new TypeReference<ServerResponse<PageInfo<ExportFileRecordBean>>>() {
//				});
//		System.out.println(parse.getData());

		String regex="^[a-z0-9A-Z]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher match=pattern.matcher("#%$$#%434343");
		System.out.println(match.matches());
		
		System.out.println(DateUtil.toIosDate(DateUtil.getCurrentCustomerDay(new Date(), -2)));;
	}
}
