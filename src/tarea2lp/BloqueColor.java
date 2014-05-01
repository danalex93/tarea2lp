package tarea2lp;

public class BloqueColor extends Bloque {
	public String color;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public void destruirBloque() {
		// Limpiar en pantalla y borrar referencia
	}
	
}
