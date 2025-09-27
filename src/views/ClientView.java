package views;

import model.Client;
import model.Compte;
import model.Transaction;
import model.enums.TypeTransaction;

import java.util.*;

public class ClientView {
    private Scanner scanner;
    public ClientView(){
        this.scanner=new Scanner(System.in);
    }
    public int showMenu(Client client) {
        System.out.println("\n=== Menu Client ===");
        System.out.println("1. Afficher les informations du compte");
        System.out.println("2. Afficher les transactions");
        System.out.println("3. Virement");
        System.out.println("4. Dépôt");
        System.out.println("5. Retrait");
        System.out.println("6. Changer le mot de passe");
        System.out.println("7. Déconnecter");
        System.out.print("Votre choix : ");

        Scanner scanner = new Scanner(System.in);
        int choix = scanner.nextInt();
        scanner.nextLine();
        return choix;
    }

    public void displayAccountInfo(Client client) {
        System.out.println("======= Informations Personnelles =======");
        System.out.println("===============================================");
        System.out.println("Nom: " + client.getNom());
        System.out.println("Prénom: " + client.getPrenom());
        System.out.println("Email: " + client.getEmail());

        if (client.getComptes() == null || client.getComptes().isEmpty()) {
            System.out.println("Aucun compte trouvé.");
        } else {
            System.out.println("\n--- Comptes ---");
            for (Compte compte : client.getComptes()) {
                System.out.println("Numéro de compte: " + compte.getIdCompte());
                System.out.println("Type: " + compte.getTypeCompte());
                System.out.println("Solde: " + compte.getSolde() + " DH");
                System.out.println("---");
            }
        }
        System.out.println("===============================================");
    }


    public void displayTransactions(Compte compte, List<Transaction> transactions) {
        System.out.println("---------------------------------------------------");
        System.out.println("Transactions pour le compte: " + compte.getIdCompte());
        if (transactions == null || transactions.isEmpty()) {
            System.out.println("Aucune transaction trouvée.");
        } else {
            for (Transaction t : transactions) {
                System.out.println("Type: " + t.getTypeTransaction());
                System.out.println("Montant: " + t.getMontant());
                System.out.println("Date: " + t.getDate());
                System.out.println("Raison: " + t.getMotif());
                 if (t.getTypeTransaction() == TypeTransaction.VIREMENT && t.getCompteDestination() != null) {
                    System.out.println("Destination: " + t.getCompteDestination().getIdCompte());
                }
                System.out.println("---------------------------------------------------");
            }
        }
    }

    public int getSourceAccountId() {
        System.out.print("Entrez l'ID du compte source : ");
        return scanner.nextInt();
    }

    public int getDestinationAccountId() {
        System.out.print("Entrez l'ID du compte destination : ");
        return scanner.nextInt();
    }

    public double getTransferAmount() {
        System.out.print("Entrez le montant à transférer : ");
        return scanner.nextDouble();
    }

    public String getTransferReason() {
        scanner.nextLine();
        System.out.print("Entrez la raison du virement (facultatif) : ");
        return scanner.nextLine();
    }
}