package ConcurrencyControl;

import java.util.concurrent.Semaphore;
import javax.swing.*;

public class SemaphorePrintThread extends Thread {
	
	private String str;
	private JLabel result;
	private Semaphore sem;
	
	SemaphorePrintThread(String str,JLabel result,Semaphore sem){
		this.str=str;
		this.result=result;
		this.sem=sem;
		}
	
	public void run() {
		try {
			Thread.sleep(3*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		//用于在线程里修改result的值
		SwingUtilities.invokeLater(new Runnable() {
		public void run() {
			result.setText(str);
			sem.release();
		}
	});
}
}
