package util.test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author shishun.wang
 * @date 上午10:45:52 2017年9月15日
 * @version 1.0
 * @describe 
 */
public class ObdTest {

	public static void main(String[] args) {
		//0E7200E74D2016100119252EE6170B09040C30002E
		String data = "E7020000764D2016100119254E7A180B0908083803A70000000001D3226B063360460000000000000009A627060000000000000000000000000000000000000000000011FFFBFF80007F0100040F4D00009F4300000000005F580000964600005F00C4013100EE00000000013D0503F7021C00040F4D0001E42F0077007600780078007BAFE7";
		data = escapeRestoreObdData(data, false);
		System.out.println(data);
		System.out.println(escapeE6orE7(data));
		
		
		String date = new SimpleDateFormat("yyyyMMddHH").format(new Date());
		String temp = String.valueOf((Long.valueOf(date) * 8080) << 5);
		String superValidateCode = temp.substring(temp.length() - 6, temp.length());
		
		System.out.println(superValidateCode);
	}
	
	/**
    *
    * @param data 去掉前置后置E7标识数据
    * @param restore 还原
    * @return
    */
   private static String escapeRestoreObdData(String data,boolean restore){
       data = data.substring(2).toUpperCase();//去头部E7
       data = data.substring(0,data.length()-2);//去尾部E7
       if(restore){
           data = data.replaceAll("E602","*").replaceAll("E601","#");
           data = data.replaceAll("\\*","E7").replaceAll("\\#","E6");
       }else{
           data = data.replaceAll("E7","*").replaceAll("E6","#");
           data = data.replaceAll("\\*","E602").replaceAll("\\#","E601");
       }
       return "E7"+data+"E7";
   }
	
	private static String escapeE601orE602(String content) {
		String data = content;
		data = data.substring(2).toUpperCase();//去头部E7
        data = data.substring(0,data.length()-2);//去尾部E7
		
		String regex = "(.{2})";
		String dataTmp = data.replaceAll(regex, "$1 ");
		String[] datas = dataTmp.split("\\s+");
		
		String lastCode = "";
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < datas.length; i++) {
			
			if("E602".equals(lastCode + datas[i])){
				builder.delete(builder.length() -2, builder.length());
				builder.append("E7");
				lastCode = datas[i];
				continue;
			}
			
			if("E601".equals(lastCode + datas[i])){
				builder.delete(builder.length() -2, builder.length());
				builder.append("E6");
				lastCode = datas[i];
				continue;
			}
			
			lastCode = datas[i];
			builder.append(datas[i]);
		}
		return "E7"+builder.toString()+"E7";
	}
	
	private static String escapeE6orE7(String content) {
		String data = content;
		data = data.substring(2).toUpperCase();//去头部E7
        data = data.substring(0,data.length()-2);//去尾部E7
        
		String regex = "(.{2})";
		String dataTmp = data.replaceAll(regex, "$1 ");
		String[] datas = dataTmp.split("\\s+");
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < datas.length; i++) {
			if("E7".equals(datas[i])){
				builder.append("E602");
				continue;
			}
			if("E6".equals(datas[i])){
				builder.append("E601");
				continue;
			}
			builder.append(datas[i]);
		}
		return "E7"+builder.toString()+"E7";
	}
}
