import java.util.ArrayList;

//class that is both an observer and a subject

public abstract class AbstractObserverSubject implements Subject, Observer {

	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private Subject[] subject;

	public AbstractObserverSubject()
	{
		
	}
	public AbstractObserverSubject(Subject[] subjects) {
		for (int i = 0; i < subjects.length; i++) {
			this.subject = subjects;
			subject[i].registerObserver(this);
		}
	}

	public void registerObserver(Observer o) {
		observers.add(o);
	}

	public void removeObsever(Observer o) {
		int i = observers.indexOf(o);
		if (i >= 0)
			observers.remove(i);
	}

	@Override
	public void publishMessage(Message m) {
		for (int i = 0; i < observers.size(); i++) {
			Observer observer = (Observer) observers.get(i);
			observer.update(m);
		}
	}

	@Override
	public abstract void update(Message m);
}
