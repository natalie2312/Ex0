package myMath;

public class Point {

	private double x, y;

	public double getX() {

		return x;
	}
	public double getY() {
		
		return y;
	}
	
	public void setX(double x) {
		this.x=x;
	}
	
	public void setY(double y) {
		this.y=y;
	}
	
	public Point(double x, double y) {
		setX(x);//this.x=x;
		setY(y);//this.y=y;
	}
	
	public Point(Point p) {
		setX(p.getX());       //this.x=p.getX();
		setY(p.getY()); 
		}

	public String toString() {
		return "(" + this.getX() + "," + this.getY()  +")" ;
	}

}
