package util.test;

import com.hm.common.network.ssh2.RemoteSSH2Factory;

/**
 * @author shishun.wang
 * @date 上午11:42:55 2017年8月29日
 * @version 1.0
 * @describe
 */
public class TestRemoteSsh2 {

	public static void main(String[] args) {
		try {

			// System.out.println(Charset.defaultCharset().toString());
			RemoteSSH2Factory tool = new RemoteSSH2Factory("122.114.176.221", 2222, "root", "78t7t3212121en", "utf-8");
			String result = tool.exec("/data/dbdata/backup_mysql.sh");
			System.out.print(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
