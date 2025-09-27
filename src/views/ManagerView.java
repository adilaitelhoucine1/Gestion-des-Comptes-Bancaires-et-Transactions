package views;

import model.Manager;
import model.Client;
import model.Compte;
import model.enums.TypeCompte;

import java.util.List;
import java.util.Scanner;

public class ManagerView {

    private Scanner scanner;

    public ManagerView() {
        this.scanner = new Scanner(System.in);
    }

    public int showMenu(Manager manager) {
        System.out.println("\n=== Menu Manager ===");
        System.out.println("Bienvenue " + manager.getNom() + " " + manager.getPrenom());
        System.out.println("1. Afficher tous les clients");
        System.out.println("2. Rechercher un client");
        System.out.println("3. Créer un compte pour un client");
        System.out.println("4. Supprimer un compte");
        System.out.println("5. Afficher tous les comptes");
        System.out.println("6. Afficher les statistiques");
        System.out.println("7. Déconnecter");
        System.out.print("Votre choix : ");
        return scanner.nextInt();
    }

    public void displayAllClients(List<Client> clients) {
        System.out.println("\n======= Liste des Clients =======");
        if (clients == null || clients.isEmpty()) {
            System.out.println("Aucun client trouvé.");
            return;
        }

        for (Client client : clients) {
            System.out.println("===============================================");
            System.out.println("ID: " + client.getIdClient());
            System.out.println("Nom: " + client.getNom());
            System.out.println("Prénom: " + client.getPrenom());
            System.out.println("Email: " + client.getEmail());
            System.out.println("Nombre de comptes: " + (client.getComptes() != null ? client.getComptes().size() : 0));
            System.out.println("===============================================");
        }
    }

    public void displayClientDetails(Client client) {
        System.out.println("\n======= Détails du Client =======");
        System.out.println("ID: " + client.getIdClient());
        System.out.println("Nom: " + client.getNom());
        System.out.println("Prénom: " + client.getPrenom());
        System.out.println("Email: " + client.getEmail());

        List<Compte> comptes = client.getComptes();
        if (comptes != null && !comptes.isEmpty()) {
            System.out.println("\n--- Comptes du client ---");
            for (Compte compte : comptes) {
                System.out.println("Numéro de compte: " + compte.getIdCompte());
                System.out.println("Solde: " + compte.getSolde() + " DH");
                System.out.println("---");
            }
        } else {
            System.out.println("Ce client n'a aucun compte.");
        }
    }

    public void displayAllAccounts(List<Client> clients) {
        System.out.println("\n======= Tous les Comptes =======");
        int totalAccounts = 0;
        double totalBalance = 0;

        for (Client client : clients) {
            List<Compte> comptes = client.getComptes();
            if (comptes != null && !comptes.isEmpty()) {
                System.out.println("\nClient: " + client.getNom() + " " + client.getPrenom());
                for (Compte compte : comptes) {
                    System.out.println("  Compte N°: " + compte.getIdCompte());
                    System.out.println("  Solde: " + compte.getSolde() + " DH");
                      System.out.println("  Type de Compte: " + compte.getTypeCompte());
                    System.out.println("  ---");
                    totalAccounts++;
                    totalBalance += compte.getSolde();
                }
            }
        }

        System.out.println("\n=== Résumé ===");
        System.out.println("Nombre total de comptes: " + totalAccounts);
        System.out.println("Solde total: " + totalBalance + " DH");
    }

    public void displayStatistics(List<Client> clients) {
        System.out.println("\n======= Statistiques =======");

        int totalClients = clients.size();
        int totalAccounts = 0;
        double totalBalance = 0;
        double maxBalance = 0;
        double minBalance = Double.MAX_VALUE;

        for (Client client : clients) {
            List<Compte> comptes = client.getComptes();
            if (comptes != null && !comptes.isEmpty()) {
                totalAccounts += comptes.size();
                for (Compte compte : comptes) {
                    double solde = compte.getSolde();
                    totalBalance += solde;
                    if (solde > maxBalance) maxBalance = solde;
                    if (solde < minBalance) minBalance = solde;
                }
            }
        }

        double averageBalance = totalAccounts > 0 ? totalBalance / totalAccounts : 0;

        System.out.println("Nombre total de clients: " + totalClients);
        System.out.println("Nombre total de comptes: " + totalAccounts);
        System.out.println("Solde total: " + totalBalance + " DH");
        System.out.println("Solde moyen par compte: " + String.format("%.2f", averageBalance) + " DH");
        System.out.println("Solde maximum: " + maxBalance + " DH");
        System.out.println("Solde minimum: " + (minBalance == Double.MAX_VALUE ? 0 : minBalance) + " DH");
    }

    public String getClientEmail() {
        scanner.nextLine();
        System.out.print("Entrez l'email du client : ");
        return scanner.nextLine();
    }
    public String getClientPassword(){
        scanner.nextLine();
        System.out.print("Entrez nouveau mot de passe : ");
        return scanner.nextLine();
    }
    public double getInitialBalance() {
        System.out.print("Entrez le solde initial du compte : ");
        return scanner.nextDouble();
    }

    public long getAccountId() {
        System.out.print("Entrez l'ID du compte à supprimer : ");
        return scanner.nextLong();
    }

    public void showAccountCreated(Compte compte) {
        System.out.println("Compte créé avec succès !");
        System.out.println("ID Compte: " + compte.getIdCompte() + " | Solde: " + compte.getSolde() + " DH");
    }
    public TypeCompte getAccountType() {
        Scanner scanner = new Scanner(System.in);
        TypeCompte[] types = TypeCompte.values();

        System.out.println("=== Choisir le type de compte ===");
        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + ". " + types[i]);
        }

        int choice = 0;
        while (true) {
            System.out.print("Entrez votre choix (1-" + types.length + "): ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= types.length) {
                    break;
                } else {
                    System.out.println("Choix invalide. Réessayez.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Réessayez.");
            }
        }

        return types[choice - 1];
    }



}