package notepad;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ColorDialog implements ActionListener, WindowListener{
	public JDialog Dialog;
	public JLabel NFL,NBL,SFL,SBL;
	public JTextArea Normal,Selected;
	public JButton NFB,NBB,SFB,SBB,OK,Cancel,Reset;
	public Color NFC,NBC,SFC,SBC;
	public ColorChooser Chooser;
	public ColorDialog(JFrame notepad, int x, int y){
		NFC=new Color(0,0,0);
		NBC=new Color(249,249,251);
		SFC=new Color(0,0,0);
		SBC=new Color(191,207,223);
		Dialog=new JDialog(notepad,"Color...",true);
		NFL=new JLabel("Normal Foreground:");
		NBL=new JLabel("Normal Background:");
		SFL=new JLabel("Selected Foreground:");
		SBL=new JLabel("Selected Background:");
		Normal=new JTextArea("\n    Normal    正常");
		Selected=new JTextArea("\n    Selected  选中 ");
		NFB=new JButton("");
		NBB=new JButton("");
		SFB=new JButton("");
		SBB=new JButton("");
		OK=new JButton("OK");
		Cancel=new JButton("Cancel");
		Reset=new JButton("Reset");
		Chooser=new ColorChooser(Dialog, x+65, y-15);
		Normal.setEditable(false);
		Normal.setFocusable(false);
		Normal.setFont(new Font("新宋体", 0, 16));
		Normal.setForeground(NFC);
		Normal.setBackground(NBC);
		Selected.setEditable(false);
		Selected.setFocusable(false);
		Selected.setFont(Normal.getFont());
		Selected.setForeground(SFC);
		Selected.setBackground(SBC);
		NFB.setBackground(NFC);
		NBB.setBackground(NBC);
		SFB.setBackground(SFC);
		SBB.setBackground(SBC);
		Dialog.setLayout(null);
		Dialog.setLocation(x, y);
		Dialog.setSize(410, 220);
		Dialog.setResizable(false);
		Reset.setFocusable(false);
		OK.setFocusable(false);
		Cancel.setFocusable(false);
		Dialog.add(Normal);
		Dialog.add(Selected);
		Dialog.add(NFL);
		Dialog.add(NBL);
		Dialog.add(SFL);
		Dialog.add(SBL);
		Dialog.add(SBB);
		Dialog.add(SFB);
		Dialog.add(NBB);
		Dialog.add(NFB);
		Dialog.add(OK);
		Dialog.add(Cancel);
		Dialog.add(Reset);
		SBB.setBounds(144, 100, 60, 22);
		SFB.setBounds(144, 70, 60, 22);
		NBB.setBounds(144, 40, 60, 22);
		NFB.setBounds(144, 10, 60, 22);
		NFL.setBounds(10, 10, 130, 22);
		NBL.setBounds(10, 40, 130, 22);
		SFL.setBounds(10, 70, 130, 22);
		SBL.setBounds(10, 100, 130, 22);
		Normal.setBounds(220, 10, 174, 56);
		Selected.setBounds(220, 66, 174, 56);
		Reset.setBounds(10, 160, 74, 24);
		OK.setBounds(236, 160, 74, 24);
		Cancel.setBounds(320, 160, 74, 24);
		Dialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Dialog.addWindowListener(this);
		NFB.addActionListener(this);
		NBB.addActionListener(this);
		SFB.addActionListener(this);
		SBB.addActionListener(this);
		Reset.addActionListener(this);
		OK.addActionListener(this);
		Cancel.addActionListener(this);
	}
	public void setTextAreaColor(){
		Normal.setForeground(NFB.getBackground());
		Normal.setBackground(NBB.getBackground());
		Selected.setForeground(SFB.getBackground());
		Selected.setBackground(SBB.getBackground());
	}
	public void cancel(){
		Normal.setForeground(NFC);
		Normal.setBackground(NBC);
		Selected.setForeground(SFC);
		Selected.setBackground(SBC);
		NFB.setBackground(NFC);
		NBB.setBackground(NBC);
		SFB.setBackground(SFC);
		SBB.setBackground(SBC);
		Dialog.setVisible(false);
	}
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		if(obj==Reset){
			NFB.setBackground(new Color(0,0,0));
			NBB.setBackground(new Color(249,249,251));
			SFB.setBackground(new Color(0,0,0));
			SBB.setBackground(new Color(191,207,223));
			setTextAreaColor();
		}
		else if(obj==OK){
			NFC=NFB.getBackground();
			NBC=NBB.getBackground();
			SFC=SFB.getBackground();
			SBC=SBB.getBackground();
			Dialog.setVisible(false);
		}
		else if(obj==Cancel)
			cancel();
		else{
			Chooser.init(((Component) obj).getBackground());
			Chooser.Dialog.setVisible(true);
			((Component) obj).setBackground(Chooser.New.getBackground());
			setTextAreaColor();
		}
	}
	public void windowClosing(WindowEvent e) {
		cancel();
	}
	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}
}

