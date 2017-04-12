package util.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hm.common.util.StringUtil;

/**
 * @author shishun.wang
 * @date 2017年4月7日 下午1:47:19
 * @version 1.0
 * @describe
 */
public class TestString {

	public static void main(String[] args) {
		String str = StringUtil.token() + "|" + StringUtil.token() + "|" + StringUtil.token();
		System.out.println(str);
		System.out.println(str.replaceAll("\\|", ",").split("\\,").length);

		String reg = "\\r\\n";
		String st1r = "发岁时\r\n伏腊萨芬\r\ncsdnsdfsdfs\r\n";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(st1r);
		while (matcher.find()) {
			System.out.println(matcher.start() + " " + matcher.end());
		}
		
		System.out.println(Long.valueOf("6225330380000072"));
	}
}
