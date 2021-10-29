package calculator;

import java.util.Stack;

public class Calculate {

	//�洢��������ջ
	static Stack<Character> op = new Stack<>();
	
	//����׺���ʽת��Ϊ��׺���ʽ
	public static String getsuffix(String s)
	{
		char[] arr = s.toCharArray();
		String result = "";
		
		int i;
		for(i = 0; i < arr.length; i++)
		{
			char ch = arr[i];
			
			if(ch == ' ')
			{
				continue;
			}
			
			//����
			if(ch >= '0' && ch <= '9')
			{
				result += ch;
				continue;
			}
			
			//�У�e
			if(ch == '��' || ch == 'e')
			{
				result += ch;
				continue;
			}
			
			//С����.
			if(ch == '.')
			{
				result += ch;
				continue;
			}
			
			//�����
			if(ch == '+' || ch == '-')
			{
				while(!op.empty() && ((op.peek() != '(')|| (op.peek() == '��')))
				{
					result += op.pop();
				}
				op.push(ch);
				continue;
			}
			
			if(ch == '*' || ch == '/' || ch == '%' || ch == '��')
			{
				while(!op.empty() && (op.peek() == '*' || op.peek() == '/' || op.peek() == '%' || op.peek() == '��' || op.peek() == '��'))
				{
					result += op.pop();
				}
				op.push(ch);
				continue;
			}
			
			//��
			if(ch == '��')
			{
				op.push(ch);
				continue;
			}
			
			//()
			if(ch == '(')
			{
				op.push(ch);
				continue;
			}
			
			if(ch == ')')
			{
				while(!op.empty() && op.peek() != '(')
				{
					result += op.pop();
				}
				op.pop();
				continue;
			}
			
		}
		
		while(!op.empty())
		{
			result += op.pop();
		}
		
		return result;
	}
	
	
	//��������
	public static Float calFund(char op, Float f1, Float f2)
	{
		if(op == '+')
		{
			return f2 + f1;
		}
		else if(op == '-')
		{
			return f2 - f1;
		}
		else if(op == '*')
		{
			return f2 * f1;
		}
		else if(op == '/')
		{
			String s = String.valueOf(f2 / f1);
			String str[] = s.split("\\.");
			Float f = Float.valueOf(str[0]);
			return f;
		}
		else if(op == '%')
		{
			return f2 % f1;
		}
		else if(op == '��')
		{
			return f2 / f1;
		}
		else
		{
			return Float.valueOf(-0);
		}
	}
	
	//��������
	public static Float calExtract(Float f)
	{
		return (float) Math.sqrt(f);
	}
	
	//�����׺���ʽ
	public static String calSuffix(String suffix)
	{
		Stack<Float> v = new Stack<>();
        char[] arr = suffix.toCharArray();
        
        int i;
        for(i = 0; i < arr.length; i++)
        {
            Character ch = arr[i];
  
            if(ch >= '0' && ch <= '9')
            {
            	v.push(Float.valueOf(ch - '0'));
            }
            else if(ch == '.')
            {
            	int j = i + 1;
            	String str = "0.";
            	str += arr[j];	
            	Float num = Float.valueOf(str);
            	v.push(calFund('+', v.pop(), num));
            	i = j;
            }
            else if(ch == '��')
            {
            	v.push(Float.valueOf((float) 3.14159265));
            }
            else if(ch == 'e')
            {
            	v.push(Float.valueOf((float) 2.71828182));
            }
            else if(ch == '��')
            {
            	v.push(calExtract(v.pop()));
            }
            else
            {
            	v.push(calFund(ch, v.pop(), v.pop()));
           	}
         }
         
        return v.pop().toString();
	}
	
	
}
