package fletning.test;

import fletning.common.Common;
import fletning.thread.ThreadPrint;
import fletning.thread.ThreadRead;

public class Opgave_01_test {
	public static void main(String[] args) throws InterruptedException {
		Common c = new Common();
		ThreadPrint print = new ThreadPrint(c);
		ThreadRead read = new ThreadRead(c);
		print.start();
		read.start();
		print.join();
		read.join();
		System.out.println(c.getHistory());
	}
}
