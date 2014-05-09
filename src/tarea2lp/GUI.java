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
		JButton j1 = grid[x1][y1];
		JButton j2 = grid[x2][y2];
		grid[x1][y1] = createButton(j2.getText());
		grid[x1][y1].addActionListener(this);
		grid[x1][y1].setActionCommand("Swap-"+x1+"-"+y1);
		grid[x2][y2] = createButton(j1.getText());
		grid[x2][y2].addActionListener(this);
		grid[x2][y2].setActionCommand("Swap-"+x2+"-"+y2);
		frame.remove(j1);
		frame.remove(j2);
		
		
		for (int y = 0; y < 15; y++) {
			for (int x = 0; x < 15; x++) {
				frame.add(grid[x][y]);
			}
		}

		frame.pack();
		frame.setVisible(true);
	}
	
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		String[] params = action.split("-");
		JButton j = grid[Integer.parseInt(params[1])][Integer.parseInt(params[2])];
		twoButtonAction(j, Integer.parseInt(params[1]), Integer.parseInt(params[2]));
	}

}