import java.util.ArrayList;

public abstract class AbstractSubject implements Subject {

	private ArrayList<Observer> observers = new ArrayList<Observer>();

	public AbstractSubject() {
		observers = new ArrayList<Observer>();
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
}