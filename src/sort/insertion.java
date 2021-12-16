package sort;

public class insertion {
	private int[] arr;
	private int idx;
	private int step;

	public insertion(int[] arr, int step, int idx) {
		this.arr = arr;
		this.idx = idx;
		this.step = step;
	}
	

	public void algorithm(int[] arr) {
		int length = this.arr.length;
		
        for (int j = this.idx; j < length; j++) {
            int key = arr[j];
            int i = j-1;
            while ( (i > -1) && ( arr [i] > key ) ) {
            	arr [i+1] = arr [i];
                i--;
            }  
            arr[i+1] = key;
        }
	}

}
