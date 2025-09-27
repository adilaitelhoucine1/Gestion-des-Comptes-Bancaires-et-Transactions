package controller;

import model.Client;
import model.Compte;
import model.Transaction;
import service.ClientService;
import service.TransactionService;
import views.ClientView;

import java.util.List;

public class ClientController {
    private ClientService clientService;
    private ClientView clientView;
    private TransactionService transactionService;

    public ClientController(ClientService clientService, ClientView clientView, TransactionService transactionService) {
        this.clientService = clientService;
        this.clientView = clientView;
        this.transactionService = transactionService;
    }

    public void startSession(Client client) {
        boolean sessionActive = true;
        while (sessionActive) {
            int choice = clientView.showMenu(client);

            switch (choice) {
                case 1:
                    clientView.displayAccountInfo(client);
                    break;
                case 2:
                    List<Compte> comptes = client.getComptes();
                    if (comptes == null || comptes.isEmpty()) {
                        System.out.println("Aucun compte trouvé pour ce client.");
                        break;
                    }
                    for (Compte compte : comptes) {
                        List<Transaction> transactions = transactionService.getTransactionsByCompte(compte);
                        clientView.displayTransactions(compte, transactions);
                    }
                    break;
                case 3:
                    // makeTransfer(client);
                    break;
                case 4:
                    // deposit(client);
                    break;
                case 5:
                    // withdraw(client);
                    break;
                case 6:
                    // changePassword(client);
                    break;
                case 7:
                    System.out.println("Déconnexion réussie !");
                    sessionActive = false; // This will exit the while loop and return to AuthController
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        }
    }
}