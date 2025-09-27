import controller.AuthController;
import controller.ClientController;
import service.ClientService;
import service.ManagerService;
import service.TransactionService;
import views.ClientView;

public class Main {
    public static void main(String[] args) {

        ClientService clientService = new ClientService();
        ManagerService managerService = new ManagerService();
        ClientView clientView = new ClientView();
        TransactionService transactionService = new TransactionService();

         ClientController clientController = new ClientController(clientService, clientView, transactionService);
        AuthController authController = new AuthController(clientService, managerService, clientController);

        authController.start();
    }
}