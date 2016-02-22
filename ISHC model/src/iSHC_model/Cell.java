package iSHC_model;

import java.util.ArrayList;
import java.util.HashMap;


import java.util.Random;

import repast.simphony.query.space.grid.GridCellNgh;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;

/**
 * 
 * 
 *
 */
public class Cell {
	int instanceCount = 0,id=-1;
	//Cells can contain solutes and enzymes
	public ArrayList<Solute> solutes = new ArrayList<Solute>();
    public ArrayList<Enzyme> enzymes = new ArrayList<Enzyme>();
    HashMap<Enzyme, Solute> enzymeSolutePair= new HashMap<Enzyme, Solute>();
    Random rng = new Random();
    //Cells contains physiomimetic mechanisms
    HashMap physiomimeticProperties = null;
    String type;
    //Mechanism cellMechanism = new Mechanism();
    
    private ContinuousSpace < Object > space ;
	private Grid < Object > grid ;
    
	public Cell(ContinuousSpace<Object> space, Grid<Object> grid /*,Random rng*/) {
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
	
	public ArrayList<Solute> getSolutes() {
		return solutes;
	}


	public void setSolutes(ArrayList<Solute> solutes) {
		this.solutes = solutes;
	}


	public ArrayList<Enzyme> getEnzymes() {
		return enzymes;
	}


	public void setEnzymes(ArrayList<Enzyme> enzymes) {
		this.enzymes = enzymes;
	}


	public HashMap<Enzyme, Solute> getEnzymeSolutePair() {
		return enzymeSolutePair;
	}


	public void setEnzymeSolutePair(HashMap<Enzyme, Solute> enzymeSolutePair) {
		this.enzymeSolutePair = enzymeSolutePair;
	}

	public boolean hasProperty(String pname) {
	     return physiomimeticProperties.containsKey(pname);
	  }
	  public Object getProperty(String pname) {
	     return physiomimeticProperties.get(pname);
	  }
	  public void setProperties(HashMap p) {
	     if (p != null) physiomimeticProperties = p;
	     else throw new RuntimeException("Cell: properties cannot be null.");
	  }
	  public void setProperty(String pname, Object pvalue) {
	    if (physiomimeticProperties.containsKey(pname)) physiomimeticProperties.put(pname, pvalue);
	    else throw new RuntimeException("Cell property "+pname+" does not exist.");
	  }

	public boolean accept(Solute s) {
	       Boolean retVal = false;
	       if (s.hasProperty("membraneCrossing"))
	          retVal = (Boolean)s.getProperty("membraneCrossing");
	       return retVal.booleanValue();
	}
	
	public void scheduleRelease(Enzyme e){
		Solute s = enzymeSolutePair.remove(e);
	}
	
	public float randomDraw(){
		float result = rng.nextFloat();
		return result;
	}
	
	
}