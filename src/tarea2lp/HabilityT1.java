package tarea2lp;
import java.awt.Color;

public class HabilityT1 implements HabilityBehavior {
	public Bloque block;
	
	public HabilityT1(Bloque b){
		this.block = b;
	}
		
	@Override
	public void Habilidad(){
		//Borra toda la fila
		for (int x=0;x<15;x++){
			//R,B,O,G,Y
			String colour = GUI.grid[block.y][x].getText(); 
			if (colour.equals("R")){
				GUI.estadisticas[0] += 1;
			}
			else if (colour.equals("B")){
				GUI.estadisticas[1] += 1;
			}
			else if (colour.equals("O")){
				GUI.estadisticas[2] += 1;
			}
			else if (colour.equals("G")){
				GUI.estadisticas[3] += 1;
			}
			else if (colour.equals("Y")){
				GUI.estadisticas[4] += 1;
			}
			GUI.grid[block.y][x].setBackground(Color.WHITE);
			GUI.grid[block.y][x].setText("-");
			for (int r=block.y;r>0;r--){
				if(GUI.grid[r-1][x].getText().equals("-"))
					break;
				   	GUI.swapWhite(x,r,x,r-1);
			}
			   
		}
		GUI.fillWhites();
		GUI.bloquesFaltantes -= 15;
		GUI.setEstadisticasLabel();
	}
}
