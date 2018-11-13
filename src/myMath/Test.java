package myMath;

public class Test {

	public static void main(String[] args) {

		testMonom();
		testPolynom();
	}

	public static void testMonom() {

		////// Test Monom

		//constractors
		System.out.println("\ncon Test\n");

		Monom m1= new Monom(0,0);
		Monom m2= new Monom("2x^1");
//		Monom m3= new Monom(1,-3);  // ok
		Monom m4= new Monom("0x^4");
		Monom m5= new Monom(-0.3,0);
		Monom m6= new Monom(5.2,11);
		Monom m7= new Monom(-0,9);
		Monom m8= new Monom(m2);
		Monom m9= new Monom(3,9);
		Monom m0= new Monom(-1,9);


		//to String
		System.out.println("\ntostring Test\n");

		System.out.println(m1.toString());    // 0
		System.out.println(m2.toString());    //2x
//		System.out.println(m3.toString());    // err . ok
		System.out.println(m4.toString());    //0
		System.out.println(m5.toString());    //-0.3
		System.out.println(m6.toString());    //5.2x^11
		System.out.println(m7.toString());    //0
		System.out.println(m8.toString());    //2x

		//f
		System.out.println("\nf Test\n");

		System.out.println(m1.f(2));   //0
		System.out.println(m2.f(2));   //4
		System.out.println(m4.f(2));   //0
		System.out.println(m5.f(2));  //-0.3
		System.out.println(m6.f(2));  //10649.6
		System.out.println(m7.f(2)); //0
		System.out.println(m8.f(2));  //4

		//add
		System.out.println("\nadd Test\n");

//		m2.add(m4); // err
		m5.add(m1);
		m9.add(m0); 
		System.out.println(m5.toString());  // -0.3
		System.out.println(m9.toString());  // 2x^9

		//mult
		System.out.println("\nmult Test\n");

		m1.multiply(m9);
		m6.multiply(m5);
		m0.multiply(m5);
		m2.multiply(m8);
		m7.multiply(m2);
		System.out.println(m1.toString());  //0
		System.out.println(m6.toString());  // -1.56x^11
		System.out.println(m0.toString());  // 0.3x^9
		System.out.println(m2.toString());  // 4x^2
		System.out.println(m7.toString());  //0

		//derivative
		System.out.println("\nder Test\n");

		m6.derivative();
		m5.derivative();
		m0.derivative();
		m1.derivative();
		m2.derivative();
		System.out.println(m6.toString()); // -17.16x^10
		System.out.println(m5.toString());  //0
		System.out.println(m0.toString());  // 2.7x^8
		System.out.println(m1.toString());  //0
		System.out.println(m2.toString());  //8x

		//is zero
		System.out.println("\nzero Test\n");

		System.out.println(m1.isZero()); // true
		System.out.println(m2.isZero()); // false
	}
	

