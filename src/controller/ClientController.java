package controller;

import model.Client;
import service.ClientService;
import service.TransactionService;

import java.util.Scanner;

public class ClientController {

    private ClientService clientService;
    private TransactionService transactionService;
    private Scanner scanner;
    private Client activeClient;

    public ClientController(ClientService clientService , TransactionService transactionService){
        this.clientService=clientService;
        this.transactionService=transactionService;
        this.scanner=new Scanner(System.in);
    }
    public void register(){
        System.out.println("Entrer Votre Nom");
        String firstName=scanner.nextLine();
        System.out.println("Entrer Votre Prenom");
        String secondName=scanner.nextLine();
        System.out.println("Entrer votre Email");
        String email= scanner.nextLine();
        System.out.println("Entrer votre Mot De Passe");
        String password= scanner.nextLine();
        this.activeClient=new Client(firstName,secondName,email,password);
        if(this.isValidEmail(email)){
            clientService.addClient(activeClient);
            System.out.println("Vous avez connecté avec succes");
        }else{
            System.out.println("Erreur de creation de compte");
        }

    }


    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

}
