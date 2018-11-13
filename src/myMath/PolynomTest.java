package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PolynomTest {
	Polynom p1;
	Polynom p2;
	Polynom p3;
	Polynom p4;
	Polynom p5;
	Polynom p6;
	Polynom po;  
	Polynom pol;  

	@BeforeEach
	void setUp() throws Exception {
		p1= new Polynom("0+0+0+2");
		p2= new Polynom("0");
		p3= new Polynom("3x^2+2x-4+0x^5");
		p4= new Polynom("2*x^4-7*x^3");
		p5= new Polynom("-x^1+9.5x^2-1x^3");   // -4+x+12.5x^2-x^3
		p6= new Polynom(p5);
		po= new Polynom("-3x+x^2");   
		pol= new Polynom("0+x-2x^3");  
	}


    @Test
	void testF() {
		assertEquals(-2.0, po.f(1));
	}

	@Test
	void testAddPolynom_able() {
		p6.add(p1);
		assertEquals("2.0-x+9.5x^2-x^3", p6.toString());
		p6.add(p3);
		assertEquals("-2.0+x+12.5x^2-x^3", p6.toString());
		
	}

	@Test
	void testAddMonom() {
		p5.add(new Monom("0"));
		assertEquals("-x+9.5x^2-x^3", p5.toString());
		p5.add(new Monom("x^3"));
		assertEquals("-x+9.5x^2", p5.toString());
		p5.add(new Monom("-x"));
		assertEquals("-2.0x+9.5x^2", p5.toString());
		p5.add(new Monom("0.5x^2"));
		assertEquals("-2.0x+10.0x^2", p5.toString());
	}

	@Test
	void testSubstract() {
		p6.substract(p3);
		assertEquals("4.0-3.0x+6.5x^2-x^3", p6.toString());
		p6.substract(p2);
		assertEquals("4.0-3.0x+6.5x^2-x^3", p6.toString());

	}

	@Test
	void testMultiply() {
		p6.multiply(p2);
		assertEquals("0", p6.toString());
		po.multiply(pol);
		assertEquals("-3.0x^2+x^3+6.0x^4-2.0x^5", po.toString());

	}

	@Test
	void testEqualsPolynom_able() {
		Polynom w1= new Polynom("3x^7+2");
		Polynom w2= new Polynom("-2x^4+2+3x^7");
		w2.add(new Monom(2,4));
		assertTrue(w2.equals(w1));
		assertTrue(w1.equals(w2));
		assertFalse(p1.equals(p2));
		Polynom p11= new Polynom("2.0");
		assertTrue(p1.equals(p11));


	}

	@Test
	void testIsZero() {
		assertFalse(p1.isZero());
		assertTrue(p2.isZero());
	}

	@Test
	void testRoot() {
		Polynom c= new Polynom("x^2-x-2");
		assertEquals(2.0009765625, c.root(1, 6, 0.01));
	}

	@Test
	void testCopy() {
		Polynom_able pv= p5.copy();
		assertEquals("-x+9.5x^2-x^3",pv.toString());
	}

	@Test
	void testDerivative() {
		Polynom pa= new Polynom(po.derivative().toString());
		assertEquals("-3.0+2.0x", pa.toString());
		pa= new Polynom(pa.derivative().toString());
		assertEquals("2.0", pa.toString());

	}

	@Test
	void testArea() {
		Polynom sh= new Polynom("-x+5");
		assertEquals(12.47500000000009, sh.area(0, 5, 0.01));
		assertEquals(12.47500000000009, sh.area(0, 10, 0.01));

	}

}
