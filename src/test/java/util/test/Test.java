package util.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.hm.common.util.EncryptUtil.AES;
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

		List<Integer> asynDownloadIds = new ArrayList<Integer>();
		{
			asynDownloadIds.add(1);
			asynDownloadIds.add(2);
		}

		asynDownloadIds.forEach(a -> {
			if (a == 1) {
				return;
			}
			System.out.println(a);
		});

		System.out.println(String.class.getName());

		String strs = "59e05db727ac984fd0b0b906:" + System.currentTimeMillis();

		System.err.println(strs);
		System.out.println(AES.encrypt(strs));
		System.out.println(AES.decrypt(
				"534965E87C859E6B35868EAA3F5F665E3C0D79D058BCEC7C402A819DC7645C0D99396F41F94E437AF69455E3D7F7F9FB"));
	}

	public static double pay(double x, double l, double r) {
		return x + (x / l) * r;
	}

	public static double x(double p, double l, double r) {
		return (p * l) / (l + r);
	}
}
