package myMath;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author natali and michal
 *
 */
public class Polynom implements Polynom_able{

	ArrayList <Monom> pol;
	static Comparator<Monom> comper= new Monom_Comperator();

	public Polynom () {
		pol= new ArrayList<Monom>();
	}
	/**
	 * copy constractor
	 * @param p
	 */
	public Polynom (Polynom p) {
		pol= new ArrayList<Monom>();
		Iterator<Monom> it= p.iteretor();
		while(it.hasNext()) {
			Monom m= it.next();
			this.add(m);
		}
	}

	/**
	 * constractor function - gets a string and create new polynom
	 * @param p
	 */
	public Polynom (String p) {	
		p= p.replaceAll("\\*", "");
		p= p.replaceAll(" ", "");
		pol= new ArrayList<Monom>();
		if(p.length()==0)
			throw new RuntimeException("ERR cant be empty");
		p= p.replaceAll("-", "+-");
		if(p.indexOf("+")==0)
			p= p.substring(1, p.length());
		String[] s= p.split("\\+");
		for(int i=0; i<s.length; i++) {
			Monom m= new Monom(s[i]);
			this.add(m);
		}
	}

	/**
	 * get x, calculate and return the Polynom in this value
	 * @param x
	 */
	@Override
	public double f(double x) {
		Iterator<Monom> it= this.iteretor();
		double sum=0;
		while(it.hasNext()) {
			Monom m= it.next();
			sum+= m.f(x);
		}
		return sum;
	}

	@Override
	/**
	 * get Polynom p1 and add it to the current Polynom
	 */
	public void add(Polynom_able p1) {
		Iterator<Monom> it= p1.iteretor();
		while(it.hasNext()) {
			Monom ot= it.next();
			this.add(ot);
		}
	}

	@Override
	/**
	 * get a Monom m1 and add it to the Polynom
	 */
	public void add(Monom m1) {
		Iterator<Monom> it= this.iteretor();
		int p= m1.get_power();
		boolean flag= false;
		if(m1.isZero())
			flag=true;
		if(pol.isEmpty() && !flag) {
			pol.add(m1);
			flag= true;
		}
		else while(it.hasNext() && !flag) {
			Monom ot= it.next();
			if(ot.get_power()==p) {
				if(ot.get_coefficient()+m1.get_coefficient()==0) {
					it.remove();
					return;
				}
				ot.add(m1);
				flag= true;
			}
		}
		if(!flag) 
			pol.add(m1);
		pol.sort(comper);
	}

	/**
	 * get Polynom p1 and substract from the current Polynom
	 */
	@Override
	public void substract(Polynom_able p1) {
		Iterator<Monom> it= p1.iteretor();
		while(it.hasNext()) {
			Monom m= it.next();
			m.multByNumber(-1);
			this.add(m);
		}
	}

	/**
	 * get pOlynom p1 and multiply with the current Polynom
	 */
	@Override
	public void multiply(Polynom_able p1) {
		Polynom_able mult= this.copy();
		pol.clear();
		Iterator<Monom> it= mult.iteretor();
		while(it.hasNext()) {
			Monom m1= it.next();
			Iterator<Monom> it2= p1.iteretor();
			while(it2.hasNext()) {
				Monom m2= new Monom(it2.next());
				m2.multiply(m1);
				this.add(m2);
			}
		}
	}

	/**
	 * get Polynom p1 and check if P1 and the current Polynom are equals
	 */
	@Override
	public boolean equals(Polynom_able p1) {
		if(this.toString().equals(p1.toString()))
			return true;
		return false;
	}

	@Override
	/**
	 * Checks if the Polynom is a zero polynom 
	 */
	public boolean isZero() {
		Iterator<Monom> it= this.iteretor();
		while(it.hasNext()) {
			Monom m= it.next();
			if(!m.isZero())
				return false;
		}
		return true;
	}

	@Override
	/**
	 * get 2 values of x ,find and return the root of the function (with eps approximatly)
	 */
	public double root(double x0, double x1, double eps) {
		double right= Math.max(x0, x1);
		double left= Math.min(x0, x1);
		double mid= (right+left)/2;
		while(Math.abs(this.f(mid))>eps) {
			if(this.f(mid)>0)
				right= mid;
			else
				left= mid;
			mid= (right+left)/2;
		}
		return mid;
	}

	@Override
	/**
	 * create a copy of the polynom
	 */
	public Polynom_able copy() {
		return new Polynom(this);
	}

	/**
	 * return the derivative of the polynom (without changing the original polynom)
	 */
	@Override
	public Polynom_able derivative() {
		Polynom p= new Polynom();
		Iterator<Monom> it= this.iteretor();
		while(it.hasNext()) {
			Monom m= new Monom(it.next());
			m.derivative();
			p.add(m);
		}
		return p;
	}

	@Override
	/**
	 * get 2 values of x and returm the area between the function and x axis 
	 */
	public double area(double x0, double x1, double eps) {
		double sum=0;
		double right= Math.max(x0, x1);
		double left= Math.min(x0, x1);
		while(left<right) {
			left+= eps;
			if(this.f(left)>0)
				sum+= eps*this.f(left);
		}
		return sum;
	}

	/**
	 * return the iterator of polynom
	 */
	@Override
	public Iterator<Monom> iteretor() {
		Iterator<Monom> it= pol.iterator();
		return it;
	}

	/**
	 * return a string with the polynom
	 */
	public String toString() {
		Iterator<Monom> it= this.iteretor();
		String ans= "";
		if(this.isZero())
			ans= "0";
		else while(it.hasNext()) {
			
			ans= ans+ "+" + it.next().toString();
		}
		if(ans.indexOf("+")==0)
			ans= ans.substring(1, ans.length());
		ans= ans.replaceAll("\\+-","\\-");
		return ans;
	}

}
