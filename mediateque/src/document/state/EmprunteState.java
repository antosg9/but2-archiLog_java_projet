package document.state;

import document.AbstractDocument;

public class EmprunteState extends State {

	public EmprunteState(AbstractDocument document) {
		super(document);
		super.stateName ="Emprunté";
	}

	public String rendre() {
		super.document.setState(new DisponibleState(super.document));
		return "Document rendu avec succés !";
	}

	@Override
	public String emprunter() {
		return "Ce document est déjà emprunté !";
	}

	@Override
	public String reserver() {
		return "Ce document n'est pas réservable !";
	}

}
