package myMath;
import java.awt.Color;
import javax.swing.JFrame;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

public class LinePlotTest extends JFrame {
	/**
	 * A function that draws a graph with a connection between points
	 * @param pp - We get a polynomial that we draw as a graph
	 * @param a - start of the range 
	 * @param b - end of the range
	 */
	public LinePlotTest(Polynom pp, double a, double b) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 400);


		Polynom_able pp2= pp.derivative();
		DataTable data = new DataTable(Double.class, Double.class);
		DataTable data2 = new DataTable(Double.class, Double.class); 

		data2.add(a, pp.f(a));        //Local extreme point
		double z= 0;              //A variable that will preserve the last local extreme point

		//Move over the X points and find the extreme points and their Y values
		for (double x = a; x <= b; x+=0.12) {
			double y = pp.f(x);
			double xb= x-0.05;
			double xa= x+0.05;
			if(pp2.f(xb)*pp2.f(xa)<0) {        //We will check if there is an extreme point in the area
				x= pp2.root(xb, xa, 0.1);
				y= pp.f(x);
				data2.add(x, y);
			}
			else
				data.add(x, y);
			z=x;
		}
		data2.add(z, pp.f(z));

		XYPlot plot = new XYPlot(data, data2);
		getContentPane().add(new InteractivePanel(plot));
		LineRenderer lines = new DefaultLineRenderer2D();
		plot.setLineRenderers(data, lines);

		Color color = new Color(0.0f, 0.3f, 1.0f);
		Color color2 = new Color(1.0f, 0.3f, 0.0f);     //Specify another color to paint the extreme points

		plot.getPointRenderers(data).get(0).setColor(color);     //Paint all points
		plot.getPointRenderers(data2).get(0).setColor(color2);   //Extreme points coloring

		plot.getLineRenderers(data).get(0).setColor(color);    //Coloring connecting the dots
	}

	public static void main(String[] args) {
		Polynom pp= new Polynom("0.2x^4-1.5x^3+3.0x^2-x-5");
		double a= -2;       //a  is the right point of the range
		double b= 6;       // b is the left point of the range
		LinePlotTest frame = new LinePlotTest(pp,a,b);		
		frame.setVisible(true);
	}
}