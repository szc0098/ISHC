package iSHC_model;

import repast.simphony.query.space.grid.GridCellNgh;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;

public class CellSpace extends Space{
	
	private ContinuousSpace < Object > space ;
	private Grid < Object > grid ;

	
	public CellSpace(ContinuousSpace<Object> space, Grid<Object> grid) {
		super(space, grid);
		this.grid = grid;
		this.space = space;
		
	}
	
	public void step(){
		GridPoint pt = grid.getLocation ( this );
		// retreive a list of GridCells that represent
		 //the contents and location of the 8 neighboring cells around a GridPoint.
		GridCellNgh <Object> neighbor = new GridCellNgh <Object>( grid , pt ,Object.class , 1 , 1);
		//Since CellSpace maps to a monolayer of cell and may also contain Solute

	}
	
	
	}


