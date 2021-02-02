package fletning;

import java.util.List;

public class ThreadSort extends Thread {
	private FletteSortering sort;
	private List<Integer> list;
	private int startIndex, slutIndex;

	public ThreadSort(FletteSortering f, List<Integer> list, int start, int slut) {
		sort = f;
		this.list = list;
		startIndex = start;
		slutIndex = slut;
	}

	@Override
	public void run() {
		try {
			sort.mergesort(list, startIndex, slutIndex);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
