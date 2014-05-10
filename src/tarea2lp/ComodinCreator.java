package tarea2lp;

public class ComodinCreator extends AbstractCreator {

	public BloqueComodin crearBloque() {
		BloqueComodin b = new BloqueComodin();
		b.setActionListener(actionlistener);
		return b;
	}
}