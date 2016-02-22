package iSHC_model;

import java.util.ArrayList;

public class DegradationHandler extends Mechanism {
	
	Cell associatedCell = null;
	public DegradationHandler(Cell cell){
		associatedCell = cell;
		//if(associatedCell.)
		
	}

	public void run(){
		ArrayList<Solute> solutes = new ArrayList<Solute>();
		for(Solute s: solutes ){
			String p = (s.getProperty("pDegrade")).toString();
			float pDegrade = Float.parseFloat(p);
			if(pDegrade<(associatedCell.randomDraw())){
				associatedCell.solutes.remove(s);
				
			}
		}
	}
}
