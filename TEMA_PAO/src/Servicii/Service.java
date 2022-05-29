package Servicii;

import CLASE.*;
import CLASE.PERSOANE.Author;
import CLASE.PERSOANE.Client;
import CLASE.PRODUSE.Carti.Books;
import CLASE.PRODUSE.Carti.Reviews;
import CLASE.PRODUSE.Supplies;

import java.io.IOException;
import java.util.*;
import static java.rmi.server.LogStream.log;

public class Service {

    private TreeSet<Section> sections1 = new TreeSet<>();
    private HashSet<Client> clients1 = new HashSet<>();
    private AuditService writing;
    private ReaderCSV reading;
    public Service(){
        this.writing = AuditService.getInstance();
        this.reading = ReaderCSV.getInstance();
    }
    public TreeSet<Section> getSections1() {
        return sections1;
    }
    public HashSet<Client> getClients1s1() {
        return clients1;
    }
    public void setSections1(TreeSet<Section> sections1) {
        this.sections1 = sections1;
    }
    public void setClients1(HashSet<Client> clients1) {
        this.clients1 = clients1;
    }
    public void addInSection(){
        writing.WriteTimestamp("Add in Section");
        HashMap<Books, Integer> sectiune = new HashMap<>();
        Vector<Reviews> reviews = new Vector<>();
        Reviews rev1 = new Reviews("Cosmin", " Carte foarte buna", 7);
        Reviews rev2 = new Reviews("Andreea", " Carte foarte foarte buna", 8);
        Reviews rev3 = new Reviews("Georgi", " Carte foarte foarte foarte buna", 10);
        reviews.add(rev1);
        reviews.add(rev2);
        Author author = new Author("Cosmin", "074388343", 23, "19 02 1999", 3);
        Books book = new Books(author, "Cinderella", "Horror", 2002, 20, reviews);
        sectiune.put(book, 4);
        Reviews rev4 = new Reviews("Pixie", " Carte foarte proasta", 4);
        Vector<Reviews> reviews1 = new Vector<>();
        reviews1.add(rev3);
        reviews1.add(rev4);
        Author author1 = new Author("Laura", "07433433", 25, "19 02 1950", 5);
        Books book1 = new Books(author, "Blabla", "Horror", 2007, 40, reviews1);
        sectiune.put(book1, 7);
        Section section = new Section("Horror", sectiune);
        sections1.add(section);
    }
    public void laura(Client client) throws IOException {
        writing.WriteTimestamp("Add a Client");
        log("Added client");
        clients1.add(client);
    }
    public void removeClient(String name) {
        writing.WriteTimestamp("Remove a client");
        Iterator it = clients1.iterator();
        while(it.hasNext()){
            Client x = (Client) it.next();
            if(x.getName().equals(name)){
                clients1.remove(x);
            }

    }

    }
    public void blockBook(Client client, Books book, Section section) {
        writing.WriteTimestamp("Book a book");
        try {
            section.lowerStock(book);
            client.addPurchasedBooks(book);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void unblockBook(Client client, Books book, Section section) {
        writing.WriteTimestamp("Unblock book");
        if (clients1.contains(client)) {
            section.addStock(book);
            client.removePurchasedBooks(book);
        } else {
            System.out.println("Client was not registered!");
        }
    }
    public void blockSupply(Client client, Supplies supply) {
        writing.WriteTimestamp("Book a supply");
        if (clients1.contains(client)) {
            try {
                client.addPurchasedSupplies(supply);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Client was not registered!");
        }
    }
    public void unblockSupply(Client client, String name) {
        writing.WriteTimestamp("Unbook a supply");
        if (clients1.contains(client)) {
            int nr;
            for(nr = 0; nr < client.getPurchasedSupplies().size(); nr++){
                if(client.getPurchasedSupplies().get(nr).getType().equals(name))
                    client.getPurchasedSupplies().remove(nr);
            }

        } else {
            System.out.println("Client was not registered!");
        }
    }
    public void newSection(Section section) {
        writing.WriteTimestamp("Add a new section");
        sections1.add(section);
    }
    public void removeSection(String name) {
        writing.WriteTimestamp("Remove a section");
        sections1.removeIf(s -> s.getDenumire().equals(name));
    }
    public Section getSection(String name) {
        writing.WriteTimestamp("Get a Section");
        for (Section section : sections1) {
            if (section.getDenumire().equals(name)) {
                return section;
            }
        }
        return null;
    }
    public TreeSet<Section> getAllSections() {
        writing.WriteTimestamp("Get all sections");
        return sections1;
    }
    public Set<Books> getAllBooksFromSection(String name) {
        writing.WriteTimestamp("Get all books from a section");
        for (Section sect : sections1) {
            if (sect.getDenumire().equals(name)) {
                return sect.getBooks();
            }
        }
        return null;
    }
    public Set<Books> getAllBooksFromSection2(Section section) {
        writing.WriteTimestamp("Get all books from a section");
        return section.getBooks();
    }
    public void emptySection(Section section) {
        writing.WriteTimestamp("Empty a section");
        section.setStockAll(new HashMap<Books, Integer>());
    }
    public void removeAllStockFromSection(String name) {
        writing.WriteTimestamp("Remove all stock from a section");
        for (Section sect : sections1) {
            if (sect.getDenumire().equals(name)) {
                for (Books book : sect.getBooks()) {
                    sect.setStock(book, 0);
                }
            }
        }
    }
    public void addStock(Section section, Books book) {
        writing.WriteTimestamp("Add stock");
        section.addStock(book);
    }
    public void addStock(Section section, Books book, int cantitate) {
        writing.WriteTimestamp("Add stock");
        section.addStock(book, cantitate);
    }
    public void addStock(/*String sectionName,*/Section section, String name) {
        writing.WriteTimestamp("Add stock");
       /* Iterator<Section> itr = sections1.iterator();
        while(itr.hasNext()){
            Map.Entry entry = (Map.Entry) itr.next();
            if(((Section) entry.getValue()).getDenumire().equals(sectionName)){
                for(Books book : itr.next().getBooks()){
                    if(book.getName().equals(bookName)){
                        sections1.
                    }
                }
            }
*/
        for (Books book : section.getBooks()) {
            if (book.getName().equals(name)) {
                section.addStock(book);
            }
        }
    }
    public void addStock(Section section, String name, int cantitate) {
        writing.WriteTimestamp("Add stock");
        for (Books book : section.getBooks()) {
            if (book.getName().equals(name)) {
                section.addStock(book, cantitate);
            }
        }
    }
    public void newBook(String name, String section, Author author, int releaseDate) {
        writing.WriteTimestamp("Add a new book");
        Books book = new Books(name, author, releaseDate);

        for (Section sect : sections1) {
            if (sect.getDenumire().equals(section)) {
                sect.addStock(book);
            }
        }
    }
    public void newBook(String name, Section section, Author author, int releaseDate) {
        writing.WriteTimestamp("Add a new book");
        Books book = new Books(name, author, releaseDate);
        section.addStock(book);
    }
    public void deleteBook(String name) throws Exception {
        writing.WriteTimestamp("Remove a book");
        for (Section sect : sections1) {
            for (Books book : sect.getBooks()) {
                if (book.getName().equals(name)) {
                    sect.removeStock(book);
                    return;
                }
            }
        }
    }
    public void deleteBook(Books book){
        writing.WriteTimestamp("Remove a book");
        for(Section sect : sections1){
            try{
                sect.removeStock(book);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public Vector<Books> getAllBooks() {
        writing.WriteTimestamp("Get all books");
        Vector<Books> book = new Vector<>();
        for(Section sect : sections1){
            book.addAll(sect.getBooks());
        }

            book.addAll(this.reading.getBooks());
        return book;
    }
    public void addReview(Books book, Reviews review){
        writing.WriteTimestamp("Add a new review");
        book.addReview(review);
    }
    public int releaseDateBiggerThan(int year, String section){
        writing.WriteTimestamp("Get the books with a release date bigger tham x");
        Set<Books> books = getAllBooksFromSection(section);
        Iterator it = books.iterator();
        int number = 0;
        while(it.hasNext()){
            Map.Entry entry = (Map.Entry) it.next();
            if(((Books) entry.getValue()).biggerThan(year) == 1){
                number++;
            }
        }
        return number;
    }
    public void greatestBookFromSection(String name){
        writing.WriteTimestamp("Get the greatest book from a section");
        Set<Books> books = getAllBooksFromSection(name);
        Iterator it = books.iterator();
        float maxx = 0;
        String nameBook = null;
        while(it.hasNext()){
            Map.Entry entry = (Map.Entry) it.next();
            if(((Books) entry.getValue()).averageBookRate() > maxx){
                maxx = ((Books) entry.getValue()).averageBookRate();
                nameBook = ((Books) entry.getValue()).getName();
            }
        }
        System.out.println(nameBook);
    }
    public void writeOneSection(){
        writing.WriteTimestamp("write a section in CSV");

        Scanner var = new Scanner(System.in);
        WriterCSV write = WriterCSV.getInstance();

        System.out.println("Introduce the name of the section you want to write in CSV: ");
        String name = var.nextLine();
        System.out.println("Give the path of the file do you want to write in");
        System.out.println("For example: src/Files/Output.csv");
        System.out.print("Introduce path: ");
        String path = var.nextLine();

        Iterator it = sections1.iterator();
        int ok = 0;
        while(it.hasNext()){
            Section entry = (Section) it.next();
            if(((Section)entry).getDenumire().equalsIgnoreCase(name)){
                System.out.println("Found the section " + name);
                ok = 1;
                write.writeSection((Section)entry, ((Section)entry).getClass(), path);
                break;
            }
        }
        if(ok == 0){
            System.out.println("Sorry, the section " + name + " doesn't exists");
        }
    }

}