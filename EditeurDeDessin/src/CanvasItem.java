
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Color;
import java.awt.Point;
import java.awt.Stroke;

import java.awt.BasicStroke;

abstract class CanvasItem {

	protected PersistentCanvas canvas;
	protected Color outline, fill;
	protected Shape shape;
	protected Boolean isSelected;
	
	protected Point firstpoint;

	public CanvasItem(PersistentCanvas c, Color o, Color f) {
		canvas = c;
		fill = f;
		outline = o;
		shape = null;
		isSelected = false;
	}

	public void setOutlineColor(Color c) {
		outline = c;
		canvas.repaint();
	}

	public void setFillColor(Color c) {
		fill = c;
		canvas.repaint();
	}
	
	public Color getOutlineColor() {return outline;}
	public Color getFillColor() {return fill;}
	public Shape getShape() {return shape;}

	public void select() {
		isSelected = true;
		canvas.repaint();
	}

	public void deselect() {
		isSelected = false;
		canvas.repaint();
	}

	protected void fillShape(Graphics2D g) {
		g.setColor(fill);
		g.fill(shape);
	}

	protected void drawShape(Graphics2D g) {
		Stroke oldstrk = null;
		if (isSelected) {
			oldstrk = g.getStroke();
			g.setStroke(new BasicStroke(2));
		}
		g.setColor(outline);
		g.draw(shape);
		if (oldstrk != null)
			g.setStroke(oldstrk);
	}

	public void paint(Graphics2D g) {
		fillShape(g);
		drawShape(g);
	}

	public Boolean contains(Point p) {
		return shape.contains(p);
	}
	
	public boolean isSelect() {
		return isSelected;
	}

	public abstract CanvasItem duplicate();

	public abstract void update(Point p);

	public abstract void move(int dx, int dy);

}
