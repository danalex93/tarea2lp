package tarea2lp;
import javax.swing.JButton;

@SuppressWarnings("serial")
public abstract class Bloque extends JButton{
	public int x;
	public int y;
	public abstract void destruirBloque();
}
