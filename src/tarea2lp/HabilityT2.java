package tarea2lp;

import java.awt.Color;

public class HabilityT2 implements HabilityBehavior {
	public Bloque block;
	
	public HabilityT2(Bloque b){
		this.block = b;
	}
	
	@Override
	public void Habilidad(){
		//Borra toda la columna
		for (int y=0;y<15;y++){
			//R,B,O,G,Y
			String colour = GUI.grid[y][block.x].getText(); 
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
	       GUI.grid[y][block.x].setBackground(Color.WHITE);
		   GUI.grid[y][block.x].setText("-");
		   for (int r=block.y;r>0;r--){
		       if(GUI.grid[r-1][block.x].getText().equals("-"))
			       break;
				   GUI.swapWhite(block.x,r,block.x,r-1);
		   }
			   
		 }
		 GUI.fillWhites();
		 GUI.bloquesFaltantes -= 15;
		 GUI.setEstadisticasLabel();
	}
}
