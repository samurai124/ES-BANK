package service;

import java.util.*;

import model.Account;
import model.Client;
import model.SavingAccount;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;

public class Bank {

//    private ArrayList<Client> clients = new ArrayList<>();
//    private ArrayList<Account> accounts = new ArrayList<>();
    private ArrayList<Client> clients = new ArrayList<>(Arrays.asList(
            new Client("zaidi","hamza",21,"mail",123),
            new Client("ke","ven",23,"mail",133),
            new Client("see","mantes",23,"mail",323)

    ));
    private ArrayList<Account> accounts = new ArrayList<>(Arrays.asList(
            new Account(12344,1000,clients.get(0)),
            new Account(1000,1000,clients.get(1)),
            new Account(1111,1000,clients.get(2))

    ));
    static Scanner input = new Scanner(System.in);

    // function pour affiche  les clients
    public void afficherClients() {
        System.out.println("_______________________________________________________________________\n____________________________List des client____________________________\n_______________________________________________________________________");
        if (clients.isEmpty()){
            System.out.println("aucun client exist !!!");
        }
        for (Client c : clients) {
            System.out.printf("Numero Client : %d | Nom : %s | Prenom : %s \n", c.getNumeroClient(), c.getNom(), c.getPrenom());
            for (Account a : c.getAccounts()) {
                System.out.printf("       Numero compte : %d \n", a.getNumeroCompte());
            }
        }
    }

    // function pour affiche les comptes
    public void afficherComptes() {
        System.out.println("_______________________________________________________________________\n____________________________List des Compte____________________________\n_______________________________________________________________________");
        if (accounts.isEmpty()){
            System.out.println("aucun compte existe !!!");
        }
        for (Account a : accounts) {
            System.out.printf("Numero compte : %d | Solde : %.2f | Proprietaire : %s %s\n", a.getNumeroCompte(), a.getSolde(), a.getClient().getNom(), a.getClient().getPrenom());
        }

    }

