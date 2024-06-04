package document.state;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

import document.AbstractDocument;
import stockage.LogDocument;

public class ReserveState extends State{

	public ReserveState(AbstractDocument document) {
		super(document);
		super.stateName ="Réservé";
		this.LimitReservation();

		Timer timer = new Timer();
		TimerTask task = new TimerTask()
		{
			public void run() {
				LogDocument.getInstance().addLog(document.getNumAbo()+ " réservation expirée du document "+ document.getNumAbo());
				document.setState(new DisponibleState(document));
			}
		};

		timer.schedule(task, 5400000);
	}

	public String emprunter() {
		super.document.setState(new EmprunteState(super.document));
		return " à emprunté avec succés le document ";
	}

	@Override
	public String reserver() {
		return " à tenté une réservation impossible du document ";
	}

	@Override
	public String rendre() {
		return " à tenté un rendu impossible du document ";
	}

	private void LimitReservation()
	{
		Instant now = Instant.now();
		Instant future = now.plus(Duration.ofMillis(5400000));
		LocalDateTime futureDateTime = LocalDateTime.ofInstant(future, ZoneId.systemDefault());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		String formattedDateTime = futureDateTime.format(formatter);
		super.document.setFinReservation(formattedDateTime);
	}
}
