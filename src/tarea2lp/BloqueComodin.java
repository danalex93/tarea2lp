package tarea2lp;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;


public class BloqueComodin extends Bloque implements HabilityBehavior{
	public HabilityBehavior habilidad;
	
	@SuppressWarnings("serial")
	protected static final HashMap <String, Object[]> buttons = new HashMap<String, Object[]>() {
		{
			put("1", new Object[] {"$", Color.CYAN});
			put("2", new Object[] {"&", Color.MAGENTA});
		};
	};

	public HabilityBehavior getHabilidad() {
		return habilidad;
	}

	public void setHabilidad(HabilityBehavior habilidad) {
		this.habilidad = habilidad;
	}

	@Override
	public void Habilidad() {
		this.habilidad.Habilidad();
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
	
	public BloqueComodin() {
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

	@Override
	public void destruirBloque() {
		// Limpiar en pantalla y borrar referencia
	}
}
