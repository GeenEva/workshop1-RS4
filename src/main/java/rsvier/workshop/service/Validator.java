package rsvier.workshop.service;

public class Validator {

	public boolean validateEmail(String email) {

		if (!email.matches("\\w+@\\w+\\.\\w+")) {
			System.out.println("Dit is geen geldig e-mail adres");
			return false;
		}

		return true;
	}

	public boolean validatePassword(String password) {

		if (password.matches(".*\\s+.*")) {
			System.out.println("Uw wachtwoord mag geen spatie bevatten.");
			return false;
		}
		if (!(password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$"))) {
			System.out.println(
				"Uw wachtwoord moet minstens een hoofdletter, een kleine letter, een getal \nen een speciaal "
				+ "leesteken bevatten en uit minimaal 8 karakters bestaan.");
			return false;
		}

		return true;
	}
	
	public boolean validatePostalCode(String inputPostalCode) {
		
		//check if input is made of 6 characters
		if (inputPostalCode.length() != 6) {
			System.out.println("De ingevulde postcode is niet correct.");
			return false;
		}

		//check if postal code is correct
		if (!inputPostalCode.matches("/^[1-9][0-9]{3} ?(?!sa|sd|ss)[a-z]{2}$/i")) {
			System.out.println("De ingevulde postcode is niet correct.");
			return false;
		}

		return true;
	}
	
}
