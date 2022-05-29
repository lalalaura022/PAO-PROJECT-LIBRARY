package Servicii;

import CLASE.PRODUSE.Carti.Books;
import CLASE.Section;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriterCSV {
    private static WriterCSV single_instance = null;
    private BufferedWriter buffer;

    private WriterCSV(){}

    public static synchronized WriterCSV getInstance() {
        if (single_instance == null)
            single_instance = new WriterCSV();
        return single_instance;
    }

    public <T> void writeSection(Object section, Class<T> classOf, String path){
        try{
            buffer = new BufferedWriter(new FileWriter(path, true));

            new FileWriter(path, false).close();
            if(classOf.toString().equals("class CLASE.Section")){
                System.out.println("You chose to write a book");
                buffer.write(((Section)section).toString());
            }
            buffer.flush();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

}
