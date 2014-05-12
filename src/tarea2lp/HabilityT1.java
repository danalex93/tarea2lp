package tarea2lp;

public class HabilityT1 implements HabilityBehavior {
	private BloqueComodin block;
	private GameEngine engine;
	
	public HabilityT1(BloqueComodin block, Object o) {
		this.block = block;
		this.engine = (GameEngine) o;
	}

	

	public void setBlock(BloqueComodin block) {
		this.block = block;
	}

	@Override
	public void Habilidad(){
		//Borra toda la fila
		for (int x=0;x<15;x++){
			engine.boardGrid[block.y][x].setDummy();
			for (int y=block.y;y>0;y--){
				engine.swapWhite(engine.boardGrid[y][x],engine.boardGrid[y-1][x]);
			}
		}
		//engine.fillWhites();
	}
}
