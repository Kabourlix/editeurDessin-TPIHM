import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class EllipseItem extends CanvasItem{
	
	//Point firstpoint;
	
	public EllipseItem(PersistentCanvas c, Color o, Color f, Point p) {
		super(c, o, f);
		shape = new Ellipse2D.Float(p.x,p.y,0,0); // Initialize a null ellipse at coordinate (x,y)
		firstpoint = p;
	}
	
	public EllipseItem(EllipseItem other) {
		super(other.canvas, other.outline, other.fill);
		firstpoint = other.firstpoint;
		Rectangle frame = other.getShape().getBounds(); //frame that contains the selected ellipse
		shape = new Ellipse2D.Float(firstpoint.x,firstpoint.y,0,0); //Create a null ellipse
		((Ellipse2D) shape).setFrame(frame);//adapt it to the same frame
		isSelected = false;
	}
	@Override
	public CanvasItem duplicate() {
		// TODO Auto-generated method stub
		return canvas.addItem(new EllipseItem(this));
	}

	@Override
	public void update(Point p) {
		// TODO Auto-generated method stub
		((Ellipse2D) shape).setFrameFromDiagonal(firstpoint, p);
		canvas.repaint();
		
	}

	@Override
	public void move(int dx, int dy) {
		//Point center = new Point((int) ((Ellipse2D) shape).getCenterX(), (int) ((Ellipse2D) shape).getCenterY());
		Rectangle newBounds = ((Ellipse2D) shape).getBounds();
		newBounds.x += dx;
		newBounds.y += dy;
		
		((Ellipse2D) shape).setFrame(newBounds);

		canvas.repaint();
		
	}
}
