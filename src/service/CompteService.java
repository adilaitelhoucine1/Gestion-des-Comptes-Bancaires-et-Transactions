package service;

import model.Compte;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompteService {
    private List<Compte> comptes;

    public CompteService() {
        comptes = new ArrayList<>();
    }

    public void addAccount(Compte compte) {
        comptes.add(compte);
    }

    public void updateAccount(int idAccount, Compte newAccount) {
        for (int i = 0; i < comptes.size(); i++) {
            if (comptes.get(i).getIdCompte() == idAccount) {
                comptes.set(i, newAccount);
                return;
            }
        }
    }

    public boolean deleteAccount(Compte compte) {
        return comptes.remove(compte);
    }

    public Optional<Compte> findCompteByID(int AccId) {
        for (Compte compte : comptes) {
            if (compte.getIdCompte() == AccId) {
                return Optional.of(compte);
            }
        }
        return Optional.empty();
    }

    public List<Compte> getAllAccounts() {
        return comptes;
    }

    public List<Compte> findAccountsByClient(int IdClient) {
        List<Compte> AccountsClient = new ArrayList<>();
        for (Compte compte : comptes) {
            if (compte.getClient().getIdClient() == IdClient) {
                AccountsClient.add(compte);
            }
        }

        return AccountsClient;
    }
}
