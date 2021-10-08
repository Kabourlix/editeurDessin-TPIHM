
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.Dimension;
import javax.swing.border.LineBorder;

import java.awt.Color;

@SuppressWarnings("serial")
public class EditeurDessin extends JFrame {

	private JPanel contentPane;
	private Color currentFillColor = Color.BLUE;
	private Color currentOutlineColor = Color.BLACK;
	private PersistentCanvas canvas;
	
	private Mode mode;
	private Mode rectangleMode;
	private Mode ellipseMode;
	private Mode selectionMode;
	private Mode lineMode;
	private Mode pathMode;
	
	private JPanel fill;
	private JPanel outline;
	
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					EditeurDessin frame = new EditeurDessin();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PersistentCanvas getCanvas() {
		return canvas;
	}
	/**
	 * Create the frame.
	 */
	public EditeurDessin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		canvas = new PersistentCanvas();
		
		selectionMode = new SelectionMode(this);
		rectangleMode = new RectangleMode(this);
		ellipseMode = new EllipseMode(this);
		lineMode = new LineMode(this);
		pathMode = new PathMode(this);
		mode = selectionMode;
		this.setTitle("Graphical Editor (" + mode.getModeName() + ")");
		
		
		/////////////////////////// CENTER PANEL ///////////////////////////////
		
		canvas.setBackground(Color.WHITE);
		contentPane.add(canvas, BorderLayout.CENTER);
		
		canvas.addMouseListener(new MouseAdapter() { // pas de lambda expression car MouseMotionListener n'est pas une interface fonctionnelle
			@Override
			public void mousePressed(MouseEvent e) {
				mode.handleMousePressed(e);
			}
		});
		
		canvas.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				mode.handleMouseDragged(e);
			}
		});
		
		canvas.addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				// TODO Auto-generated method stub
				if(mode.getModeName().equals("selection")) {
					((SelectionMode) mode).handleMouseWheel(e);
				}
				
			}
		});
		
		/////////////////////////////////////////////////////////////////////////
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		ButtonGroup btGroup = new ButtonGroup();
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Select/Move");
		panel_1.add(rdbtnNewRadioButton_1);
		
		rdbtnNewRadioButton_1.setSelected(true);;

		btGroup.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Rectangle");
		panel_1.add(rdbtnNewRadioButton);
		btGroup.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Ellipse");
		panel_1.add(rdbtnNewRadioButton_2);
		btGroup.add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("Line");
		panel_1.add(rdbtnNewRadioButton_3);
		btGroup.add(rdbtnNewRadioButton_3);
		
		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("Path");
		panel_1.add(rdbtnNewRadioButton_4);
		btGroup.add(rdbtnNewRadioButton_4);
		
		
		//Add the listeners
		// ! If you select a shape and then click on another mode, the shape is still considered as selected.
		rdbtnNewRadioButton_1.addActionListener(e -> {
			setMode(selectionMode);
			});
		rdbtnNewRadioButton.addActionListener(e -> {
			setMode(rectangleMode);
			});
		
		rdbtnNewRadioButton_2.addActionListener(e -> {
			setMode(ellipseMode);
		});
		rdbtnNewRadioButton_3.addActionListener(e -> {
			setMode(lineMode);
		});
		rdbtnNewRadioButton_4.addActionListener(e -> {
			setMode(pathMode);
		});
		
		
		
		
		//////////////////////////////////////////
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panel_1.add(verticalStrut);
		
		JLabel lblNewLabel_1 = new JLabel("Fill color:");
		panel_1.add(lblNewLabel_1);
		
		Component verticalStrut_3 = Box.createVerticalStrut(5);
		panel_1.add(verticalStrut_3);
		
		fill = new JPanel(); // We need access for selection mode
		fill.setBorder(new LineBorder(Color.BLACK));
		fill.setMaximumSize(new Dimension(250, 15));
		fill.setBackground(currentFillColor);
		panel_1.add(fill);
		
		fill.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				currentFillColor = JColorChooser.showDialog(contentPane, "Choose the fill color", currentFillColor);
				fill.setBackground(currentFillColor);
				mode.handleColorChanged(currentOutlineColor, currentFillColor);
			}
		});
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel_1.add(verticalStrut_1);
		
		JLabel lblNewLabel = new JLabel("Outline color :");
		panel_1.add(lblNewLabel);
		
		Component verticalStrut_4 = Box.createVerticalStrut(5);
		panel_1.add(verticalStrut_4);
		
		outline = new JPanel(); // We need access for selection mode
		outline.setBorder(new LineBorder(Color.BLACK));
		outline.setMaximumSize(new Dimension(250, 15));
		outline.setBackground(Color.BLACK);
		panel_1.add(outline);
		
		outline.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				currentOutlineColor = JColorChooser.showDialog(contentPane, "Choose the fill color", currentOutlineColor);
				outline.setBackground(currentOutlineColor);
				mode.handleColorChanged(currentOutlineColor, currentFillColor);
			}
		});
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		panel_1.add(verticalStrut_2);
		
		
		
		///////////////////////////
		btnNewButton = new JButton("Delete");
		btnNewButton_1 = new JButton("Clone");
		btnNewButton.setEnabled(false);
		btnNewButton_1.setEnabled(false);
		
		panel_1.add(btnNewButton); //Delete
		panel_1.add(btnNewButton_1); //Clone
		
		// ! They must be desactivated if an item is selected and we change mode.
		//We add the listeners, we must be on selection mode with a shape selected to be able to click on the button (Context)
		btnNewButton.addActionListener(e -> {
			canvas.removeItem(((SelectionMode) mode).getSelectedDessin());
			setDeleteAndCloneButtonOff();
		});
		
		btnNewButton_1.addActionListener(e ->{
			CanvasItem currentDessin = ((SelectionMode) mode).getSelectedDessin();
			currentDessin.deselect();
			CanvasItem duplicata = currentDessin.duplicate();
			duplicata.move(15, 15);
			duplicata.select();
		});
		
	}
	
	public Color getOutlineColor() {return currentOutlineColor;}
	public Color getFillColor() {return currentFillColor;}
	public void updateOutlineColor(Color c) {
		currentOutlineColor = c;
		outline.setBackground(c);
		}
	public void updateFillColor(Color c) {
		currentFillColor = c;
		fill.setBackground(c);
		}
	
	public void setDeleteAndCloneButtonOn() {
		btnNewButton.setEnabled(true);
		btnNewButton_1.setEnabled(true);
	}
	
	public void setDeleteAndCloneButtonOff() {
		btnNewButton.setEnabled(false);
		btnNewButton_1.setEnabled(false);
	}
	
	private void setMode(Mode m) {
		if (mode.getModeName().equals("selection")) {
			try {
			((SelectionMode) mode).getSelectedDessin().deselect();
			}catch (Exception e) {
				// On ne fait rien, juste rien n'est déselectionné, ce n'est pas très propre mais ben
			}
		}
		mode = m;
		this.setTitle("Graphical Editor ("+ mode.getModeName()+")");
	}

}