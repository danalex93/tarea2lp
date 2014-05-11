package tarea2lp;

@SuppressWarnings("serial")
public class BloqueComodin extends Bloque implements HabilityBehavior{
	public HabilityBehavior habilidad;

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

	@Override
	public void destruirBloque() {
		// Limpiar en pantalla y borrar referencia
	}
}
