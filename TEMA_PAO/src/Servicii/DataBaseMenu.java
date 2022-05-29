package Servicii;

import CLASE.AuditService;
import CLASE.PERSOANE.Author;
import CLASE.PERSOANE.Client;
import CLASE.PERSOANE.Seller;
import Data.DataSetup;
import Repository.AuthorRepository;
import Repository.ClientRepository;
import Repository.SellerRepository;

import java.util.Scanner;

public class DataBaseMenu {

    private DataSetup setUpData;
    private AuditService writing;
    Scanner var;
    private static DataBaseMenu single_instance = null;

    private DataBaseMenu(){
        this.writing = AuditService.getInstance();
        this.setUpData = new DataSetup();
        this.var=new Scanner(System.in);
    }

    public static synchronized DataBaseMenu getInstance() {
        if (single_instance == null)
            single_instance = new DataBaseMenu();
        return single_instance;
    }

    private void Options(){
        System.out.println("---------Database Manipulation Menu-----------");
        System.out.println("--------1) List the rows of a table-----------");
        System.out.println("------------2) Insert a person----------------");
        System.out.println("----3) Get a person by name from database-----");
        System.out.println("-----------4) Update a person-----------------");
        System.out.println("-----------5) Delete a person-----------------");
        System.out.println("---6) Delete information from every table-----");
        System.out.println("------------7) Drop all tables----------------");
    }

    private void LoadDatabase(){
        this.setUpData.dropAllTables();
        this.setUpData.createTables();
        this.setUpData.addRows();
    }

    private void insertPerson(){
        writing.WriteTimestamp("Insert a person in database");

        System.out.print("What type of person do you want to add? (author / client/ seller)");

        String choice = this.var.nextLine();

        if(choice.equalsIgnoreCase("author")){

            AuthorRepository authorRepository = new AuthorRepository();
            Author author = new Author();
            author.scan();
            authorRepository.insertAuthor(author);

        }else if(choice.equalsIgnoreCase("client")){

            ClientRepository clientRepository = new ClientRepository();
            Client client = new Client();
            client.scan2();
            clientRepository.insertClient(client);

        }else if(choice.equalsIgnoreCase("seller")){

            SellerRepository sellerRepository = new SellerRepository();
            Seller seller = new Seller();
            seller.scan();
            sellerRepository.insertSeller(seller);

        }
    }

    private void getPerson(){
        writing.WriteTimestamp("Show a person from database");

        System.out.print("What type of person do you want to show? (author / client/ seller)");

        String choice = this.var.nextLine();
        String name="";

        if(choice.equalsIgnoreCase("author")){

            AuthorRepository authorRepository = new AuthorRepository();
            System.out.print("Author's name: ");
            name = var.nextLine();
            Author author = authorRepository.getAuthorByName(name);
            System.out.println(author);

        }else if(choice.equalsIgnoreCase("client")){

            ClientRepository clientRepository = new ClientRepository();
            System.out.print("Client's name: ");
            name = var.nextLine();
            Client client = clientRepository.getClientByName(name);
            System.out.println(client);

        }else if(choice.equalsIgnoreCase("seller")){

            SellerRepository sellerRepository = new SellerRepository();
            System.out.print("Seller's name: ");
            name = var.nextLine();
            Seller seller = sellerRepository.getSellerByName(name);
            System.out.println(seller);

        }
    }

    private void updatePerson(){
        writing.WriteTimestamp("Update a person from database");

        System.out.print("What type of person do you want to update? (author / client/ seller)");

        String choice = this.var.nextLine();
        String name="";

        if(choice.equalsIgnoreCase("author")){

            AuthorRepository authorRepository = new AuthorRepository();
            System.out.print("Author's name: ");
            name = var.nextLine();
            System.out.print("Author's new number: ");
            String number = var.nextLine();
            authorRepository.updateAuthor(name, number);
            Author author = authorRepository.getAuthorByName(name);
            System.out.println(author);

        }else if(choice.equalsIgnoreCase("client")){

            ClientRepository clientRepository = new ClientRepository();
            System.out.println("Client's name: ");
            name = var.nextLine();
            System.out.print("Clien't new number: ");
            String number = var.nextLine();
            clientRepository.updateClient(name, number);
            Client client = clientRepository.getClientByName(name);
            System.out.println(client);

        }else if(choice.equalsIgnoreCase("seller")){

            SellerRepository sellerRepository = new SellerRepository();
            System.out.println("Seller's name: ");
            name = var.nextLine();
            System.out.print("Seller't new number: ");
            String number = var.nextLine();
            sellerRepository.updateSeller(name, number);
            Seller seller = sellerRepository.getSellerByName(name);
            System.out.println(seller);

        }
    }

    private void deletePerson(){
        writing.WriteTimestamp("Delete a person from database");

        System.out.print("What type of person do you want to delete? (author / client/ seller)");

        String choice = this.var.nextLine();
        String name="";

        if(choice.equalsIgnoreCase("author")){

            AuthorRepository authorRepository = new AuthorRepository();
            System.out.print("Author's name: ");
            name = var.nextLine();
            authorRepository.deleteAuthorByName(name);

        }else if(choice.equalsIgnoreCase("client")){

            ClientRepository clientRepository = new ClientRepository();
            System.out.print("Client's name: ");
            name = var.nextLine();
            clientRepository.deleteClientByName(name);

        }else if(choice.equalsIgnoreCase("seller")){

            SellerRepository sellerRepository = new SellerRepository();
            System.out.print("Seller's name: ");
            name = var.nextLine();
            sellerRepository.deleteSellerByName(name);

        }
    }

    public void DatabaseService(){
        this.LoadDatabase();

        Scanner Var = new Scanner(System.in);

        while(true) {
            this.Options();
            System.out.print("Choose one action[1/2/3/4/5/6/7]:");
            int option = Var.nextInt();
            if (option == 1) {
                this.writing.WriteTimestamp("Display a table");
                this.setUpData.displayTable();
            } else if (option == 2) {
                this.writing.WriteTimestamp("Insert a person");
                this.insertPerson();
            } else if (option == 3) {
                this.writing.WriteTimestamp("Display a person");
                this.getPerson();
            } else if (option == 4) {
                this.writing.WriteTimestamp("Update a person");
                this.updatePerson();
            } else if (option == 5) {
                this.writing.WriteTimestamp("Delete a person");
                this.deletePerson();
            }else if (option == 6){
                this.writing.WriteTimestamp("Delete all informations");
                this.setUpData.deleteAllRows();
            }else if (option == 7){
                this.writing.WriteTimestamp("Drop all tables");
                this.setUpData.dropAllTables();
                System.out.println("All tables were dropped successfully!");
            }
            else {
                System.out.println("Invalid input!");
                continue;
            }
            System.out.print("Do you want another action?[yes/no]: ");

            Var.nextLine();
            String answer = Var.nextLine();

            if(!answer.equalsIgnoreCase("yes")) {
                break;
            }

        }
    }
}
