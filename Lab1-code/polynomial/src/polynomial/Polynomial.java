package polynomial;

import java.util.Scanner;

public class polynomial {
  public static boolean isequal(char a[],char b[],int a_length,int b_length){
    boolean isequal=true;
    if(a_length == b_length)
    {
      for(int g= 0;g<a_length;g++)
      {
		if(a[g] != b[g])
        {
		  isequal = false;
          break;
		}
      }
	}
    else
    {
      isequal = false;
    }
  return isequal;
}
	
  public static void sortindex(int a, char...c)//a??????,sort the character index
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
	
	public static void movechar(char a[],char t,int in,int length)
	{
	  /**
	  *
	  * @param c, (purpose), not modified by method.
	  * @param b, (purpose), not modified by method.
	  * @param a, (purpose), not modified by method.
	  * @param in, (purpose), not modified by method.
	  */
		for(int c=length-1;c>=in+1;c--)
		{
			a[c+1] = a[c];
 		}
		a[in+1] = t;
	}
	
  public static void expression(String command)//????
  {
	boolean[] x = new boolean[10];
	int c = -1;
	int[] cunchu1 = new int[10];//?洢???
	int[] moxiabiao = new int[10];
	char[][] cunchu2 = new char[10][5];//?洢????
    for(String a:command.split("\\+"))//???+????????????????????
    {
      c++;
      x[c] = true;
      moxiabiao[c] = -1;
      cunchu1[c] = 1;
      for(String b:a.split("\\*"))//??*????
      {
		if(b.matches("[\\d+]"))//?????????
        {
          cunchu1[c]= (Integer.parseInt(b))*cunchu1[c];
		}
        else//????????
        {
          moxiabiao[c]++;
          cunchu2[c][moxiabiao[c]] = b.charAt(0);
        }
      }
    }
    for(int q=0;q<=c;q++)
    {
      sortindex(moxiabiao[q]+1,cunchu2[q]);
    }
    for(int q=0;q<=c-1;q++)//????????
    {
	  if(x[q])
      {
        for(int u=q+1;u<=c;u++)
        {
		      if(isequal(cunchu2[q],cunchu2[u],moxiabiao[q]+1,moxiabiao[u]+1))
          {
			      cunchu1[q] = cunchu1[q] + cunchu1[u];
			      x[u] = false;
		      }
        }
	  }
    }

    for(int i=0;i<=c;i++)//???????
    {
      int m =-1;
  	  if(x[i])
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
    int m =-1;
    for(int y=0;y<=m;y++)
    {
	  if(cunchu1[y]!=0)
      {
		System.out.printf("%d",cunchu1[y]);
        for(int p=0;p<=moxiabiao[y];p++)
        {
          System.out.printf("%c",cunchu2[y][p]);
        }
		if(y!=m)
        {
		  System.out.printf("+");
		}
	  }
    }
	System.out.printf("\n");
  }
	
public String simplify(String command,String suanshi)//化简
{
	boolean finish = false;
	char[] temp = new char[100];//交换的数组，用于存储算式
	int length = suanshi.length();
	suanshi.getChars(0,suanshi.length(),temp,0);
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
				 // temp[g] = a.charAt(2);
			      add++;
				}
                else//11
                {
                 // temp[g] = a.charAt(2);
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
	
	
	
	/**
	* 
	*
	* @author George Bush
	*/
	public String derivative(String mmm,String s){//s是新输入的字符串，mmm是要求的字母
		String[] tokens = s.split("[\\+]");//将s按照加号分隔开
		StringBuffer ne = new StringBuffer("");
		if(s.contains("-"))
		{
			return "error";
		}
    for(int i=0;i<tokens.length;i++){//对于每个单项式,求导！
      int ooo=0;
      String[] temp = tokens[i].split("[\\*]");//再对其进行用乘号的分隔，temp是各个项
      int ppp=0;
      for(int j=0;j<temp.length;j++){//对于各个项
    	  if(temp[j].length()==0)
    	  {
    		  return "error";
    	  }
	    if(temp[j].charAt(0)==mmm.charAt(4)){//如果那个项等于mmm的第五个字母
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
    	return "error";
    }
	ne.deleteCharAt(ne.length()-1);
	return ne.toString();
	//expression(ne.toString());
  }
	
  public static void main(String[] args) {
		// TODO Auto-generated method stub
		    final Scanner input;
			input = new Scanner(System.in);
			System.out.println("Input Your Polynomial:");
			String str = input.nextLine();
			if(str.matches("((\\d+|[a-z])[*+])*(\\d+|[a-z])")){
				System.out.println("True Polynomial");

				System.out.println("Input Command started with !");
				String com = input.nextLine();
	  if(com.matches("!simplify[\\s]([\\w]=[\\d+])+"))
      {
      polynomial youbet = new polynomial();
	    youbet.simplify(com, str);
	  }
      else if(com.matches("!d/d[\\s][\\w]"))
	  {
        polynomial youbet = new polynomial();
		    youbet.derivative(com,str);
	  }
      else 
      {
        System.out.println("Wrong command!");
      }
			}
			
				
    else
    {
      System.out.println("Wrong");
    }
	input.close();
	}
}