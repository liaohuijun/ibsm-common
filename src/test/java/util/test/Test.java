package util.test;

import java.util.Arrays;

import com.hm.common.util.DateUtil;
import com.hm.common.util.StringUtil;

/**
 * @author shishun.wang
 * @date 2017年4月17日 下午3:22:22
 * @version 1.0
 * @describe
 */
public class Test {

	public static void main(String[] args) {
		System.out.println(x(142.5, 100, 80));
		System.out.println(pay(205, 100, 80));

		String str = "java.lang.RuntimeException: com.smzc.basic.common.exception.ServiceException: REQUIRED_PARAMETERS_MISSING:*:必填参数缺失";
		String[] splits = str.split("\\s+");
		System.out.println(splits[splits.length - 1]);

		Arrays.asList("aa", "bb", "vv", "cd", "32").forEach(a -> {
			if (a.equals("bb")) {
				return;
			}
			System.out.println(a);
		});

		System.out.println(("SMZCKK" + StringUtil.token()).length());
		System.out.println("SMZC451E901940128B3AFE7C203B215B".length());
		System.out.println("1501689600000");
		System.out.println(System.currentTimeMillis());
		
	}

	public static double pay(double x, double l, double r) {
		return x + (x / l) * r;
	}

	public static double x(double p, double l, double r) {
		return (p * l) / (l + r);
	}
}
