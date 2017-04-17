package util.test;

import com.hm.common.oss.qiniu.QiNiuConfig;
import com.hm.common.oss.qiniu.QiNiuUpload;
import com.hm.common.util.FileUtil;

/**
 * @author shishun.wang
 * @date 2017年4月17日 上午11:39:38
 * @version 1.0
 * @describe
 */
public class TestQiniu {

	public static void main(String[] args) throws Exception {
		QiNiuConfig.instance();
		String accessKey = "JThHMlAGBk-icmnKl7ujGYvA0ZyQxqEYRZHXRpiW";
		String secretKey = "pKpJuadjOKWkIl3k2S6VwbYIFEXYPZp-zeQtSfNR";
		QiNiuConfig.load().init(accessKey, secretKey);

		System.out.println(QiNiuUpload.Sample.upload("fvsdfvsdre", "122.png", FileUtil.getFile("d:/122.png")));
	}
}
