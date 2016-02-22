package iSHC_model;

import java.util.HashMap;

import repast.simphony.query.space.grid.GridCellNgh;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;


public class Solute {
	int id=-1, instanceCount=0;
	String type = null;
	boolean bindable = false;
	HashMap properties = null;
	HashMap<String,Double> metabolizeProduction = null;
	private ContinuousSpace < Object > space ;
	private Grid < Object > grid ;

	public Solute(String t, boolean b) {
	     if (t != null) type = t;
	     else throw new RuntimeException("Solute: type cannot be null.");
	     bindable = b;
	     id = instanceCount++;
	     
	  }

	
	public void step(){
		GridPoint pt = grid.getLocation ( this );
		// retreive a list of GridCells that represent
		 //the contents and location of the 8 neighboring cells around a GridPoint.
		GridCellNgh <Object> neighbor = new GridCellNgh <Object>( grid , pt ,Object.class , 1 , 1);
		//Since CellSpace maps to a monolayer of cell and may also contain Solute

	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isBindable() {
		return bindable;
	}

	public void setBindable(boolean bindable) {
		this.bindable = bindable;
	}
	
	public boolean hasProperty(String pname) {
	     return properties.containsKey(pname);
	  }
	  public Object getProperty(String pname) {
	     return properties.get(pname);
	  }
	  public void setProperties(HashMap p) {
	     if (p != null) properties = p;
	     else throw new RuntimeException("Solute: properties cannot be null.");
	  }
	  public void setProperty(String pname, Object pvalue) {
	    if (properties.containsKey(pname)) properties.put(pname, pvalue);
	    else throw new RuntimeException("Solute:"+id+" property "+pname+" does not exist.");
	  }

	public HashMap<String, Double> getMetabolizeProduction() {
		return metabolizeProduction;
	}

	public void setMetabolizeProduction(HashMap<String, Double> metabolizeProduction) {
		this.metabolizeProduction = metabolizeProduction;
	}
	
}
