package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MonomTest {

	Monom m1= new Monom(0,0);
	Monom m2= new Monom("2x^1");
	Monom m3= new Monom(-1,9);
	Monom m4= new Monom("0x^4");
	Monom m5= new Monom(-0.3,0);
	Monom m6= new Monom(5.2,11);
	Monom m7= new Monom(-0,9);
	Monom m8= new Monom(m2);
	Monom m9= new Monom(3,9);
	

	@Test
	void testMonomString() {
	assertEquals("2.0x", m2.toString());
	assertEquals("0", m4.toString());
	assertEquals("-0.3", m5.toString());
	assertEquals("5.2x^11", m6.toString());
	assertEquals("0", m7.toString());
	assertEquals("2.0x", m8.toString());

	}

	@Test
	void testF() {
	assertEquals(0, m1.f(2));
	assertEquals(4, m2.f(2));
	assertEquals(0, m4.f(2));
	assertEquals(-0.3, m5.f(2));
	assertEquals(10649.6, m6.f(2));
	assertEquals(0, m7.f(2));
	assertEquals(4, m8.f(2));
	}

	@Test
	void testAdd() {
		m5.add(m1);
		assertEquals("-0.3", m5.toString());
		m9.add(m3);
		assertEquals("2.0x^9", m9.toString());
		
	}

	@Test
	void testMultiply() {
		m1.multiply(m9);
		assertEquals("0", m1.toString());
		m6.multiply(m5);
		assertEquals("-1.56x^11", m6.toString());
		m3.multiply(m5);
		assertEquals("0.3x^9", m3.toString());
		m2.multiply(m8);
		assertEquals("4.0x^2", m2.toString());
		m7.multiply(m2);
		assertEquals("0", m7.toString());
	}

	@Test
	void testDerivative() {
		
		m6.derivative();
		assertEquals("57.2x^10", m6.toString());
		m5.derivative();
		assertEquals("0", m5.toString());
		m1.derivative();
		assertEquals("0", m1.toString());
		m2.derivative();
		assertEquals("2.0", m2.toString());
		m3.derivative();
		assertEquals("-9.0x^8", m3.toString());
 
	}

	@Test
	void testIsZero() {
		assertTrue(m1.isZero());
		assertFalse(m2.isZero());
	
	}

}
