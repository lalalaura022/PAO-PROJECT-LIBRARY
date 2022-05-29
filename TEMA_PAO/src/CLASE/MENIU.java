package CLASE;

import CLASE.PERSOANE.Client;
import CLASE.PRODUSE.Carti.Books;
import CLASE.PRODUSE.Carti.Reviews;
import CLASE.PRODUSE.Supplies;
import Servicii.DataBaseMenu;
import Servicii.Service;
//import jdk.incubator.foreign.CLinker;
import java.util.Scanner;
import java.util.*;

public class MENIU {

    private static MENIU single_instance = null;
    private MENIU(){}
    public static synchronized MENIU getInstance() {
        if (single_instance == null)
            single_instance = new MENIU();
        return single_instance;
    }

    private void listActionsForADMIN(){
        System.out.println("----------SIGNED AS ADMIN----------");
        System.out.println("------------1)Add client-----------");
        System.out.println("----------2)Remove client----------");
        System.out.println("-----------3)New section-----------");
        System.out.println("---------4)Remove section----------");
        System.out.println("--5)List all books from a section--");
        System.out.println("--------6)Empty a section----------");
       // System.out.println("-----7)Add stock in a section------");
       // System.out.println("-------8)Add book to section-------");
        System.out.println("-----------9)Delete book-----------");
        System.out.println("------10)Add review for a book-----");
        System.out.println("-----11)Write a section in CSV-----");
        System.out.println("--------12)Manage database---------");
        System.out.println("What operation do you want?(1/2/3/4/5/6/9/10/11/12)");
    }
    private void listActionsForUser(){
        System.out.println("---------------------SIGNED AS CLIENT---------------------");
        System.out.println("----------------------1)Book a book-----------------------");
        System.out.println("--------------------2)Unblock a book----------------------");
        System.out.println("--------------------3)Book a supply-----------------------");
        System.out.println("-------------------4)Unblock a supply---------------------");
        System.out.println("-------------5)List all books from a section--------------");
        System.out.println("--------------------6)List all books----------------------");
        System.out.println("--7)Show the number of books released after a chosen year-");
        System.out.println("--------8)Show the best reviewed book from a section------");
        System.out.println("What operation do you want?(1/2/3/4/5/6/7/8)");
    }
    private void adminActions(Service service) throws Exception {
        Scanner var = new Scanner(System.in);
        service.addInSection();
        while(true){
            this.listActionsForADMIN();
            int option = var.nextInt();
            var.nextLine();
            if(option == 1){
                Client client = new Client();
                client.scan();
                service.laura(client);
            }else if(option == 2){
                String name;
                System.out.println("Those are the customers: ");
                System.out.println(service.getClients1s1());
                System.out.println("What costumer do you want to delete?");
                name = var.nextLine();
                service.removeClient(name);
            }else if(option == 3){
                Section section = new Section();
                section.scan();
                System.out.println("What section do you want to add?");
                service.newSection(section);
            }else if(option == 4){
                String name;
                int nr = 0;
                TreeSet<Section> Sections = new TreeSet<Section>();
                Sections = service.getAllSections();
                for(Section i : Sections){
                    nr++;
                    System.out.println("Section number: " + nr + " " + i.getDenumire());
                }
                System.out.println("What section do you want to remove?(Introduce the name");
                name = var.nextLine();
                service.removeSection(name);
            }else if(option == 5){
                String name;
                System.out.println("What section do you want to list?");
                Set<Books> bks = new HashSet<Books>();
                name = var.nextLine();
                bks = service.getAllBooksFromSection(name);
                System.out.println(bks);
            } else if(option == 6) {
                String name;
                System.out.println("What section do you want to empty?");
                name = var.nextLine();
                service.removeAllStockFromSection(name);
            } /*else if(option == 7){
                int op, nr = 0;
                System.out.println("What is the name of the book u want to add?");
                String name, name2;
                name = var.nextLine();
                System.out.println("Do you want to add only one stock or more? [1/2]");
                op = var.nextInt();
                if(op == 1){
                    TreeSet<Section> sectiuni = new TreeSet<Section>();
                    sectiuni = service.getAllSections();
                    System.out.println(sectiuni);
                    System.out.println("In which section do you want to add a new stock?");
                    name2 = var.nextLine();
                    Iterator<Section> itr = sectiuni.iterator();
                    while(itr.hasNext()){
                        Map.Entry entry = (Map.Entry) itr.next();
                        if(((Section) entry.getValue()).getDenumire().equals(name2)){
                            service.addStock(entry.getValue(), name);
                        }
                    }
                }
            }*/else if(option == 9){
                String name;
                System.out.println("What book do you want to delete?");
                name = var.nextLine();
                service.deleteBook(name);
            }else if(option == 10){
                Reviews review = new Reviews();
                Vector<Books> books = service.getAllBooks();
                int ans;
                for (int i = 0 ; i< books.size();i++) {
                    System.out.println(i + " " +books.get(i).getName());
                }
                System.out.println("alege un nuar de carte pentru review: ");
                ans = var.nextInt();
                var.nextLine();
                review.scan();
                service.addReview(books.get(ans), review);
            }else if(option == 11){
                service.writeOneSection();
            }else if(option == 12){
                DataBaseMenu databaseMenu=DataBaseMenu.getInstance();
                databaseMenu.DatabaseService();
            }
            System.out.print("Do you want another action? [yes/ no]");
            String answer=var.nextLine();
            if(!answer.equalsIgnoreCase("yes"))
                break;
        }
    }
    private void userAction(Service service) {
        Scanner var = new Scanner(System.in);
        service.addInSection();
        while (true) {
            this.listActionsForUser();
            int option = var.nextInt();
            var.nextLine();
            if(option == 1){
                Client client = new Client();
                client.scan();
                Books book = new Books();
                book.scan();
                Section section = new Section();
                section.scan();
                service.blockBook(client, book, section);

            }else if(option == 2){
                Client client = new Client();
                client.scan();
                Books book = new Books();
                book.scan();
                Section section = new Section();
                section.scan();
                service.unblockBook(client, book, section);
            }else if(option == 3){
                Client client = new Client();
                Supplies supply = new Supplies();
                client.scan();
                supply.scan();
                service.blockSupply(client, supply);
            }else if(option == 4){
                Client client = new Client();
                String name;
                client.scan();
                System.out.println("What supply do you want to remove?");
                name = var.nextLine();
                service.unblockSupply(client, name);
            }else if(option == 5){
                String name;
                System.out.println("What section do you want to list?");
                Set<Books> bks = new HashSet<Books>();
                name = var.nextLine();
                bks = service.getAllBooksFromSection(name);
                System.out.println(bks);
            }else if(option == 6){
                Vector<Books> books = new Vector<Books>();
                books = service.getAllBooks();
                System.out.println(books);
            }else if(option == 7) {
                String secction;
                System.out.println("From what section do you want?");
                secction = var.nextLine();
                int year;
                System.out.println("What year do you want?");
                year = var.nextInt();
                System.out.println(service.releaseDateBiggerThan(year, secction));
            }else if(option == 8){
                String name;
                System.out.println("From what section do you want?");
                name = var.nextLine();
                service.greatestBookFromSection(name);
            }
            System.out.print("Do you want another action? [yes/ no]");
            String answer=var.nextLine();
            if(!answer.equalsIgnoreCase("yes"))
                break;

        }
    }
    public void start() throws Exception {
        Scanner var = new Scanner(System.in);
        System.out.println("Do you want to be admin or user? [0/1]");
        Service service = new Service();
        int nr = var.nextInt();
        var.nextLine();
        if(nr == 0){
            this.adminActions(service);
        }
        else if(nr == 1){
            this.userAction(service);
        }

    }
}
