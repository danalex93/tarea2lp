package tarea2lp;

public class BloqueComodin extends Bloque implements HabilityInterface{
	public HabilityInterface habilidad;

	public HabilityInterface getHabilidad() {
		return habilidad;
	}

	public void setHabilidad(HabilityInterface habilidad) {
		this.habilidad = habilidad;
	}

	@Override
	public void Habilidad() {
		this.habilidad.Habilidad();
	}

	@Override
	public void destruirBloque() {
		// Limpiar en pantalla y borrar referencia
	}
}
