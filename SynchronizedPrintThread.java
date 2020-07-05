package ConcurrencyControl;

import javax.swing.*;

public class SynchronizedPrintThread extends Thread{
    private String str;     //保存文本
    private JLabel result;      //文本框对象
    static private Object lock;     //lock对象

    SynchronizedPrintThread(String str,JLabel result,Object lock){
        this.str=str;
        this.result=result;
        this.lock=lock;

    }

    
	public void run(){
        try{
            Thread.sleep((long)(Math.random()*10000+1000));     //随机延时1-10秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                result.setText(str);        //修改文本框的内容
                synchronized (lock){
                    lock.notify();
                }
            }
    });
    }

}