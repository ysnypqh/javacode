package notepad;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutDialog implements ActionListener {
	public JDialog Dialog;
	public JButton OK, Icon;
	public JLabel Name, Version, Author, Java;
	public JPanel Panel;

	AboutDialog(JFrame notepad, int x, int y) {
		Dialog = new JDialog(notepad, "About Notepad...", true);
		OK = new JButton("OK");
		Icon = new JButton(new ImageIcon("Notepad.jpg"));
		Name = new JLabel("Notepad");
		Version = new JLabel("Version 1.0");
		Java = new JLabel("JRE Version 6.0");
		Author = new JLabel("Copyright (c) 2011 Sky. No rights reserved.");
		Panel = new JPanel();
		Color c = new Color(0, 95, 191);
		Name.setForeground(c);
		Version.setForeground(c);
		Java.setForeground(c);
		Panel.setBackground(Color.WHITE);
		OK.setFocusable(false);
		Dialog.setSize(280, 180);
		Dialog.setLocation(x, y);
		Dialog.setResizable(false);
		Dialog.setLayout(null);
		Panel.setLayout(null);
		OK.addActionListener(this);
		Icon.setFocusable(false);
		Icon.setBorderPainted(false);
		Author.setFont(new Font(null, Font.PLAIN, 11));
		Panel.add(Icon);
		Panel.add(Name);
		Panel.add(Version);
		Panel.add(Author);
		Panel.add(Java);
		Dialog.add(Panel);
		Dialog.add(OK);
		Panel.setBounds(0, 0, 280, 100);
		OK.setBounds(180, 114, 72, 26);
		Name.setBounds(80, 10, 160, 20);
		Version.setBounds(80, 27, 160, 20);
		Author.setBounds(15, 70, 250, 20);
		Java.setBounds(80, 44, 160, 20);
		Icon.setBounds(16, 14, 48, 48);
	}

	public void actionPerformed(ActionEvent e) {
		Dialog.setVisible(false);
	}
}
