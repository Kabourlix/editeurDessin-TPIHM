import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;

public class LineMode extends Mode{

	public LineMode(EditeurDessin editor) {
		super(editor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleMousePressed(MouseEvent e) {
		dessin = new LineItem(canvas, editor.getOutlineColor(), editor.getFillColor(), new Point(e.getX(),e.getY()));
		canvas.addItem(dessin);
		
	}

	@Override
	public void handleMouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("in MousDragged" + new Point(e.getX(),e.getY()).toString());
		dessin.update(new Point(e.getX(),e.getY()));
	}

	@Override
	public String getModeName() {
		// TODO Auto-generated method stub
		return "line";
	}

	@Override
	public void handleColorChanged(Color o, Color f) {
		// TODO Auto-generated method stub
		
	}


}
