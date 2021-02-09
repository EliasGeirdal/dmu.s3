package fletning.test;

import fletning.common.Common;
import fletning.thread.ThreadPrint;
import fletning.thread.ThreadRead;

public class Opgave_01_test {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Game begun.. Type something");
		Common c = new Common();
		ThreadPrint print = new ThreadPrint(c);
		ThreadRead read = new ThreadRead(c);
		print.start();
		read.start();
	}
}
