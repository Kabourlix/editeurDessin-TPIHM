import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;


public class EllipseMode extends Mode{

	public EllipseMode(EditeurDessin editor) {
		super(editor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleMousePressed(MouseEvent e) {
		dessin = new EllipseItem(canvas,editor.getOutlineColor(), editor.getFillColor(), new Point(e.getX(),e.getY()));
		canvas.addItem(dessin);
	}

	@Override
	public void handleMouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		dessin.update(new Point(e.getX(),e.getY()));
	}

	@Override
	public String getModeName() {
		// TODO Auto-generated method stub
		return "ellipse";
	}

	@Override
	public void handleColorChanged(Color o, Color f) {
		// TODO Auto-generated method stub
		
	}


}
