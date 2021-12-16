package sort;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SortPanel extends JPanel{
	public final int MAX_VALUE = 100;
	public final int MIN_VALUE = 0;
	private int arrLength;
	private String algo;
	private JLabel arrText = new JLabel();
	private int[] initArr = new int[arrLength];
	private int[] arr = new int[arrLength];
//	private int[] changedList = new ArrayList<>();

	
	public SortPanel(String algo, int arrLength) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.algo = algo;
		this.arrLength = arrLength;
		initArr = new int[arrLength];
		arr = new int[arrLength];
		this.setSize(700,200);
		
		// set initial random array
		setRandomArr();
		setArrText();
		this.add(arrText, BorderLayout.CENTER);
		
	}
	
	private void setRandomArr() {
		for (int i=0; i<this.arrLength; i++) {
			this.arr[i] = MIN_VALUE + (int)(Math.random() * (MAX_VALUE-MIN_VALUE));	
		}
		initArr = arr.clone();
	}
	
	private void setArrText() {
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < this.arrLength-1; i++) {
			builder.append('[' + Integer.toString(arr[i]) + "] - ");
		}
		
		// last integer
		builder.append('[' + Integer.toString(arr[arrLength-1]) + ']');
		
		arrText.setText(builder.toString());
	}
	
	
	public void play() {
		
	}
	
	public void reset() {
		
	}
	
	public static void main(String[] args) {
		SortPanel sortPanel = new SortPanel("test", 10);
		sortPanel.setVisible(true);
	}
}
