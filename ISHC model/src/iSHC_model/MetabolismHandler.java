package iSHC_model;

import java.util.ArrayList;

public class MetabolismHandler extends Mechanism {
	
	Cell associatedCell = null;
	public MetabolismHandler(Cell cell){
		associatedCell = cell;
		//if(associatedCell.)
		
	}
	
	public void run(){
		ArrayList<Enzyme> unboundEnzymes = new ArrayList<Enzyme>();
		for(Enzyme e: associatedCell.enzymeSolutePair.keySet()){
			Solute s = associatedCell.enzymeSolutePair.get(e);
			float randomDraw = associatedCell.randomDraw();
			if((s.getProperty("pMetabolize")).equals(randomDraw)){
			unboundEnzymes.add(e);
			
			 if (s.properties.containsKey("metaboliteTypes")) {
				 //assuming solute has the metbolize property secified during initialization
				 //if there is a metabolize type, production map is supposed to be present
			     float metaboliteDraw = associatedCell.randomDraw();
                 float cumulative = 0;
                 String metType = null;
                 // choose which metabolite to generate
                 for (java.util.Map.Entry<String, Double> met : s.getMetabolizeProduction().entrySet()) {
                     cumulative += met.getValue();
                     if (metaboliteDraw <= cumulative) {
                         metType = met.getKey();
                         break;
                     }
                 } // end loop over metabolite types this solute produces
                 
                 Solute metabolite = new Solute(metType,false);
                 //assuming the binding is false and placed in the cell
                 associatedCell.solutes.add(metabolite);
                 
                 
		}
			 //if solute can not produce a metabolite, remove from system
			 else{
				  for(Solute soluteTobeRemoved: associatedCell.solutes){
					  if(soluteTobeRemoved.equals(s))
						  associatedCell.solutes.remove(s);
				  }
			 }
		
	}

}
		//REmove it from the pair
		for(Enzyme enzymeToBeRemoved: unboundEnzymes){
			associatedCell.enzymeSolutePair.remove(enzymeToBeRemoved);
		}
	}
}
