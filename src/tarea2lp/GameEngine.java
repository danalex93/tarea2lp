package tarea2lp;


import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.JLabel;



public class GameEngine implements ActionListener {
	private Bloque[][] boardGrid;
	int breakPercent = 95;
	private static final ColorCreator colorFactory = new ColorCreator();
	private static final ComodinCreator comodinFactory = new ComodinCreator();
	private Bloque prevBlock;
	private Boolean blockSelected;
	private int height;
	private int width;
	private GUIEngine gui;
	
	// Estadisticas de juego
	private static Integer[] estadisticas = {0,0,0,0,0};//R,B,O,G,Y
	private static JLabel[] estadisticasLabel = new JLabel[5];

	
	public Bloque createBlock() {
		int percentage = randInt(0, 100);
		if (percentage <= breakPercent) {
			return colorFactory.crearBloque();
		} else {
			return comodinFactory.crearBloque();
		}
	}
	
	public Bloque[][] getGrid() {
		return boardGrid;
	}
	
	public void setGrid(Bloque[][] g) {
		boardGrid = g;
	}
	
	
	private void initEstadisticasLabel() {
		estadisticasLabel[0] = new JLabel("R: "+estadisticas[0]);
		estadisticasLabel[1] = new JLabel("B: "+estadisticas[1]);
		estadisticasLabel[2] = new JLabel("O: "+estadisticas[2]);
		estadisticasLabel[3] = new JLabel("G: "+estadisticas[3]);
		estadisticasLabel[4] = new JLabel("Y: "+estadisticas[4]);
		gui.addComponent(estadisticasLabel);
	}
	
	private void setEstadisticasLabel(){
		estadisticasLabel[0].setText("R: "+estadisticas[0]);
		estadisticasLabel[1].setText("B: "+estadisticas[1]);
		estadisticasLabel[2].setText("O: "+estadisticas[2]);
		estadisticasLabel[3].setText("G: "+estadisticas[3]);
		estadisticasLabel[4].setText("Y: "+estadisticas[4]);
	}
	
	public GameEngine() {
		height = 15;
		width = 15;
		
		boardGrid = new Bloque[height][width];
		gui = new GUIEngine();
		Bloque.setListener(this);
		int y, x;
		y = 0; x = 0;
		for (y = 0; y < height; y++) {
			for ( x = 0; x < width; x++) {
				boardGrid[y][x] = createBlock();
				boardGrid[y][x].setCoords(x, y);	
			}
		}
		System.out.println(x + " " + y);
		gui.loadGrid(boardGrid);
		initEstadisticasLabel();
		gui.updateGrid();
		blockSelected = false;
	}
	
	public void checkSwap(Bloque bloque){
		if (!blockSelected) {
			prevBlock = bloque;
			blockSelected = true;
		} else if ((Math.abs(bloque.x-prevBlock.x) == 1 && (bloque.y == prevBlock.y)) ||
			(Math.abs(bloque.y-prevBlock.y) == 1) && (bloque.x == prevBlock.x)) {
			swapButton(bloque, prevBlock);
			blockSelected = false;
		} else {
			blockSelected = false;
		}
	}
	
	public void swapButton(Bloque bloque1, Bloque bloque2) {
		int auxX, auxY;
		
		boardGrid[bloque1.y][bloque1.x] = bloque2;
		boardGrid[bloque2.y][bloque2.x] = bloque1;
		
		auxX = bloque1.x; auxY = bloque1.y;
		bloque1.setCoords(bloque2.x, bloque2.y);
		bloque2.setCoords(auxX, auxY);
		
		BlockButton button1 = bloque1.getButton();
		BlockButton button2 = bloque2.getButton();
		
		Color auxbackground = button1.getBackground();
		String auxtext = button1.getText();
		
		button1.setText(button2.getText());
		button1.setBackground(button2.getBackground());
		button2.setText(auxtext);
		button2.setBackground(auxbackground);
		auxX = button1.xCoord;
		auxY = button1.yCoord;
		button1.xCoord = button2.xCoord;
		button1.yCoord = button2.yCoord;
		button2.xCoord = auxX;
		button2.yCoord = auxY;
		
		bloque1.setButton(button2);
		bloque2.setButton(button1);
		
		for (int y=0;y<height;y++){
			for (int x=0;x<width;x++){
				if ((boardGrid[y][x].getColor().equals("-")) || 
					(boardGrid[y][x].getColor().equals("$")) || 
					(boardGrid[y][x].getColor().equals("&"))) {
					continue;
				}
				clearButtons(boardGrid[y][x]);
			}
		}
		fillWhites();
		gui.updateGrid();
		
		if (isGameOver()){
			gui.msgbox("Game Over!");
			System.exit(0);
		}
		
	}
	
