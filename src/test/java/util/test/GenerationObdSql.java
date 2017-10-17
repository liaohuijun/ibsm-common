package util.test;

import java.io.File;
import java.io.IOException;

import com.hm.common.util.FileUtil;

/**
 * @author shishun.wang
 * @date 下午5:05:20 2017年10月16日
 * @version 1.0
 * @describe 
 */
public class GenerationObdSql {

	public static void main(String[] args) throws IOException {
		String tag = "20171016";
		String deviceId = "M201612010177";
		
		String[] collections = { "obdLoginDoc", "obdAlarmDescDoc", "obdAlarmUpsideDoc", "obdBaseStationDoc",
				"obdCarsUpsideDoc", "obdDormancyAwakenDoc", "obdDormancyEnterDoc", "obdDrivingBehaviorUpsideDoc",
				"obdMalfunctionCodeDoc" };
		StringBuffer buffer = new StringBuffer();
		generationDataStatic(buffer, collections, tag, deviceId);
		generationUpsideId(buffer,collections, tag, deviceId);
		generationUpsideDataUpsideId(buffer, collections, tag);
		generationDownloadDataUpsideId(buffer, collections, tag);
		generationExportData(buffer, collections, tag);
		generationDeleteTmpData(buffer, collections, tag);
		System.out.println(buffer.toString());
		
		FileUtil.writer("d:/export_data.txt", buffer.toString());
		
		String[] collectionTmps = { "登陆上行数据结构体", "报警描述", "报警数据", "基站定位数据", "车辆数据", "休眠唤醒数据", "休眠进入数据", "驾驶行为数据",
		"故障码数据" };
		
		for (int i = 0; i < collections.length; i++) {
			String name = collections[i];
			FileUtil.copy(new File("D:/var/20171017/"+name+".json"), new File("D:/var/20171017/"+collectionTmps[i]+".json"));
			
		}
	}
	
	public static void generationDataStatic(StringBuffer buffer,String[] collections,String tag,String deviceId){
		buffer.append("导出数据统计,数量少则直接导出数量多则加时间限制，数据到出前，数据量大小查看").append(System.lineSeparator()).append(System.lineSeparator());
		for (int i = 0; i < collections.length; i++) {
			String name = collections[i];
			
			buffer.append("db.getCollection('"+name+"').find({$and:[{'upsideId':{'$ne':null }},{'deviceId':'"+deviceId+"'}]}).count();").append(System.lineSeparator());
		}
		
		buffer.append(System.lineSeparator()).append(System.lineSeparator());
	}
	
	public static void generationUpsideId(StringBuffer buffer,String[] collections,String tag,String deviceId){
		buffer.append("1、获取上行报文id").append(System.lineSeparator()).append(System.lineSeparator());
		for (int i = 0; i < collections.length; i++) {
			String name = collections[i];
			
			buffer.append("db."+name+".aggregate([{$match:{$and:[{'upsideId':{'$ne':null }},{'deviceId':'"+deviceId+"'}]}},").append(System.lineSeparator());
			buffer.append("    {$project:{'_id':0,'upsideId':1}},                                                              ").append(System.lineSeparator());
			buffer.append("    {$out:'"+name+"_"+tag+"'}                                                                   ").append(System.lineSeparator());
			buffer.append("]);                                                                                                 ").append(System.lineSeparator());
		}
	}
	
	public static void generationUpsideDataUpsideId(StringBuffer buffer,String[] collections,String tag){
		buffer.append("2、根据上行报文ID获取上行报文").append(System.lineSeparator()).append(System.lineSeparator());
		for (int i = 0; i < collections.length; i++) {
			String name = collections[i];
			
			buffer.append("db."+name+"_"+tag+".aggregate([").append(System.lineSeparator());
			buffer.append("    {                              ").append(System.lineSeparator());
			buffer.append("      $lookup:                     ").append(System.lineSeparator());
			buffer.append("        {                          ").append(System.lineSeparator());
			buffer.append("          from: 'OBD_UPSIDE_DATA', ").append(System.lineSeparator());
			buffer.append("          localField: 'upsideId',  ").append(System.lineSeparator());
			buffer.append("          foreignField: '_id',     ").append(System.lineSeparator());
			buffer.append("          as: 'upside_data'        ").append(System.lineSeparator());
			buffer.append("        }                          ").append(System.lineSeparator());
			buffer.append("   },                              ").append(System.lineSeparator());
			buffer.append("   {$out:'"+name+"_"+tag+"_02'}").append(System.lineSeparator());
			buffer.append("]);                                ").append(System.lineSeparator());
		}
			
	}
	
	public static void generationDownloadDataUpsideId(StringBuffer buffer,String[] collections,String tag){
		buffer.append("3、根据上行报文ID获取下行报文").append(System.lineSeparator()).append(System.lineSeparator());
		for (int i = 0; i < collections.length; i++) {
			String name = collections[i];
			
			buffer.append("db."+name+"_"+tag+"_02.aggregate([").append(System.lineSeparator());
			buffer.append("    {                                 ").append(System.lineSeparator());
			buffer.append("      $lookup:                        ").append(System.lineSeparator());
			buffer.append("        {                             ").append(System.lineSeparator());
			buffer.append("          from: 'OBD_DOWNSIDE_DATA',  ").append(System.lineSeparator());
			buffer.append("          localField: 'upsideId',     ").append(System.lineSeparator());
			buffer.append("          foreignField: 'upsideId',   ").append(System.lineSeparator());
			buffer.append("          as: 'down_data'             ").append(System.lineSeparator());
			buffer.append("        }                             ").append(System.lineSeparator());
			buffer.append("   },                                 ").append(System.lineSeparator());
			buffer.append("   {$out:'"+name+"_"+tag+"_02_03'}").append(System.lineSeparator());
			buffer.append("]);                                   ").append(System.lineSeparator());
		}
	}
	
	public static void generationExportData(StringBuffer buffer,String[] collections,String tag){
		buffer.append("4、导出数据到本地").append(System.lineSeparator()).append(System.lineSeparator());
		for (int i = 0; i < collections.length; i++) {
			String name = collections[i];
			
			buffer.append("mongoexport -h 127.0.0.1 -d smzc -c "+name+"_"+tag+"_02_03 -o /var/data/"+name+".json;").append(System.lineSeparator());
		}
	}
	
	public static void generationDeleteTmpData(StringBuffer buffer,String[] collections,String tag){
		buffer.append("5、清楚导出数据生成临时表").append(System.lineSeparator()).append(System.lineSeparator());
		for (int i = 0; i < collections.length; i++) {
			String name = collections[i];
			
			buffer.append("db."+name+"_"+tag+".drop();       ").append(System.lineSeparator());
			buffer.append("db."+name+"_"+tag+"_02.drop();    ").append(System.lineSeparator());
			buffer.append("db."+name+"_"+tag+"_02_03.drop(); ").append(System.lineSeparator());
		}
	}
}
