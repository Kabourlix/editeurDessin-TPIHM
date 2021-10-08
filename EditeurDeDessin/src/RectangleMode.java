import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;

public class RectangleMode extends Mode{
	
	//private CanvasItem dessin;
	
	public RectangleMode(EditeurDessin editor) {
		super(editor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleMousePressed(MouseEvent e) {
		dessin = new RectangleItem(canvas, editor.getOutlineColor(), editor.getFillColor(), new Point(e.getX(),e.getY()));
		canvas.addItem(dessin);
		
	}

	@Override
	public void handleMouseDragged(MouseEvent e) {
		dessin.update(new Point(e.getX(),e.getY()));
		
	}

	@Override
	public String getModeName() {
		// TODO Auto-generated method stub
		return "rectangle";
	}

	@Override
	public void handleColorChanged(Color o, Color f) {
		// Nothing todo
		
	}


}
