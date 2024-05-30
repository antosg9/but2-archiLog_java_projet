package abonne;

import java.util.Calendar;
import java.util.Date;

class Age {

	static int getAge(Date dateNaiss) {
		
		Date dateActuelle = new Date();

		if ((dateNaiss != null) && (dateActuelle != null) && dateNaiss.before(dateActuelle)) {
			Calendar birth = Calendar.getInstance();
			birth.setTime(dateNaiss);
			Calendar today = Calendar.getInstance();
			today.setTime(dateActuelle);

			int yearDifference = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
			int monthDifference = today.get(Calendar.MONTH) - birth.get(Calendar.MONTH);
			int dayDifference = today.get(Calendar.DAY_OF_MONTH) - birth.get(Calendar.DAY_OF_MONTH);

			if (monthDifference < 0 || (monthDifference == 0 && dayDifference < 0)) {
				yearDifference--;
			}

			return yearDifference;

		} else {
			System.out.println("Rentrez une date valide !");
			return 0;
		}
	}
}