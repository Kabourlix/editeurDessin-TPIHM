import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;

public class PathMode extends Mode{

	public PathMode(EditeurDessin editor) {
		super(editor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleMousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		dessin = new PathItem(canvas, editor.getOutlineColor(), editor.getFillColor(), new Point(e.getX(),e.getY()));
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
		return "path";
	}

	@Override
	public void handleColorChanged(Color o, Color f) {
		// TODO Auto-generated method stub
		
	}


}
