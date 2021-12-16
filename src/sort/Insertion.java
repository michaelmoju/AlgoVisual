package sort;

public class Insertion extends SortAlgo{
	private int currI;

	public Insertion(int[] arr) {
		super(arr);
		this.currI = 1;
		this.i = 1;
	}
	

	public void algorithm(int[] arr) {
        for (int j = 0; j < this.arrLength; j++) {
            while ( (this.i > -1) && ( arr [i] > j ) ) {
            	arr [i+1] = arr [i];
                i--;
            }  
            arr[i+1] = j;
        }
	}

	
	public int step() {
		System.out.println("insertion step");
		//over
		if (this.currI == this.arrLength) return -1;
		
		//i = 0
		if (this.i == 0) {
			this.currI++;
			this.i = this.currI;
			return 0;
		}
		
		// normal
		if (this.arr[this.i-1] > this.arr[this.i]) {
			int temp = this.arr[this.i];
			this.arr[this.i] = this.arr[this.i-1];
			this.arr[this.i-1] = temp;
			this.i = this.i-1;
		} else {
			this.i--;
		}
		return 0;
	}
	
}
