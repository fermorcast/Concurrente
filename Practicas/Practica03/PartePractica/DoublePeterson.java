public class DoublePeterson {
	private volatile Peterson lock1, lock2, lock3;
	private long[] map_id_threads = new long[4];

	public DoublePeterson() {
		this.lock1 = new Peterson();
		this.lock2 = new Peterson();
		this.lock3 = new Peterson();
		for(int i = 0; i < 4; i++){
			map_id_threads[i] = -1;
	    }
	}

	public void lock() {
		Thread currentThread = Thread.currentThread();
		long id = currentThread.getId();
		int me = (int) (id % 4);
		if (me == 0 || me == 1) {
		    lock1.lock();
		} else if (me == 2 || me == 3) {
		    lock2.lock();
		}
		lock3.lock();

	}

	public void unlock() {
		Thread currentThread = Thread.currentThread();
		long id = currentThread.getId();
		int me = (int) (id % 4);
		if (me == 0 || me == 1) {
			lock1.unlock();
		} else if (me == 2 || me == 3) {
			lock2.unlock();
		}
		lock3.unlock();
	}
}
