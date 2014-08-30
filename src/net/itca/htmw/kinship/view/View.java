package net.itca.htmw.kinship.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

import net.itca.htmw.kinship.controllers.ViewController;
import net.itca.htmw.kinship.core.Bulletin;
import net.itca.htmw.kinship.interfaces.Observer;

public class View extends JFrame implements Observer
{
	private JPanel memoryLabels;
	private JPanel memoryLists;
	private JList<String> longTerm;
	private JList<String> shortTerm;
	private JList<String> goal;
	private DefaultListModel LTListModel;
	private DefaultListModel STListModel;
	private DefaultListModel goalListModel;
	private ViewController control;
	private Bulletin bulletin = Bulletin.getBulletin();
	public View(ViewController vc)
	{
		control = vc;
		setup();
	}
	
	private void setup()
	{
		this.setTitle("Bulletin board");
		this.setSize(450,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		JLabel ltMem = new JLabel("Long-term memory");
		JLabel stMem = new JLabel("Short-term memory");
		JLabel goals = new JLabel("Goals");
		memoryLabels = new JPanel();
		memoryLabels.setLayout(new GridLayout(1,3));
		memoryLabels.add(ltMem);memoryLabels.add(stMem);memoryLabels.add(goals);
		
		setupLists();
		this.add(memoryLabels, BorderLayout.NORTH);
		this.add(memoryLists, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	private void setupLists()
	{
		LTListModel = new DefaultListModel();
		STListModel = new DefaultListModel();
		goalListModel = new DefaultListModel();
		
		
		longTerm = new JList(LTListModel);
		shortTerm = new JList(STListModel);
		goal = new JList(goalListModel);
		memoryLists = new JPanel();
		memoryLists.setLayout(new GridLayout(1,3));
		memoryLists.add(longTerm); memoryLists.add(shortTerm); memoryLists.add(goal);
		
	}

	/**
	 * Actualise the graphics.
	 */
	@Override
	public void update()
	{
		// Update the listmodels.
		// Fetch the data from the controller
		ArrayList<String> LTMemory = bulletin.getLongTermMemory();
		for(String memory : LTMemory)
		{
			if(!LTListModel.contains(memory))
			{
				LTListModel.addElement(memory);
				
				// Sleep based on a "debug" boolean?
				try
				{
					//Thread.sleep(100);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
		// Do same for other memory areas.
	}
	
	
}
