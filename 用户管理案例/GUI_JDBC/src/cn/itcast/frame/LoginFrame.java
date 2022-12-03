package cn.itcast.frame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;

public class LoginFrame extends JFrame {
	/**
	 * 用户名
	 */
	private static final JLabel usernameLabel = new JLabel("用户名");
	private static final JTextField usernameInput = new JTextField();
	/**
	 * 密码
	 */
	private static final JLabel passwordLabel = new JLabel("密码");
	private static final JTextField passwordInput = new JPasswordField();
	/**
	 * 登录和退出按钮
	 */
	private static final JButton loginButton = new JButton(new ImageIcon("image/loginquedingC.png"));//使用图片
//	private static final JButton loginButton = new JButton("登录");
	private static final JButton exitButton = new JButton("退出");
	/**
	 * 构造方法
	 */
	public LoginFrame() {
		// 设置窗体的宽和高 
		setSize(new Dimension(380, 180));
		
		// 方法一：
		// 设置窗体居于屏幕中央， jdk1.4以后可以使用，在应用此方法时应该注意的一点是，  
		// setSize() 方法一定要放在 setLocationRelativeTo()之前， 否则只有窗体左上角是正对屏幕或所属组件中心，整个窗体看起来会是偏向右下角的。
		setLocationRelativeTo(null);
		
//		// 方法二：设置窗体居于屏幕中央，通过计算位置实现
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕的尺寸对象
//        Dimension frameSize = getSize();//获取当前窗体的尺寸对象
//        if (frameSize.height > screenSize.height) {
//            frameSize.height = screenSize.height;
//        }
//        if (frameSize.width > screenSize.width) {
//            frameSize.width = screenSize.width;
//        }
//        setLocation((screenSize.width - frameSize.width) / 2,
//                          (screenSize.height - frameSize.height) / 2);
		
		
		// 设置窗体的标题
		setTitle("用户登录");
		setLayout(null);

		// 初始化界面
		initUI();

		// 点击关闭退出程序
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// 设置窗体可见
		setVisible(true);

		// 该处设计为登录按钮的监听
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 获取用户名和密码输入框的值
				String userName = usernameInput.getText().trim();
				String password = passwordInput.getText().trim();

				// 用户名为空时提示用户输入
				if ("".equals(userName)) {
					JOptionPane.showMessageDialog(LoginFrame.this, "请输入用户名");
					return;
				}
				// 密码为空时提示用户输入
				if ("".equals(password)) {
					JOptionPane.showMessageDialog(LoginFrame.this, "请输入密码");
					return;
				}
				
				// 查询数据库
				UserDao udao = new UserDao();
				User u = udao.login(userName,password);
				
				if(u == null){
					JOptionPane.showMessageDialog(LoginFrame.this, "用户名或密码错误");
					return;
				}

				// 首先关闭当前界面
				dispose();
				// 打开用户管理界面
				new JTableAppFrame();
			}
		});

		// 该处设计为退出按钮的监听
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	/**
	 * 初始化界面
	 */
	private void initUI() {
		usernameLabel.setBounds(10, 10, 100, 21);
		usernameInput.setBounds(150, 10, 200, 21);
		passwordLabel.setBounds(10, 40, 100, 21);
		passwordInput.setBounds(150, 40, 200, 21);
		loginButton.setBounds(60, 100, 120, 21);
		exitButton.setBounds(200, 100, 120, 21);

		add(usernameLabel);
		add(usernameInput);
		add(passwordLabel);
		add(passwordInput);
		add(loginButton);
		add(exitButton);
	}

	public static void main(String[] args) {
		new LoginFrame();
	}
}
