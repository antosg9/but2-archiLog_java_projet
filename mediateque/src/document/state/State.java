package document.state;

import document.AbstractDocument;

public abstract class State {
	protected AbstractDocument document;
	protected String stateName;
	
	public State(AbstractDocument document)
	{
		this.document=document;
	}
	
	public String getStateName()
	{
		return this.stateName;
	}
	
	public abstract String emprunter();
	public abstract String reserver();
	public abstract String rendre();
}
