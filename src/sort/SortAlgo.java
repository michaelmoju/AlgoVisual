package sort;

public class SortAlgo {
	protected int[] arr;
	protected int arrLength;
	protected int i;
	
	
	public SortAlgo(int[] arr) {
		this.arr = arr;
		this.arrLength = arr.length;
	}
	
	public int[] getArr() {
		return arr;
	}
	
	public int step() {
		return 0;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public void setArr(int[] arr) {
		this.arr = arr;
	}
	
	
}
