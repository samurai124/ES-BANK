package Client;

import Account.Account;
import Person.Person;
import java.util.ArrayList;

public class Client extends Person {
    static int nextId = 1;
    private int idClient;
    private int numeroClient;
    private ArrayList<Account> accounts = new ArrayList<>();

    public Client(String nom, String prenom, int age,String email,int numeroClient){
        super(nom, prenom, age, email);
        this.idClient = nextId++;
        this.numeroClient = numeroClient;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getNumeroClient() {
        return numeroClient;
    }

    public void setNumeroClient(int numeroClient) {
        this.numeroClient = numeroClient;
    }

    public void ajouterCompte(Account account){
        accounts.add(account);
    }
    public void supprimerCompte(Account account){
        accounts.remove(account);
    }
}
