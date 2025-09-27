package service;

import model.Manager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManagerService {
    private List<Manager> managers ;
    private int nextId = 1;

    public  ManagerService(){
        this.managers=new ArrayList<>();
    }

    public void addGestionnaire(Manager manager) {
        managers.add(manager);
    }

     public Optional<Manager> findGestionnaireByEmail(String email) {
        return managers.stream()
                .filter(g -> g.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

     public List<Manager> getAllGestionnaires() {
        return new ArrayList<>(managers);
    }

     public boolean removeGestionnaireById(int idGestionnaire) {
        return managers.removeIf(g -> g.getIdGestionnaire() == idGestionnaire);
    }

     public boolean addClientToGestionnaire(int idGestionnaire, model.Client client) {
        Optional<Manager> optGest = managers.stream()
                .filter(g -> g.getIdGestionnaire() == idGestionnaire)
                .findFirst();
        if (optGest.isPresent()) {
            optGest.get().getListeClients().add(client);
            return true;
        }
        return false;
    }

    public boolean removeClientFromGestionnaire(int idGestionnaire, model.Client client) {
        Optional<Manager> optGest = managers.stream()
                .filter(g -> g.getIdGestionnaire() == idGestionnaire)
                .findFirst();
        if (optGest.isPresent()) {
            return optGest.get().getListeClients().remove(client);
        }
        return false;
    }
}