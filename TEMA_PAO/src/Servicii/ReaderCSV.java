package Servicii;

import CLASE.PERSOANE.Author;
import CLASE.PRODUSE.Carti.Books;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;


public class ReaderCSV {
    private static ReaderCSV single_instance = null;
    public Vector<Books>books;
    private void ReadBook(String [] array, int k){

        String name = array[k++];
        Author a = new Author(array[k++], array[k++], Integer.parseInt(array[k++]), array[k++], Integer.parseInt(array[k++]));
        int releaseDate = Integer.parseInt(array[k++]);
        Books book = new Books(name, a, releaseDate);
        books.add(book);
    }

    private <T> void AuxiliaryMethod(String path, Class<T> classOf){
        try{
            BufferedReader buffer = new BufferedReader(new FileReader(path));
            String line = buffer.readLine();
            while(line != null){
                String [] array = line.split(",");
                int k = 0;
                if (classOf.toString().equals("class CLASE.PRODUSE.Carti.Books")){
                    this.ReadBook(array, k);
                }
                else
                    System.out.println("Eroare la citirea din fisier");
                line = buffer.readLine();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    private ReaderCSV(){
        this.books = new Vector<Books>();
        this.AuxiliaryMethod("src/Files/Books.csv", Books.class);
    }

    public static synchronized ReaderCSV getInstance(){
        if (single_instance == null)
            single_instance = new ReaderCSV();
        return single_instance;
    }

    public Vector<Books> getBooks() {
        return books;
    }
}
