package model;

public class Account {
    static int nexId = 1;
    private int idCompt;
    private int numeroCompte;
    private float solde;
    private Client client;

    public Account(int numeroCompte, float solde, Client client){
        this.idCompt = nexId++;
        this.numeroCompte = numeroCompte;
        this.solde = solde;
        this.client = client;
    }

    public int getIdCompt() {
        return idCompt;
    }

    public void setIdCompt(int idCompt) {
        this.idCompt = idCompt;
    }

    public int getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(int numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void consulterSold(){
        System.out.printf("votre solde est : %.2f\n",this.solde);
    }

    public void deposer(float montant){
        this.solde += montant;
    }

    public void retirer(float montant){
        this.solde -= montant;
    }
}
