package tarea2lp;

public class HabilityT2 implements HabilityBehavior {
	private BloqueComodin block;
	private GameEngine engine;
	
	public HabilityT2(BloqueComodin block, Object o) {
		this.block = block;
		this.engine = (GameEngine) o;
	}

	

	public void setBlock(BloqueComodin block) {
		this.block = block;
	}

	@Override
	public void Habilidad(){
		//Borra toda la fila
		for (int y=0;y<15;y++){
			engine.boardGrid[y][block.x].setDummy();
		}
		//engine.fillWhites();
	}
}

