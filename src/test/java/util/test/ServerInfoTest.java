package util.test;

import java.util.Properties;

/**
 * @author shishun.wang
 * @date 2018年3月7日 上午11:06:47
 * @version 1.0
 * @describe 
 */
public class ServerInfoTest {

	public static void main(String[] args) {
		Properties props=System.getProperties();  
        System.out.println("Java的运行环境版本："+props.getProperty("java.version"));   
        System.out.println("默认的临时文件路径："+props.getProperty("java.io.tmpdir"));   
        System.out.println("操作系统的名称："+props.getProperty("os.name"));   
        System.out.println("操作系统的构架："+props.getProperty("os.arch"));   
        System.out.println("操作系统的版本："+props.getProperty("os.version"));   
        System.out.println("用户的账户名称："+props.getProperty("user.name"));   
        System.out.println("用户的当前工作目录："+props.getProperty("user.dir"));  
	}
}
