package tarea2lp;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;
import java.lang.Math;


public class GUI implements ActionListener{
	JFrame frame = new JFrame();
	BlockButton[][] grid;
	private int joker;
	static JButton firstButton;
	static int prevX = 0;
	static int prevY = 0;
	private Boolean buttonSelected;
	
	public final Border baseBorder = new EmptyBorder(10, 10, 10, 10);
	public final Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
	public final Border customBorder = new CompoundBorder(lineBorder, baseBorder);
	
	HashMap <String, Color> normalButtons = new HashMap<String, Color>();
	HashMap <String, Color> bonusButtons = new HashMap<String, Color>();
	HashMap <String, Color> allButtons = new HashMap<String, Color>();

	//normal button probability
	static int nbpercent = 96;
	
	@SuppressWarnings("serial")
	public class BlockButton extends JButton {
		public int x;
		public int y;
		
		public BlockButton(String key, int x, int y) {
			super(key);
			this.setBackground(allButtons.get(key));
			this.setOpaque(true);
			this.setBorder(customBorder);
			this.x = x;
			this.y = y;
		}
	}
	
	public BlockButton createButton(Object key, int x, int y) {
		BlockButton b = new BlockButton((String) key, x, y);
		b.addActionListener(this);
		return b;
	}
	
	public String randKey(HashMap<String, Color> hm) {
		Object[] values = hm.keySet().toArray();
		String randomKey = (String) values[randInt(0, values.length-1)];
		return randomKey;
	}
	
	
	public GUI(int width, int height) {
		try {
			//temas de compatibilidad para los botones
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) { }
		
		//aqui se agrega cada boton a un hashmap
		//con esto se simplifican las llamadas a los botones
		//y se asocia cada label del boton a su color
		normalButtons.put("R", Color.RED);
		normalButtons.put("B", Color.BLUE);
		normalButtons.put("O", Color.ORANGE);
		normalButtons.put("G", Color.GREEN);
		normalButtons.put("Y", Color.YELLOW);
		bonusButtons.put("$", Color.MAGENTA);
		bonusButtons.put("&", Color.CYAN);
		allButtons.putAll(normalButtons);
		allButtons.putAll(bonusButtons);
		buttonSelected = false;
		
		frame.setLayout(new GridLayout(height, width));
		grid = new BlockButton[height][width];
		String auxkey;
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				joker = randInt(0, 100);
				if (joker < nbpercent) {
					auxkey = randKey(normalButtons);
				} else {
					auxkey = randKey(bonusButtons);
				}
				//grid[y][x] = createButton("x"+x+"y"+y, x, y);
				grid[y][x] = createButton(auxkey, x, y);
				frame.add(grid[y][x]);
			}
		}
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public void checkSwap(BlockButton button){
		if (!buttonSelected) {
			prevX = button.x;
			prevY = button.y;
			buttonSelected = true;
		} else if ((Math.abs(button.x-prevX) == 1 && (button.y == prevY)) ||
			(Math.abs(button.y-prevY) == 1) && (button.x == prevX)) {
			swapButton(button.x, button.y, prevX, prevY);
			buttonSelected = false;
		}
	}
	
	public void swapButton(int x1, int y1, int x2, int y2){
		BlockButton button1 = grid[y1][x1];
		BlockButton button2 = grid[y2][x2];
		button1.setBackground(allButtons.get(button2.getText()));
		button2.setBackground(allButtons.get(button1.getText()));
		String auxstr = button1.getText();
		button1.setText(button2.getText());
		button2.setText(auxstr);
		button1.setOpaque(true);
		button1.setBorder(customBorder);
		button2.setOpaque(true);
		button2.setBorder(customBorder);
		button1.x = x1;
		button1.y = y1;
		button2.x = x2;
		button2.y = y2;
		frame.pack();
		clearButtons(button1);
		clearButtons(button2);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	public void clearButtons(BlockButton j){		
		// Chequear iguales
		// En X:
		int xplus = j.x, xminus = j.x;
		int yplus = j.y, yminus = j.y;
		for (int y1=(j.y+1);y1<15;y1++){
			if ( (grid[y1][j.x].getText().equals(j.getText())) || (grid[y1][j.x].getText().equals("$")) || (grid[y1][j.x].getText().equals("&")) ){
				yplus = y1;
			}
			else{
				break;
			}
		}
		for (int y2=(j.y-1);y2>=0;y2--){
			if (grid[y2][j.x].getText().equals(j.getText()) || (grid[y2][j.x].getText().equals("$")) || (grid[y2][j.x].getText().equals("&")) ){
				yminus = y2;
			}
			else{
				break;
			}
		}
		// En Y:		
		for (int x1=(j.x+1);x1<15;x1++){
			if (grid[j.y][x1].getText().equals(j.getText()) || (grid[j.y][x1].getText().equals("$")) || (grid[j.y][x1].getText().equals("&")) ){
				xplus = x1;
			}
			else{
				break;
			}
		}
		for (int x2=(j.x-1);x2>=0;x2--){
			if (grid[j.y][x2].getText().equals(j.getText()) || (grid[j.y][x2].getText().equals("$")) || (grid[j.y][x2].getText().equals("&")) ){
				xminus = x2;
			}
			else{
				break;
			}
		}
		// Eliminear iguales
		// En X:
		if (xplus-xminus>=2){
			for (int i = xminus; i <= xplus; i++){
				grid[j.y][i].setBackground(Color.WHITE);
			}
		}
		// En Y:
		if (yplus-yminus>=2){
			for (int i = yminus; i <= yplus; i++){
				grid[i][j.x].setBackground(Color.WHITE);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		BlockButton button =  (BlockButton) e.getSource();
		checkSwap(button);
	}

}
