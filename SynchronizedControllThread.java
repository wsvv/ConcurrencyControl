package ConcurrencyControl;
import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SynchronizedControllThread extends Thread{
    private String str1;
    private String str2;
    private String str3;
    private JLabel result1;
    private JLabel result2;
    private JLabel result3;
    private JLabel result;

    public SynchronizedControllThread(String str1, String str2, String str3, JLabel result1, JLabel result2,JLabel result3, JLabel result) {
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
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        Object lock=new Object();
        new SynchronizedPrintThread(str1,result1,lock).start();
        new SynchronizedPrintThread(str2,result2,lock).start();
        new SynchronizedPrintThread(str3,result3,lock).start();

        while(result1.getText().isEmpty()||
                result2.getText().isEmpty()||
                result3.getText().isEmpty()){
            synchronized (lock){
                try{
                    lock.wait();
                }catch (InterruptedException e){}
            }
        }


        //用于在线程里修改result的值
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                result.setText(result1.getText()+"  "+result2.getText()+"  "+result3.getText());
            }
        });
    }
}
