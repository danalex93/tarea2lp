package tarea2lp;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;

public class GUIEngine {
	JFrame frame = new JFrame();
	private int height;
	private int width;
	
	public void loadGrid(Bloque[][] grid) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				frame.add(grid[y][x].getButton());
			}
		}
	}
	
	public void updateGrid() {
		frame.pack();
		frame.setVisible(true);
	}
	
	public void addComponent(Component comp) {
		frame.add(comp);
	}
	
	public void removeComponent(Component comp) {
		frame.remove(comp);
	}
	
	public void addComponent(JLabel[] comp) {
		for (JLabel c : comp) {
			addComponent(c);
		}
	}
	
	public void msgbox(String s){
		JOptionPane.showMessageDialog(null, s);
	}
	
	public GUIEngine() {
		height = 15;
		width = 15;
		
		try {
			//temas de compatibilidad para los botones
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) { }
		
		//presets para los bordes de botones de bloques
		Bloque.baseBorder = new EmptyBorder(10, 10, 10, 10);
		Bloque.lineBorder = BorderFactory.createLineBorder(Color.BLACK);
		Bloque.customBorder = new CompoundBorder(Bloque.lineBorder, Bloque.baseBorder); 
		
		frame.setLayout(new GridLayout(height+1, width));
		//loadGrid(initialGrid);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	
}