    // function pour ajouter un client
    public void ajouterClient() {
        Random r = new Random();
        String nom, prenom, email;
        int age, numeroClient;
        System.out.println("______________________________________________________________________\n____________________________Ajouter client____________________________\n______________________________________________________________________");
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
        System.out.println("_______________________________________________________________________\n_____________________________Creer compte_____________________________\n_______________________________________________________________________");
        if (!accounts.isEmpty()) {
        do {
            System.out.print("est-ce un compte d’épargne ? (oui/non) : ");
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
            System.out.printf("Numero client : %d ,Nom et prenom: %s %s \n", clients.get(i).getNumeroClient(), clients.get(i).getNom(), clients.get(i).getPrenom());
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
                    "Compt  d’épargne  ajouté avec succès. Numéro compt : %d%n\n",
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
        }}else{
            Client c = null;
            System.out.printf("Aucun client exist  !!!\n");
            System.out.print("\nTu ajouter un cleint ? (oui/non) : ");
            String ajouterClient = input.nextLine();
            if (ajouterClient.equals("oui")) {
                ajouterClient();
                c = clients.get(clients.size() - 1);
            } else {
                return;
            }
            int numCompte;
            if (accounts.isEmpty()) {
                numCompte = r.nextInt(1000, 9999);
            } else {
                boolean isEqual = false;
                do {
                    numCompte = r.nextInt(1000, 9999);
                    for (int i = 0; i < accounts.size(); i++) {
                        if (accounts.get(i).getNumeroCompte() == numCompte) {
                            isEqual = true;
                            break;
                        }
                    }
                } while (isEqual == true);
            }
            Account a = new Account(numCompte, solde, c);
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
        System.out.println("_______________________________________________________________________\n____________________________Supprimer  client____________________________\n_______________________________________________________________________");
        if (clients.isEmpty()) {
            System.out.println("aucun client existe !!!");
            return;
        }

        System.out.println("Veuillez choisir le numero Client :");
        for (int i = 0; i < clients.size(); i++) {
            System.out.printf("Numero client : %d ,Nom et prenom: %s %s \n", clients.get(i).getNumeroClient(), clients.get(i).getNom(), clients.get(i).getPrenom());
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
                    System.out.printf("\nle Compte avec numero : %d est supprimer avec succes\n", accounts.get(i).getNumeroCompte());
                    accounts.remove(i);
                }
            }
        }
    }

    // supprimer un compte
    public void supprimerCompte() {
        int numCompt = 0;
        System.out.println("\n_______________________________________________________________________\n____________________________Supprimer compte____________________________\n_______________________________________________________________________");
        for (Account a : accounts) {
            System.out.printf("Numero copmte : %d | solde : %.2f | client numero : %d\n", a.getNumeroCompte(), a.getSolde(), a.getClient().getNumeroClient());
        }
        if (accounts.isEmpty()) {
            System.out.println("auc'un compt existe !!!! ");
            return;
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
            if (res.equals("non")) {
                System.out.println("ok");
            } else if (res.equals("oui")) {
                clients.remove(client);
                System.out.println("client est supprimer !");
            } else {
                System.out.println("Invalide choix !!!!");
            }
        }
    }
    // deposer function
    public void deposerfunc(){
        System.out.println("_______________________________________________________________________\n____________________________ Deposer ____________________________\n_______________________________________________________________________");
        for (Account a : accounts) {
            System.out.printf("Numero compte : %d | Solde : %.2f | Proprietaire : %s %s\n", a.getNumeroCompte(), a.getSolde(), a.getClient().getNom(), a.getClient().getPrenom());
        }
        int choice ;
        do {
            System.out.print("Entrez le numero du compte dans lequel tu veux déposer : ");
            String choix = input.nextLine();
            if (choix.isEmpty()) {
                System.out.println("Veuillez entrer  un numero  !!! ");
                choice = 0;
            } else {
                try {
                    choice = Integer.parseInt(choix);
                } catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer  un numero valide  !!! ");
                    choice = 0;
                }
            }
        }while (choice == 0);
        Account c = null;
        for (Account a : accounts){
            if (a.getNumeroCompte() == choice){
                c = a;
            }
        }
        float montant;
        if (c == null){
            System.out.printf("Le compte avec le numero %d n'exsist pas !!",choice);
        }else{
            do {
                System.out.print("Entrez le montant que vous souhaitez deposer : ");
                String montantEntrer = input.nextLine();
                if (montantEntrer.isEmpty()) {
                    System.out.println("Veuillez entrer  un montant valide  !!! ");
                    montant = 0;
                } else {
                    try {
                        montant = Float.parseFloat(montantEntrer);
                    } catch (NumberFormatException e) {
                        System.out.println("Veuillez entrer  un montant valide  !!! ");
                        montant = 0;
                    }
                    if (montant < 0){
                        System.out.println("Veuillez entrer  un montant valide  !!! ");
                        montant = 0;
                    }
                }
            }while (montant == 0);
            c.deposer(montant);
            System.out.println("Le depot a ete effectue avec succes ");
        }
    }
    // retirier function
    public void retirerfunc(){
        System.out.println("_______________________________________________________________________\n____________________________ Retirer ____________________________\n_______________________________________________________________________");

        for (Account a : accounts) {
            System.out.printf(
                    "Numero compte : %d | Solde : %.2f | Proprietaire : %s %s\n",a.getNumeroCompte(),a.getSolde(),a.getClient().getNom(),a.getClient().getPrenom()
            );
        }

        int choice;
        do {
            System.out.print("Entrez le numero du compte dans lequel tu veux retirer : ");
            String choix = input.nextLine();

            if (choix.isEmpty()) {
                System.out.println("Veuillez entrer un numero valide !!!");
                choice = 0;
            } else {
                try {
                    choice = Integer.parseInt(choix);
                } catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer un numero valide !!!");
                    choice = 0;
                }
            }
        } while (choice == 0);

        Account c = null;
        for (Account a : accounts) {
            if (a.getNumeroCompte() == choice) {
                c = a;
            }
        }

        float montant;
        if (c == null) {
            System.out.printf("Le compte avec le numero %d n'existe pas !!\n", choice);
        } else {
            do {
                System.out.print("Entrez le montant que vous souhaitez retirer : ");
                String montantEntrer = input.nextLine();

                if (montantEntrer.isEmpty()) {
                    System.out.println("Veuillez entrer un montant valide !!!");
                    montant = 0;
                } else {
                    try {
                        montant = Float.parseFloat(montantEntrer);
                    } catch (NumberFormatException e) {
                        System.out.println("Veuillez entrer un montant valide !!!");
                        montant = 0;
                    }

                    if (montant <= 0) {
                        System.out.println("Veuillez entrer un montant valide !!!");
                        montant = 0;
                    } else if (montant > c.getSolde()) {
                        System.out.println("Solde insuffisant !!!");
                        montant = 0;
                    }
                }
            } while (montant == 0);

            c.retirer(montant);
            System.out.println("Le retrait a ete effectue avec succes.");
        }
    }
    // consulter solde function
    public void consulterSolde(){
        System.out.println("_______________________________________________________________________\n___________________________ Consulter Solde ____________________________\n_______________________________________________________________________");

        for (Account a : accounts) {
            System.out.printf("Numero compte : %d | Proprietaire : %s %s\n", a.getNumeroCompte(), a.getClient().getNom(), a.getClient().getPrenom());
        }

        int choice;
        do {
            System.out.print("Entrez le numero du compte que vous souhaitez consulter : ");
            String choix = input.nextLine();

            if (choix.isEmpty()) {
                System.out.println("Veuillez entrer un numero valide !!!");
                choice = 0;
            } else {
                try {
                    choice = Integer.parseInt(choix);
                } catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer un numero valide !!!");
                    choice = 0;
                }
            }
        } while (choice == 0);

        Account c = null;
        for (Account a : accounts) {
            if (a.getNumeroCompte() == choice) {
                c = a;
            }
        }

        if (c == null) {
            System.out.printf("Le compte avec le numero %d n'existe pas !!\n", choice);
        } else {
            System.out.println("---------------------------------------------------------------");
            System.out.printf("Solde du compte %d (%s %s) : %.2f\n", c.getNumeroCompte(), c.getClient().getNom(), c.getClient().getPrenom(),c.getSolde());
            System.out.println("---------------------------------------------------------------");
        }
    }


    // menu
    public int menu() {
        System.out.println("_______________________________________________________________________\n____________________________E-bank menu____________________________\n_______________________________________________________________________");
        System.out.println("1 ==> Afficher les comptes\n2 ==> AFficher les clients\n3 ==> Ajouter un client\n4 ==> Créer un compte bancaire\n5 ==> Consulter le solde\n6 ==> Déposer de l’argent\n7 ==> Retirer de l’argent\n8 ==> Supprimer un compte\n9 ==> Supprimer un client\n10 ==> Export les comptes sous form exel\n11 ==> transféré un  montant\n12 ==> quitter l'app");
        System.out.print("Veuillez entrer  votre choix : ");
        String choix = input.nextLine();
        int choice = 0 ;
        if (choix.isEmpty()) {
            System.out.println("Veuillez entrer  un choix !!!!");
            return 0;
        } else {
            try {
                choice = Integer.parseInt(choix);
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer  un choix valide !!!!!!");
                return 0;
            }
        }
        return choice;
    }
    // export the data as exel
    public void exportExcel() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Comptes");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Numero Compte");
        header.createCell(1).setCellValue("Nom");
        header.createCell(2).setCellValue("Prenom");
        header.createCell(3).setCellValue("Solde");

        int rowNum = 1;

        for (Account a : accounts) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(a.getNumeroCompte());
            row.createCell(1).setCellValue(a.getClient().getNom());
            row.createCell(2).setCellValue(a.getClient().getPrenom());
            row.createCell(3).setCellValue(a.getSolde());
        }

        try (FileOutputStream fos = new FileOutputStream("C:\\Users\\Pc\\Desktop\\comptes.xlsx")) {
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Export Excel terminé avec succès !");
    }
    public void transfererMontant() {
        System.out.println("_______________________________________________________________________\n____________________________ Transférer ____________________________\n_______________________________________________________________________");
        for (Account a : accounts) {
            System.out.printf("Numero compte : %d | Proprietaire : %s %s\n", a.getNumeroCompte(), a.getClient().getNom(), a.getClient().getPrenom());
        }
        // requperer le compte source
        int numeroCompte = 0;
        do {
            System.out.print("Veuillez entrer le numero du compte  source : ");
            String numeroCompteSourceString = input.nextLine();
            if (numeroCompteSourceString.isEmpty()) {
                System.out.println("Veuillez entrer un numero !!!!");
                numeroCompte = 0;

            }else {
                try {
                    numeroCompte = Integer.parseInt(numeroCompteSourceString);
                }catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer un numero valide !!!!");
                    numeroCompte = 0;
                }
            }
        }while (numeroCompte == 0);
        Account cs = null;
        for (Account a : accounts) {
            if (a.getNumeroCompte() == numeroCompte) {
                cs = a;
            }
        }
        if (cs == null) {
            System.out.println("Compte n'existe pas !!!!");
            return;
        }
        if (cs.getSolde() == 0) {
            System.out.println("Solde suffisant !!!!");
            return;
        }
        // recuperer le montant
        float montantAtrensferer = 0;
        do {
            System.out.print("Veuillez entrer la montant  : ");
            try {
                montantAtrensferer = Float.parseFloat(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un montant valide !!!!");
                montantAtrensferer = 0;
            }
            if(montantAtrensferer < 0){
                System.out.println("Veuillez entrer un montant valide !!!!");
                montantAtrensferer = 0;
            }
        }while (montantAtrensferer == 0);
        // verifier le solde du compte source
        if (cs.getSolde() == 0) {
            System.out.println("Solde insuffisant !!!!");
            return;
        }
        if (cs.getSolde() - montantAtrensferer < 0) {
            System.out.println("Solde insuffisant !!!!");
            return;
        }
        // recuperer le compte destination
        Account cd = null;
        int numeroCompted = 0;
        do {
            System.out.print("Veuillez entrer le numero du compte destination : ");
            try {
                numeroCompted = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un numero valide !!!!");
                numeroCompted = 0;
            }
        }while (numeroCompted == 0);
        for(Account a : accounts){
            if (a.getNumeroCompte() == numeroCompted) {
                cd = a;
            }
        }
        if (cd == null) {
            System.out.println("Compte n'existe pas !!!!");
            return;
        }
        // transeferer le montant
        cd.deposer(montantAtrensferer);
        cs.retirer(montantAtrensferer);

        System.out.println("Le montant est transfere avec succes");

    }

    public void EbankApp(){
        System.out.println("_______________________________________________________________________\n____________________________WELCOMME TO E-bank____________________________\n_______________________________________________________________________");
        int choix = 0;
        do {
            choix = menu();
            switch (choix){
                case 1 : afficherComptes(); break;
                case 2 : afficherClients();break;
                case 3 : ajouterClient();break;
                case 4 : creerCompte();break;
                case 5 : consulterSolde();break;
                case 6 : deposerfunc();break;
                case 7 : retirerfunc();break;
                case 8 : supprimerCompte();break;
                case 9 : supprimerClient();break;
                case 10 : exportExcel();break;
                case 11: transfererMontant();break;
                case 12 : System.out.println("____________________________E-bank____________________________");break;
                default : System.out.println("Invalide choix !!!!!");break;
            }
        }while (choix != 12);
    }
}

