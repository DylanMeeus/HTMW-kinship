package net.itca.htmw.kinship.interfaces;

public interface Observable
{
	public void addObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers(); // Notify all observers. All 'Demons' in this case always need to get update.
}
