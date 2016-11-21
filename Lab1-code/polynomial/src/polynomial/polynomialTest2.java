package polynomial;

import static org.junit.Assert.*;

import org.junit.Test;

public class polynomialTest2 {

	@Test
	public void testsimplify() {
		
		polynomial s = new polynomial();
		
		String result = s.simplify("", "3*2*x+y");
		assertEquals("错误！1不一致！","Error",result);
		
		String result2 = s.simplify("!simplify", "4*5*x+y");
		assertEquals("错误！2不一致！","Error",result2);
		
		String result3 = s.simplify("!simplify x", "4*5*x+y");
		assertEquals("错误！3不一致！","Error",result3);
		
		String result4 = s.simplify("!simplify x=2", "");
		assertEquals("错误！4不一致！","Error",result4);
		
		String result5 = s.simplify("!simplify z=3", "3*x+4*y");
		assertEquals("错误！5不一致！","Error",result5);
		
		String result6 = s.simplify("!simplify y=", "5*6+2*y");
		assertEquals("错误！6不一致！","Error",result6);

		String result7 = s.simplify("!simplify y=5", "4*x+12*y");
		assertEquals("错误！7不一致！","4*x+12*5",result7);
		
		String result8 = s.simplify("!simplify y=170", "x*5+y+10");
		assertEquals("错误！8不一致！","x*5+170+10",result8);
		
		String result9 = s.simplify("!simplify x=17 y=10", "3*x*10+y*8+12");
		assertEquals("错误！9不一致！","3*17*10+10*8+12",result9);
		
		String result10 = s.simplify("!simplify x!17", "3*x*10+y*8+12");
		assertEquals("错误！10不一致！","Error",result10);
		
		String result11 = s.simplify("!simplify 5", "3*x*10+y*8+12");
		assertEquals("错误！11不一致！","Error",result11);
	}

}