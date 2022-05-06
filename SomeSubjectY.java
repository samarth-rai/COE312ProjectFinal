
public class SomeSubjectY extends AbstractSubject implements Runnable{
	SomeSubjectY(){
		Thread t = new Thread(this);
		t.start();
	}
	@Override
	public void run() {
		//here use PublishMessage
		
	}

}
