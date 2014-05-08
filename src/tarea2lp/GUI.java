package tarea2lp;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.lang.Math;


public class GUI implements ActionListener{
	JFrame frame = new JFrame();
	JButton[][] grid;
	private int colour;
	private int joker;
	static JButton firstButton;
	static int firstX, firstY;
	
	public GUI(int width, int length){
		frame.setLayout(new GridLayout(width,length));
		grid = new JButton[width][length];		
		for (int y=0;y<length;y++){
			for (int x=0;x<width;x++){
				joker = randInt(0,100);
				if (joker < 96){
					colour = randInt(0,5);
					if (colour == 0){
						grid[x][y] = new JButton("R");
						grid[x][y].setBackground(Color.RED);
					}
					else if (colour == 1){
						grid[x][y] = new JButton("B");
						grid[x][y].setBackground(Color.BLUE);
					}
					else if (colour == 2){
						grid[x][y] = new JButton("O");
						grid[x][y].setBackground(Color.ORANGE);
					}
					else if (colour == 3){
						grid[x][y] = new JButton("G");
						grid[x][y].setBackground(Color.GREEN);
					}
					else{
						grid[x][y] = new JButton("Y");
						grid[x][y].setBackground(Color.YELLOW);
					}
					grid[x][y].addActionListener(this);
					grid[x][y].setActionCommand("Swap-"+x+"-"+y);
				}
				else{
					colour = randInt(0,1);
					if (colour == 0){
						grid[x][y] = new JButton("$");
						grid[x][y].setBackground(Color.MAGENTA);
					}
					else{
						grid[x][y] = new JButton("&");
						grid[x][y].setBackground(Color.CYAN);
					}
				}
					
				frame.add(grid[x][y]);
			}
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
		frame.pack();
		frame.setVisible(true);
	}
	
	public void twoButtonAction(JButton j, int x, int y){
		if (firstButton == null){
			firstButton = j;
			firstX = x;
			firstY = y;
		}
		else{
			if (firstButton == j){
				firstButton = null;
				firstX = 0;
				firstY = 0;
			}
			else{
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
		
		grid[x1][y1] = new JButton(j2.getText());
		if (j2.getText().equals("R")){
			grid[x1][y1].setBackground(Color.RED);
		}
		else if(j2.getText().equals("B")){
			grid[x1][y1].setBackground(Color.BLUE);
		}
		else if(j2.getText().equals("O")){
			grid[x1][y1].setBackground(Color.ORANGE);
		}
		else if(j2.getText().equals("G")){
			grid[x1][y1].setBackground(Color.GREEN);
		}
		else{
			grid[x1][y1].setBackground(Color.YELLOW);
		}
		grid[x1][y1].addActionListener(this);
		grid[x1][y1].setActionCommand("Swap-"+x1+"-"+y1);
		
		grid[x2][y2] = new JButton(j1.getText());
		if (j1.getText().equals("R")){
			grid[x2][y2].setBackground(Color.RED);
		}
		else if(j1.getText().equals("B")){
			grid[x2][y2].setBackground(Color.BLUE);
		}
		else if(j1.getText().equals("O")){
			grid[x2][y2].setBackground(Color.ORANGE);
		}
		else if(j1.getText().equals("G")){
			grid[x2][y2].setBackground(Color.GREEN);
		}
		else{
			grid[x2][y2].setBackground(Color.YELLOW);
		}
		grid[x2][y2].addActionListener(this);
		grid[x2][y2].setActionCommand("Swap-"+x2+"-"+y2);
			
		frame.remove(j1);
		frame.remove(j2);
		for (int y=0;y<15;y++){
			for (int x=0;x<15;x++){
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