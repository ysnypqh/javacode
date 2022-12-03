package cn.itcast.frame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;

public class LoginFrame extends JFrame {
	/**
	 * �û���
	 */
	private static final JLabel usernameLabel = new JLabel("�û���");
	private static final JTextField usernameInput = new JTextField();
	/**
	 * ����
	 */
	private static final JLabel passwordLabel = new JLabel("����");
	private static final JTextField passwordInput = new JPasswordField();
	/**
	 * ��¼���˳���ť
	 */
	private static final JButton loginButton = new JButton(new ImageIcon("image/loginquedingC.png"));//ʹ��ͼƬ
//	private static final JButton loginButton = new JButton("��¼");
	private static final JButton exitButton = new JButton("�˳�");
	/**
	 * ���췽��
	 */
	public LoginFrame() {
		// ���ô���Ŀ�͸� 
		setSize(new Dimension(380, 180));
		
		// ����һ��
		// ���ô��������Ļ���룬 jdk1.4�Ժ����ʹ�ã���Ӧ�ô˷���ʱӦ��ע���һ���ǣ�  
		// setSize() ����һ��Ҫ���� setLocationRelativeTo()֮ǰ�� ����ֻ�д������Ͻ���������Ļ������������ģ��������忴��������ƫ�����½ǵġ�
		setLocationRelativeTo(null);
		
//		// �����������ô��������Ļ���룬ͨ������λ��ʵ��
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//��ȡ��Ļ�ĳߴ����
//        Dimension frameSize = getSize();//��ȡ��ǰ����ĳߴ����
//        if (frameSize.height > screenSize.height) {
//            frameSize.height = screenSize.height;
//        }
//        if (frameSize.width > screenSize.width) {
//            frameSize.width = screenSize.width;
//        }
//        setLocation((screenSize.width - frameSize.width) / 2,
//                          (screenSize.height - frameSize.height) / 2);
		
		
		// ���ô���ı���
		setTitle("�û���¼");
		setLayout(null);

		// ��ʼ������
		initUI();

		// ����ر��˳�����
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// ���ô���ɼ�
		setVisible(true);

		// �ô����Ϊ��¼��ť�ļ���
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ��ȡ�û���������������ֵ
				String userName = usernameInput.getText().trim();
				String password = passwordInput.getText().trim();

				// �û���Ϊ��ʱ��ʾ�û�����
				if ("".equals(userName)) {
					JOptionPane.showMessageDialog(LoginFrame.this, "�������û���");
					return;
				}
				// ����Ϊ��ʱ��ʾ�û�����
				if ("".equals(password)) {
					JOptionPane.showMessageDialog(LoginFrame.this, "����������");
					return;
				}
				
				// ��ѯ���ݿ�
				UserDao udao = new UserDao();
				User u = udao.login(userName,password);
				
				if(u == null){
					JOptionPane.showMessageDialog(LoginFrame.this, "�û������������");
					return;
				}

				// ���ȹرյ�ǰ����
				dispose();
				// ���û��������
				new JTableAppFrame();
			}
		});

		// �ô����Ϊ�˳���ť�ļ���
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	/**
	 * ��ʼ������
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
