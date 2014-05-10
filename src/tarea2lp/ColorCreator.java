package tarea2lp;

public class ColorCreator extends AbstractCreator {

	@Override
	public BloqueColor crearBloque() {
		BloqueColor b = new BloqueColor();
		b.setActionListener(actionlistener);
		return b;
	}
}
