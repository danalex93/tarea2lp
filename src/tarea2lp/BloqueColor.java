package tarea2lp;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.HashMap;



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

	@Override
	public void destruirBloque() {
		// Limpiar en pantalla y borrar referencia
	}
	
}
