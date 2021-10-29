package calculator;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class View extends JFrame implements ActionListener{

	
	private static final long serialVersionUID = 1L;
	
	//记录表达式
	private String expression;

	//button字符
	private final String[] keys = {"π", "CE", "%", "/", "DEL",
								   "e", "7", "8", "9", "÷",
								   "√", "4", "5", "6", "*",
								   "(", "1", "2", "3", "-",
								   ")", "0", ".", "=", "+"};

	//button
	private JButton[] button = new JButton[keys.length];
	//容纳button的面板
	private JPanel jp_button = new JPanel();
	
	//text
	private JTextField jtf_text = new JTextField();
	//容纳text的面板
	private JPanel jp_text = new JPanel();
	
	//answer
	private JTextField jtf_answer = new JTextField();
	//容纳answer的面板
	private JPanel jp_answer = new JPanel();
	
	
	
	//View的构造函数
	public View()
	{
		
		//定义窗口参数
		this.setSize(350, 500);
		this.setTitle("计算器");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		//定义answer面板位置
		jp_answer.setBounds(-5, 10, 350, 50);
		//添加answer面板，因为样式与text面板差不多直接用text的方法构造
		jtf_answer = this.setJtf(jp_answer);
		jtf_answer.setForeground(Color.BLUE);
		
		//定义面板所在位置，可以修改在窗口上方还是下方
		jp_text.setBounds(-5, 55, 350, 50);
		//添加text面板
		jtf_text = this.setJtf(jp_text);
			
		//添加button面板
		JPanel jp_button_after = this.setButton(jp_button);
		jp_button_after.setBounds(8, 120, 320, 320);
		
		this.setVisible(true);
		
		//不设置按下第一个button会先显示null再显示输入内容
		expression = "";
		
	}
	
	
	//构造text函数
	private JTextField setJtf(JPanel jp_text)
	{
		
		//text参数
		JTextField jtf_text = new JTextField("0");
		jtf_text.setColumns(20);
		jtf_text.setHorizontalAlignment(JTextField.RIGHT);
		jtf_text.setForeground(Color.black);
		jtf_text.setFont(new Font("SansSerif", Font.PLAIN, 16));
		jtf_text.setEditable(false);
		jp_text.add(jtf_text);
		
		this.add(jp_text);
		
		return jtf_text;
		
	}
	
	
	//构造button函数
	private JPanel setButton(JPanel jp_button)
	{
		
		JPanel jp_button_after = new JPanel();
		jp_button_after.setLayout(new GridLayout(5, 5, 5, 5));
		
		//循环创建button
		int i;
		for(i = 0; i < keys.length; i++)
		{
			button[i] = new JButton(keys[i]);
			button[i].setFont(new Font("SansSerif", Font.PLAIN, 13));
			jp_button_after.add(button[i]);
			button[i].addActionListener(this);
		}
		
		this.add(jp_button_after);
		
		return jp_button_after;
	}
	
	
	//按下数字，运算符，π，e
	private void handleExpression(String act)
	{
		expression += act;
		jtf_text.setText(expression);
	}
	
	//按下CE（全部清除）
	private void handleCE()
	{
		expression = "";
		jtf_text.setText(expression);
		jtf_answer.setText(expression);
	}
	
	//按下DEL（清除上一步输入）
	private void handleDEL()
	{
		expression = expression.substring(0, expression.length() - 1);
		jtf_text.setText(expression);
	}
	
	//按下=
	private void handleEqual()
	{
		jtf_answer.setText(Calculate.calSuffix(Calculate.getsuffix(expression)));
		expression = "";
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String act = e.getActionCommand();
		
		if(act.equals(keys[1]))
		{
			handleCE();
		}
		else if(act.equals(keys[4]))
		{
			handleDEL();
		}
		else if(act.equals(keys[23]))
		{
			handleEqual();
		}
		else
		{
			handleExpression(act);
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		View v = new View();
		
	}

}

