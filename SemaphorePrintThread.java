package ConcurrencyControl;

import java.util.concurrent.Semaphore;
import javax.swing.*;

public class SemaphorePrintThread extends Thread {
	
	private String str;		//保存文本
	private JLabel result;	//文本框对象
	private Semaphore sem; 		//信号量
	
	SemaphorePrintThread(String str,JLabel result,Semaphore sem){
		this.str=str;
		this.result=result;
		this.sem=sem;
		}
	
	public void run() {
		try {
			Thread.sleep((long)(Math.random()*10000+1000));		//随机延时1-10秒
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		//用于在线程里修改result的值
		SwingUtilities.invokeLater(new Runnable() {
		public void run() {
			result.setText(str);	//修改文本框的内容
			sem.release();
		}
	});
}
}
