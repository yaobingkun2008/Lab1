package polynomial;
import java.util.Scanner;

public class Polynomial {
	
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
	
	public static void movechar(char a[],char t,int in,int length)
	{
		for(int c=length-1;c>=in+1;c--)
		{
			a[c+1] = a[c];
 		}
		a[in+1] = t;
	}
	
	public static void expression(String command)//化简
	{
		boolean[] x = new boolean[10];
		int c = -1;
		int[] cunchu1 = new int[10];//存储系数
		int[] moxiabiao = new int[10];
		char[][] cunchu2 = new char[10][5];//存储变量
		for(String a:command.split("\\+"))//对按+分离出来的字符串进行处理
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
	
	public boolean simplify(String command,String suanshi)//代入并且取而代之
	{
		boolean finish = false;
		char[] temp = new char[100];
		int length = suanshi.length();
		suanshi.getChars(0,suanshi.length(),temp,0);
		boolean error = false;
		for(String a:command.split("\\s"))
		{
			if(a.length()!=9)
			{
				if(Character.isLetter(a.charAt(0))==true && a.charAt(1)=='=')
				{
					int found = 0;
					for(int u=0;u<length;u++)
					{
						if(temp[u] == a.charAt(0))
						{
							found = 1;
							int add = 2;
							for(int g=u;g<=a.length()-3+u;g++)
							{
								if(g==u)
								{
									temp[g] = a.charAt(2);
									add++;
								}
								else
								{
									movechar(temp,a.charAt(add),g-1,length);
									length++;
								}
							}
						}
					}
					if(found == 0)
					{
						System.out.println("ERROR!");
						error = true;
						break;
					}
				}
				else
				{
					System.out.println("ERROR!");
					error = true;
					break;
				}
			}
		}
		if(error == false)
		{
			String t1 = "";
			t1 = t1.copyValueOf(temp,0,length);
			expression(t1);
			finish = true;
		}
		return finish;
	}
	
	
	
	
	public void derivative(String m,String s){
		String[] tokens = s.split("[\\+]");
		int o=0,p=0;
		StringBuffer ne = new StringBuffer("");
		for(int i=0;i<tokens.length;i++){
			String[] temp = tokens[i].split("[\\*]");
			for(int j=0;j<temp.length;j++){
				if(temp[j].charAt(0)==m.charAt(5)){
					o++;
					p=j;
				}
					
			}
			if(o==0) continue;
			else{
				for(int k=0;k<temp.length;k++){
					if(k!=p){
						ne.append(temp[k]).append("*");
					}
				}
				ne.append(String.valueOf(o));
				o=0;
			}
			ne.append("+");
		}
		ne.deleteCharAt(ne.length()-1);
		expression(ne.toString());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Scanner input = new Scanner(System.in);
			System.out.println("Input Your Polynomial:");
			String str = input.nextLine();
			if(str.matches("((\\d+|[a-z])[*+])*(\\d+|[a-z])")){
				System.out.println("True Polynomial");
				Polynomial youbet = new Polynomial();
				System.out.println("Input Command started with !");
				String com = input.nextLine();
				if(com.matches("!simplify[\\s]([\\w]=[\\d+])+"))
					youbet.simplify(com, str);
				else if(com.matches("!d/d[\\s][\\w]"))
					youbet.derivative(com,str);
				else System.out.println("Wrong command!");
			}
			
				
			else 
				System.out.println("Wrong");
			input.close();
			}

}
