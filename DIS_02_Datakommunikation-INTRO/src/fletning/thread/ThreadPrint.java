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
			long start = System.nanoTime();
			long threeSec = 3000000000L;
			boolean ran = false;
			while (!ran) {
				long current = System.nanoTime();
				if (current - start >= threeSec) {
					System.out.println(c.getString());
					c.reset();
					ran = true;
				}
			}
		}
	}
}
