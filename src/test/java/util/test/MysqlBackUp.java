package util.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author shishun.wang
 * @date 下午4:52:09 2017年8月23日
 * @version 1.0
 * @describe
 */
public class MysqlBackUp {

	public static void main(String[] args) {
		backup();
	}

	private static void backup() {

		try {
			Runtime rt = Runtime.getRuntime();

			// 调用 调用mysql的安装目录的命令
//			Process child = rt.exec(
//					"C:/Program Files (x86)/MySQL/MySQL Server 5.4/bin/mysqldump.exe --opt -h 115.28.66.183 --user=ibsm --password=ibsm123 hmbase  > d:/test.sql");
//			
//			System.out.println("-------------");
			
			StringBuffer sb = new StringBuffer();  
            sb.append("C:/Program Files (x86)/MySQL/MySQL Server 5.4/bin/");  
            sb.append("mysqldump.exe ");  
            sb.append("--opt ");  
            sb.append("-h ");  
            sb.append("115.28.66.183");  
            sb.append(" ");  
            sb.append("--user=");  
            sb.append("ibsm");  
            sb.append(" ");  
            sb.append("--password=");  
            sb.append("ibsm123");  
            sb.append(" ");  
            sb.append("--lock-all-tables=true ");  
            sb.append("--result-file=");  
            sb.append("d:/test.sql");  
            sb.append(" ");  
            sb.append("--default-character-set=utf8 ");  
            sb.append("hmbase");  
            sb.append(" ");  
//            sb.append(tableName);  
            Runtime cmd = Runtime.getRuntime();  
            Process p = cmd.exec("cmd /c C:/Program Files (x86)/MySQL/MySQL Server 5.4/bin>mysqldump  -h 115.28.66.183:3306 -uibsm -pibsm123 --default-character-set=utf-8 --opt hmbase > d:/test.sql");  
            p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
