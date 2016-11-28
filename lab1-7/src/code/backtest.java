package code;

import static org.junit.Assert.*;

import org.junit.Test;

public class backtest {

	@Test
	public void testFortest() {
		plo a = new plo();
		String result = a.fortest("3*3+4*a+b*a","!d/da");
		assertEquals("错误！不一致！","4+1b",result);
				
		String result2 = a.fortest("3*3+4*a+b*a+","!d/da");
		assertEquals("错误！不一致！","Error",result2);
		String result3 = a.fortest("3*3+4*a+b*a","!d/dx,y");
		assertEquals("错误！不一致！","Error",result3);
		String result4 = a.fortest("3*3+4*a+b-a","!d/da");
		assertEquals("错误！不一致！","Error",result4);
		String result5 = a.fortest("x*x*8++7+23","!d/dx");
		assertEquals("错误！不一致！","Error",result5);
		String result6 = a.fortest("s*8*7+zx+23*3","!d/ds");
		assertEquals("错误！不一致！","Error",result6);
		String result7 = a.fortest("3*c*5+23d+c","!d/dc");
		assertEquals("错误！不一致！","Error",result7);
		
		String result8 = a.fortest("3*2*x+y","");
		assertEquals("错误！不一致！","Error",result8);
		String result9 = a.fortest("4*5*x+y","!simplify");
		assertEquals("错误！不一致！","Error",result9);
		String result10 = a.fortest("4*5*x+y","!simplify x");
		assertEquals("错误！不一致！","Error",result10);
		String result11 = a.fortest("","!simplify x=2");
		assertEquals("错误！不一致！","Error",result11);
		String result12 = a.fortest("3*x+4*y","!simplify z=3");
		assertEquals("错误！不一致！","Error",result12);
		String result13 = a.fortest("5*6+2*y","!simplify y=");
		assertEquals("错误！不一致！","Error",result13);
		String result14 = a.fortest("4*x+12*y","!simplify y=5");
		assertEquals("错误！不一致！","4x+51",result14);
		
		
		
		
		
		//String result = s.derivative("!d/da", "3*3+4*a+b*a");
		//assertEquals("错误！不一致！","4*1+b*1",result);
		
		//fail("Not yet implemented");
	}

}
