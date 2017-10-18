package util.test;

/**
 * @author shishun.wang
 * @date 下午7:12:46 2017年10月17日
 * @version 1.0
 * @describe 
 */
public class Test123 {

	public static void main(String[] args) {
		String[] collections = { "obdLoginDoc", "obdAlarmDescDoc", "obdAlarmUpsideDoc", "obdBaseStationDoc",
				"obdCarsUpsideDoc", "obdDormancyAwakenDoc", "obdDormancyEnterDoc", "obdDrivingBehaviorUpsideDoc",
				"obdMalfunctionCodeDoc" };
		for (String string : collections) {
			System.out.println("db."+string+".remove({'createTime' : {$lt:ISODate('2017-10-17T11:16:35.639Z')}});");
		}
	}
}
