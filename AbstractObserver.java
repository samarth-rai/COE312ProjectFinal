public abstract class AbstractObserver implements Observer {

	private Subject[] subject;

	public AbstractObserver(Subject[] subject2) {
		for (int i = 0; i < subject2.length; i++) {
			this.subject = subject2;
			subject[i].registerObserver(this);
		}
	}

	public AbstractObserver()
	{
		
	}


	@Override
	public abstract void update(Message m);
}