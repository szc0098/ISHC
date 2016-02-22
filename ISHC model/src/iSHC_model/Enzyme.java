package iSHC_model;

public class Enzyme {
	boolean bindable = false;
	boolean downRegulated = false;

	public Enzyme(){}
	
	public Enzyme(boolean downRegulated){
		this.downRegulated = downRegulated;
	}
	public boolean isBound() {
		return bindable;
	}

	public void setBound(boolean bound) {
		this.bindable = bound;
	}

	public boolean isDownRegulated() {
		return downRegulated;
	}

	public void setDownRegulatable(boolean downRegulated) {
		this.downRegulated = downRegulated;
	}
	

}
