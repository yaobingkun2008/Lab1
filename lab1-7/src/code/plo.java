package code;
import java.util.Scanner;


class InputShizi{//边界类1
	
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

class InputCommand{//边界类2
	
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
class RR{//接受与识别
	private String expression;
	private String command;
	public String recognize()//识别读进来的表达式是否合法，并根据结果采取一系列的措施
	{
		if(this.expression.matches("((\\d+|[a-z])[*+])*(\\d+|[a-z])")){//如果表达式符合情况
			if(this.command.matches("!simplify[\\s]([\\w]=[\\d+])+"))//代入化简操作
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

class Shizi{//表达式和值
	private String caozuo;
	private String plo;
	private String command;
	//private String end;

	public String operation()//operation的结果决定输出的是error还是正确的表达式
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
	
	public String simplify()//化简
	{
		//boolean finish = false;
		char[] temp = new char[100];//交换的数组，用于存储算式
		int length =this.plo.length();
		this.plo.getChars(0,this.plo.length(),temp,0);
		boolean error = false;
		if(command.length()==0||command.length()<=9)
		{
			return "Error";
		}
	    for(String a:command.split("\\s"))//把命令按照空格分隔
	    {
	      if(a.length()!=9)//如果不是命令，是赋值的3
	      {
	        if((Character.isLetter(a.charAt(0)) && a.length()>=3)&&(a.charAt(1)=='='))//如果赋值符合情况4
			{
			  int found = 0;//标明有没有找到对应的东西
	          for(int u=0;u<length;u++)//66
	          {
			    if(temp[u] == a.charAt(0))//算式中找到，就取代7
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
			  if(found == 0)//如果没有找到13
	          {
			      error = true;
				  break;
			  }
		   }
	       else//输入的化简式非法？
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

	
	public String derivative(){//s是算式，mmm是命令
		String[] tokens = this.plo.split("[\\+]");//将s按照加号分隔开
		StringBuffer ne = new StringBuffer("");
		if(this.plo.contains("-"))
		{
			return "Error";
		}
    for(int i=0;i<tokens.length;i++){//对于每个单项式,求导！
      int ooo=0;
      String[] temp = tokens[i].split("[\\*]");//再对其进行用乘号的分隔，temp是各个项
      int ppp=0;
      for(int j=0;j<temp.length;j++){//对于各个项
    	  if(temp[j].length()==0)
    	  {
    		  return "Error";
    	  }
	    if(temp[j].charAt(0)==this.command.charAt(4)){//如果那个项等于mmm的第五个字母
		    ooo++;//这个单项式对应的次数加1
		    ppp=j;//记录最后出现的项是第几项？
		}			
      }
      if(ooo == 0){
    	  continue;
      }
      else{//先消掉最后一项，再给予系数
        for(int k=0;k<temp.length;k++){
		  if(k!=ppp){
			ne.append(temp[k]).append("*");
		  }
        }
        ne.append(String.valueOf(ooo));//最后再乘上次数
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
class end{//end实体类
	private String endstring;
	private String errormessage;
	private String shizi;//结果正确时最终的值
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
	
	public static void sortindex(int a,char c[])//a是数量,sort the character index
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
			int[] cunchu1 = new int[10];//存储系数
			int[] moxiabiao = new int[10];
			char[][] cunchu2 = new char[10][5];//存储变量
			for(String a:this.endstring.split("\\+"))//对按+分离出来的字符串进行处理
			{
				c++;
				x[c] = true;
				moxiabiao[c] = -1;
				cunchu1[c] = 1;
				for(String b:a.split("\\*"))//按*处理
				{
					if(b.matches("[\\d+]"))//检测到是数字
					{
						cunchu1[c]= (Integer.parseInt(b))*cunchu1[c];
					}
					else//检测到是字母
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
			for(int q=0;q<=c-1;q++)//合并同类项
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
			for(int i=0;i<=c;i++)//显示出来
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



class outputcon{//控制类“输出”
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


class output{//最终输出，边界类
	private String foutput;
	
	public void eoutput(){
		System.out.println(this.foutput);
	}
	public void setFoutput(String foutput) {
		this.foutput = foutput;
	}
}

public class plo{
	
	public String fortest(String shizi,String command)//用于回归测试的函数
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
	
	
	public static void main(String[] args) //主函数
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






