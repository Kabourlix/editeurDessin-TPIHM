import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

public class PathItem extends CanvasItem{

	public PathItem(PersistentCanvas c, Color o, Color f, Point p) {
		super(c, o, f);
		GeneralPath path = new GeneralPath();
		path.moveTo((float) p.x, (float) p.y);
		shape = path;
	}
	
	public PathItem(PathItem other) {
		super(other.canvas,other.outline,other.fill);
		shape = new GeneralPath(other.shape);
		isSelected = false;
	}

	@Override
	public CanvasItem duplicate() {
		// TODO Auto-generated method stub
		return canvas.addItem(new PathItem(this));
	}

	@Override
	public void update(Point p) {
		// TODO Auto-generated method stub
		GeneralPath path = (GeneralPath) shape;
		path.lineTo(p.getX(), p.getY());
		canvas.repaint();
		
	}

	@Override
	public void move(int dx, int dy) {
		// TODO Auto-generated method stub
		shape = AffineTransform.getTranslateInstance(dx, dy).createTransformedShape(shape);
		canvas.repaint();
		
	}
	
	@Override
	public void paint(Graphics2D g) {
		drawShape(g);
	}

}
