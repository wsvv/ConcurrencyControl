package ConcurrencyControl;

import java.util.*;
import java.util.concurrent.Semaphore;
import javax.swing.*;


class SemaphoreControllThread extends Thread{
	private String str1;
	private String str2;
	private String str3;
	private JLabel result1;
	private JLabel result2;
	private JLabel result3;
	private JLabel result;
	
	public SemaphoreControllThread(String str1, String str2, String str3, JLabel result1, JLabel result2,JLabel result3, JLabel result) {
		// TODO Auto-generated constructor stub
		this.str1=str1;
		this.str2=str2;
		this.str3=str3;
		this.result1=result1;
		this.result2=result2;
		this.result3=result3;
		this.result=result;
	}

	public void run() {
		Semaphore sem=new Semaphore(-2);
		new SemaphorePrintThread(str1,result1,sem).start();
		new SemaphorePrintThread(str2,result2,sem).start();
		new SemaphorePrintThread(str3,result3,sem).start();
		
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//用于在线程里修改result的值
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				result.setText(result1.getText()+"  "+result2.getText()+"  "+result3.getText());
			}
		});
	}
	
}
