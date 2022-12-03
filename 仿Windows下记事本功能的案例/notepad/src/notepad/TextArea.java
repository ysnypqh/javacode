package notepad;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

public class TextArea extends JTextArea implements MouseListener,UndoableEditListener,
MenuListener,ActionListener,ItemListener{
	private static final long serialVersionUID = 1L;
	public boolean Saved;
	public String Name,Path;
	public JScrollPane Pane;
	public JPopupMenu Popup;
	public JMenuItem Redo,Undo,Cut,Copy,Paste,Delete,SelectAll,FindAndReplace;
	public UndoManager Manager;
	public MenuList menu;
	public FindAndReplace find;
	TextArea(JFrame notepad,int x,int y){
		super();
		Saved=true;
		Name=null;
		Path=null;
		Popup=new JPopupMenu();
		Undo=new JMenuItem("  Undo");
		Redo=new JMenuItem("  Redo");
		Cut=new JMenuItem("  Cut");
		Copy=new JMenuItem("  Copy");
		Paste=new JMenuItem("  Paste");
		Delete=new JMenuItem("  Delete");
		SelectAll=new JMenuItem("  Select All");
		FindAndReplace=new JMenuItem("  Find And Replace...");
		Pane=new JScrollPane(this);
		Manager=new UndoManager();
		menu=new MenuList();
		find=new FindAndReplace(notepad);
		find.Dialog.setLocation(x,y);
		Undo.setEnabled(false);
		Redo.setEnabled(false);
		setLineWrap(true);
		setWrapStyleWord(true);
		Manager.setLimit(-1);
		Popup.add(Undo);
		Popup.add(Redo);
		Popup.addSeparator();
		Popup.add(Cut);
		Popup.add(Copy);
		Popup.add(Paste);
		Popup.add(Delete);
		Popup.addSeparator();
		Popup.add(SelectAll);
		Popup.add(FindAndReplace);
		add(Popup);
		menu.Edit.addMenuListener(this);
		menu.WordWrap.addItemListener(this);
		menu.Truncation.addItemListener(this);
		getDocument().addUndoableEditListener(this);
		addMouseListener(this);
		find.FindNext.addActionListener(this);
		find.Replace.addActionListener(this);
		find.ReplaceAll.addActionListener(this);
		find.Finish.addActionListener(this);
	}
	public void saveFile(){
		try {
			FileWriter fw = new FileWriter(Path+Name,false);
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write(getText());
			bw.close();
			fw.close();
			Saved=true;
		} catch (IOException e){}
	}
	public void openFile(){
		try {
			int c;
			StringBuffer sb=new StringBuffer();
			FileReader fr=new FileReader(Path+Name);
			setText(null);
			while((c=fr.read())!=-1)
				sb.append((char)c);
			setText(sb.toString());
			Saved=true;
			fr.close();
			Undo.setEnabled(false);
			Redo.setEnabled(false);
			menu.Undo.setEnabled(false);
			menu.Redo.setEnabled(false);
		}
		catch (IOException e){}
	}
	public void delete(){
		int start=getSelectionStart();
		int end=getSelectionEnd();
		replaceRange("",start,end);
	}
	public int lastOf(String s1,int i){
		String s=getText();
		if(find.MatchCase.isSelected()) return s.lastIndexOf(s1,i);
		else{
			s=s.toLowerCase();
			return s.lastIndexOf(s1.toLowerCase(),i);
		}
	}
	public int nextOf(String s1,int i){
		String s=getText();
		if(find.MatchCase.isSelected()) return s.indexOf(s1,i);
		else{
			s=s.toLowerCase();
			return s.indexOf(s1.toLowerCase(),i);
		}
	}
	public void actionPerformed(ActionEvent e){
		Object obj=e.getSource();
		if(obj==find.Finish) find.Dialog.setVisible(false);
		String s1=find.FindText.getText();
		String s2=find.ReplaceText.getText();
		int len1=s1.length(),len2=s2.length();
		if(len1<1) return;
		int head=getSelectionStart(),rear=getSelectionEnd();
		if(obj==find.Replace){
			if(head<rear) replaceRange(s2,head,rear);
			else obj=find.FindNext;
		}
		if(obj==find.FindNext){
			if(find.Up.isSelected()){
				head=lastOf(s1,head-len1);
				if(head<0) return;
				select(head, head+len1);
			}
			else{
				rear=nextOf(s1, rear);
				if(rear<0) return;
				select(rear,rear+len1);
			}
		}
		else if(obj==find.ReplaceAll){
			rear=0;
			while(true){
				rear=nextOf(s1,rear);
				if(rear<0) return;
				replaceRange(s2,rear,rear+len1);
				rear=rear+len2;
				setCaretPosition(rear);
			}
		}
	}
	public void menuSelected(MenuEvent e){
		Clipboard Board=Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents = Board.getContents(Board);
		DataFlavor flavor = DataFlavor.stringFlavor;
		if(contents.isDataFlavorSupported(flavor))
			menu.Paste.setEnabled(true);
		else
			menu.Paste.setEnabled(false);
		if(getSelectedText()!=null){
			menu.Cut.setEnabled(true);
			menu.Copy.setEnabled(true);
			menu.Delete.setEnabled(true);
		}
		else{
			menu.Cut.setEnabled(false);
			menu.Copy.setEnabled(false);
			menu.Delete.setEnabled(false);
		}
		if(getText().isEmpty()){
			menu.SelectAll.setEnabled(false);
			menu.FindAndReplace.setEnabled(false);
		}
		else{
			menu.SelectAll.setEnabled(true);
			menu.FindAndReplace.setEnabled(true);
		}
	}
	public void undoableEditHappened(UndoableEditEvent e){
		Manager.addEdit(e.getEdit());
		Saved=false;
		menu.Undo.setEnabled(true);
		Undo.setEnabled(true);
		menu.Redo.setEnabled(false);
		Redo.setEnabled(false);
	}
	public void mouseReleased(MouseEvent e) {
		if(e.isPopupTrigger())
		{
			Clipboard Board=Toolkit.getDefaultToolkit().getSystemClipboard();
			Transferable contents = Board.getContents(Board);
			DataFlavor flavor = DataFlavor.stringFlavor;
			if(contents.isDataFlavorSupported(flavor))
				Paste.setEnabled(true);
			else
				Paste.setEnabled(false);
			if(getSelectedText()!=null){
				Cut.setEnabled(true);
				Copy.setEnabled(true);
				Delete.setEnabled(true);
			}
			else{
				Cut.setEnabled(false);
				Copy.setEnabled(false);
				Delete.setEnabled(false);
			}
			if(getText().isEmpty()){
				SelectAll.setEnabled(false);
				FindAndReplace.setEnabled(false);
			}
			else{
				SelectAll.setEnabled(true);
				FindAndReplace.setEnabled(true);
			}
			Popup.show(this,e.getX(),e.getY());
		}
	}
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==menu.WordWrap){
			setLineWrap(menu.WordWrap.isSelected());
			menu.Truncation.setEnabled(menu.WordWrap.isSelected());
		}
		else
			setWrapStyleWord(!menu.Truncation.isSelected());
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void menuCanceled(MenuEvent e) {}
	public void menuDeselected(MenuEvent e) {}
}
