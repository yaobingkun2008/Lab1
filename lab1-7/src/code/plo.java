package code;
import java.util.Scanner;


class InputShizi{//�߽���1
	
	private String expression;
	public void inputexpression()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Input Your Polynomial:");
		this.expression = input.nextLine();
	}
	public void setexpression(String expression)
	{
		this.expression = expression;
	}
	public String toidentify()
	{
		return this.expression;
	}
}

class InputCommand{//�߽���2
	
	private String command;
	public void inputcommand()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Input Your Command:");
		this.command = input.nextLine();
	}
	public void setcommand(String command)
	{
		this.command = command;
	}
	public String toidentify()
	{
		return this.command;
	}
}
class command{
	private String com;

	public String getCom() {
		return com;
	}

	public void setCom(String com) {
		this.com = com;
	}
	
}
class RR{//������ʶ��
	private String expression;
	private String command;
	public String recognize()//ʶ��������ı��ʽ�Ƿ�Ϸ��������ݽ����ȡһϵ�еĴ�ʩ
	{
		if(this.expression.matches("((\\d+|[a-z])[*+])*(\\d+|[a-z])")){//������ʽ�������
			if(this.command.matches("!simplify[\\s]([\\w]=[\\d+])+"))//���뻯�����
			{
				return "huajian";
			}
			else if(this.command.matches("!d/d[\\w]"))
			{
				return "qiudao";
			}
			else
			{
				return "error!";
			}
		}
		else
		{
			return "error!";
		}
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
}

class Shizi{//���ʽ��ֵ
	private String caozuo;
	private String plo;
	private String command;
	//private String end;

	public String operation()//operation�Ľ�������������error������ȷ�ı��ʽ
	{
		if(this.caozuo.equals("huajian"))
		{
			return simplify();
		}
		else if(this.caozuo.equals("qiudao"))
		{
			return derivative();
		}
		else
		{
			return "Error";
		}
	}

	public static void movechar(char a[],char t,int in,int length)
	{
		for(int c=length-1;c>=in+1;c--)
		{
			a[c+1] = a[c];
 		}
		a[in+1] = t;
	}
	
	public String simplify()//����
	{
		//boolean finish = false;
		char[] temp = new char[100];//���������飬���ڴ洢��ʽ
		int length =this.plo.length();
		this.plo.getChars(0,this.plo.length(),temp,0);
		boolean error = false;
		if(command.length()==0||command.length()<=9)
		{
			return "Error";
		}
	    for(String a:command.split("\\s"))//������տո�ָ�
	    {
	      if(a.length()!=9)//�����������Ǹ�ֵ��3
	      {
	        if((Character.isLetter(a.charAt(0)) && a.length()>=3)&&(a.charAt(1)=='='))//�����ֵ�������4
			{
			  int found = 0;//������û���ҵ���Ӧ�Ķ���
	          for(int u=0;u<length;u++)//66
	          {
			    if(temp[u] == a.charAt(0))//��ʽ���ҵ�����ȡ��7
	            {
				  found = 1;
				  int add = 2;
	              for(int g=u;g<=a.length()-3+u;g++)//9
	              {
					if(g==u)//10
	                {
					 temp[g] = a.charAt(2);
				      add++;
					}
	                else//11
	                {
	                 temp[g] = a.charAt(2);
	                  movechar(temp,a.charAt(add),g-1,length);
	                  length++;
	                  add++;
	                }
	              }
			    }
	          }
			  if(found == 0)//���û���ҵ�13
	          {
			      error = true;
				  break;
			  }
		   }
	       else//����Ļ���ʽ�Ƿ���
	       {
	          error = true;
	          break;
	       }
		 }
	   }
	   if(!error)
	   {
		 String tt1 = "";
		 tt1 = tt1.copyValueOf(temp,0,length);
		//expression(tt1);
		 return tt1;
	   }
	   else
	   {
	       return "Error";
	   }
	}

	
	public String derivative(){//s����ʽ��mmm������
		String[] tokens = this.plo.split("[\\+]");//��s���ռӺŷָ���
		StringBuffer ne = new StringBuffer("");
		if(this.plo.contains("-"))
		{
			return "Error";
		}
    for(int i=0;i<tokens.length;i++){//����ÿ������ʽ,�󵼣�
      int ooo=0;
      String[] temp = tokens[i].split("[\\*]");//�ٶ�������ó˺ŵķָ���temp�Ǹ�����
      int ppp=0;
      for(int j=0;j<temp.length;j++){//���ڸ�����
    	  if(temp[j].length()==0)
    	  {
    		  return "Error";
    	  }
	    if(temp[j].charAt(0)==this.command.charAt(4)){//����Ǹ������mmm�ĵ������ĸ
		    ooo++;//�������ʽ��Ӧ�Ĵ�����1
		    ppp=j;//��¼�����ֵ����ǵڼ��
		}			
      }
      if(ooo == 0){
    	  continue;
      }
      else{//���������һ��ٸ���ϵ��
        for(int k=0;k<temp.length;k++){
		  if(k!=ppp){
			ne.append(temp[k]).append("*");
		  }
        }
        ne.append(String.valueOf(ooo));//����ٳ��ϴ���
        ooo=0;
      }
      ne.append("+");
    }
    if(ne.length()==0)
    {
    	return "Error";
    }
	ne.deleteCharAt(ne.length()-1);
	return ne.toString();
	
  }
	
	
	public void setCaozuo(String caozuo) {
		this.caozuo = caozuo;
	}


	public void setPlo(String plo) {
		this.plo = plo;
	}
	public void setCommand(String command) {
		this.command = command;
	}

}
class end{//endʵ����
	private String endstring;
	private String errormessage;
	private String shizi;//�����ȷʱ���յ�ֵ
	private StringBuffer zhongzhuan = new StringBuffer("");
	
