package controller;

import model.Client;
import model.Manager;
import model.Personne;
import service.ClientService;
import service.ManagerService;
import views.ClientView;

import java.util.Optional;
import java.util.Scanner;

public class AuthController {

    private ClientService clientService;
    private ManagerService managerService;
    private  ClientController clientController;
    private Scanner scanner;
    private Personne activeClient;

    public AuthController(ClientService clientService, ManagerService managerService,ClientController clientController) {
        this.clientService = clientService;
        this.managerService = managerService;
        this.clientController=clientController;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("1. Login as Client");
            System.out.println("2. Register as Client");
            System.out.println("3. Login as Manager");
            System.out.println("4. Register as Manager");
            System.out.println("5. Exit");

            System.out.println("Choice : ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    loginClient();
                    break;
                case "2":
                    registerClient();
                    break;
                case "3":
                    loginManager();
                    break;
                case "4":
                    registerManager();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public void registerClient() {
        System.out.println("Entrer Votre Nom");
        String firstName = scanner.nextLine();
        System.out.println("Entrer Votre Prenom");
        String secondName = scanner.nextLine();
        System.out.println("Entrer votre Email");
        String email = scanner.nextLine();
        System.out.println("Entrer votre Mot De Passe");
        String password = scanner.nextLine();
        if (!isValidEmail(email)) {
            System.out.println("Email invalide.");
            return;
        }
        Client client = new Client(firstName, secondName, email, password);
        this.activeClient=client;
        clientService.addClient(client);
        clientController.startSession(client);

        System.out.println("client cree avec succes.");
        ClientView clientView = new ClientView();
        clientView.showMenu((Client) activeClient);
    }

    public void loginClient() {
        System.out.println("Entrer Votre Email");
        String email = scanner.nextLine();
        System.out.println("Entrer Votre Mot de passe");
        String password = scanner.nextLine();
        Optional<Client> optionalClient = clientService.findClientByEmail(email);
        if (optionalClient.isPresent() && optionalClient.get().getMotDePasse().equals(password)) {
            System.out.println("Connexion avec succes");
            this.activeClient=optionalClient.get();
          //  ClientController clientController = new ClientController(clientService, /* transactionService */ null);
           // clientController.startSession(optionalClient.get());
        } else {
            System.out.println("Connexion echouee");
        }
    }

    public void registerManager() {
        System.out.println("Entrer le nom du manager:");
        String firstName = scanner.nextLine();
        System.out.println("Entrer le prénom du manager:");
        String lastName = scanner.nextLine();
        System.out.println("Entrer Votre Departement:");
        String departement =scanner.nextLine();
        System.out.println("Entrer l'email du manager:");
        String email = scanner.nextLine();
        System.out.println("Entrer le mot de passe du manager:");
        String password = scanner.nextLine();


        if (!isValidEmail(email)) {
            System.out.println("Email invalide.");
            return;
        }

        Optional<Manager> existingManager = managerService.findGestionnaireByEmail(email);
        if (existingManager.isPresent()) {
            System.out.println("Un manager avec cet email existe déjà.");
            return;
        }

        Manager manager = new Manager(firstName, lastName, email, password,departement);
        managerService.addGestionnaire(manager);
        this.activeClient=manager;
        System.out.println("Manager enregistré avec succès.");
    }

    public void loginManager() {
        System.out.println("Entrer l'email du manager:");
        String email = scanner.nextLine();
        System.out.println("Entrer le mot de passe du manager:");
        String password = scanner.nextLine();

        Optional<Manager> optionalManager = managerService.findGestionnaireByEmail(email);

        if (optionalManager.isPresent() && optionalManager.get().getMotDePasse().equals(password)) {
            System.out.println("Connexion du manager reussie !");
            Manager manager = optionalManager.get();
            this.activeClient=manager;
            //ManagerController managerController = new ManagerController(managerService);
           // managerController.startSession(manager);
        } else {
            System.out.println("Email ou mot de passe incorrect.");
        }
    }

    public void logout() {
        this.activeClient = null;
        System.out.println("Deconnexion reussie !");

    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }
}