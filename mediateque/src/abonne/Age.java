package abonne;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

class Age {

	public static int getAge(String strDateNaiss) throws Exception
	{
		DateTimeFormatter formateur = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dateActuelle = LocalDate.now();
		LocalDate dateNaiss;
		try {
			dateNaiss = LocalDate.parse(strDateNaiss, formateur);
		} catch (Exception e) {
			throw new Exception("Rentrez une date valide au format dd/MM/yyyy !");
		}

		if ((dateNaiss != null) && (dateActuelle != null) && dateNaiss.isBefore(dateActuelle)) {
			return Period.between(dateNaiss, dateActuelle).getYears();
		} else {
			throw new Exception("Rentrez une date valide !");
		}
	}
}
