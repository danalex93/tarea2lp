package tarea2lp;

public class HabilityT2 implements HabilityBehavior {
	@Override
	public void Habilidad(){
		// Borra toda la columna
		/* for (int y=0;y<15;y++){
		        //R,B,O,G,Y
		        String colour = grid[y][block.x]; 
				if (colour.equals("R")){
					estadisticas[0] += 1;
				}
				else if (colour.equals("B")){
					estadisticas[1] += 1;
				}
				else if (colour.equals("O")){
					estadisticas[2] += 1;
				}
				else if (colour.equals("G")){
					estadisticas[3] += 1;
				}
				else if (colour.equals("Y")){
					estadisticas[4] += 1;
				}
		       grid[y][block.x].setBackground(Color.WHITE);
			   grid[y][block.x].setText("-");
			   for (int r=block.y;r>0;r--){
			       if(grid[r-1][i].getText().equals("-"))
				       break;
					   swapWhite(i,r,i,r-1);
			   }
			   
		 * }
		 * fillWhites();
		 * GUI.bloquesFaltantes -= 15;
		 * setEstadisticasLabel();
		 */
	}
}
