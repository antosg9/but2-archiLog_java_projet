package document.state;

import document.AbstractDocument;

public class DisponibleState extends State {

	public DisponibleState(AbstractDocument document) {
		super(document);
		super.stateName ="Disponible";
	}

	@Override
	public String emprunter() {
		super.document.setState(new EmprunteState(super.document));
		return "Document emprunté avec succés !"; //A envoyer au serveur méthode sendData static service concerné
	}
	
	@Override
	public String reserver()
	{
		super.document.setState(new ReserveState(super.document));
		return "Document réservé avec succés !";
	}

	@Override
	public String rendre() {
		return "Ce document est déjà rendu !";
	}

}
