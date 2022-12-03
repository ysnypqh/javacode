package notepad;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.print.PrinterException;
import javax.swing.JFrame;
public class Notepad implements ActionListener,WindowListener{
	static Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
	static Image icon=Toolkit.getDefaultToolkit().getImage("Notepad.png");
	JFrame notepad;
	EnsureDialog ensure;
	TextArea text;
	FileDialog dialog;
	FontDialog font;
	ColorDialog color;
	AboutDialog about;
	Notepad(){
		notepad=new JFrame("Notepad");
		dialog=new FileDialog(notepad);
		text=new TextArea(notepad,screen.width/2-190,screen.height/2-90);
		ensure=new EnsureDialog(notepad,screen.width/2-180,screen.height/2-80);
		font=new FontDialog(notepad,screen.width/2-240,screen.height/2-150);
		color=new ColorDialog(notepad,screen.width/2-205,screen.height/2-110);
		about=new AboutDialog(notepad,screen.width/2-140,screen.height/2-100);
		notepad.setJMenuBar(text.menu.Menu);
		notepad.add(text.Pane);
		notepad.setSize(640,420);
		notepad.setLocation(screen.width/2-320,screen.height/2-220);
		notepad.setMinimumSize(new Dimension(185,185));
		notepad.setIconImage(icon);
		notepad.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		notepad.addWindowListener(this);
		text.setFont(font.getFont());
		text.setForeground(color.NFC);
		text.setBackground(color.NBC);
		text.setSelectedTextColor(color.SFC);
		text.setSelectionColor(color.SBC);
		text.menu.Save.addActionListener(this);
		text.menu.SaveAs.addActionListener(this);
		text.menu.Open.addActionListener(this);
		text.menu.New.addActionListener(this);
		text.menu.Exit.addActionListener(this);
		text.menu.Undo.addActionListener(this);
		text.menu.Redo.addActionListener(this);
		text.menu.Cut.addActionListener(this);
		text.menu.Copy.addActionListener(this);
		text.menu.Paste.addActionListener(this);
		text.menu.Delete.addActionListener(this);
		text.menu.SelectAll.addActionListener(this);
		text.menu.FindAndReplace.addActionListener(this);
		text.menu.WordWrap.addActionListener(this);
		text.menu.Truncation.addActionListener(this);
		text.menu.Font.addActionListener(this);
		text.menu.Color.addActionListener(this);
		text.menu.ViewHelp.addActionListener(this);
		text.menu.AboutNotepad.addActionListener(this);
		text.Undo.addActionListener(this);
		text.Redo.addActionListener(this);
		text.Cut.addActionListener(this);
		text.Copy.addActionListener(this);
		text.Paste.addActionListener(this);
		text.Delete.addActionListener(this);
		text.SelectAll.addActionListener(this);
		text.FindAndReplace.addActionListener(this);
	}
	public void windowClosing(WindowEvent e) {
		if(text.Saved) System.exit(0);
		else ensure.Ensure.setVisible(true);
		if(ensure.Status==ensure.YES && saveFile()) System.exit(0);
		else if(ensure.Status==ensure.NO) System.exit(0);
	}
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		if(obj==text.menu.Save) saveFile();
		else if(obj==text.menu.SaveAs) saveAsFile();
		else if(obj==text.menu.New){
			if(!(text.Saved)){
				ensure.Ensure.setVisible(true);
				if(ensure.Status==ensure.YES && saveFile()){}
				else if(ensure.Status==ensure.NO){}
				else return;
			}
			newFile();
		}
		else if(obj==text.menu.Open){
			if(!(text.Saved)){
				ensure.Ensure.setVisible(true);
				if(ensure.Status==ensure.YES && saveFile()){}
				else if(ensure.Status==ensure.NO){}
				else return;
			}
			openFile();
		}
		else if(obj==text.menu.Print){
			try {
				text.print();
			} catch (PrinterException pe){}
		}
		else if(obj==text.menu.Exit){
			if(text.Saved) System.exit(0);
			else ensure.Ensure.setVisible(true);
			if(ensure.Status==ensure.YES && saveFile()) System.exit(0);
			else if(ensure.Status==ensure.NO) System.exit(0);
		}
		else if(obj==text.menu.Undo || obj==text.Undo){
			text.Manager.undo();
			text.Saved=false;
			text.menu.Redo.setEnabled(true);
			text.Redo.setEnabled(true);
			if(!text.Manager.canUndo()){
				text.menu.Undo.setEnabled(false);
				text.Undo.setEnabled(false);
			}
		}
		else if(obj==text.menu.Redo || obj==text.Redo){
			text.Manager.redo();
			text.Saved=false;
			text.menu.Undo.setEnabled(true);
			text.Undo.setEnabled(true);
			if(!text.Manager.canRedo()){
				text.menu.Redo.setEnabled(false);
				text.Redo.setEnabled(false);
			}
		}
		else if(obj==text.Cut || obj==text.menu.Cut){
			text.cut();
		}
		else if(obj==text.Copy || obj==text.menu.Copy){
			text.copy();
		}
		else if(obj==text.Paste || obj==text.menu.Paste){
			text.paste();
		}
		else if(obj==text.Delete || obj==text.menu.Delete){
			text.delete();
		}
		else if(obj==text.SelectAll || obj==text.menu.SelectAll){
			text.selectAll();
		}
		else if(obj==text.FindAndReplace || obj==text.menu.FindAndReplace){
			text.find.Dialog.setVisible(true);
		}
		else if(obj==text.menu.Font){
			font.Dialog.setVisible(true);
			if(text.getFont()!=font.getFont())
				text.setFont(font.getFont());
		}
		else if(obj==text.menu.Color){
			color.Dialog.setVisible(true);
			text.setForeground(color.NFC);
			text.setBackground(color.NBC);
			text.setSelectedTextColor(color.SFC);
			text.setSelectionColor(color.SBC);
			text.setCaretColor(color.NFC);
		}
		else if(obj==text.menu.AboutNotepad){
			about.Dialog.setVisible(true);
		}
	}
	public boolean saveFile(){
		if(text.Name==null){
			dialog.setMode(FileDialog.SAVE);
			dialog.setTitle("Save As...");
			dialog.setFile("Untitled.txt");
			dialog.setVisible(true);
			text.Path=dialog.getDirectory();
			text.Name=dialog.getFile();
		}
		if(text.Name==null) return false;
		text.saveFile();
		notepad.setTitle(text.Name+" - Notepad");
		return true;
	}
	public void saveAsFile(){
		String path=text.Path;
		String name=text.Name;
		dialog.setMode(FileDialog.SAVE);
		dialog.setTitle("Save As...");
		if(text.Name==null)
			dialog.setFile("Untitled.txt");
		else dialog.setFile(text.Name);
		dialog.setVisible(true);
		text.Path=dialog.getDirectory();
		text.Name=dialog.getFile();
		if(text.Name!=null){
			text.saveFile();
			notepad.setTitle(text.Name+" - Notepad");
		}
		else{
			text.Name=name;
			text.Path=path;
		}
	}
	public void openFile(){
		String path=text.Path;
		String name=text.Name;
		dialog.setTitle("Open...");
		dialog.setMode(FileDialog.LOAD);
		dialog.setVisible(true);
		text.Path=dialog.getDirectory();
		text.Name=dialog.getFile();
		if(text.Name!=null){
			text.openFile();
			notepad.setTitle(text.Name+" - Notepad");
		}
		else{
			text.Name=name;
			text.Path=path;
		}
	}
	public void newFile(){
		text.Path=null;
		text.Name=null;
		text.setText(null);
		notepad.setTitle("Notepad");
		text.Saved=true;
		text.Undo.setEnabled(false);
		text.Redo.setEnabled(false);
		text.menu.Undo.setEnabled(false);
		text.menu.Redo.setEnabled(false);
	}
	public static void main(String s[]){
		System.setProperty("java.awt.im.style","on-the-spot"); //去除输入中文时的浮动框
		Notepad np=new Notepad();
		np.notepad.setVisible(true);
	}
	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}
}
