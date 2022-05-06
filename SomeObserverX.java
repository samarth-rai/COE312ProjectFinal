public class SomeObserverX extends AbstractObserver implements Runnable {
	//this is a typical class of an observer.
	//add the topic checks and the actions here.
	public SomeObserverX(AbstractSubject[] subjects) {
		super(subjects);
		Thread t = new Thread(this);
		t.start();
	}
	public void update(Message m) {
		System.out.println("received a "+ m.payload+" message from "+m.origin);
		if(m.topic=="time") {
		// do nothing
		}
		else if(m.topic =="speaking") {
		System.out.println("some one is speaking.");
		}
		}
	@Override
	public void run() {
		while(true) {
		// do nothing because the message will be automatically printed
		// through update.
		}
	}
	
}