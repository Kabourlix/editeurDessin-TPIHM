
import java.awt.Color;
import java.awt.event.MouseEvent;

public abstract class Mode {

	protected EditeurDessin editor;
	protected PersistentCanvas canvas;
	protected CanvasItem dessin;

	public Mode(EditeurDessin editor) {
		this.editor = editor;
		this.canvas = editor.getCanvas();
	}
	
	public abstract void handleMousePressed(MouseEvent e);
	
	public abstract void handleMouseDragged(MouseEvent e);
	
	public abstract String getModeName();
	
	public abstract void handleColorChanged(Color o, Color f);
}
