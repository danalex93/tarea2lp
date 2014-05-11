package tarea2lp;

public class HabilityT1 implements HabilityBehavior {
	/* public Bloque = block;
	 * public HabilityT1(Bloque b){
	 *   this.block = b;
	 * }
	 */
	
	@Override
	public void Habilidad(){
		//Borra toda la fila
		/*
		 * for (int x=0;x<15;x++){
		*       //R,B,O,G,Y
		*       String colour = grid[block.y][x]; 
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
		 *     grid[block.y][x].setBackground(Color.WHITE);
			   grid[block.y][x].setText("-");
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
