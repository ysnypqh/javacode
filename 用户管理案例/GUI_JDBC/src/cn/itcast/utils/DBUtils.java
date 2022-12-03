package cn.itcast.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	// 数据库连接
	public static Connection conn;

	/**
	 * 获取数据库连接
	 */
	public static Connection getConn() {
		try {
			// 加载数据库驱动，注册到驱动管理器
			Class.forName("com.mysql.jdbc.Driver");
			// 数据库连接字符串
			String url = "jdbc:mysql://localhost:3306/itcast?useUnicode=true&characterEncoding=utf8";
			// 数据库用户名
			String username = "root";
			// 数据库用密码
			String password = "itcast";
			// 创建Connection连接
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("com.mysql.jdbc.Driver类未找到！");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("数据库链接失败！");
		}
		// 返回数据库连接
		return conn;
	}

}
