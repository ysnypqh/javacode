package cn.itcast.calculator;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
class Calculator implements ActionListener {
	JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15,
			b16;
	JFrame f;
	GridLayout c = new GridLayout(4, 4);
	JTextField tf = new JTextField();

	public void init() {
		f = new JFrame("CardLayout testing");
		Container ct = f.getContentPane();
		tf.setHorizontalAlignment(JTextField.RIGHT);
		ct.add(tf, "North");
		JPanel p2 = new JPanel();
		ct.add(p2, "Center");
		GridLayout c = new GridLayout(4, 4);
		p2.setLayout(c);
		JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16;
		b1 = new JButton("1");
		b2 = new JButton("2");
		b3 = new JButton("3");
		b4 = new JButton("4");
		b5 = new JButton("5");
		b6 = new JButton("6");
		b7 = new JButton("7");
		b8 = new JButton("8");
		b9 = new JButton("9");
		b10 = new JButton("0");
		b11 = new JButton(".");
		b12 = new JButton("=");
		b13 = new JButton("+");
		b14 = new JButton("-");
		b15 = new JButton("x");
		b16 = new JButton("/");
		p2.add(b1);
		b1.addActionListener(this);
		p2.add(b2);
		b2.addActionListener(this);
		p2.add(b3);
		b3.addActionListener(this);
		p2.add(b13);
		b13.addActionListener(this);
		p2.add(b4);
		b4.addActionListener(this);
		p2.add(b5);
		b5.addActionListener(this);
		p2.add(b6);
		b6.addActionListener(this);
		p2.add(b14);
		b14.addActionListener(this);
		p2.add(b7);
		b7.addActionListener(this);
		p2.add(b8);
		b8.addActionListener(this);
		p2.add(b9);
		b9.addActionListener(this);
		p2.add(b15);
		b15.addActionListener(this);
		p2.add(b10);
		b10.addActionListener(this);
		p2.add(b11);
		b11.addActionListener(this);
		p2.add(b12);
		b12.addActionListener(this);
		p2.add(b16);
		b16.addActionListener(this);

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.setSize(200, 180);
		f.setVisible(true);
	}

	double sum = 0;
	Vector v = new Vector(1, 1);
	Vector v2 = new Vector(1, 1);

	public Calculator() {
		v2.addElement(new String("init"));
		v.addElement(new String("init"));
	}

