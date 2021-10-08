import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;

public class LineItem extends CanvasItem{
	
	
	public LineItem(PersistentCanvas c, Color o, Color f, Point p) {
		super(c, o, f);
		shape = new Line2D.Float(p, p);
		firstpoint = p;
	}
	
	public LineItem(LineItem other) {
		super(other.canvas,other.outline,other.fill);
		shape = new Line2D.Float();
		((Line2D) shape).setLine((Line2D) other.shape);
		firstpoint = other.firstpoint;
		isSelected = false;
	}

	@Override
	public CanvasItem duplicate() {
		// TODO Auto-generated method stub
		return canvas.addItem(new LineItem(this));
	}

	@Override
	public void update(Point p) {
		((Line2D) shape).setLine(firstpoint.x,firstpoint.y, p.x,p.y);
		canvas.repaint();
		
	}

	@Override
	public void move(int dx, int dy) {
		Point2D.Float start = (Float) ((Line2D) shape).getP1();
		Point2D.Float end = (Float) ((Line2D) shape).getP2();
		start.x += dx;
		end.x += dx;
		start.y += dy;
		end.y += dy;
		((Line2D) shape).setLine(start, end);
		canvas.repaint();
	}

}
