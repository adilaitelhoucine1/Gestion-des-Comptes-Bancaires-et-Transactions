package service;

import model.Client;
import java.util.*;

public class ClientService {
    private Map<Integer, Client> clients;

    public ClientService() {
        this.clients = new HashMap<>();
    }
    public void AjouterClient(Client c){
        clients.put(c.getIdClient(),c);
    }
    public void modifierClient(int idClient, Client client){
        if(clients.containsKey(idClient)){
            clients.put(idClient,client);
        }else{
            throw new NoSuchElementException("aucun utilisateur trouve");
        }
    }
    public void SupprimerClient(int idClient){
        if(clients.containsKey(idClient)){
        clients.remove(idClient);
        }else{
            throw new NoSuchElementException("aucun utilisateur trouve");
        }

    }

}
