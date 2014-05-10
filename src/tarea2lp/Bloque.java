package tarea2lp;

import javax.swing.border.Border;
import java.awt.Color;
import java.util.HashMap;
import java.util.Random;


public abstract class Bloque {
	protected static Border baseBorder;
	protected static Border lineBorder;
	protected static Border customBorder;
	public static Object actionlistener;

	//no static
	protected String color;
	protected static HashMap<String, Object[]> buttons;
	protected BlockButton innerButton;
	protected int x;
	protected int y;
	
	public static void setListener(Object o) {
		actionlistener = o;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setButton(BlockButton b) {
		innerButton = b;
	}
	
	public BlockButton getButton() {
		return innerButton;
	}
	
	public void setDummy() {
		innerButton.setName("-");
		innerButton.setBackground(Color.WHITE);
		color = "-";
	}

	public void setColor(String color) {
		try {
			Object[] values = buttons.get(color);
			innerButton.setName((String) values[0]);
			innerButton.setBackground((Color) values[1]);
			this.color = color;
		} catch (Exception e) {
			System.out.println("No existe el color!");
		}
	}
	
	public void setCoords(int x, int y) {
		this.x = x;
		this.y = y;
		innerButton.x = x;
		innerButton.y = x;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	protected String randKey(HashMap<String, Object[]> hm) {
		Random rand = new Random();
		Object[] keys = hm.keySet().toArray();
		String randomKey = (String) keys[rand.nextInt(keys.length)];
		return randomKey;
	}

	public abstract void destruirBloque();
}
