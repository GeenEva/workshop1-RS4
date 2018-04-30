package rsvier.workshop.controller;

import java.util.List;

import rsvier.workshop.dao.*;
import rsvier.workshop.controller.*;
import rsvier.workshop.domain.Person;
import rsvier.workshop.view.*;
import rsvier.workshop.view.View;

public class CustomerController extends Controller {

	private CustomerView customerView = new CustomerView();
	private PersonDAO personDao = new PersonDAOImp();
	private AccountDAO accountDao = new AccountDAOImp();
	private AddressDAO addressDao = new AddressDAOImp();
	private AccountController accountController = new AccountController();
	
	
	

	
	@Override
	public void runView() {
		
		customerView.printHeaderMessage();
		customerView.printMenuMessage();
		customerMenuSwitch(customerView.getIntInput());

	}
	
	public void customerMenuSwitch(int menuNumber) {

		switch (menuNumber) {

		case 0://leave and go back to employee-menu
			
			customerView.printExitApplicationMessage();
			EmployeeController employeeController = new EmployeeController();
			employeeController.runView();
			break;

		case 1://search customer by lastname
			Person person = searchCustomer();
			if (person != null) {
				updateOrDeleteCustomerSwitch(person);
			} else {
				runView();
			}

			break;

		case 2:
			//add customer
			AccountView.printMakeCustomerAccount();
			accountController.doCreateAccount(); //we should also make the accountType 2 here, so
												//have to add a constructor in the accountdomain for that
												
			runView();
			break;

		default:
			customerView.printMenuInputIsWrong();

		}
	}
	
	
	public void updateOrDeleteCustomerSwitch(Person person) {
		// PersonController has been removed from the data field to prevent stackoverflow error
		PersonController personController = new PersonController();

		customerView.printAskDeleteOrUpdateCustomer();
		int choice = customerView.getIntInput();

		switch (choice) {
		case 1: 
			// after a integer is given the returned person object from the search is also passed to use that to edit the user details.
			personController.personUpdateMenuSwitch(choice, person);
			break;
		case 2:
			//delete customer
			String yesOrNo = customerView.confirmYesOrNo();
			if (yesOrNo.equals("J")) {
				
				addressDao.deleteAddressByPersonId(person.getPersonId());
				accountDao.deleteAccount(person.getAccount());
				personDao.deletePerson(person);
			} else {

			}

			break;
		case 0: //back to previous menu
			break;
		default:
			break;
		}
	}


	public Person searchCustomer() {

		customerView.printAskCustomerLastName();
		String customerLastName = customerView.getStringInput();
		List<Person> customerList = personDao.getCustomerByLastName(customerLastName);


		if (customerList.size() == 0){
			customerView.printCustomerNotFound();
			return null;
		}

		if (customerList.size() == 1) {
			System.out.println(customerList.get(0).toString());
			return customerList.get(0);
		} else {

			for (int i = 1; i < customerList.size(); i++) {
				System.out.println("No. " + i + " : " + customerList.get(i - 1).toString());
			}

		}
		return customerList.get(selectCustomer() - 1);
	}
	

	public int selectCustomer() {

		customerView.printAskNumberOfCustomer();
		int chosenCustomerNumber = customerView.getIntInput();

		return chosenCustomerNumber;
		
	}


}
