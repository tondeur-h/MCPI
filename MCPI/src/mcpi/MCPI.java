package mcpi;

import java.util.*;

public class MCPI extends Thread{
	public static void main(String[] args) {
		choosing = new boolean[10];
		num = new int[10];
		for(int i = 0; i < 10; i++) {
			num[i] = 0;
			new MCPI(i).start();
		}
	}

	public MCPI(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		double x, y;
		do {
			x = rand.nextDouble();											//ASSIGN X AND Y A RANDOM VALUE BETWEEN 0.0 TO 1.0
			y = rand.nextDouble();

			choosing[this.id] = true;
			num[this.id] = max();
			choosing[this.id] = false;
			
			CNT++;
			for(int j = 0; j < 10; j++) {
				while(choosing[j]) {}
				while(num[j] > 0 && ((num[j] < num[this.id]) || (num[j] == num[this.id]) && (j < this.id))) {} 
			}	
			
			if(x*x + y*y <= 1) INARC++;
			if(CNT % 100 == 0) System.out.println(this.id+" - "+(double)INARC/CNT*4);

			num[this.id] = 0;
		}while(true);	
	}

	public int max() {
		int maxVal = 0;
		for(int i = 0; i < num.length; i++) 
			maxVal = num[i] > maxVal ? num[i] : maxVal;

		return maxVal;
	}

	public static Random rand = new Random();
	public static int CNT = 0;
	public static int INARC = 0;
	public static boolean[] choosing;
	public static int[] num;

	public int id;
}