package ConcurrencyControl;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame extends JFrame implements ActionListener{
	//面板一：提示标签 输入框 结果显示框
	private JPanel p1 = new JPanel();
	
	//提示标签
	private JLabel la1=new JLabel("第一个");
	private JLabel la2=new JLabel("第二个");
	private JLabel la3=new JLabel("第三个");
	//输入框
	private JTextField tf1=new  JTextField();
	private JTextField tf2=new  JTextField();
	private JTextField tf3=new  JTextField();
	//结果显示框
	private JLabel result1 = new JLabel();
	private JLabel result2 = new JLabel();
	private JLabel result3 = new JLabel(); 
	
	//总结果显示
	private JLabel result = new JLabel();
	
	//面板二：按钮选择区 
	private JPanel p2 = new JPanel();
	
	//确定和清除按钮
	private JButton ok=new JButton("确定");
	private JButton clear=new JButton("清除");
	private JLabel la4=new JLabel("请选择实现方式：");
	//两种实现方式选择按钮
	private ButtonGroup grp=new ButtonGroup();
	private JRadioButton bsem=new JRadioButton("semaphore");
	private JRadioButton bwn=new JRadioButton("wait、notify");
		
	public MainFrame(){
		super("并发控制实例");
	    //单选按钮的组合
		grp.add(bsem);grp.add(bwn);
		//为标签设置居中显示
		la1.setHorizontalAlignment(JLabel.CENTER);
		la2.setHorizontalAlignment(JLabel.CENTER);
		la3.setHorizontalAlignment(JLabel.CENTER);
		result.setHorizontalAlignment(JLabel.CENTER);
		result1.setHorizontalAlignment(JLabel.CENTER);
		result2.setHorizontalAlignment(JLabel.CENTER);
		result3.setHorizontalAlignment(JLabel.CENTER);
		
		Font font1 = new Font(Font.MONOSPACED, Font.BOLD, 46);
        result.setFont(font1);
        
        //添加标签边框
		result1.setBorder(BorderFactory.createEtchedBorder());
		result2.setBorder(BorderFactory.createEtchedBorder());
		result3.setBorder(BorderFactory.createEtchedBorder());
		result.setBorder(BorderFactory.createEtchedBorder());
		
		//布局
		GridLayout grid1=new GridLayout(3,3);
		p1.setLayout(grid1);
		p1.add(la1);p1.add(la2);p1.add(la3);
		p1.add(tf1);p1.add(tf2);p1.add(tf3);
		p1.add(result1);p1.add(result2);p1.add(result3);
		this.add(p1);
		
		GridLayout grid2=new GridLayout(5,1);
		p2.setLayout(grid2);
		p2.add(ok);
		p2.add(clear);
		p2.add(la4);
		p2.add(bsem);
		p2.add(bwn);
		
		this.getContentPane().add(p1,BorderLayout.NORTH);
		this.getContentPane().add(result,BorderLayout.CENTER);
		this.getContentPane().add(p2,BorderLayout.EAST);
		
		//注册监听者
		ok.addActionListener(this);
		clear.addActionListener(this);
		
		bsem.setActionCommand("sem");
		bwn.setActionCommand("bwn");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(520,230);
	}
	
	//清空输入框
	public void clearInput() {
		tf1.setText("");
		tf2.setText("");
		tf3.setText("");
	}
	
	//清空输出框
	public void clearOutput() {
		result1.setText("");
		result2.setText("");
		result3.setText("");
		result.setText("");
	}
	
	//事件处理
	public void actionPerformed(ActionEvent e) {
		JButton jbt=(JButton)e.getSource();
		//如果按“确定”
		if(jbt==ok) {
			clearOutput();
            String str1=tf1.getText().trim();
    		String str2=tf2.getText().trim();
    		String str3=tf3.getText().trim();
    		//未选择实现方式的弹窗提示
    		if(str1.equals("")|str2.equals("")|str3.equals("")) {
    			JOptionPane.showMessageDialog(null,"请输入完整数据");
    			}
    		
    		String choose=grp.getSelection().getActionCommand();
    		//信号量方式
    		if(choose.equals("sem")) {
    			new SemaphoreControllThread(str1,str2,str3,result1,result2,result3,result).start();
    		}
    		//睡眠唤醒方式
    		else if (choose.equals("bwn")) {
    			new SynchronizedControllThread(str1,str2,str3,result1,result2,result3,result).start();
    		}

		}	
		
		//如果按清空，则两个区域都清空
		else if(jbt==clear) {
			clearInput();
			clearOutput();
		}
	}
}

