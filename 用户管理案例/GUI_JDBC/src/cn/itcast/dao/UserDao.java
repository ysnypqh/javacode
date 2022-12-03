package cn.itcast.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import cn.itcast.domain.User;
import cn.itcast.utils.DBUtils;

public class UserDao {

	Statement stat;
	ResultSet rs;
	private static Connection conn = DBUtils.getConn();

	// �û���¼
	public User login(String userName, String password) {
		// ��ȡ����
//		conn = DBUtils.getConn();
		// ִ�е�sql���
		String sql = "select * from user where username=? and pwd=?";
		User u = null;
		try {
			// ��ȡPreparedStatement����
			PreparedStatement pstat = conn.prepareStatement(sql);
			// ����sql��ѯ������ֵ
			pstat.setString(1, userName);
			pstat.setString(2, password);
			// ��ѯ���
			rs = pstat.executeQuery();
			// ����в�ѯ�����˵�����û���ע���û�
			if (rs.next()) {
				u = new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPwd(rs.getString("pwd"));
				u.setAddress(rs.getString("address"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}

	// ��ѯ�����û�
	public ArrayList<User> select() {
		String sql = "select * from user";
//		conn = DBUtils.getConn();
		ArrayList<User> list = new ArrayList<User>();
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				User u = new User();
				// u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPwd(rs.getString("pwd"));
				u.setAge(rs.getInt("age"));
				u.setAddress(rs.getString("address"));
				list.add(u);
			}
		} catch (SQLException ex) {
			System.out.println("��ѯ���ݷ����쳣:" + ex.getMessage());
		} finally {
			try {
				rs.close();
			} catch (SQLException ex1) {
				System.out.println("��ѯ���ݹر�����쳣:" + ex1.getMessage());
			}
		}
		return list;
	}

//	public void insert(String name, int age, String address) {
//		String sql = "insert into user(username,age,address) values(?,?,?)";
//		PreparedStatement ps = null;
//		try {
//			ps = conn.prepareStatement(sql);
//			ps.setString(1, name);
//			ps.setInt(2, age);
//			ps.setString(3, address);
//			if (ps.executeUpdate() != 0) {
//				JOptionPane.showMessageDialog(null, "��ϲ���������ݳɹ���", "��Ϣ", 1);
//			}
//		} catch (SQLException ex) {
//			System.out.println("�������ݷ����쳣:" + ex.getMessage());
//		} finally {
//			try {
//				ps.close();
//			} catch (SQLException ex1) {
//				System.out.println("�������ݹر�����쳣:" + ex1.getMessage());
//			}
//		}
//	}
//	
	public void insert(User u) {
		String sql = "insert into user(username,age,address,pwd) values(?,?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, u.getUsername());
			ps.setInt(2, u.getAge());
			ps.setString(3, u.getAddress());
			ps.setString(4, u.getPwd());
			if (ps.executeUpdate() != 0) {
				JOptionPane.showMessageDialog(null, "��ϲ���������ݳɹ���", "��Ϣ", 1);
			}
		} catch (SQLException ex) {
			System.out.println("�������ݷ����쳣:" + ex.getMessage());
		} finally {
			try {
				ps.close();
			} catch (SQLException ex1) {
				System.out.println("�������ݹر�����쳣:" + ex1.getMessage());
			}
		}
	}

	public void update(String name, int age, String address, String pwd) {
		String sql = "update user set age = ?,address = ?,pwd = ? where username =?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, age);
			ps.setString(2, address);
			ps.setString(3, pwd);
			ps.setString(4, name);
			if (ps.executeUpdate() != 0) {
				JOptionPane.showMessageDialog(null, "��ϲ���������ݳɹ���", "��Ϣ", 1);
			}
		} catch (SQLException ex) {
			System.out.println("�޸����ݷ����쳣!");
		} finally {
			try {
				ps.close();
			} catch (SQLException ex1) {
				System.out.println("�޸����ݹر�����쳣:" + ex1.getMessage());
			}
		}
	}

	public void delete(String name) {
		String sql = "delete from user where username=?";
		PreparedStatement ps = null;
		int delNumber;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			delNumber = ps.executeUpdate();
			if (delNumber != 0) {
				JOptionPane.showMessageDialog(null, "��ϲ��ɾ�����ݳɹ���", "��Ϣ", 1);
			}
		} catch (SQLException ex) {
			System.out.println("ɾ�����ݷ����쳣:" + ex.getMessage());
		} finally {
			try {
				ps.close();
			} catch (SQLException ex1) {
				System.out.println("ɾ�����ݹر�����쳣:" + ex1.getMessage());
			}
		}
	}

	public void destoryConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException ex) {
				System.out.println("�ͷ������쳣��" + ex.getMessage());
			}
		}
	}

}
