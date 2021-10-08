
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.ArrayList;


@SuppressWarnings("serial")
class PersistentCanvas extends Component {

	private ArrayList<CanvasItem> items;
	private CanvasItem toGet; // Temporary variable for finding an item in the list above

	PersistentCanvas() {
		items = new ArrayList<CanvasItem>();
	}

	public CanvasItem getItemAt(Point p) {
		// TODO pick the 2D item under the Point p
		// You can use the function contain(Point p) of each CanvasItem
		toGet = null;
		
		items.forEach(item -> {
			Rectangle hitBox = new Rectangle(p.x-5, p.y-5, 10, 10);
			//boolean condition = item.shape.intersects(hitBox);
			toGet =  item.shape.intersects(hitBox) ? item : toGet;}); //It should get the item at the top since its a stack. 
		return toGet;
	}
	

	public CanvasItem addItem(CanvasItem item) {
		if (item == null)
			return null;
		items.add(item);
		repaint();
		return item;
	}

	public void removeItem(CanvasItem item) {
		if (item == null)
			return;
		items.remove(item);
		repaint();
	}
	
	public void changePositionInList(CanvasItem item, int d) {
		int index = 0;
		for(int i = 0;i<items.size();i++) {
			if(items.get(i)==item) {
				break;
			}
			index++;
		}
		if(index == 0&&d==-1) { //Si on veut remonter un item déjà tout en haut on ne fait rien
			return;
		}else if(index==(items.size()-1)&&d==1) {
			return;
		}else {
			CanvasItem a = items.get(index);
			CanvasItem b = items.get(index+d);
			items.set(index, b);
			items.set(index+d, a);
			this.repaint();
		}
		
	}
	
	public void paint(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		for (CanvasItem item : items)
			item.paint(g);
	}

}
