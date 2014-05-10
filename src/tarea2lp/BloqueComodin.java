package tarea2lp;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.HashMap;


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
