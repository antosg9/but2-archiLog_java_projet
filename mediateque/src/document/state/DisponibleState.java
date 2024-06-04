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
		return " à emprunté avec succès le document n° "; //A envoyer au serveur méthode sendData static service concerné
	}
	
	@Override
	public String reserver()
	{
		super.document.setState(new ReserveState(super.document));
		return " à réservé avec succès le document n° ";
	}

	@Override
	public String rendre() {
		return " à tenté un rendu impossible du document n° ";
	}

}
