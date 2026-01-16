package Bank;

import java.util.*;

import Account.Account;
import Client.Client;
import Account.SavingAccount;

public class Bank {
    private ArrayList<Account> accounts = new ArrayList<>();
    private ArrayList<Client> clients = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    // function pour affiche  les clients
    public void afficherClients(){
        System.out.println("____________________________\n_______List des client_______\n____________________________");
        for(Client c:clients){
            System.out.printf("Numero Client : %d | Nom : %s | Prenom : %s \n",c.getNumeroClient(),c.getNom(),c.getPrenom());
            for (Account a : c.getAccounts()){
                System.out.printf("       Numero compte : %d \n",a.getNumeroCompte());
            }
        }
    }
    // function pour affiche les comptes
    public void afficherComptes(){
        System.out.println("____________________________\n_______List des Compte_______\n____________________________");
        for (Account a:accounts){
            System.out.printf("Numero compte : %d | Solde : %.2f | Proprietaire : %s %s\n",a.getNumeroCompte(),a.getSolde(),a.getClient().getNom(),a.getClient().getPrenom());
        }

    }
    // function pour ajouter un client
    public void ajouterClient() {
        Random r = new Random();
        String nom, prenom, email;
        int age, numeroClient;
        System.out.println("____________________________\n_______Ajouter client_______\n____________________________");
        // Récupérer le nom
        do {
            System.out.print("Entrez votre nom : ");
            nom = input.nextLine();
            if (nom.trim().isEmpty()) {
                System.out.println("veuillez remplir le nom !!!");
            }
        } while (nom.trim().isEmpty());

        // Récupérer le prenom
        do {
            System.out.print("Entrez votre prenom : ");
            prenom = input.nextLine();
            if (prenom.trim().isEmpty()) {
                System.out.println("veuillez remplir le prenom !!!");
            }
        } while (prenom.trim().isEmpty());

        // Récupérer l'email
        do {
            System.out.print("Entrez votre email : ");
            email = input.nextLine();
            if (email.trim().isEmpty()) {
                System.out.println("veuillez remplir l'email !!!");
            }
        } while (email.trim().isEmpty());
        // Récupérer l'age
        do {
            System.out.print("Entrez votre age : ");
            String ageEntered = input.nextLine();
            if (ageEntered.isEmpty()) {
                System.out.println("veuillez entrer un age  !!!");
                age = -1;
            } else {
                try {
                    age = Integer.parseInt(ageEntered);
                } catch (NumberFormatException e) {
                    System.out.println("entrer un age  (it's a string)!!!.");
                    age = -1;
                }
                if (age < 0) {
                    age = -1;
                    System.out.println("veuillez entrer un age valide !!!");
                } else if (age < 18) {
                    age = -1;
                    System.out.println("L'âge doit être supérieur ou égal à 18 ans !");
                }
            }
        } while (age == -1);

        // generer le numero client;
        if (clients.isEmpty()) {
            numeroClient = r.nextInt(1, 99);
        } else {
            boolean isEqual = false;
            do {
                isEqual = false;
                numeroClient = r.nextInt(1, 99);
                for (int i = 0; i < clients.size(); i++) {
                    if (clients.get(i).getNumeroClient() == numeroClient) {
                        isEqual = true;
                        break;
                    }
                }
            } while (isEqual == true);
        }
        // creer l'obj client
        Client client = new Client(nom, prenom, age, email, numeroClient);
        clients.add(client);
        System.out.printf(
                "Client %s %s ajouté avec succès. Numéro client : %d%n",
                nom, prenom, numeroClient
        );
    }

    // fonction pour cree un compte
    public void creerCompte() {
        Random r = new Random();
        boolean isSavingAccount = false;
        int numeroCompte;
        float solde = 0;
        Client client;
        String answer;
        System.out.println("____________________________\n________Creer compte________\n____________________________");

        do {
            System.out.println("est-ce un compte d’épargne ? (oui/non)");
            answer = input.nextLine();
            if (answer.equals("oui")) {
                isSavingAccount = true;
            } else if (answer.equals("non")) {
                isSavingAccount = false;
            } else {
                System.out.println("Veuillez entrer une valide reponse !!!!!");
                answer = "ok";
            }
        } while (answer.equals("ok"));
        if (accounts.isEmpty()) {
            numeroCompte = r.nextInt(1000, 9999);
        } else {
            boolean isEqual = false;
            do {
                numeroCompte = r.nextInt(1000, 9999);
                for (int i = 0; i < accounts.size(); i++) {
                    if (accounts.get(i).getNumeroCompte() == numeroCompte) {
                        isEqual = true;
                        break;
                    }
                }
            } while (isEqual == true);
        }
        do {
            System.out.print("Entrez solde de base : ");
            String soldeEntered = input.nextLine();
            if (soldeEntered.isEmpty()) {
                System.out.println("veuillez entrer un solde  !!!");
            } else {
                try {
                    solde = Float.parseFloat(soldeEntered);
                } catch (NumberFormatException e) {
                    solde = -1;
                }
                if (solde < 0) {
                    solde = -1;
                    System.out.println("veuillez entrer un solde valide !!!");
                }
            }
        } while (solde == -1);

        // client
        System.out.println("Veuillez choisir le numero Client :");
        for (int i = 0; i < clients.size(); i++) {
            System.out.printf("Numero client : %d ,Nom et prenom: %s %s ", clients.get(i).getNumeroClient(), clients.get(i).getNom(), clients.get(i).getPrenom());
        }
        int numeroClient = 0;
        do {
            System.out.print("\nVeuillez entrer le clinet numero : ");
            String clientNumeroString = input.nextLine();
            if (clientNumeroString.isEmpty()) {
                System.out.println("Veuillez entrer  un numero !!!!!!");
                numeroClient = 0;
            } else {
                try {
                    numeroClient = Integer.parseInt(clientNumeroString);
                } catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer  un numero !!!!!!");
                    numeroClient = 0;
                }
                if (numeroClient < 0) {
                    System.out.println("Veuillez entrer  un numero valide !!!!!!");
                }
            }
        } while (numeroClient == 0);

