package fletning.thread;

import fletning.common.Common;

public class ThreadPrint extends Thread {
	private Common c;

	public ThreadPrint(Common common) {
		c = common;
	}

	@Override
	public void run() {
		while (true) {
			try {
				sleep(3000);
				if (!c.isEmpty()) {
					System.out.println(c.getString());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
