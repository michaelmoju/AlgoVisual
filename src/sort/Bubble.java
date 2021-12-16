package sort;

public class Bubble extends SortAlgo{
	private int j;

	public Bubble(int[] arr) {
		super(arr);
		this.j = 0;
		this.i = this.arrLength-1;
	}
	

//	public void algorithm(int[] arr) {
//        for (int j = 0; j < this.arrLength; j++) {
//            while ( (this.i > -1) && ( arr [i] > j ) ) {
//            	arr [i+1] = arr [i];
//                i--;
//            }  
//            arr[i+1] = j;
//        }
//	}

	
	public int step() {
		System.out.println("bubble step");
		System.out.println("j = " + this.j);
		System.out.println("i = " + this.i);
		
		//over
		if (this.j >= this.arrLength) return -1;
		
		//start
		if ((this.i == this.arrLength-1) && (this.j == 0)) {
			this.i--;
			return -2;
		}
		
		if (this.i == this.j) {
			this.j ++;
			this.i = this.arrLength-1;
		} else {
			if (this.arr[this.i-1] > this.arr[this.i]) {
				int temp = this.arr[this.i];
				this.arr[this.i] = this.arr[this.i-1];
				this.arr[this.i-1] = temp;
				this.i --;
			} else {
				this.i --;
			}
		}
		return 0;
	}
	
}
