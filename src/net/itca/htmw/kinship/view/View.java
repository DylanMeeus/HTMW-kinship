package net.itca.htmw.kinship.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class View extends JFrame
{

	JPanel memoryLabels;
	JPanel memoryLists;
	public View()
	{
		setup();
	}
	
	private void setup()
	{
		this.setTitle("Bulletin board");
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		JLabel ltMem = new JLabel("Long-term memory");
		JLabel stMem = new JLabel("Short-term memory");
		JLabel goals = new JLabel("Goals");
		memoryLabels = new JPanel();
		memoryLabels.setLayout(new GridLayout(1,3));
		memoryLabels.add(ltMem);memoryLabels.add(stMem);memoryLabels.add(goals);
		
		
		this.add(memoryLabels, BorderLayout.NORTH);
		this.setVisible(true);
	}
	
	
}
