package util.test;

import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import com.hm.common.dbutil.BaseDBUtil;

/**
 * @author shishun.wang
 * @date 2017年5月4日 下午2:26:12
 * @version 1.0
 * @describe
 */
public class TestBaseDBUtil {

	public static void main(String[] args) throws Exception {
		Connection connection = BaseDBUtil.MYSQL.build("115.28.66.183", 3306, "ibsm-manage", "admin", "admin").connection();
		for (int i = 0; i < 100; i++) {
			QueryRunner qRunner = new QueryRunner();
			qRunner.query(connection, "select * from im_role ", new ArrayListHandler()).forEach(row -> {
				for (Object cloumn : row) {
					System.out.println(cloumn);
				}
			});
		}
	}
}
