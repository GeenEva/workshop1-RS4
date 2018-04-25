package rsvier.workshop.view;

public class AccountView extends View{

	@Override
	public void printHeaderMessage() {
		System.out.println("=========== Account ===========");	
	}

	@Override
	public void printMenuMessage() {
		System.out.println("1- Maak nieuw account\n2- Terug naar Hoofdmenu\n0- Verlaat Evvo Meubels");
	}
		
	public void printRequestEmailInput() {
		System.out.print("Vul email adres in: ");
	}

	public void printRequestPasswordInput() {
		System.out.print("Vul wachtwoord in: ");
	}

	public void printLoginDetailsWrong() {
        System.out.println("De opgegeven inloggegevens zijn onjuist!");
    }
	
	public void printLoginAccountIsSuccessful() {
		System.out.println("U bent nu ingelogd in uw account.");
	}
	
	
}