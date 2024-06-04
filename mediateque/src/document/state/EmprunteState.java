package document.state;

import document.AbstractDocument;

public class EmprunteState extends State {

	public EmprunteState(AbstractDocument document) {
		super(document);
		super.stateName ="Emprunté";
	}

	public String rendre() {
		super.document.setState(new DisponibleState(super.document));
		return " à été rendu avec succés ";
	}

	@Override
	public String emprunter() {
		return " à tenté un emprunt impossible du document ";
	}

	@Override
	public String reserver() {
		return " à tenté une réservation impossible du document ";
	}

}