	public static boolean isequal(char a[],char b[],int a_length,int b_length)
	{
		boolean isequal = true;
		if(a_length == b_length)
		{
			for(int g= 0;g<a_length;g++)
			{
				if(a[g] != b[g])
				{
					isequal = false;
					return isequal;
				}
			}
		}
		else
		{
			isequal = false;
		}
		return isequal;
	}
	
	public static void sortindex(int a,char c[])//a������,sort the character index
	{
		for(int h=1;h<=a;h++)
		{
			for(int v=0;v<a-1;v++)
			{
				if(c[v]>c[v+1])
				{
					char temp = c[v];
					c[v] = c[v+1];
					c[v+1] = temp;
				}
			}
		}
	}
	
	public void expression(){
		if(this.endstring.equals("Error"))
		{
			this.errormessage = "Error";
			this.shizi = "";
		}
		else
		{
			//System.out.println(this.endstring);
			this.errormessage = "";
			boolean[] x = new boolean[10];
			int c = -1;
			int[] cunchu1 = new int[10];//�洢ϵ��
			int[] moxiabiao = new int[10];
			char[][] cunchu2 = new char[10][5];//�洢����
			for(String a:this.endstring.split("\\+"))//�԰�+����������ַ������д���
			{
				c++;
				x[c] = true;
				moxiabiao[c] = -1;
				cunchu1[c] = 1;
				for(String b:a.split("\\*"))//��*����
				{
					if(b.matches("[\\d+]"))//��⵽������
					{
						cunchu1[c]= (Integer.parseInt(b))*cunchu1[c];
					}
					else//��⵽����ĸ
					{
						moxiabiao[c]++;
						cunchu2[c][(moxiabiao[c])] = b.charAt(0);
					}
				}
			}
			for(int q=0;q<=c;q++)
			{
				sortindex(moxiabiao[q]+1,cunchu2[q]);
			}
			for(int q=0;q<=c-1;q++)//�ϲ�ͬ����
			{
				if(x[q] == true)
				{
					for(int u=q+1;u<=c;u++)
					{
						if(isequal(cunchu2[q],cunchu2[u],moxiabiao[q]+1,moxiabiao[u]+1)==true)
						{
							cunchu1[q] = cunchu1[q] + cunchu1[u];
							x[u] = false;
						}
					}
				}
			}
			int m =-1;
			for(int i=0;i<=c;i++)//��ʾ����
			{
				if(x[i]==true)
				{
					m++;
					cunchu1[m] = cunchu1[i];
					moxiabiao[m] = moxiabiao[i];
					for(int p=0;p<=moxiabiao[i];p++)
					{
						cunchu2[m][p]=cunchu2[i][p];
					}
				}
			}
			for(int y=0;y<=m;y++)
			{
				if(cunchu1[y]!=0)
				{
					this.zhongzhuan.append(cunchu1[y]);
					//System.out.printf("%d",cunchu1[y]);
					for(int p=0;p<=moxiabiao[y];p++)
					{
						this.zhongzhuan.append(cunchu2[y][p]);
						//System.out.printf("%c",cunchu2[y][p]);
					}
					if(y!=m)
					{
						this.zhongzhuan.append("+");
						//System.out.printf("+");
					}
				}
			}
			this.shizi = this.zhongzhuan.toString();
			//System.out.printf("\n");
		}
	}
	

	public void setEndstring(String endstring) {
		this.endstring = endstring;
	}
	public String getShizi() {
		return shizi;
	}
	public String getErrormessage() {
		return errormessage;
	}
}



class outputcon{//�����ࡰ�����
	private String errormessage;
	private String shizi;
	public String control(){
		if(this.errormessage.equals("Error"))
		{
			return this.errormessage;
		}
		else
		{
			return this.shizi;
		}
	}
	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public void setShizi(String shizi) {
		this.shizi = shizi;
	}
}


class output{//����������߽���
	private String foutput;
	
	public void eoutput(){
		System.out.println(this.foutput);
	}
	public void setFoutput(String foutput) {
		this.foutput = foutput;
	}
}

public class plo{
	
	public String fortest(String shizi,String command)//���ڻع���Եĺ���
	{
		InputShizi a = new InputShizi();
		InputCommand b = new InputCommand();
		command c = new command();
		RR d = new RR();
		Shizi e  =new Shizi();
		end f = new end();
		outputcon g = new outputcon();
		
		a.setexpression(shizi);
		b.setcommand(command);
		c.setCom(b.toidentify());
		d.setExpression(a.toidentify());
		d.setCommand(c.getCom());
		e.setCaozuo(d.recognize());
		e.setCommand(b.toidentify());
		e.setPlo(a.toidentify());
		f.setEndstring(e.operation());
		f.expression();
		g.setErrormessage(f.getErrormessage());
		g.setShizi(f.getShizi());
		return g.control();
	}
	
	
	public static void main(String[] args) //������
	{
		InputShizi a = new InputShizi();
		InputCommand b = new InputCommand();
		command c = new command();
		RR d = new RR();
		Shizi e  =new Shizi();
		end f = new end();
		outputcon g = new outputcon();
		output h = new output();
		a.inputexpression();
		b.inputcommand();
		c.setCom(b.toidentify());
		d.setExpression(a.toidentify());
		d.setCommand(c.getCom());
		e.setCaozuo(d.recognize());
		e.setCommand(b.toidentify());
		e.setPlo(a.toidentify());
		f.setEndstring(e.operation());
		f.expression();
		g.setErrormessage(f.getErrormessage());
		g.setShizi(f.getShizi());
		h.setFoutput(g.control());
		h.eoutput();
	}
}






