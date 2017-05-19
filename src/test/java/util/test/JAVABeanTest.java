package util.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import com.hm.common.dbutil.BaseDBUtil;

/**
 * @author shishun.wang
 * @date 2017年5月16日 下午2:40:41
 * @version 1.0
 * @describe 
 */
public class JAVABeanTest {

	public static void main(String[] args) throws Exception {
		Connection connection = BaseDBUtil.MYSQL.build("master.dbsmzc.yw.vvip-u.com", 3306, "smzc", "smapp", "smcdyanfa").connection();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT t2.systag_code AS systagCode, t1.name AS systagCodeDesc, t2.limit_start_value AS limitStartValue, t2.limit_end_value AS limitEndValue, t2.icon  ");
		sql.append("			, t2.car_icon AS carIcon                                                                                                                   ");
		sql.append("		FROM system_tag t1, vip_grade_ext_info t2                                                                                                      ");
		sql.append("		WHERE t1.`code` = t2.systag_code                                                                                                               ");
		sql.append("			AND t2.del_flag = 1                                                                                                                        ");
		PreparedStatement prepareStatement = connection.prepareStatement(sql.toString());
		ResultSet resultSet = prepareStatement.executeQuery();
		
		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
		for(int i  = 1 ; i<= resultSetMetaData.getColumnCount();i++){
			System.out.println(resultSetMetaData.getColumnName(i) +"==="+resultSetMetaData.getColumnClassName(i)+"==="+resultSetMetaData.getColumnLabel(i));
		}
		
		
		while(resultSet.next()){
			System.out.println(resultSet.getString(1));
		}
	}
}
