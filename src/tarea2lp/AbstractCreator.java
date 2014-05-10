package tarea2lp;

public abstract class AbstractCreator {
	protected static Object actionlistener;
	
	public static void setListener(Object o) {
		actionlistener = o;
	}
	
	public static Object getListener() {
		return actionlistener;
	}
	
	public abstract Bloque crearBloque();
}
