package controller;

import javafx.application.Platform;
import ringprogress.RingProgressIndicator;

public class threadForRingProgress extends Thread{
	RingProgressIndicator progress ;
	int i =0;
	int max ;
	public threadForRingProgress(RingProgressIndicator progress , int max) {
		this.progress = progress;
		this.max = max;
	}
	public void run(){
		
		while(max+1 != i) {			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Platform.runLater(()->{
				progress.setProgress(i);
			});
			i++;
		}
	}

}
