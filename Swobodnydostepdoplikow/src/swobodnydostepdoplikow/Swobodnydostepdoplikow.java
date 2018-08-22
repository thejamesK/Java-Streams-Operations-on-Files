package swobodnydostepdoplikow;

import java.io.*;


public class Swobodnydostepdoplikow 
{

    public static void main(String[] args) 
    {
        Towar[] product = new Towar[3];
        
        product[0] = new Towar();
        product[1] = new Towar(29.0, "Video Kurs Java");
        product[2] = new Towar(39.0, "Video Kurs C++", 2008, 11, 21);
        
        try
        {
           
            RandomAccessFile RAF = new RandomAccessFile("database.txt", "rw");
            
            Towar.saveToFile(product, RAF);
            RAF.seek(0);
            
            Towar [] products = Towar.readFromFile(RAF);
            
            for(int i = 0; i < products.length; i++)
            {
                System.out.println(products[i].getPrice());
                System.out.println(products[i].getName());
                System.out.println(products[i].getDate());
                System.out.println("------------------------");
            }
            
            /*
            int n = 2;
            RAF.seek((n - 1) * Towar.RECORD_LENGHT);
            
            Towar a = new Towar();
            a.readData(RAF);
            
            
            System.out.println(a);
            */
            
            try
            {
                Towar b = new Towar();

                b.readRecord(RAF, 123);
                System.out.println(b);

                System.out.println("lala");
            }
            catch(MissingRecord err)
            {
                System.out.println(err.getMessage());   
            }
            RAF.close();
        }
        
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
        
    }
    
}