package service;

import model.Compte;
import model.Transaction;
import model.enums.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class TransactionService {
    private Transaction transaction;
    private List<Transaction> transactions;

    public TransactionService() {
        this.transactions = new ArrayList<>();
    }
    public void  deposit(Compte compte, double amount, String reason){
         compte.setSolde(compte.getSolde()+amount);
        Transaction transaction = new Transaction(
                TypeTransaction.DEPOT,
                amount,
                LocalDateTime.now(),
                reason,
                compte,
                null
        );
        transactions.add(transaction);
    }
    public boolean withdraw(Compte compte, double amount, String reason){
        if(amount<=compte.getSolde()){
            compte.setSolde(compte.getSolde()-amount);
            Transaction transaction = new Transaction(
                    TypeTransaction.RETRAIT,
                    amount,
                    LocalDateTime.now(),
                    reason,
                    compte,
                    null
            );
            transactions.add(transaction);
            return  true;
        }
        return false;
    }

    public boolean transfer(Compte source, Compte destination, double amount, String reason) {
        if (amount <= source.getSolde()) {
            source.setSolde(source.getSolde() - amount);
            destination.setSolde(destination.getSolde() + amount);
            Transaction transaction = new Transaction(
                    TypeTransaction.VIREMENT,
                    amount,
                    LocalDateTime.now(),
                    reason,
                    source,
                    destination
            );
            transactions.add(transaction);
            return true;
        }
        return false;
    }
    public List<Transaction> getTransactionsByCompte(Compte compte){
        return this.transactions;
    }


}
