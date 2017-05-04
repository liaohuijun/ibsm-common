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
		Connection connection = BaseDBUtil.MYSQL.build("localhost", 3306, "ibsm-manage", "root", "root").connection();
		QueryRunner qRunner = new QueryRunner();
		qRunner.query(connection, "select id,note from im_role ", new ArrayListHandler()).forEach(row -> {
			System.out.println(row);
		});
	}
}
