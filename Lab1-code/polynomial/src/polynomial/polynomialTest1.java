package polynomial;

import static org.junit.Assert.*;

import org.junit.Test;

public class polynomialTest1 {

	@Test
	public void testderivative() {
		
		polynomial s = new polynomial();
		
		//String result = s.derivative("!d/da", "3*3+4*a+b*a");
		//assertEquals("错误！不一致！","4*1+b*1",result);
		
		//String result2 = s.derivative("!d/da", "3*3+4*a+b*a+");
		//assertEquals("错误！不一致！","4*1+b*1",result2);
		
		//String result3 = s.derivative("!d/dx,y", "3*3+4*a+b*a");
		//assertEquals("错误！不一致！","error",result3);
		
		//String result4 = s.derivative("!d/da", "3*3+4*a+b-a");
		//assertEquals("错误！不一致！","error",result4);
		
		//String result5 = s.derivative("!d/dx", "x*x*8++7+23");
		//assertEquals("错误！不一致！","error",result5);
		
		String result6 = s.derivative("!d/ds", "s*8*7+zx+23*3");
		assertEquals("错误！不一致！","8*7*1",result6);

		String result7 = s.derivative("!d/dc", "3*c*5+23d+c");
		assertEquals("错误！不一致！","3*5*1+1",result7);


		
	}

}
