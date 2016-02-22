package iSHC_model;


import java.util.ArrayList;

public class BindingHandler extends Mechanism {
	
	Cell associatedCell = null;
	public BindingHandler(Cell cell){
		associatedCell = cell;
		//if(associatedCell.)
		
	}
	
	ArrayList<Solute> associatedSolute = associatedCell.getSolutes();
	ArrayList<Enzyme> associatedEnzymes = associatedCell.getEnzymes();
	
	public void run(){
		for (Solute s : associatedSolute){
			if(s.bindable && !(associatedCell.enzymeSolutePair.containsValue(s))){
				for (Enzyme e: associatedEnzymes){
					if(!(associatedCell.enzymeSolutePair.containsKey(e))){
						float randomDraw = associatedCell.randomDraw();
						if((s.getProperty("pBind")).equals(randomDraw)){
							associatedCell.enzymeSolutePair.put(e, s);
							associatedCell.scheduleRelease(e);
						}
					}
				}
			}
		}
	}

}
