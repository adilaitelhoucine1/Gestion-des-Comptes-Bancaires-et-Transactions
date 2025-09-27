package controller;

import model.Client;
import model.Compte;
import model.Manager;
import model.enums.TypeCompte;
import service.ClientService;
import service.CompteService;
import service.ManagerService;
import views.ManagerView;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ManagerController {

    private ManagerService managerService;
    private ClientService clientService;
    private ManagerView managerView;
    private CompteService compteService;

    public ManagerController(ManagerService managerService, ClientService clientService, ManagerView managerView, CompteService compteService) {
        this.managerService = managerService;
        this.clientService = clientService;
        this.managerView = managerView;
        this.compteService = compteService;
    }

    public void startSession(Manager manager) {
        boolean sessionActive = true;
        while (sessionActive) {
            int choice = managerView.showMenu(manager);
            switch (choice) {
                case 1:
                    List<Client> clients = clientService.getAllClients();
                    managerView.displayAllClients(clients);
                    break;
                case 2:
                    searchClient();
                    break;
                case 3:

                    createAccountForClient();
                    break;
                case 4:
                    String email = managerView.getClientEmail();
                    var optionalClient = clientService.findClientByEmail(email);

                    if (optionalClient.isEmpty()) {
                        System.out.println("Client introuvable !");
                        break;
                    }

                    Client client = optionalClient.get();
                    try {
                        clientService.deleteClient(client.getIdClient());
                        System.out.println("Client supprimé avec succès !");
                    } catch (NoSuchElementException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    List<Client> allClients = clientService.getAllClients();
                    managerView.displayAllAccounts(allClients);
                    break;
                case 6:
                    displayStatistics();
                    break;
                case 7:
                    sessionActive = false;
                    System.out.println("Déconnexion réussie !");
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        }
    }

    private void createAccountForClient() {
        System.out.println("\n=== Créer un compte pour un client ===");

        String email = managerView.getClientEmail();

        Optional<Client> optionalClient = clientService.findClientByEmail(email);
        if (optionalClient.isEmpty()) {
            System.out.println("Aucun client trouvr avec cet email.");
            return;
        }
        Client client = optionalClient.get();

        double balance = managerView.getInitialBalance();
        TypeCompte accountType = managerView.getAccountType();


        Compte compte = new Compte(accountType, balance, client);

        if (client.getComptes() == null) {
            client.setComptes(new java.util.ArrayList<>());
        }
        client.getComptes().add(compte);


        managerView.showAccountCreated(compte);
    }

    public void searchClient() {
        System.out.println("\n=== Recherche client ===");
        String email=managerView.getClientEmail();
        Optional<Client> optionalClient = clientService.findClientByEmail(email);
        if (optionalClient.isEmpty()) {
            System.out.println("Aucun client trouvr avec cet email.");
            return;
        }
        Client client = optionalClient.get();
        managerView.displayClientDetails(client);
    }

    public  void displayStatistics(){
        List<Client> clients = clientService.getAllClients();
        managerView.displayStatistics(clients);
    }

}