class ColorChooser implements ActionListener,WindowListener,KeyListener{
	JDialog Dialog;
	JButton Choice[][],Old,New,OK,Cancel;
	JPanel Panel;
	JTextField R,G,B;
	JLabel OldLabel,NewLabel,RL,GL,BL;
	ColorChooser(JDialog color,int x, int y){
		Dialog=new JDialog(color,true);
		Choice=new JButton[8][8];
		Panel=new JPanel();
		Old=new JButton("");
		New=new JButton("");
		OldLabel=new JLabel("Old:");
		NewLabel=new JLabel("New:");
		RL=new JLabel("R:");
		GL=new JLabel("G:");
		BL=new JLabel("B:");
		R=new JTextField("");
		G=new JTextField("");
		B=new JTextField("");
		OK=new JButton("OK");
		Cancel=new JButton("Cancel");
		Panel.setLayout(new GridLayout(8,8,0,0));
		int i=0,j=0;
		Color c;
		Choice[0][7]=new JButton("");Choice[0][7].setBackground(new Color(255,255,255));
		Choice[1][7]=new JButton("");Choice[1][7].setBackground(new Color(255,223,191));
		Choice[2][7]=new JButton("");Choice[2][7].setBackground(new Color(255,207,207));
		Choice[3][7]=new JButton("");Choice[3][7].setBackground(new Color(223,191,255));
		Choice[4][7]=new JButton("");Choice[4][7].setBackground(new Color(207,207,255));
		Choice[5][7]=new JButton("");Choice[5][7].setBackground(new Color(191,223,255));
		Choice[6][7]=new JButton("");Choice[6][7].setBackground(new Color(207,255,207));
		Choice[7][7]=new JButton("");Choice[7][7].setBackground(new Color(223,255,191));
		for(i=0;i<8;i++){
			c=Choice[i][7].getBackground();
			for(j=0;j<8;j++){
				if(j!=7){
					Choice[i][j]=new JButton("");
					Choice[i][j].setBackground(new Color(c.getRed()*(j+1)/8,c.getGreen()*(j+1)/8,c.getBlue()*(j+1)/8));
				}
				Choice[i][j].setFocusable(false);
				Choice[i][j].addActionListener(this);
				Panel.add(Choice[i][j]);
			}
		}
		Dialog.setSize(280,250);
		Dialog.setLayout(null);
		Dialog.setLocation(x, y);
		Dialog.setResizable(false);
		Dialog.add(Panel);
		Panel.setBounds(10, 10, 160, 160);
		Dialog.add(Old);
		Dialog.add(OldLabel);
		Old.setEnabled(false);
		Old.setBorderPainted(false);
		Old.setBounds(214, 10, 44, 22);
		OldLabel.setBounds(180, 10, 44, 22);
		Dialog.add(New);
		Dialog.add(NewLabel);
		New.setEnabled(false);
		New.setBorderPainted(false);
		New.setBounds(214, 32, 44, 22);
		NewLabel.setBounds(180, 32, 44, 22);
		Dialog.add(R);
		Dialog.add(G);
		Dialog.add(B);
		R.setBounds(214, 97, 44, 22);
		G.setBounds(214, 123, 44, 22);
		B.setBounds(214, 149, 44, 22);
		Dialog.add(RL);
		Dialog.add(GL);
		Dialog.add(BL);
		RL.setBounds(196, 97, 16, 22);
		GL.setBounds(196, 123, 16, 22);
		BL.setBounds(196, 149, 16, 22);
		Dialog.add(OK);
		Dialog.add(Cancel);
		OK.setFocusable(false);
		Cancel.setFocusable(false);
		OK.setBounds(106, 190, 74, 24);
		Cancel.setBounds(190, 190, 74, 24);
		OK.addActionListener(this);
		Cancel.addActionListener(this);
		G.addKeyListener(this);
		R.addKeyListener(this);
		B.addKeyListener(this);
	}
	public void setText(Color c){
		R.setText(String.valueOf(c.getRed()));
		G.setText(String.valueOf(c.getGreen()));
		B.setText(String.valueOf(c.getBlue()));
	}
	public void init(Color c){
		New.setBackground(c);
		Old.setBackground(c);
		setText(c);
	}
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		if(obj==OK) Dialog.setVisible(false);
		else if(obj==Cancel){
			New.setBackground(Old.getBackground());
			Dialog.setVisible(false);
		}
		else{
			New.setBackground(((Component) obj).getBackground());
			setText(New.getBackground());
		}
	}
	public void windowClosing(WindowEvent e) {
		New.setBackground(Old.getBackground());
		Dialog.setVisible(false);
	}
	public void keyReleased(KeyEvent e) {
		try{
			int r,g,b;
			if(R.getText().length()==0) r=0;
			else r=Integer.valueOf(R.getText());
			if(G.getText().length()==0) g=0;
			else g=Integer.valueOf(G.getText());
			if(B.getText().length()==0) b=0;
			else b=Integer.valueOf(B.getText());
			New.setBackground(new Color(r,g,b));
		}
		catch(NumberFormatException nfe){setText(New.getBackground());}
		catch(IllegalArgumentException iae){setText(New.getBackground());}
	}
	public void keyPressed(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}
}