	public void actionPerformed(ActionEvent e) {
		String str = tf.getText();
		// 加号的实现
		if (e.getActionCommand() == "+") 
		{
			if (("+").equals((String) v2.lastElement())
					|| ("-").equals((String) v2.lastElement())
					|| ("x").equals((String) v2.lastElement())
					|| ("/").equals((String) v2.lastElement())
					|| ("=").equals((String) v2.lastElement())) {
				tf.setText(String.valueOf(sum));
			} else if (("init").equals((String) v2.lastElement())) {
				tf.setText(String.valueOf(sum));
				v2.addElement(new String("="));
			} else {
				double d = Double.parseDouble(str);
				if (("+").equals((String) v.lastElement())) {
					sum = sum + d;
					tf.setText(String.valueOf(sum));
				} else if (("-").equals((String) v.lastElement())) {
					sum = sum - d;
					tf.setText(String.valueOf(sum));
				} else if (("x").equals((String) v.lastElement())) {
					sum = sum * d;
					tf.setText(String.valueOf(sum));
				} else if (("/").equals((String) v.lastElement())) {
					sum = sum / d;
					tf.setText(String.valueOf(sum));
				} else if (("=").equals((String) v.lastElement())) {
					sum = sum + d;
					tf.setText(String.valueOf(sum));
				} else {
					sum = sum + d;
					tf.setText(String.valueOf(sum));
				}
			}
			v.addElement(new String("+"));
			v2.addElement(new String("+"));
		}

		// 减号的实现
		if (e.getActionCommand() == "-") 
		{
			if (("+").equals((String) v2.lastElement())
					|| ("-").equals((String) v2.lastElement())
					|| ("x").equals((String) v2.lastElement())
					|| ("/").equals((String) v2.lastElement())
					|| ("=").equals((String) v2.lastElement())) {
				tf.setText(String.valueOf(sum));
			} else if (("init").equals((String) v2.lastElement())) {
				tf.setText(String.valueOf(sum));
				v2.addElement(new String("="));
			} else {
				double d = Double.parseDouble(str);
				if (("+").equals((String) v.lastElement())) {
					sum = sum + d;
					tf.setText(String.valueOf(sum));
				} else if (("-").equals((String) v.lastElement())) {
					sum = sum - d;
					tf.setText(String.valueOf(sum));
				} else if (("x").equals((String) v.lastElement())) {
					sum = sum * d;
					tf.setText(String.valueOf(sum));
				} else if (("/").equals((String) v.lastElement())) {
					sum = sum / d;
					tf.setText(String.valueOf(sum));
				} else if (("=").equals((String) v.lastElement())) {
					sum = sum + d;
					tf.setText(String.valueOf(sum));
				} else {
					sum = sum + d;
					tf.setText(String.valueOf(sum));
				}
			}
			v.addElement(new String("-"));
			v2.addElement(new String("-"));
		}

		// 乘号的实现
		if (e.getActionCommand() == "x") 
		{
			if (("+").equals((String) v2.lastElement())
					|| ("-").equals((String) v2.lastElement())
					|| ("x").equals((String) v2.lastElement())
					|| ("/").equals((String) v2.lastElement())
					|| ("=").equals((String) v2.lastElement())) {
				tf.setText(String.valueOf(sum));
			} else if (("init").equals((String) v2.lastElement())) {
				tf.setText(String.valueOf(sum));
				v2.addElement(new String("="));
			} else {
				double d = Double.parseDouble(str);
				if (("+").equals((String) v.lastElement())) {
					sum = sum + d;
					tf.setText(String.valueOf(sum));
				} else if (("-").equals((String) v.lastElement())) {
					sum = sum - d;
					tf.setText(String.valueOf(sum));
				} else if (("x").equals((String) v.lastElement())) {
					sum = sum * d;
					tf.setText(String.valueOf(sum));
				} else if (("/").equals((String) v.lastElement())) {
					sum = sum / d;
					tf.setText(String.valueOf(sum));
				} else if (("=").equals((String) v.lastElement())) {
					sum = sum + d;
					tf.setText(String.valueOf(sum));
				} else {
					sum = sum + d;
					tf.setText(String.valueOf(sum));
				}
			}
			v.addElement(new String("x"));
			v2.addElement(new String("x"));
		}

		// 除号的实现
		if (e.getActionCommand() == "/") 
		{
			if (("+").equals((String) v2.lastElement())
					|| ("-").equals((String) v2.lastElement())
					|| ("x").equals((String) v2.lastElement())
					|| ("/").equals((String) v2.lastElement())
					|| ("=").equals((String) v2.lastElement())) {
				tf.setText(String.valueOf(sum));
			} else if (("init").equals((String) v2.lastElement())) {
				tf.setText(String.valueOf(sum));
				v2.addElement(new String("="));
			} else {
				double d = Double.parseDouble(str);
				if (("+").equals((String) v.lastElement())) {
					sum = sum + d;
					tf.setText(String.valueOf(sum));
				} else if (("-").equals((String) v.lastElement())) {
					sum = sum - d;
					tf.setText(String.valueOf(sum));
				} else if (("x").equals((String) v.lastElement())) {
					sum = sum * d;
					tf.setText(String.valueOf(sum));
				} else if (("/").equals((String) v.lastElement())) {
					sum = sum / d;
					tf.setText(String.valueOf(sum));
				} else if (("=").equals((String) v.lastElement())) {
					sum = sum + d;
					tf.setText(String.valueOf(sum));
				} else {
					sum = sum + d;
					tf.setText(String.valueOf(sum));
				}
			}
			v.addElement(new String("/"));
			v2.addElement(new String("/"));
		}

		// 等号实现
		if (e.getActionCommand() == "=") 
		{
			if (("+").equals((String) v2.lastElement())
					|| ("-").equals((String) v2.lastElement())
					|| ("x").equals((String) v2.lastElement())
					|| ("/").equals((String) v2.lastElement())
					|| ("=").equals((String) v2.lastElement())) {
				tf.setText(String.valueOf(sum));
			} else if (("init").equals((String) v2.lastElement())) {
				tf.setText(String.valueOf(sum));
			} else {
				double d = Double.parseDouble(str);
				if (("+").equals((String) v.lastElement())) {
					sum = sum + d;
					tf.setText(String.valueOf(sum));
				} else if (("-").equals((String) v.lastElement())) {
					sum = sum - d;
					tf.setText(String.valueOf(sum));
				} else if (("x").equals((String) v.lastElement())) {
					sum = sum * d;
					tf.setText(String.valueOf(sum));
				} else if (("/").equals((String) v.lastElement())) {
					sum = sum / d;
					tf.setText(String.valueOf(sum));
				} else if (("=").equals((String) v.lastElement())) {
					sum = sum + d;
					tf.setText(String.valueOf(sum));
					// tf.setText(String.valueOf(sum));
				} else {
					sum = sum + d;
					tf.setText(String.valueOf(sum));
				}
			}
			v.addElement(new String("="));
			v2.addElement(new String("="));
		}

		if (e.getActionCommand() == "1" || e.getActionCommand() == "2"
				|| e.getActionCommand() == "3" || e.getActionCommand() == "4"
				|| e.getActionCommand() == "5" || e.getActionCommand() == "6"
				|| e.getActionCommand() == "7" || e.getActionCommand() == "8"
				|| e.getActionCommand() == "9" || e.getActionCommand() == "0"
				|| e.getActionCommand() == ".") // 1，2，3，4，5，6，7，8，9，0，.的实现；
		{
			if (("+").equals((String) v2.lastElement())
					|| ("-").equals((String) v2.lastElement())
					|| ("x").equals((String) v2.lastElement())
					|| ("/").equals((String) v2.lastElement())
					|| ("init").equals((String) v2.lastElement())) {
				tf.setText("");
				tf.setText(e.getActionCommand());
			} else if (("=").equals((String) v2.lastElement())) {
				sum = 0;
				tf.setText("");
				tf.setText(e.getActionCommand());
			} else {
				tf.setText(tf.getText() + e.getActionCommand());// 调用其父类中的getText()方法
			}
			v2.addElement(new String(e.getActionCommand()));
		}
	}

	public static void main(String args[]) {
		new Calculator().init();
	}

}