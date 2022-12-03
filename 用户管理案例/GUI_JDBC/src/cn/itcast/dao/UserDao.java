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

	// 用户登录
	public User login(String userName, String password) {
		// 获取链接
//		conn = DBUtils.getConn();
		// 执行的sql语句
		String sql = "select * from user where username=? and pwd=?";
		User u = null;
		try {
			// 获取PreparedStatement对象
			PreparedStatement pstat = conn.prepareStatement(sql);
			// 设置sql查询条件的值
			pstat.setString(1, userName);
			pstat.setString(2, password);
			// 查询结果
			rs = pstat.executeQuery();
			// 如果有查询结果，说明该用户是注册用户
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

	// 查询所有用户
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
			System.out.println("查询数据发生异常:" + ex.getMessage());
		} finally {
			try {
				rs.close();
			} catch (SQLException ex1) {
				System.out.println("查询数据关闭语句异常:" + ex1.getMessage());
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
//				JOptionPane.showMessageDialog(null, "恭喜！插入数据成功！", "消息", 1);
//			}
//		} catch (SQLException ex) {
//			System.out.println("插入数据发生异常:" + ex.getMessage());
//		} finally {
//			try {
//				ps.close();
//			} catch (SQLException ex1) {
//				System.out.println("插入数据关闭语句异常:" + ex1.getMessage());
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
				JOptionPane.showMessageDialog(null, "恭喜！插入数据成功！", "消息", 1);
			}
		} catch (SQLException ex) {
			System.out.println("插入数据发生异常:" + ex.getMessage());
		} finally {
			try {
				ps.close();
			} catch (SQLException ex1) {
				System.out.println("插入数据关闭语句异常:" + ex1.getMessage());
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
				JOptionPane.showMessageDialog(null, "恭喜！更新数据成功！", "消息", 1);
			}
		} catch (SQLException ex) {
			System.out.println("修改数据发生异常!");
		} finally {
			try {
				ps.close();
			} catch (SQLException ex1) {
				System.out.println("修改数据关闭语句异常:" + ex1.getMessage());
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
				JOptionPane.showMessageDialog(null, "恭喜！删除数据成功！", "消息", 1);
			}
		} catch (SQLException ex) {
			System.out.println("删除数据发生异常:" + ex.getMessage());
		} finally {
			try {
				ps.close();
			} catch (SQLException ex1) {
				System.out.println("删除数据关闭语句异常:" + ex1.getMessage());
			}
		}
	}

	public void destoryConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException ex) {
				System.out.println("释放连接异常：" + ex.getMessage());
			}
		}
	}

}
