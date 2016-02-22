package iSHC_model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class KuppferCells extends Cell{
	
	int inflammatoryThreshold =0;
	int cytokineThreshold =0;
	int exponentialThreshold =0;
	
	public KuppferCells(ContinuousSpace<Object> space, Grid<Object> grid) {
		super(space, grid);
		// TODO Auto-generated constructor stub
	}
	
	
	
	public int getInflammatoryThreshold() {
		return inflammatoryThreshold;
	}
	public void setInflammatoryThreshold(int inflammatoryThreshold) {
		this.inflammatoryThreshold = inflammatoryThreshold;
	}
	public int getCytokineThreshold() {
		return cytokineThreshold;
	}
	public void setCytokineThreshold(int cytokineThreshold) {
		this.cytokineThreshold = cytokineThreshold;
	}
	public int getExponentialThreshold() {
		return exponentialThreshold;
	}
	public void setExponentialThreshold(int exponentialThreshold) {
		this.exponentialThreshold = exponentialThreshold;
	}



	public void inflammatoryStimuli(){
		 int numInflammatoryStimuli = 0;
	        int numCytokines = 0;
	        for(Object o : solutes)
	        {
	            Solute s = (Solute) o;
	            if(s.hasProperty("inflammatory") && ((Boolean)s.getProperty("inflammatory")))
	            {
	                numInflammatoryStimuli++;
	            }
	            if(s.type.equals("Cytokine"))
	            {
	                numCytokines++;
	            }          
	        }
	        
	        if(numCytokines >= cytokineThreshold)
	        {
	            return;
	        }
	        
	        //If past inflammation threshold, there's a chance to produce Cytokine
	        if(numInflammatoryStimuli >= inflammatoryThreshold)
	        {               
	            double probability = 1.0 - Math.exp(-1*(numInflammatoryStimuli - inflammatoryThreshold) / exponentialThreshold);
	            
	            float draw = randomDraw();
	            if(draw <= probability)
	                addCytokine();
	        }
	}
	
	public Solute addCytokine(){
		Solute cytokine = new Solute("Cytokine", false);
		solutes.add(cytokine);
		return cytokine;
	}
	
	public void run(){
	final int MAX = 2;
	List<Integer> listIndexes = new ArrayList<Integer>();
	for(int i = 1; i <= MAX; i++) {
		    listIndexes.add(i);
	}
	while(true){
		Collections.shuffle(listIndexes);
		for(Integer index: listIndexes){
			switch(index){
				case 1:
				DegradationHandler dh = new DegradationHandler(this);
				dh.run();
				break;
				case 2:
				this.inflammatoryStimuli();
				default:
				break;
			}
	}
	}
	}
	


}
