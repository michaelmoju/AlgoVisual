package sort;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import server.ClientRequest;
import server.ServerResponse;

public class SortPanel extends JPanel{
	public final int MAX_VALUE = 100;
	public final int MIN_VALUE = 0;
	private int arrLength;
	private String algo;
	private JLabel arrText = new JLabel();
	private int[] initArr = new int[arrLength];
	private int[] arr = new int[arrLength];
	private JButton StepButton;
	private SortAlgo sortAlgo;
	private JLabel message;


	
	public SortPanel(String algo, int arrLength) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.algo = algo;
		this.arrLength = arrLength;
		initArr = new int[arrLength];
		arr = new int[arrLength];
		this.setSize(700,200);
		
		createButton();
		
		// set initial random array
		setRandomArr();
		setArrText();
		this.add(arrText, BorderLayout.CENTER);
		switch(algo) {
		case "insertion": 
			sortAlgo =  new Insertion(this.arr);
			break;
		case "bubble":
			sortAlgo = new Bubble(this.arr);
			break;
		}
		
		//set message
		message = new JLabel(this.algo + ": " + "Press Next Step");
		this.add(message, BorderLayout.SOUTH);
		
	}
	
	private void setRandomArr() {
		for (int i=0; i<this.arrLength; i++) {
			this.arr[i] = MIN_VALUE + (int)(Math.random() * (MAX_VALUE-MIN_VALUE));	
		}
		initArr = arr.clone();
	}
	
	private void setArrText() {
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < this.arrLength; i++) {
			String intString = Integer.toString(arr[i]);
			if (i == this.arrLength-1) builder.append('[' + intString + ']');
			else builder.append('[' + intString + "] - ");
		}
		
		arrText.setText(builder.toString());
		System.out.println(builder.toString());
	}
	
	private void setArrText(int changedIdx) {
		StringBuilder builder = new StringBuilder();
		builder.append("<html>");
		
		for (int i = 0; i < this.arrLength; i++) {
			String intString;
			if (i == changedIdx) intString = "<font color='red'>" + Integer.toString(arr[i]) + "</font>";
			else intString = Integer.toString(arr[i]);
			if (i == this.arrLength-1) builder.append('[' + intString + ']');
			else builder.append('[' + intString + "] - ");
		}
		
		builder.append("</html>");
		arrText.setText(builder.toString());
//		System.out.println(builder.toString());
	}
	
	public void over() {
		message.setText(this.algo + ": " +"Algorithm Done!!");
	}
	

	
	private void createButton() {
		class StepListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				step();					
			} 
		}	
		
		StepButton = new JButton("Next Step");
		StepButton.addActionListener(new StepListener());
		
		this.add(StepButton);
	}
	
	public void step() {
		int status;
		
		switch(this.algo) {
		case "insertion": 
			status = sortAlgo.step();
			if (status == -1) {
				this.arr = sortAlgo.getArr();
				setArrText();
				over();
			} else {
				this.arr = sortAlgo.getArr();
				setArrText(sortAlgo.getI());
			}
			break;
		case "bubble":
			status = sortAlgo.step();
			System.out.println(status);
			if (status == -1) {
				this.arr = sortAlgo.getArr();
				setArrText();
				over();
			} else {
				this.arr = sortAlgo.getArr();
				setArrText(sortAlgo.getI());
			}
			break;
		}
	
	}
}