        Client c = null;
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getNumeroClient() == numeroClient) {
                c = clients.get(i);
            }
        }
        if (c == null) {
            System.out.printf("Le client avec le numero %d n'exist pas !!!", numeroClient);
            System.out.print("\nTu veux l'ajouter ? (oui/non) : ");
            String ajouterClient = input.nextLine();
            if (ajouterClient.equals("oui")) {
                ajouterClient();
                c = clients.get(clients.size() - 1);
            } else {
                return;
            }
        }
        if (isSavingAccount) {
            SavingAccount s = new SavingAccount(numeroCompte, solde, c, 10);
            c.ajouterCompte(s);
            accounts.add(s);
            System.out.printf(
                    "Compt  d’épargne  ajouté avec succès. Numéro compt : %d%n",
                    s.getNumeroCompte()
            );

        } else {
            Account a = new Account(numeroCompte, solde, c);
            c.ajouterCompte(a);
            accounts.add(a);
            System.out.printf(
                    "Compt ajouté avec succès. Numéro compt : %d%n",
                    a.getNumeroCompte()
            );
        }

    }

    // supprimer client
    public void supprimerClient() {
        System.out.println("____________________________\n________Supprimer  client________\n____________________________");

        System.out.println("Veuillez choisir le numero Client :");
        for (int i = 0; i < clients.size(); i++) {
            System.out.printf("Numero client : %d ,Nom et prenom: %s %s ", clients.get(i).getNumeroClient(), clients.get(i).getNom(), clients.get(i).getPrenom());
        }
        int numeroClient = 0;
        String numeroClientstring;
        do {
            System.out.print("\nVeuillez entrer le clinet numero : ");
            numeroClientstring = input.nextLine();
            if (numeroClientstring.isEmpty()) {
                System.out.println("Veuillez entrer  un numero !!!!!!");
                numeroClient = 0;
            } else {
                try {
                    numeroClient = Integer.parseInt(numeroClientstring);
                } catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer  un numero !!!!!!");
                    numeroClient = 0;
                }
                if (numeroClient < 0) {
                    System.out.println("Veuillez entrer  un numero valide !!!!!!");
                    numeroClient = 0;
                }
            }
        } while (numeroClient == 0);
        Client c = null;
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getNumeroClient() == numeroClient) {
                c = clients.get(i);
            }
        }
        if (c == null) {
            System.out.printf("Le client avec le numero %d n'exist pas !!!", numeroClient);
        } else {
            System.out.printf("\nLe client avec le numero : %d est supprimer avec succes", numeroClient);
            clients.remove(c);
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).getClient().getNumeroClient() == numeroClient) {
                    System.out.printf("\nle Compte avec numero : %d est supprimer avec succes", accounts.get(i).getNumeroCompte());
                    accounts.remove(i);
                }
            }
        }
    }

    // supprimer un compte
    public void supprimerCompte() {
        int numCompt = 0;
        System.out.println("____________________________\n______Supprimer compte______\n____________________________");
        for (Account a : accounts) {
            System.out.printf("Numero copmte : %d | solde : %.2f | client numero : %d", a.getNumeroCompte(), a.getSolde(), a.getClient().getNumeroClient());
        }
        System.out.print("\nEntrez le numero compt : ");
        do {
            String numeroCompte = input.nextLine();
            if (numeroCompte.isEmpty()) {
                System.out.println("Veuillez entrer  un numero valide !!!!!!");
                numCompt = -1;
            } else {
                try {
                    numCompt = Integer.parseInt(numeroCompte);
                } catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer  un numero valide !!!!!!");
                    numCompt = -1;
                }
                if (numCompt < 0) {
                    System.out.println("Veuillez entrer  un numero valide !!!!!!");
                    numCompt = -1;
                }
            }
        } while (numCompt == -1);
        Account account = null;
        for (Account a : accounts) {
            if (a.getNumeroCompte() == numCompt) {
                account = a;
            }
        }
        if (account == null) {
            System.out.println("Compte n'exist pas !!!!");
            return;
        }
        Client client = account.getClient();
        client.supprimerCompte(account);
        accounts.remove(account);
        System.out.println("Compte est supprimer !");
        if (client.getAccounts().isEmpty()) {
            System.out.printf("Le client %d n’a plus aucun compte. Voulez-vous le supprimer ?(oui/non)", client.getNumeroClient());
            String res = input.nextLine();
            if (res.equals("non")){
                System.out.println("ok");
            } else if (res.equals("oui")) {
                clients.remove(client);
                System.out.println("client est supprimer !");
            }else{
                System.out.println("Invalide choix !!!!");
            }
        }
    }
}

