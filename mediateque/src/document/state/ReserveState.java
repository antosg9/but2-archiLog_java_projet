package document.state;

import document.AbstractDocument;

public class ReserveState extends State{

	public ReserveState(AbstractDocument document) {
		super(document);
		super.stateName ="Réservé";
	}

	public String emprunter() {
		super.document.setState(new EmprunteState(super.document));
		return "Document emprunté avec succés !";
	}

	@Override
	public String reserver() {
		return "Ce document est déjà réservé !";
	}

	@Override
	public String rendre() {
		return "Ce document , n'est pas rendable !";
	}

}
