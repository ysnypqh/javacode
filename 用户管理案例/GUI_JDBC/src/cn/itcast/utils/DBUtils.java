package cn.itcast.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	// ���ݿ�����
	public static Connection conn;

	/**
	 * ��ȡ���ݿ�����
	 */
	public static Connection getConn() {
		try {
			// �������ݿ�������ע�ᵽ����������
			Class.forName("com.mysql.jdbc.Driver");
			// ���ݿ������ַ���
			String url = "jdbc:mysql://localhost:3306/itcast?useUnicode=true&characterEncoding=utf8";
			// ���ݿ��û���
			String username = "root";
			// ���ݿ�������
			String password = "itcast";
			// ����Connection����
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("com.mysql.jdbc.Driver��δ�ҵ���");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("���ݿ�����ʧ�ܣ�");
		}
		// �������ݿ�����
		return conn;
	}

}
