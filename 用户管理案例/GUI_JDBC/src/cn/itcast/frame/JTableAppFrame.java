package cn.itcast.frame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.utils.DBUtils;

public class JTableAppFrame extends JFrame {
    JPanel contentPane;
    Vector CellsVector = new Vector();// ������
    Vector TitleVector = new Vector();// ������
    JScrollPane scp = new JScrollPane();
    JTable tab = null;
    UserDao udao = new UserDao();
    JLabel lbl_name = new JLabel();
    JLabel lbl_age = new JLabel();
    JLabel lbl_address = new JLabel();
    JLabel lbl_pwd = new JLabel();
    JTextField txt_name = new JTextField();
    JTextField txt_age = new JTextField();
    JPasswordField txt_pwd = new JPasswordField();
    JTextField txt_address = new JTextField();
    JButton btn_add = new JButton();
    JButton btn_del = new JButton();
    JButton btn_update = new JButton();
    JButton btn_reset = new JButton();
    
    public JTableAppFrame() {
    	
		pack();
        // ����frame�ɼ�
        setVisible(true);
        
        // ��������ϵ�close��ť���˳�����
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

   
    private void jbInit() throws Exception {
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
        this.setResizable(false);
        setSize(new Dimension(400, 340));
        setLocationRelativeTo(null);
        setTitle("�û�����");
        scp.setBounds(new Rectangle(46, 32, 297, 157));
        
        lbl_name.setFont(new java.awt.Font("����", Font.BOLD, 12));
        lbl_name.setText("������");
        lbl_name.setBounds(new Rectangle(46, 205, 42, 15));
        lbl_age.setFont(new java.awt.Font("����", Font.BOLD, 12));
        lbl_age.setText("���䣺");
        lbl_age.setBounds(new Rectangle(200, 205, 42, 15));
        lbl_address.setFont(new java.awt.Font("����", Font.BOLD, 12));
        lbl_address.setText("��ַ��");
        lbl_address.setBounds(new Rectangle(46, 232, 42, 15));
        lbl_pwd.setFont(new java.awt.Font("����", Font.BOLD, 12));
        lbl_pwd.setText("���룺");
        lbl_pwd.setBounds(new Rectangle(200, 232, 42, 15));
        txt_name.setFont(new java.awt.Font("����", Font.BOLD, 12));
        txt_name.setBounds(new Rectangle(98, 205, 72, 21));
        txt_age.setFont(new java.awt.Font("����", Font.BOLD, 12));
        txt_age.setBounds(new Rectangle(250, 205, 72, 21));
        txt_address.setFont(new java.awt.Font("����", Font.BOLD, 12));
        txt_address.setBounds(new Rectangle(98, 232, 72, 21));
        txt_pwd.setFont(new java.awt.Font("����", Font.BOLD, 12));
        txt_pwd.setBounds(new Rectangle(250, 232, 72, 21));
        btn_add.setBounds(new Rectangle(45, 271, 70, 25));
        btn_add.setFont(new java.awt.Font("����", Font.BOLD, 12));
        btn_add.setText("�� ��");
        btn_add.addActionListener(new JTableAppFrame_btn_add_actionAdapter(this));
        btn_del.setBounds(new Rectangle(125, 271, 70, 25));
        btn_del.setFont(new java.awt.Font("����", Font.BOLD, 12));
        btn_del.setText("ɾ ��");
        btn_del.addActionListener(new JTableAppFrame_btn_del_actionAdapter(this));
        btn_update.setBounds(new Rectangle(205, 271, 70, 25));
        btn_update.setFont(new java.awt.Font("����", Font.BOLD, 12));
        btn_update.setText("�� ��");
        btn_update.addActionListener(new JTableAppFrame_btn_update_actionAdapter(this));
        btn_reset.setBounds(new Rectangle(285, 271, 70, 25));
        btn_reset.setFont(new java.awt.Font("����", Font.BOLD, 12));
        btn_reset.setText("�� ��");
        btn_reset.addActionListener(new JTableAppFrame_btn_reset_actionAdapter(this));
        contentPane.add(scp);
        contentPane.add(lbl_name);
        contentPane.add(lbl_age);
        contentPane.add(lbl_address);
        contentPane.add(lbl_pwd);
        contentPane.add(txt_name);
        contentPane.add(txt_age);
        contentPane.add(txt_address);
        contentPane.add(txt_pwd);
        contentPane.add(btn_del);
        contentPane.add(btn_update);
        contentPane.add(btn_add);
        contentPane.add(btn_reset);
        
        this.showTable();
        tab = new JTable(CellsVector, TitleVector);
        scp.getViewport().add(tab);
    }

    // ����ѯ��������չʾ��table�����
    public void showTable() {
        this.TitleVector.add("����");
        this.TitleVector.add("����");
        this.TitleVector.add("��ַ");
        UserDao udao = new UserDao();
        ArrayList list = udao.select();
        for (int i = 0; i < list.size(); i++) {
            User stu = (User) list.get(i);
            Vector v = new Vector();
            v.add(stu.getUsername());
            v.add(stu.getAge());
            v.add(stu.getAddress());
            CellsVector.add(v);
        }
    }


   
    String name;
    String age;
    String address;
    String pwd;
    // ������������֤
    public int checkInformation() {
        name = this.txt_name.getText();
        age = this.txt_age.getText();
        address = this.txt_address.getText();
        pwd = String.valueOf(this.txt_pwd.getPassword());
        if (name.equals("")) {
            JOptionPane.showMessageDialog(this, "���ã�������������", "��ʾ", 1);
            this.txt_name.grabFocus();
            return 0;
        }
        if (age.equals("")) {
            JOptionPane.showMessageDialog(this, "���ã����������䣡", "��ʾ", 1);
            this.txt_age.grabFocus();
            return 0;
        }
        char[] ans = age.toCharArray();
        for (int i = 0; i < ans.length; i++) {
            if (!Character.isDigit(ans[i])) {
                JOptionPane.showMessageDialog(this, "���ã������������", "��ʾ", 1);
                this.txt_age.setText("");
                this.txt_age.grabFocus();
                return 0;
            }
        }
        if (age.length() > 3) {
            JOptionPane.showMessageDialog(this, "���ã��������ֻ��Ϊ 100�꣡", "��ʾ", 1);
            this.txt_age.setText("");
            this.txt_age.grabFocus();
            return 0;
        }
        if (address.equals("")) {
            JOptionPane.showMessageDialog(this, "���ã��������ַ��", "��ʾ", 1);
            this.txt_address.grabFocus();
            return 0;
        }
        if (pwd.equals("")) {
            JOptionPane.showMessageDialog(this, "���ã����������룡", "��ʾ", 1);
            this.txt_pwd.grabFocus();
            return 0;
        }
        ages = Integer.valueOf(age);
        return 1;
    }

    
    // ���
    int ages;
    public void btn_add_actionPerformed(ActionEvent e) {
        if (this.checkInformation() == 0) {
            return;
        }
        UserDao udao = new UserDao();
//        udao.insert(name, ages, address);
        User u = new User();
        u.setUsername(name);
        u.setAge(ages);
        u.setAddress(address);
        u.setPwd(pwd);
        
        udao.insert(u);
        this.CellsVector.clear();
        this.showTable();
        this.tab.updateUI();
        this.btn_reset_actionPerformed(e);
    }

    // ɾ��
    ResultSet rs;
    public void btn_del_actionPerformed(ActionEvent e) {
        String name = this.txt_name.getText();
        this.txt_age.setEditable(false);
        this.txt_address.setEditable(false);
        this.txt_pwd.setEditable(false);

        if (name.equals("")) {
            JOptionPane.showMessageDialog(this, "���ã�������������", "��ʾ", 1);
            this.txt_name.grabFocus();
            return;
        }
        try {
            String sql = "select * from user where username=?";
            PreparedStatement ps = DBUtils.getConn().prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                ps.close();
                udao.delete(name);
                this.CellsVector.clear();
                this.showTable();
                this.tab.updateUI();
                this.btn_reset_actionPerformed(e);
            } else {
                JOptionPane.showMessageDialog(this, "���ã����û������ڣ�", "��ʾ", 1);
                this.btn_reset_actionPerformed(e);
                return;
            }
        } catch (SQLException ex) {
            System.out.println("�˶��û���Ϣ�����쳣" + ex.getMessage());
        }
    }

