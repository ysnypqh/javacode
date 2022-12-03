package notepad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EnsureDialog implements WindowListener, ActionListener {
	public int YES, NO, CANCEL, Status;
	public JDialog Ensure;
	public JButton Yes, No, Cancel;
	public JLabel Question;
	public JPanel ButtonPanel, TextPanel;

	EnsureDialog(JFrame notepad, int x, int y) {
		YES = 0;
		NO = 1;
		CANCEL = 2;
		Status = CANCEL;
		Ensure = new JDialog(notepad, true);
		/*
		 * 这里的模式标志true的作用是使对话框处于notepad的上端， 并且当对话框显示时进程处于停滞状态， 直到对话框不再显示为止。在本程序中
		 * ，由于对对话框进行了事件监听处理，当对话框消失时状态标 志Status同时发生了变化 ，这样就可以在进程继续时获得新的Status值
		 */
		Yes = new JButton("Yes");
		No = new JButton("No");
		Cancel = new JButton("Cancel");
		Question = new JLabel("  Do you want to save changes to the file?");
		ButtonPanel = new JPanel();
		TextPanel = new JPanel();
		ButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 16, 10));
		TextPanel.setLayout(new BorderLayout());
		Ensure.setLayout(new BorderLayout());
		ButtonPanel.add(Yes);
		ButtonPanel.add(No);
		ButtonPanel.add(Cancel);
		TextPanel.add(Question);
		Ensure.add(TextPanel, BorderLayout.CENTER);
		Ensure.add(ButtonPanel, BorderLayout.SOUTH);
		Ensure.setLocation(x, y);
		Ensure.setSize(360, 140);
		Ensure.setResizable(false);
		TextPanel.setBackground(Color.WHITE);
		Question.setFont(new Font(null, Font.PLAIN, 16));
		Question.setForeground(new Color(0, 95, 191));
		Yes.setFocusable(false);
		No.setFocusable(false);
		Cancel.setFocusable(false);
		Ensure.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Ensure.addWindowListener(this);
		Yes.addActionListener(this);
		No.addActionListener(this);
		Cancel.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Yes)
			Status = YES;
		else if (e.getSource() == No)
			Status = NO;
		else if (e.getSource() == Cancel)
			Status = CANCEL;
		Ensure.setVisible(false);
	}

	public void windowClosing(WindowEvent e) {
		Status = CANCEL;
		Ensure.setVisible(false);
	}

	public void windowActivated(WindowEvent e) {
	}

	public void windowClosed(WindowEvent e) {
	}

	public void windowDeactivated(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowOpened(WindowEvent e) {
	}
}
