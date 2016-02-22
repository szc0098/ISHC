package iSHC_model;

import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class Space{
	
	private ContinuousSpace < Object > space ;
	private Grid < Object > grid ;
	
	public Space ( ContinuousSpace < Object > space , Grid < Object > grid ) {
	this.space = space ;
	this.grid = grid ;
	 }

}