    // �޸�
    public void btn_update_actionPerformed(ActionEvent e) {
        if (this.checkInformation() == 0) {
            return;
        }
        try {
            String sql = "select * from user where username=?";
            PreparedStatement ps = DBUtils.getConn().prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                ps.close();
                udao.update(name, ages, address,pwd);
                this.CellsVector.clear();
                this.showTable();
                this.tab.updateUI();
                this.btn_reset_actionPerformed(e);
            } else {
                JOptionPane.showMessageDialog(this, "���ã����û������ڣ�", "��ʾ", 1);
                this.btn_reset_actionPerformed(e);
                return;
            }
        } catch (SQLException ex) {
            System.out.println("�˶��û���Ϣ�����쳣" + ex.getMessage());
        }

    }

    // ���������
    public void btn_reset_actionPerformed(ActionEvent e) {
        this.txt_name.setText("");
        this.txt_age.setText("");
        this.txt_address.setText("");
        this.txt_pwd.setText("");
        this.txt_age.setEditable(true);
        this.txt_address.setEditable(true);
        this.txt_pwd.setEditable(true);
    }
}


class JTableAppFrame_btn_del_actionAdapter implements ActionListener {
    private JTableAppFrame adaptee;
    JTableAppFrame_btn_del_actionAdapter(JTableAppFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btn_del_actionPerformed(e);
    }
}


class JTableAppFrame_btn_update_actionAdapter implements ActionListener {
    private JTableAppFrame adaptee;
    JTableAppFrame_btn_update_actionAdapter(JTableAppFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btn_update_actionPerformed(e);
    }
}


class JTableAppFrame_btn_reset_actionAdapter implements ActionListener {
    private JTableAppFrame adaptee;
    JTableAppFrame_btn_reset_actionAdapter(JTableAppFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btn_reset_actionPerformed(e);
    }
}


class JTableAppFrame_btn_add_actionAdapter implements ActionListener {
    private JTableAppFrame adaptee;
    JTableAppFrame_btn_add_actionAdapter(JTableAppFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btn_add_actionPerformed(e);
    }
}

