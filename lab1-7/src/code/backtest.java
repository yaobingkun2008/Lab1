package code;

import static org.junit.Assert.*;

import org.junit.Test;

public class backtest {

	@Test
	public void testFortest() {
		plo a = new plo();
		String result = a.fortest("3*3+4*a+b*a","!d/da");
		assertEquals("���󣡲�һ�£�","4+1b",result);
				
		String result2 = a.fortest("3*3+4*a+b*a+","!d/da");
		assertEquals("���󣡲�һ�£�","Error",result2);
		String result3 = a.fortest("3*3+4*a+b*a","!d/dx,y");
		assertEquals("���󣡲�һ�£�","Error",result3);
		String result4 = a.fortest("3*3+4*a+b-a","!d/da");
		assertEquals("���󣡲�һ�£�","Error",result4);
		String result5 = a.fortest("x*x*8++7+23","!d/dx");
		assertEquals("���󣡲�һ�£�","Error",result5);
		String result6 = a.fortest("s*8*7+zx+23*3","!d/ds");
		assertEquals("���󣡲�һ�£�","Error",result6);
		String result7 = a.fortest("3*c*5+23d+c","!d/dc");
		assertEquals("���󣡲�һ�£�","Error",result7);
		
		String result8 = a.fortest("3*2*x+y","");
		assertEquals("���󣡲�һ�£�","Error",result8);
		String result9 = a.fortest("4*5*x+y","!simplify");
		assertEquals("���󣡲�һ�£�","Error",result9);
		String result10 = a.fortest("4*5*x+y","!simplify x");
		assertEquals("���󣡲�һ�£�","Error",result10);
		String result11 = a.fortest("","!simplify x=2");
		assertEquals("���󣡲�һ�£�","Error",result11);
		String result12 = a.fortest("3*x+4*y","!simplify z=3");
		assertEquals("���󣡲�һ�£�","Error",result12);
		String result13 = a.fortest("5*6+2*y","!simplify y=");
		assertEquals("���󣡲�һ�£�","Error",result13);
		String result14 = a.fortest("4*x+12*y","!simplify y=5");
		assertEquals("���󣡲�һ�£�","4x+51",result14);
		
		
		
		
		
		//String result = s.derivative("!d/da", "3*3+4*a+b*a");
		//assertEquals("���󣡲�һ�£�","4*1+b*1",result);
		
		//fail("Not yet implemented");
	}

}
