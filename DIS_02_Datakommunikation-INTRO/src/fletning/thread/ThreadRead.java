package fletning.thread;

import java.util.Scanner;

import fletning.common.Common;

public class ThreadRead extends Thread {
	private Common c;

	public ThreadRead(Common cmn) {
		c = cmn;
	}

	@Override
	public void run() {
		Scanner scan = new Scanner(System.in);
		while (scan.hasNext()) {
			c.setString(scan.nextLine());
		}
	}
}