package model;

public class SavingAccount extends Account{
    private float tauxInteret;
    public SavingAccount( int numeroCompte, float solde, Client client,float tauxInteret){
        super(numeroCompte,solde,client);
        this.tauxInteret = tauxInteret;
    }

    public float getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(float tauxInteret) {
        this.tauxInteret = tauxInteret;
    }

    public void calculerInter(){


    }


}
