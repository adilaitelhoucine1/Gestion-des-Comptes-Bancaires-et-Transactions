import controller.AuthController;
import controller.ClientController;
import controller.ManagerController;
import service.ClientService;
import service.CompteService;
import service.ManagerService;
import service.TransactionService;
import views.ClientView;
import views.ManagerView;

public class Main {
    public static void main(String[] args) {
         ClientService clientService = new ClientService();
        ManagerService managerService = new ManagerService();
        TransactionService transactionService = new TransactionService();
        CompteService compteService=new CompteService();

        ClientView clientView = new ClientView();
        ManagerView managerView = new ManagerView();


        ClientController clientController = new ClientController(clientService, clientView, transactionService,managerView);
        ManagerController managerController = new ManagerController(managerService, clientService, managerView,compteService);


        AuthController authController = new AuthController(clientService, managerService, clientController, managerController);


        authController.start();
    }
}