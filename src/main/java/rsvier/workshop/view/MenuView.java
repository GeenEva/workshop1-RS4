package rsvier.workshop.view;

public class MenuView extends View {

    public void printLoginOrCreateNewAccountMenu() {
        System.out.println("Welkom Op Evvo Meubels\r\n" + "\r\n" + "----------------------------------\r\n" + "\r\n"
                + "1 - Login\r\n" + "2 - Maak een nieuw Account\r\n0 - Verlaat Evvo Meubels ");
    }


    public void requestEmailInput() {
        System.out.print("Vul email adres in: ");
    }

    public void requestPasswordInput() {
        System.out.print("Vul wachtwoord in: ");
    }


}
