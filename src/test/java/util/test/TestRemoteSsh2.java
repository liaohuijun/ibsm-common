package util.test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

/**
 * @author shishun.wang
 * @date 上午11:42:55 2017年8月29日
 * @version 1.0
 * @describe 
 */
public class TestRemoteSsh2 {
	
	private Connection conn;  
    private String ipAddr;  
    private String charset = Charset.defaultCharset().toString();  
    private String userName;  
    private String password;  
  
    public TestRemoteSsh2(String ipAddr, String userName, String password,  
            String charset) {  
        this.ipAddr = ipAddr;  
        this.userName = userName;  
        this.password = password;  
        if (charset != null) {  
            this.charset = charset;  
        }  
    }  
  
    public boolean login() throws IOException {  
        conn = new Connection(ipAddr,2222);  
        conn.connect(); // 连接  
        return conn.authenticateWithPassword(userName, password); // 认证  
    }  
  
    public String exec(String cmds) {  
        InputStream in = null;  
        String result = "";  
        try {  
            if (this.login()) {  
                Session session = conn.openSession(); // 打开一个会话  
                session.execCommand(cmds);  
                  
                in = session.getStdout();  
                result = this.processStdout(in, this.charset);  
                session.close();  
                conn.close();  
            }  
        } catch (IOException e1) {  
            e1.printStackTrace();  
        }  
        return result;  
    }  
  
    public String processStdout(InputStream in, String charset) {  
      
        byte[] buf = new byte[1024];  
        StringBuffer sb = new StringBuffer();  
        try {  
            while (in.read(buf) != -1) {  
                sb.append(new String(buf, charset));  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return sb.toString();  
    }  

	public static void main(String[] args) {
		try {
			
//			System.out.println(Charset.defaultCharset().toString());
			TestRemoteSsh2 tool = new TestRemoteSsh2("122.114.176.221", "root",  
	                "78t7t321en", "utf-8");
			String result = tool.exec("/data/dbdata/backup_mysql.sh");  
	        System.out.print(result);  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
