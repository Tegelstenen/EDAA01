package mountain;

import java.util.HashMap;

import fractal.Fractal;
import fractal.TurtleGraphics;
import mountain.RandomUtilities;
import mountain.Side;

public class Mountain extends Fractal {
	private int length, dev;
	private Point a, b, c;
	private Side ab, bc, ca;
	private HashMap<Side, Point> dict;

	public Mountain(int length, int dev) {
		super();
		this.length = length;
		this.dev = dev;
		this.a = new Point(600 / 2 - length / 2, 600 / 2 + length / 4);
		this.b = new Point(a.getX() + length, a.getY() + 100);
		this.c = new Point(a.getX() + length / 3, a.getY() - length);
		this.ab = new Side(a, b);
		this.bc = new Side(b, c);
		this.ca = new Side(c, a);
		this.dict = new HashMap<Side, Point>();
	}

	@Override
	public String getTitle() {
		return "Skewed Mountain";
	}

	@Override
	public void draw(TurtleGraphics turtle) {
		turtle.moveTo(a.getX(), a.getY());
		fractalTriangle(turtle, order, a, b, c, dev);
		dict.clear();
	}

	private void fractalTriangle(TurtleGraphics turtle, int order, Point a, Point b, Point c, int dev) {
		if (order == 0) {
			turtle.moveTo(a.getX(), a.getY());
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());
		} else {
	        Point abMid = getOrCreateMidpoint(a, b, dev);
	        Point bcMid = getOrCreateMidpoint(b, c, dev);
	        Point caMid = getOrCreateMidpoint(c, a, dev);

	        fractalTriangle(turtle, order - 1, a, abMid, caMid, dev / 2);
	        fractalTriangle(turtle, order - 1, abMid, b, bcMid, dev / 2);
	        fractalTriangle(turtle, order - 1, caMid, bcMid, c, dev / 2);
	        fractalTriangle(turtle, order - 1, abMid, bcMid, caMid, dev / 2);
	    }
	}

	private Point getOrCreateMidpoint(Point p1, Point p2, int dev) {
	    Side side = new Side(p1, p2);
	    if (!dict.containsKey(side)) {
	        Point mid = midPoint(p1, p2, dev);
	        dict.put(side, mid);
	        return mid;
	    }
	    
	    return dict.remove(side);
	}

	private Point midPoint(Point p1, Point p2, int dev) {
	    int deviate = (int) RandomUtilities.randFunc(dev);
	    int xMid = (p1.getX() + p2.getX()) / 2;
	    int yMid = (p1.getY() + p2.getY()) / 2 + deviate; // apply deviation to y-coordinate
	    return new Point(xMid, yMid);
	}


}
