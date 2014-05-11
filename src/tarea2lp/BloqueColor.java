package tarea2lp;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;



public class BloqueColor extends Bloque {
	protected String color;

	@SuppressWarnings("serial")
	protected static final HashMap <String, Object[]> buttons = new HashMap<String, Object[]>() {
		{
			put("red", new Object[] {"R", Color.RED});
			put("blue", new Object[] {"B", Color.BLUE});
			put("orange", new Object[] {"O", Color.ORANGE});
			put("green", new Object[] {"G", Color.GREEN});
			put("yellow", new Object[] {"Y", Color.YELLOW});
		};
	};

	public BloqueColor() {
		String colorName = randKey(buttons);
		try {
			Object[] values = buttons.get(colorName);
			innerButton = new BlockButton((String) values[0]);
			innerButton.setBackground((Color) values[1]);
			this.color = colorName;
		} catch (Exception e) {
			System.out.println("No existe el color!");
		}
		innerButton.addActionListener((ActionListener) actionlistener);
		innerButton.setOpaque(true);
		innerButton.setBorder(customBorder);
		
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
		innerButton.setText("-");
		innerButton.setBackground(Color.WHITE);
		color = "-";
	}

	public void setColor(String color) {
		try {
			Object[] values = buttons.get(color);
			innerButton.setText((String) values[0]);
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
		innerButton.y = y;
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

	@Override
	public void destruirBloque() {
		// Limpiar en pantalla y borrar referencia
	}
	
}