	public void swapWhite(Bloque bloque1, Bloque bloque2){
		int auxX, auxY;
		
		boardGrid[bloque1.y][bloque1.x] = bloque2;
		boardGrid[bloque2.y][bloque2.x] = bloque1;
		
		auxX = bloque1.x; auxY = bloque1.y;
		bloque1.setCoords(bloque2.x, bloque2.y);
		bloque2.setCoords(auxX, auxY);
		
		BlockButton button1 = bloque1.getButton();
		BlockButton button2 = bloque2.getButton();
		
		button1.setText(button2.getText());
		button1.setBackground(button2.getBackground());
		button2.setText("-");
		button2.setBackground(Color.WHITE);
		auxX = button1.xCoord;
		auxY = button1.yCoord;
		button1.xCoord = button2.xCoord;
		button1.yCoord = button2.yCoord;
		button2.xCoord = auxX;
		button2.yCoord = auxY;
		
		bloque1.setButton(button2);
		bloque2.setButton(button1);
		
		
		
		gui.updateGrid();
		//frame.pack();
		//frame.setVisible(true);
	}
	
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	public void fillWhites() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (boardGrid[y][x].getColor().equals("-")){
					boardGrid[y][x] = createBlock();
					boardGrid[y][x].setCoords(x, y);
				}
			}
		}
	}
	
	public Boolean isGameOver(){
		for (int y=14;y>=0;y--){
			for (int x=0;x<15;x++){
				Bloque aux = boardGrid[y][x];
				if (aux.getColor().equals("-")){
					continue;
				}
				
				//Revisar siguiente
				if (x+1 < 15){
					if (aux.getColor().equals(boardGrid[y][x+1].getColor())){
						if (x+3 < 15){ // Sub-subsiguiente
							if (aux.getColor().equals(boardGrid[y][x+3].getColor()))
								return false;
						}
						if (x-2 >= 0){ // Ante-anterior
							if (aux.getColor().equals(boardGrid[y][x-2].getColor()))
								return false;
						}
						if ((x+2 < 15) && (y-1 >= 0)){ //Subsiguiente-arriba
							if (aux.getColor().equals(boardGrid[y-1][x+2].getColor()))
								return false;
						}
						if ((x+2 < 15) && (y+1 < 15)){ //Subsiguiente-abajo
							if (aux.getColor().equals(boardGrid[y+1][x+2].getColor()))
								return false;
						}
						if ((x-1 >= 0) && (y-1 >= 0)){ //Anterior-arriba
							if (aux.getColor().equals(boardGrid[y-1][x-1].getColor()))
								return false;
						}
						if ((x-1 >= 0) && (y+1 < 15)){//Anterior-abajo
							if (aux.getColor().equals(boardGrid[y+1][x-1].getColor()))
								return false;
						}
					}
				}
				
				//Revisar arriba
				if (x+1 < 15){
					if (aux.getColor().equals(boardGrid[y][x+1].getColor())){
						if (y-3 >= 0){ // Sup-supsuperior
							if (aux.getColor().equals(boardGrid[y-3][x].getColor()))
								return false;
						}
						if (y+2 < 15){ // Inf-inferior
							if (aux.getColor().equals(boardGrid[y+2][x].getColor()))
								return false;
						}
						if ((y-2 >= 0) && (x+1 < 15)){ //Sup-superior-derecha
							if (aux.getColor().equals(boardGrid[y-2][x+1].getColor()))
								return false;
						}
						if ((y-2 >= 0) && (x-1 >= 0)){ //Sup-superior-izquierda
							if (aux.getColor().equals(boardGrid[y-2][x-1].getColor()))
								return false;
						}
						if ((y+1 < 15) && (x+1 < 15)){ //Inferior-derecha
							if (aux.getColor().equals(boardGrid[y+1][x+1].getColor()))
								return false;
						}
						if ((y+1 < 15) && (x-1 >= 0)){//Inferior-izquierda
							if (aux.getColor().equals(boardGrid[y+1][x-1].getColor()))
								return false;
						}
					}
				}
				
				//Revisar sub siguiente
				if (x+2 < 15){
					if (aux.getColor().equals(boardGrid[y][x+2].getColor())){
						if ((y-1 >= 0) && (x+1 < 15)){ // derecha-arriba
							if (aux.getColor().equals(boardGrid[y-1][x+1].getColor()))
								return false;
						}
						if ((y+1 < 15) && (x+1 < 15)){ // derecha-abajo
							if (aux.getColor().equals(boardGrid[y+1][x+1].getColor()))
								return false;
						}
					}
				}
				
				//Revisar sup superior
				if (y-2 >= 0){
					if (aux.getColor().equals(boardGrid[y-2][x].getColor())){
						if ((y-1 >= 0) && (x+1 < 15)){ // arriba-derecha
							if (aux.getColor().equals(boardGrid[y-1][x+1].getColor()))
								return false;
						}
						if ((y-1 >= 0) && (x-1 >= 0)){ // arriba-izquerda
							if (aux.getColor().equals(boardGrid[y-1][x-1].getColor()))
								return false;
						}
					}
				}
				
			}
		}
		return true;
	}
	
	
	
	public void clearButtons(Bloque bloque){
		// Chequear iguales
		// En X:
		String colour = bloque.getColor();
		String bonus1 = "1";
		String bonus2 = "2";
		int xplus = bloque.x, xminus = bloque.x;
		int yplus = bloque.y, yminus = bloque.y;
		for (int y1=(bloque.y+1);y1<height;y1++){
			if ( (boardGrid[y1][bloque.x].getColor().equals(bloque.getColor())) || (boardGrid[y1][bloque.x].getColor().equals(bonus1)) || (boardGrid[y1][bloque.x].getColor().equals(bonus2)) ){
				yplus = y1;
			}
			else{
				break;
			}
		}
		for (int y2=(bloque.y-1);y2>=0;y2--){
			if (boardGrid[y2][bloque.x].getColor().equals(bloque.getColor()) || (boardGrid[y2][bloque.x].getColor().equals(bonus1)) || (boardGrid[y2][bloque.x].getColor().equals(bonus2)) ){
				yminus = y2;
			}
			else{
				break;
			}
		}
		// En Y:		
		for (int x1=(bloque.x+1);x1<height;x1++){
			if (boardGrid[bloque.y][x1].getColor().equals(bloque.getColor()) || (boardGrid[bloque.y][x1].getColor().equals(bonus1)) || (boardGrid[bloque.y][x1].getColor().equals(bonus2)) ){
				xplus = x1;
			}
			else{
				break;
			}
		}
		for (int x2=(bloque.x-1);x2>=0;x2--){
			if (boardGrid[bloque.y][x2].getColor().equals(bloque.getColor()) || (boardGrid[bloque.y][x2].getColor().equals(bonus1)) || (boardGrid[bloque.y][x2].getColor().equals(bonus2)) ){
				xminus = x2;
			}
			else{
				break;
			}
		}
		// Eliminar iguales
		Integer total = 0;
		
		// En X:
		if (xplus-xminus>=2){
			for (int i = xminus; i <= xplus; i++){
				boardGrid[bloque.y][i].setDummy();
				for (int r=bloque.y;r>0;r--){
					if(boardGrid[r-1][i].getColor().equals("-"))
						break;
					swapWhite(boardGrid[r][i],boardGrid[r-1][i]); 
				}
			}
			total += (xplus-xminus) + 1;
		}
		// En Y:
		if (yplus-yminus>=2){
			for (int i = yminus; i <= yplus; i++){
				boardGrid[i][bloque.x].setDummy();
				for (int r=i;r>0;r--){
					if(boardGrid[r-1][bloque.x].getColor().equals("-"))
						break;
					
					swapWhite(boardGrid[r][bloque.x],boardGrid[r-1][bloque.x]);
				}	
			}
			total += (yplus-yminus) + 1;
		}
		
		//R,B,O,G,Y
		if (colour.equals("red")){
			estadisticas[0] += total;
		}
		else if (colour.equals("blue")){
			estadisticas[1] += total;
		}
		else if (colour.equals("orange")){
			estadisticas[2] += total;
		}
		else if (colour.equals("green")){
			estadisticas[3] += total;
		}
		else if (colour.equals("yellow")){
			estadisticas[4] += total;
		}
		setEstadisticasLabel();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		BlockButton button = (BlockButton) e.getSource();
		System.out.println("awds");
		Bloque bloque = boardGrid[button.yCoord][button.xCoord];
		System.out.println(button.xCoord+", "+ button.yCoord);
		checkSwap(bloque);
	}

}