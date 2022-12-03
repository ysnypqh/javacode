package notepad;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuList{
	public JMenuBar Menu;
	public JMenu File, Edit, View, Help;
	public JMenuItem
		New,Open,Save,SaveAs,Print,Exit,
		ViewHelp,AboutNotepad,
		Font,Color,
		Undo,Redo,Cut,Copy,Paste,Delete,SelectAll,FindAndReplace;
	public JCheckBoxMenuItem WordWrap,Truncation;
	MenuList(){
		Menu=new JMenuBar();
		File=new JMenu(" File ");
		New=new JMenuItem("New");
		Open=new JMenuItem("Open...");
		Save=new JMenuItem("Save");
		SaveAs=new JMenuItem("Save As...");
		Print=new JMenuItem("Print...");
		Exit=new JMenuItem("Exit");
		Help=new JMenu(" Help ");
		ViewHelp=new JMenuItem("View Help...");
		AboutNotepad=new JMenuItem("About Notepad...");
		View=new JMenu(" View ");
		WordWrap=new JCheckBoxMenuItem("Word Wrap",true);
		Truncation=new JCheckBoxMenuItem("Truncation",false);
		Font=new JMenuItem("Font...");
		Color=new JMenuItem("Color...");
		Edit=new JMenu(" Edit ");
		Undo=new JMenuItem("Undo");
		Redo=new JMenuItem("Redo");
		Cut=new JMenuItem("Cut");
		Copy=new JMenuItem("Copy");
		Paste=new JMenuItem("Paste");
		Delete=new JMenuItem("Delete");
		SelectAll=new JMenuItem("Select All");
		FindAndReplace=new JMenuItem("Find And Replace...");
		Undo.setEnabled(false);
		Redo.setEnabled(false);
		Edit.add(Undo);
		Edit.add(Redo);
		Edit.addSeparator();
		Edit.add(Cut);
		Edit.add(Copy);
		Edit.add(Paste);
		Edit.add(Delete);
		Edit.addSeparator();
		Edit.add(SelectAll);
		Edit.add(FindAndReplace);
		View.add(WordWrap);
		View.add(Truncation);
		View.addSeparator();
		View.add(Font);
		View.add(Color);
		Help.add(ViewHelp);
		Help.add(AboutNotepad);
		File.add(New);
		File.add(Open);
		File.addSeparator();
		File.add(Save);
		File.add(SaveAs);
		File.addSeparator();
		File.add(Print);
		File.add(Exit);
		Menu.add(File);
		Menu.add(Edit);
		Menu.add(View);
		Menu.add(Help);
		New.setAccelerator(KeyStroke.getKeyStroke('N',128));
		Open.setAccelerator(KeyStroke.getKeyStroke('O',128));
		Save.setAccelerator(KeyStroke.getKeyStroke('S',128));
		Print.setAccelerator(KeyStroke.getKeyStroke('P',128));
		Undo.setAccelerator(KeyStroke.getKeyStroke('Z',128));
		Redo.setAccelerator(KeyStroke.getKeyStroke('Y',128));
		Cut.setAccelerator(KeyStroke.getKeyStroke('X',128));
		Copy.setAccelerator(KeyStroke.getKeyStroke('C',128));
		Paste.setAccelerator(KeyStroke.getKeyStroke('V',128));
		Delete.setAccelerator(KeyStroke.getKeyStroke(127,0));
		SelectAll.setAccelerator(KeyStroke.getKeyStroke('A',128));
	}
}
