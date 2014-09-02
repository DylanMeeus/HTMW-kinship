package net.itca.htmw.kinship.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

import net.itca.htmw.kinship.controllers.ViewController;
import net.itca.htmw.kinship.core.Bulletin;
import net.itca.htmw.kinship.interfaces.Observer;

/**
 * GUI for the program.
 * @author Dylan
 *
 */
public class View extends JFrame implements Observer
{
	private static final long serialVersionUID = 1L;
	private JPanel memoryLabels;
	private JPanel memoryLists;
	private JPanel buttons;
	private JList<String> longTerm;
	private JList<String> shortTerm;
	private JList<String> goal;
	private DefaultListModel LTListModel;
	private DefaultListModel STListModel;
	private DefaultListModel goalListModel;
	private ViewController control;
	private Bulletin bulletin = Bulletin.getBulletin();
	private JButton prevGen;
	private JButton nextGen;
	private int generation; 
	private ArrayList<Snapshot> snapshots;
	public View(ViewController vc)
	{
		snapshots = new ArrayList<Snapshot>();
		control = vc;
		generation = 0;
		setup();
	}
	
	private void setup()
	{
		this.setTitle("Bulletin board");
		this.setSize(450,500);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		JLabel ltMem = new JLabel("Long-term memory");
		JLabel stMem = new JLabel("Short-term memory");
		JLabel goals = new JLabel("Goals");
		memoryLabels = new JPanel();
		memoryLabels.setLayout(new GridLayout(1,3));
		memoryLabels.add(ltMem);memoryLabels.add(stMem);memoryLabels.add(goals);
		
		setupLists();
		
		prevGen = new JButton("<-");
		nextGen = new JButton("->");
		buttons = new JPanel();
		buttons.setLayout(new GridLayout(1,2));
		addActionListeners();
		buttons.add(prevGen); buttons.add(nextGen);
		this.add(memoryLabels, BorderLayout.NORTH);
		this.add(memoryLists, BorderLayout.CENTER);
		this.add(buttons,BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	private void populateView(int gen)
	{
		DefaultListModel one = new DefaultListModel();
		DefaultListModel two = new DefaultListModel();
		DefaultListModel three = new DefaultListModel();
		for(Snapshot snapshot : snapshots)
		{
			if(snapshot.getGeneration()==gen)
			{
				System.out.println("type: " + snapshot.getType());
				switch(snapshot.getType())
				{
					case 0: for(int i = 0; i < snapshot.getMemories().getSize()-1;i++)
							{
								one.addElement(snapshot.getMemories().elementAt(i));
							}
					break;
					case 1: for(int i = 0; i < snapshot.getMemories().getSize()-1;i++)
							{
								two.addElement(snapshot.getMemories().elementAt(i));
							}
					break;
					case 2: for(int i = 0; i < snapshot.getMemories().getSize()-1;i++)
							{
								three.addElement(snapshot.getMemories().elementAt(i));
							}
					break;
				}
			}
		}
		LTListModel.removeAllElements();
		STListModel.removeAllElements();
		goalListModel.removeAllElements();
		
		for(int i = 0; i < one.getSize()-1;i++)
		{
			LTListModel.addElement(one.get(i));
		}
		for(int i = 0; i < two.getSize()-1;i++)
		{
			STListModel.addElement(two.get(i));
		}
		for(int i = 0; i < three.getSize()-1;i++)
		{
			goalListModel.addElement(three.get(i));
		}

	}
	
	private void addActionListeners()
	{
		prevGen.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				populateView(generation-1);
			}
		});
		
		nextGen.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				populateView(generation++);
			}
		});
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
		generation++;
		// Update the listmodels.
		// Fetch the data from the controller
		ArrayList<String> LTMemory = bulletin.getLongTermMemory();
		ArrayList<String> STMemory = bulletin.getShortTermMemory();
		
		// Remove current data, to update it with the new data.
		try
		{
	//		Thread.sleep(100);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		LTListModel.removeAllElements();
		STListModel.removeAllElements();
		goalListModel.removeAllElements();
		for(String memory : LTMemory)
		{
			if(!LTListModel.contains(memory))
			{
				LTListModel.addElement(memory);
			}
		}
		try
		{
	//		Thread.sleep(100);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		for(String memory : STMemory)
		{
			if(!STListModel.contains(memory))
			{
				STListModel.addElement(memory);
			}
		}
		
		ArrayList<String> goals = bulletin.getGoals();
		
		for(String goal : goals)
		{
			if(!goalListModel.contains(goal))
			{
				goalListModel.addElement(goal);
			}
		}
		// Save a "screenshot" of the current memory state (for cycling through it)
		
		
		snapshots.add(new Snapshot(generation, 0, LTListModel));
		snapshots.add(new Snapshot(generation,1,STListModel));
		snapshots.add(new Snapshot(generation,2,goalListModel));
		
	}	
}
