package notepad;

import java.awt.TextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class FindAndReplace {
	public JDialog Dialog;
	public JButton FindNext, Replace, ReplaceAll, Finish;
	public JCheckBox MatchCase;
	public JRadioButton Up, Down;
	public ButtonGroup DirectionGroup;
	public JLabel FindWhat, ReplaceWith, Direction;
	public TextField FindText, ReplaceText;

	FindAndReplace(JFrame notepad) {
		Dialog = new JDialog(notepad, "Find And Replace...", false);
		/*
		 * 与EnsureDialog不同的是 ， 这里的模式标志false使对话框始终处于notepad的上端 ， 但点击notepad
		 * 时notepad会继续处于活动状态 ， 对话框则变成不活动状态
		 */
		FindNext = new JButton("Find Next");
		Replace = new JButton("Replace");
		ReplaceAll = new JButton("Replace All");
		Finish = new JButton("Finish");
		MatchCase = new JCheckBox("Match Case", false);
		Down = new JRadioButton("Down", true);
		Up = new JRadioButton("Up", false);
		FindWhat = new JLabel("Find What:");
		ReplaceWith = new JLabel("Replace With:");
		Direction = new JLabel("Direction:");
		FindText = new TextField("");
		ReplaceText = new TextField("");
		Dialog.setSize(380, 160);
		Dialog.setResizable(false);
		FindNext.setFocusable(false);
		Replace.setFocusable(false);
		ReplaceAll.setFocusable(false);
		MatchCase.setFocusable(false);
		Finish.setFocusable(false);
		Up.setFocusable(false);
		Down.setFocusable(false);
		DirectionGroup = new ButtonGroup();
		Dialog.setLayout(null);
		FindWhat.setBounds(10, 12, 80, 22);
		ReplaceWith.setBounds(10, 42, 80, 22);
		FindText.setBounds(95, 12, 160, 22);
		ReplaceText.setBounds(95, 42, 160, 22);
		FindNext.setBounds(265, 12, 98, 22);
		Replace.setBounds(265, 42, 98, 22);
		ReplaceAll.setBounds(265, 72, 98, 22);
		Direction.setBounds(10, 72, 80, 22);
		MatchCase.setBounds(6, 102, 98, 22);
		Down.setBounds(95, 72, 80, 22);
		Up.setBounds(175, 72, 80, 22);
		Finish.setBounds(265, 102, 98, 22);
		DirectionGroup.add(Up);
		DirectionGroup.add(Down);
		Dialog.add(FindWhat);
		Dialog.add(MatchCase);
		Dialog.add(FindText);
		Dialog.add(FindNext);
		Dialog.add(Direction);
		Dialog.add(ReplaceWith);
		Dialog.add(ReplaceText);
		Dialog.add(Replace);
		Dialog.add(ReplaceAll);
		Dialog.add(Finish);
		Dialog.add(Down);
		Dialog.add(Up);
	}
}
