package polynomial;

import static org.junit.Assert.*;

import org.junit.Test;

public class polynomialTest2 {

	@Test
	public void testsimplify() {
		
		polynomial s = new polynomial();
		
		String result = s.simplify("", "3*2*x+y");
		assertEquals("����1��һ�£�","Error",result);
		
		String result2 = s.simplify("!simplify", "4*5*x+y");
		assertEquals("����2��һ�£�","Error",result2);
		
		String result3 = s.simplify("!simplify x", "4*5*x+y");
		assertEquals("����3��һ�£�","Error",result3);
		
		String result4 = s.simplify("!simplify x=2", "");
		assertEquals("����4��һ�£�","Error",result4);
		
		String result5 = s.simplify("!simplify z=3", "3*x+4*y");
		assertEquals("����5��һ�£�","Error",result5);
		
		String result6 = s.simplify("!simplify y=", "5*6+2*y");
		assertEquals("����6��һ�£�","Error",result6);

		String result7 = s.simplify("!simplify y=5", "4*x+12*y");
		assertEquals("����7��һ�£�","4*x+12*5",result7);
		
		String result8 = s.simplify("!simplify y=170", "x*5+y+10");
		assertEquals("����8��һ�£�","x*5+170+10",result8);
		
		String result9 = s.simplify("!simplify x=17 y=10", "3*x*10+y*8+12");
		assertEquals("����9��һ�£�","3*17*10+10*8+12",result9);
		
		String result10 = s.simplify("!simplify x!17", "3*x*10+y*8+12");
		assertEquals("����10��һ�£�","Error",result10);
		
		String result11 = s.simplify("!simplify 5", "3*x*10+y*8+12");
		assertEquals("����11��һ�£�","Error",result11);
	}

}