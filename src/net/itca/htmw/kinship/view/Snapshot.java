package net.itca.htmw.kinship.view;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

public class Snapshot
{
	private int generation;
	private int type; // 0 = LT, 1 = ST, 2 = goal
	private DefaultListModel memories;
	
	public Snapshot(int gen, int t, DefaultListModel mems)
	{
		generation = gen;
		type = t;
		memories = mems;
	}
	
	public int getGeneration()
	{
		return generation;
	}
	
	public int getType()
	{
		return type;
	}
	
	public DefaultListModel getMemories()
	{
		return memories;
	}
}