	public static void testPolynom() {
		//////Test Polynom

		//constractors
		System.out.println("\ncon Test\n");
		Polynom p1= new Polynom("0+0+0+2");
		Polynom p2= new Polynom("0");
		Polynom p3= new Polynom("3x^2+2x-4+0x^5");
		Polynom p4= new Polynom("2*x^4-7*x^3");
		Polynom p5= new Polynom("-x^1+9.5x^2-1x^3");
		Polynom p6= new Polynom(p5);

		//to String
		System.out.println("\ntostring Test\n");

		System.out.println(p1.toString());  // 2
		System.out.println(p2.toString());  // 0
		System.out.println(p3.toString());  //-4+2x+3x^2
		System.out.println(p4.toString());  //-7x^3+2x^4
		System.out.println(p5.toString());  //-x+9.5x^2-x^3
		System.out.println(p6.toString());  //-x+9.5x^2-x^3

		// copy
		System.out.println("\ncopy Test\n");

		Polynom_able pv= p5.copy();
		System.out.println(pv.toString());   //-x+9.5x^2-x^3

		//add monom
		System.out.println("\nadd Monom Test\n");

		p5.add(new Monom("0"));
		System.out.println(p5.toString());  //-x+9.5x^2-x^3
		p5.add(new Monom("x^3"));
		System.out.println(p5.toString());  //-x+9.5x^2
		p5.add(new Monom("-x"));
		System.out.println(p5.toString());   //-2.0x+9.5x^2
		p5.add(new Monom("0.5x^2"));
		System.out.println(p5.toString());   //-2.0x+10.0x^2

		//add polynom
		System.out.println("\nadd polynom Test\n");
		p6.add(p1);
		System.out.println(p6.toString());   //2.0-2.0x+10.0x^2-x^3
		p6.add(p3);
		System.out.println(p6.toString()); // -2.0+13.0x^2-x^3
		Monom mm= new Monom(2,0) ;
		Polynom m= new Polynom();
		m.add(mm);
		m.add(new Monom(-1.5,0));
		m.add(new Monom(-0.5,0));
		System.out.println(m.isZero());  // true
		System.out.println(m.toString());  // 0

		//sub
		System.out.println("\nsub Test\n");
		p6.substract(p3);
		System.out.println(p6.toString());  //2.0-2.0x+10.0x^2-x^3
		p6.substract(p2);
		System.out.println(p6.toString());  // 2.0-2.0x+10.0x^2-x^3

		//mult
		System.out.println("\nmult Test\n");

		p6.multiply(p2);
		System.out.println(p6.toString());    //0
		Polynom po= new Polynom("-3x+x^2");   
		Polynom pol= new Polynom("0+x-2x^3");  
		po.multiply(pol);
		System.out.println(po.toString());  //-3.0x^2+x^3+6.0x^4-2.0x^5
		p3.multiply(po);
		System.out.println(p3.toString());   //0-12.0x^2+10.0x^3+31.0x^4-23.0x^5-14.0x^6+6.0x^7

		//equals
		System.out.println("\nequals Test\n");
		Polynom w1= new Polynom("3x^7+2");
		Polynom w2= new Polynom("-2x^4+2+3x^7");
		w2.add(new Monom(2,4));
		System.out.println(w2.toString());
		System.out.println(w1.toString());
		System.out.println(w2.equals(w1));  // true
		System.out.println(w1.equals(w2));  // true
		System.out.println(p1.equals(p2));  // false
		Polynom p11= new Polynom("2.0");
		System.out.println(p1.equals(p11));  //true
		Polynom p= new Polynom(w1.derivative().toString());
		System.out.println(w1.toString());
		System.out.println(p.toString()); // 21x^6

		// is zero
		System.out.println("\nis zero Test\n");

		System.out.println(p1.isZero()); // false
		System.out.println(p2.isZero());  // true

		//root
		System.out.println("\nroot Test\n");

		Polynom c= new Polynom("x^2-x-2");
		System.out.println(c.root(1, 6, 0.01));   // 2+-

		//derivative
		System.out.println("\nderivative Test\n");
		Polynom pa= new Polynom(po.derivative().toString());
		System.out.println(pa.toString());          //-6.0x+3.0x^2+24.0x^3-10.0x^4
		pa= new Polynom(pa.derivative().toString());
		System.out.println(pa.toString());         //-6.0+6.0x+72.0x^2-40.0x^3
		Polynom pb= new Polynom(pol.derivative().toString());
		System.out.println(pb.toString());             //1-6.0x^2
		Polynom p_zero = new Polynom("0");
		Polynom p_zero2= new Polynom(p_zero.derivative().toString());
		System.out.println(p_zero2.toString());       //0

		//area
		System.out.println("\narea Test\n");

		Polynom sh= new Polynom("-x+5");
		System.out.println(sh.area(0, 5, 0.01)); //12.5
		System.out.println(sh.area(0, 10, 0.01)); //12.5
	}
}
