import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class SelectionMode extends Mode{

	private Point initialPoint;
	public SelectionMode(EditeurDessin editor) {
		super(editor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleMousePressed(MouseEvent e) {
		try {
			initialPoint = new Point(e.getX(),e.getY());
			CanvasItem oldDessin = dessin; // À la première selection, c'est null
			dessin = canvas.getItemAt(initialPoint); // prend une valeur
			
			if (oldDessin != null) { // We have already selected a shape before.
				if (dessin == null) { // The user click outside a shape (deselecting any shape)
					editor.setDeleteAndCloneButtonOff();
					oldDessin.deselect(); 
					return;
				}
				if (!dessin.equals(oldDessin)) { // The user select another shape while he had selected another one before.
					oldDessin.deselect();
				}
				else { // The user selected the same shape as before.
					return;
				}
			}
			if (dessin == null) {
				return;
			}
			dessin.select(); // If the user select no shape at the first iteration, then it throw a null exception that is catched. 
			editor.setDeleteAndCloneButtonOn(); // I activate the button to delete or clone the shape selected.
			Color o = dessin.getOutlineColor();
			Color f = dessin.getFillColor();
			
			editor.updateOutlineColor(o);
			editor.updateFillColor(f);
		}catch (Exception e1){
			System.out.println(e1.getMessage());
		}
		
		
	}

	@Override
	public void handleMouseDragged(MouseEvent e) { 
		// TODO Auto-generated method stub
		try {
			Point currentPoint = new Point(e.getX(),e.getY());
			int dx = currentPoint.x - initialPoint.x;
			int dy = currentPoint.y - initialPoint.y;
			initialPoint = currentPoint;
			dessin.move(dx,dy);
		}catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public String getModeName() {
		// TODO Auto-generated method stub
		return "selection";
	}
	
	@Override
	public void handleColorChanged(Color o, Color f) {
		try {
			dessin.setFillColor(f);
			dessin.setOutlineColor(o);
		}catch (Exception e) {
			System.out.println("No item is selected.");
		}
	}
	
	public CanvasItem getSelectedDessin() {
		return dessin;
	}
	
	public void handleMouseWheel(MouseWheelEvent e) {
		canvas.changePositionInList(dessin, (int) e.getPreciseWheelRotation());
	}

	

}
