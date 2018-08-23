package serializacjaobiektow;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Serializacjaobiektow 
{

    public static void main(String[] args) 
    {

        Towar[] product = new Towar[3];
        
        product[0] = new Towar();
        product[1] = new Towar(29.0, "Video Kurs Java");
        product[2] = new Towar(39.0, "Video Kurs C++", 2008, 11, 21);
        
              
        try
        {
            ObjectOutputStream outS = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream("database.txt")));
            
            outS.writeObject(product);
            
            outS.close();
            
            ObjectInputStream inS = new ObjectInputStream(new GZIPInputStream(new FileInputStream("database.txt")));
            
            Towar[] a = (Towar[]) inS.readObject();
            
            for(int i = 0; i < a.length; i++)
                System.out.println(a[i].getName());
            
            inS.close();

        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());   
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }

        
    }
}

