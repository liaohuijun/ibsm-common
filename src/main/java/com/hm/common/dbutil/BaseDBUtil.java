package com.hm.common.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hm.common.exception.ErrorCode;
import com.hm.common.exception.ServiceException;
import com.hm.common.util.CommonUtil;

/**
 * @author shishun.wang
 * @date 2017年5月4日 上午10:52:35
 * @version 1.0
 * @describe
 */
public enum BaseDBUtil {

	MYSQL {

		private Logger logger = LoggerFactory.getLogger(BaseDBUtil.class);

		private String user;

		private String password;

		private String url;

		private Connection connection;

		@Override
		public BaseDBUtil build(String host, int port, String database, String user, String password) {

			if (CommonUtil.isAnyEmpty(host, port, database, user, password)) {
				throw ServiceException.warning(ErrorCode.PARAMETERS_MISSING, "host、port、database、user、password");
			}
			this.url = MessageFormat.format("jdbc:mysql://{0}:{1}/{2}?useUnicode=true&characterEncoding=utf8", host, String.valueOf(port), database);
			this.user = user;
			this.password = password;
			return this;
		}

		@Override
		public Connection connection() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(url, user, password);
				return connection;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public void close() {
			if (null != connection) {
				try {
					connection.close();
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}

	},
	SQLITE {

		private Logger logger = LoggerFactory.getLogger(BaseDBUtil.class);

		private String user;

		private String password;

		private String url;

		private Connection connection;

		@Override
		public BaseDBUtil build(String host, int port, String database, String user, String password) {

			if (CommonUtil.isEmpty(database)) {
				throw ServiceException.warning(ErrorCode.PARAMETERS_MISSING, "database");
			}
			this.url = MessageFormat.format("jdbc:sqlite:{}", database);
			this.user = user;
			this.password = password;
			return this;
		}

		@Override
		public Connection connection() {
			try {
				Class.forName("org.sqlite.JDBC");
				connection = DriverManager.getConnection(url, user, password);
				return connection;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			return null;
		}

		@Override
		public void close() {
			if (null != connection) {
				try {
					connection.close();
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}

	};

	public abstract BaseDBUtil build(String host, int port, String database, String user, String password);

	public abstract Connection connection();

	public abstract void close();
}
