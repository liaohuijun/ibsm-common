package util.test;

import com.hm.common.util.EncryptUtil;

/**
 * @author shishun.wang
 * @date 2018年1月31日 下午3:17:54
 * @version 1.0
 * @describe
 */
public class MainApp {

	public static void main(String[] args) {
		String accessKey = "I_kiCLXzXyerO0_Wg_E3QY0YPLAIAnDKZAahQX3N";
		String secretKey = "NUZPOvvZFilBgCCOKZtmWoujdtriM2bvf63Yun7G";
		String bucket = "ibsm-shopping";
		String prefixUri = "http://p2s9wr3uo.bkt.clouddn.com/";
		
		System.out.println(EncryptUtil.AES.encrypt(accessKey));
		System.out.println(EncryptUtil.AES.encrypt(secretKey));
		System.out.println(EncryptUtil.AES.encrypt(bucket));
		System.out.println(EncryptUtil.AES.encrypt(prefixUri));
		
		System.out.println("----------------------------");
		
		System.out.println(EncryptUtil.AES.encrypt("admin"));
		System.out.println(EncryptUtil.AES.encrypt("password"));
		
		System.out.println("----------------------------");
		System.out.println(EncryptUtil.AES.encrypt("ymb"));
		System.out.println(EncryptUtil.AES.encrypt("rphgt7IQ55e<mtb7v[kJiQT0J"));
	}
}
