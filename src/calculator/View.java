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
	
	//��¼���ʽ
	private String expression;

	//button�ַ�
	private final String[] keys = {"��", "CE", "%", "/", "DEL",
								   "e", "7", "8", "9", "��",
								   "��", "4", "5", "6", "*",
								   "(", "1", "2", "3", "-",
								   ")", "0", ".", "=", "+"};

	//button
	private JButton[] button = new JButton[keys.length];
	//����button�����
	private JPanel jp_button = new JPanel();
	
	//text
	private JTextField jtf_text = new JTextField();
	//����text�����
	private JPanel jp_text = new JPanel();
	
	//answer
	private JTextField jtf_answer = new JTextField();
	//����answer�����
	private JPanel jp_answer = new JPanel();
	
	
	
	//View�Ĺ��캯��
	public View()
	{
		
		//���崰�ڲ���
		this.setSize(350, 500);
		this.setTitle("������");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		//����answer���λ��
		jp_answer.setBounds(-5, 10, 350, 50);
		//���answer��壬��Ϊ��ʽ��text�����ֱ����text�ķ�������
		jtf_answer = this.setJtf(jp_answer);
		jtf_answer.setForeground(Color.BLUE);
		
		//�����������λ�ã������޸��ڴ����Ϸ������·�
		jp_text.setBounds(-5, 55, 350, 50);
		//���text���
		jtf_text = this.setJtf(jp_text);
			
		//���button���
		JPanel jp_button_after = this.setButton(jp_button);
		jp_button_after.setBounds(8, 120, 320, 320);
		
		this.setVisible(true);
		
		//�����ð��µ�һ��button������ʾnull����ʾ��������
		expression = "";
		
	}
	
	
	//����text����
	private JTextField setJtf(JPanel jp_text)
	{
		
		//text����
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
	
	
	//����button����
	private JPanel setButton(JPanel jp_button)
	{
		
		JPanel jp_button_after = new JPanel();
		jp_button_after.setLayout(new GridLayout(5, 5, 5, 5));
		
		//ѭ������button
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
	
	
	//�������֣���������У�e
	private void handleExpression(String act)
	{
		expression += act;
		jtf_text.setText(expression);
	}
	
	//����CE��ȫ�������
	private void handleCE()
	{
		expression = "";
		jtf_text.setText(expression);
		jtf_answer.setText(expression);
	}
	
	//����DEL�������һ�����룩
	private void handleDEL()
	{
		expression = expression.substring(0, expression.length() - 1);
		jtf_text.setText(expression);
	}
	
	//����=
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

