package iSHC_model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class Hepatocytes extends Cell {
	
	HashMap<String, Double> metProbMap = new HashMap<String,Double>();
    HashMap<String, HashMap<String,Double>> productionMap = new HashMap<String,HashMap<String,Double>>();
    ArrayList<Enzyme> downRegulatedEnzymes = new ArrayList<Enzyme>();
    int numEnzymesAtStart = downRegulatedEnzymes();
    
	public Hepatocytes(ContinuousSpace<Object> space, Grid<Object> grid) {
		super(space, grid);
		// TODO Auto-generated constructor stub
	}
	
	
	ArrayList elimQueue = null;
    public void downRegulationHandler()
    {      
        
        boolean thereIsACytokine = false;

        for(Solute s : solutes)
        {
            if(s.type.equalsIgnoreCase("Cytokine"))
            {
                thereIsACytokine = true;
                break;
            }
        }
        if(downRegulatedEnzymes()< numEnzymesAtStart && elimQueue != null && elimQueue.size() == 0 && !thereIsACytokine)
        {            
            if((physiomimeticProperties.get("pReplinsh").equals(randomDraw())))
                enzymes.add(new Enzyme(true));
            return;
        }
        
        
        if(elimQueue != null && elimQueue.size() > 0)
        {
            
            ArrayList<Enzyme> elimEnzyme = new ArrayList<Enzyme>();
            
            for(Enzyme e : enzymes)
            {
                
                if(!enzymeSolutePair.containsKey(e) && e.downRegulated) //if unbound
                    elimEnzyme.add(e);

            }
            
            //Actually remove them
            for(Enzyme e : elimEnzyme)
                enzymes.remove(e);
        }
        
                      
        
        //For each Cytokine, there's a chance to schedule removal of the first unbound Binder
        //If so, it's added to the queue
        ////Only one Binder can be removed each SCyc
        for(Solute s : solutes)
        {
            if(s.type.equalsIgnoreCase("Cytokine"))
            {
                if(physiomimeticProperties.get("pRemove").equals(randomDraw()))
                {
                    //Add to the queue to be removed, then return
                    if(elimQueue == null)
                        elimQueue = new ArrayList();
                                        
                    
                    elimQueue.add(randomlyDrawEnzyme());
                    return;
                }
            }
        }
    }
    
   public int downRegulatedEnzymes() {
	   int totEnzymes = 0;
	   
	   for(Enzyme e: enzymes){
		   if(e.downRegulated){
			   totEnzymes++;
			   downRegulatedEnzymes.add(e);
		   }
	   }
	   return totEnzymes;
   }

   public Enzyme randomlyDrawEnzyme(){
	   
	   Random random = new Random();
	   int index = random.nextInt(downRegulatedEnzymes.size());
	   return downRegulatedEnzymes.get(index);
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
					BindingHandler bh = new BindingHandler(this);
					bh.run();
					break;
				case 2:
					DegradationHandler dh = new DegradationHandler(this);
					dh.run();
					break;
				case 3:
					MetabolismHandler mh = new MetabolismHandler(this);
					mh.run();
					break;
				case 4:
					this.downRegulationHandler();
					break;
				default:
					break;
			
				}
		
			}
		}
   }
}
