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
	JButton[][] grid;
	private int joker;
	static JButton firstButton;
	static int firstX, firstY;
	Border customBorder;
	HashMap <String, Color> normalButtons = new HashMap<String, Color>();
	HashMap <String, Color> bonusButtons = new HashMap<String, Color>();
	HashMap <String, Color> allButtons = new HashMap<String, Color>();

	//normal button probability
	static int nbpercent = 96;
	
	public JButton createButton(Object key) {
		JButton b = new JButton((String) key);
		b.setBackground(allButtons.get(key));
		b.setOpaque(true);
		b.setBorder(customBorder);
		return b;
	}
	
	public Object randKey(HashMap<String, Color> hm) {
		Object[] values = hm.keySet().toArray();
		Object randomKey = values[randInt(0, values.length-1)];
		return randomKey;
	}
	
	
	public GUI(int width, int height) {
		try {
			//temas de compatibilidad para los botones
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) { }
		
		//Crea un borde personalizado para los botones
		//cada numero significa margenes, igual que en css el estilo margin
		Border baseBorder = new EmptyBorder(10, 10, 10, 10);
		Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
		
		//con esto se mezclan los estilos para lograr el boton con margen
		customBorder = new CompoundBorder(lineBorder, baseBorder);
		
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
		
		frame.setLayout(new GridLayout(width, height));
		grid = new JButton[width][height];
		JButton tmpbutton;
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				joker = randInt(0, 100);
				if (joker < nbpercent) {
					//muestra y registra un boton normal en el listener de eventos
					tmpbutton = createButton(randKey(normalButtons));
				} else {
					//los botones bonus no deberian tener action listener?
					tmpbutton = createButton(randKey(bonusButtons));
				}
				tmpbutton.addActionListener(this);
				tmpbutton.setActionCommand("Swap-"+x+"-"+y);
				frame.add(tmpbutton);
				grid[x][y] = tmpbutton;
			}
		}
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void twoButtonAction(JButton j, int x, int y){
		if (firstButton == null) {
			firstButton = j;
			firstX = x;
			firstY = y;
		} else {
			if (firstButton == j) {
				firstButton = null;
				firstX = 0;
				firstY = 0;
			} else {
				if ( ((Math.abs(firstX-x) == 1) && (firstY == y)) || ((firstX == x) && (Math.abs(firstY-y) == 1)) ){
					swapButton(firstX,firstY,x,y);
				}
				firstButton = null;
				firstX = 0;
				firstY = 0;
			}
		}
	}
	
	public void swapButton(int x1, int y1, int x2, int y2){
		JButton button1 = grid[x1][y1];
		JButton button2 = grid[x2][y2];
		button1.setBackground(allButtons.get(button2.getText()));
		button2.setBackground(allButtons.get(button1.getText()));
		String auxstr = button1.getText();
		button1.setText(button2.getText());
		button2.setText(auxstr);
		button1.setOpaque(true);
		button1.setBorder(customBorder);
		button2.setOpaque(true);
		button2.setBorder(customBorder);
		frame.pack();
		clearButtons(button1,x1,y1);
		clearButtons(button2,x2,y2);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	public void clearButtons(JButton j, int x, int y){		
		// Chequear iguales
		// En X:
		int xplus = x, xminus = x;
		int yplus = y, yminus = y;
		for (int x1=(x+1);x1<15;x1++){
			if (grid[x1][y].getText().equals(j.getText())){
				xplus = x1;
			}
			else{
				break;
			}
		}
		for (int x2=(x-1);x2>=0;x2--){
			if (grid[x2][y].getText().equals(j.getText())){
				xminus = x2;
			}
			else{
				break;
			}
		}
		// En Y:		
		for (int y1=(y+1);y1<15;y1++){
			if (grid[x][y1].getText().equals(j.getText())){
				yplus = y1;
			}
			else{
				break;
			}
		}
		for (int y2=(y-1);y2>=0;y2--){
			if (grid[x][y2].getText().equals(j.getText())){
				yminus = y2;
			}
			else{
				break;
			}
		}
		// Eliminear iguales
		// En X:
		if (xplus-xminus>=2){
			for (int i = xminus; i <= xplus; i++){
				grid[i][y].setBackground(Color.WHITE);
			}
		}
		// En Y:
		if (yplus-yminus>=2){
			for (int i = yminus; i <= yplus; i++){
				grid[x][i].setBackground(Color.WHITE);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		String[] params = action.split("-");
		JButton j = grid[Integer.parseInt(params[1])][Integer.parseInt(params[2])];
		twoButtonAction(j, Integer.parseInt(params[1]), Integer.parseInt(params[2]));
	}

}