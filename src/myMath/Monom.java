
package myMath;

import javax.management.RuntimeErrorException;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{

	/**
	 * constractor function - gets 2 parameters and create new monom
	 * @param a - coefficient
	 * @param b - power
	 */
	public Monom(double a, int b) {
		if(b<0)
			throw new RuntimeException("ERR power illigal");
		this.set_coefficient(a);
		this.set_power(b);
	}

	/**
	 * copy constractor- gets a monom and copy it
	 * @param ot - monom that we want to copy
	 */
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	/**
	 * constractor function - gets a string and create new monom
	 * @param m - string monom
	 */
	public Monom(String m) {
		if(!m.contains("x"))    // if only number
			m+= "x^0";
		if(!m.contains("^"))    // if there is no power
			m+= "^1";
		if(m.indexOf("x")==0)   // if the coefficient=1
			m= "1"+m;
		String s1= m.substring(0, m.indexOf("x"));             // create the string coefficient
		String s2= m.substring(m.indexOf("^")+1, m.length());  // create the string power
		if(s1.equals("-"))    // if the coefficient= -1
			s1= "-1";
		this.set_coefficient(Double.parseDouble(s1));  // set the coefficient
		this.set_power(Integer.parseInt(s2));          // set the power
	}

	public double get_coefficient() {
		return this._coefficient;
	}
	public int get_power() {
		return this._power;
	}

	/**
	 * get x, calculate and return the monom in this value
	 */
	public double f(double x) {
		return _coefficient*Math.pow(x, _power);
	}

	/**
	 * get a monom and add it to the exist one
	 * @param other
	 */
	public void add(Monom other) {
		if(this._power != other.get_power())    // if the powers are different , cant add and throw error
			throw new IllegalArgumentException("Exponents must match in order to add");
		this.set_coefficient(_coefficient + other.get_coefficient());
	}

	/**
	 * get a monom and multiply it to the exist one
	 * @param other
	 */
	public void multiply(Monom other) {
		this.set_coefficient(_coefficient*other.get_coefficient());
		this.set_power(_power+other.get_power());
	}

	/**
	 * get a number and multiply it whit the monom
	 * @param num
	 */
	public void multByNumber(int num) {
		this.set_coefficient(_coefficient*num);
	}

	/**
	 * derivative the monom
	 */
	public void derivative() {
		this.set_coefficient(_coefficient*this._power);
		this.set_power(_power-1);
	}

	/**
	 * checks if this is a zero monom
	 * @return
	 */
	public boolean isZero() {
		return this.get_coefficient()==0;
	}


	/**
	 * return a string with the monom
	 */
	public String toString() {
		String ans= "";
		if(this.isZero())
			ans= "0";
		else if(this.get_power()==0) {
			if(this.get_coefficient()==1)
				ans= "1";
			else if(this.get_coefficient()==-1)
				ans= "-1";
			else
				ans+= this.get_coefficient();
		}
		else {
			if(this.get_coefficient()==-1)
				ans+= "-";
			if(this.get_coefficient()!=1 && this.get_coefficient()!=-1)
				ans+= this.get_coefficient();
			if(this.get_power()>0) {
				ans+= "x";
				if(this.get_power()>1)
					ans+= "^"+ this.get_power();
			}
		}
		return ans;
	}

	//****************** Private Methods and Data *****************

	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		this._power = p;
	}

	private double _coefficient; // 
	private int _power; 
}
