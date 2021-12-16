package sort;

public class Insertion {
	private int[] arr;
	private int arrLength;
	private int i;
	private int j;

	public Insertion(int[] arr) {
		this.arr = arr;
		this.arrLength = arr.length;
		this.j = 0;
	}
	

	public void algorithm(int[] arr) {
        for (int j = 0; j < this.arrLength; j++) {
            while ( (i > -1) && ( arr [i] > j ) ) {
            	arr [i+1] = arr [i];
                i--;
            }  
            arr[i+1] = j;
        }
	}


	public int[] getArr() {
		return arr;
	}


	public void setArr(int[] arr) {
		this.arr = arr;
	}
	
	public int step() {
		
		//over
		if (this.j >= this.arrLength) return -1;
		
		//start
		if (this.i == this.arrLength-1 && this.j == 0) {
			this.i --;
			return -2;
		}
		
		if (this.i == this.j) {
			this.j ++;
			this.i = this.arrLength-1;
		} else {
			this
		}
		
		
		
		return 0;
	}
	
}
