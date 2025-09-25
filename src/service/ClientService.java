package service;

import model.Client;

import java.util.*;

public class ClientService {
    private Map<Integer, Client> clients;

    public ClientService() {
        this.clients = new HashMap<>();
    }

    public void AddClient(Client c) {
        clients.put(c.getIdClient(), c);
    }

    public void UpdateClient(int idClient, Client client) {
        if (clients.containsKey(idClient)) {
            clients.put(idClient, client);
        } else {
            throw new NoSuchElementException("aucun utilisateur trouve");
        }
    }

    public void DeleteClient(int idClient) {
        if (clients.containsKey(idClient)) {
            clients.remove(idClient);
        } else {
            throw new NoSuchElementException("aucun utilisateur trouve");
        }

    }

    public Optional<Client> FindClientByID(int idClient) {
        if (clients.containsKey(idClient)) {
            return Optional.of(clients.get(idClient));
        } else {
            return Optional.empty();
        }
    }


    public Optional<Client> FindClientByEmail(String email) {
        for (Map.Entry<Integer, Client> c : clients.entrySet()) {
            if (c.getValue().getEmail().equals(email)) {
                return Optional.of(c.getValue());
            }

        }
        return Optional.empty();
    }

    public List<Client> getAllClients(){
        return  new ArrayList<>(clients.values());
    }
    public boolean CheckClient(int ClientID){
        return clients.containsKey(ClientID);
    }
}
