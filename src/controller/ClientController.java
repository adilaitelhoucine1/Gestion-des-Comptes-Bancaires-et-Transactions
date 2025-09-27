package controller;

import model.Client;
import model.Compte;
import model.Transaction;
import service.ClientService;
import service.ManagerService;
import service.TransactionService;
import views.ClientView;
import views.ManagerView;

import java.util.EnumMap;
import java.util.List;

public class ClientController {
    private ClientService clientService;
    private ClientView clientView;
    private TransactionService transactionService;
    private ManagerView managerView;

    public ClientController(ClientService clientService, ClientView clientView, TransactionService transactionService , ManagerView managerView) {
        this.clientService = clientService;
        this.clientView = clientView;
        this.transactionService = transactionService;
        this.managerView=managerView;
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
                     makeTransfer(client);
                    break;
                case 4:
                     deposit(client);
                    break;
                case 5:
                     withdraw(client);
                    break;
                case 6:
                     changePassword(client);
                    break;
                case 7:
                    System.out.println("Déconnexion réussie !");
                    sessionActive = false;
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        }
    }
    public  void changePassword(Client client){
        String email = managerView.getClientEmail();
        if(!clientService.checkClient(email)){
            System.out.println("Email introuvable");
        }else{
            String newPasword=managerView.getClientPassword();
            client.setMotDePasse(newPasword);
            System.out.println("Mot de Passe Mis a jour avec Succes");
        }
    }

    public  void makeTransfer(Client client){
        int sourceId = clientView.getSourceAccountId();
        int destinationId = clientView.getDestinationAccountId();
        double amount = clientView.getTransferAmount();
        String reason = clientView.getTransferReason();

        Compte compteSource=client.getComptes().stream()
                .filter(c-> c.getIdCompte()==sourceId).findFirst().orElse(null);
       Compte compteDest= clientService.getAllClients().stream()
                .flatMap(c -> c.getComptes().stream())
                .filter(c -> c.getIdCompte() == destinationId)
                .findFirst()
                .orElse(null);

        if(compteDest== null || compteSource==null){
            System.out.println("Vous avez insere fausse infos");
        }else{
            transactionService.transfer(compteSource,compteDest,amount,reason);
            System.out.println("Operation done");
        }
    }

    public void  deposit(Client client){
        int sourceId = clientView.getSourceAccountId();
        double amount = clientView.getTransferAmount();
        String reason = clientView.getTransferReason();
        Compte compteSource=client.getComptes().stream()
                .filter(c-> c.getIdCompte()==sourceId).findFirst().orElse(null);

        if(compteSource == null){
            System.out.println("Vous avez insere fausse infos");
        }

        transactionService.deposit(compteSource,amount,reason);
        System.out.println("Operation done");
    }

    public void  withdraw(Client client){
        int sourceId = clientView.getSourceAccountId();
        double amount = clientView.getTransferAmount();
        String reason = clientView.getTransferReason();
        Compte compteSource=client.getComptes().stream()
                .filter(c-> c.getIdCompte()==sourceId).findFirst().orElse(null);

        if(compteSource == null){
            System.out.println("Vous avez insere fausse infos");
        }

        transactionService.withdraw(compteSource,amount,reason);
        System.out.println("Operation done");
    }
}