package tokenizacja;

import java.io.*;


public class Tokenizacja 
{

    public static void main(String[] args) 
    {
        Towar[] product = new Towar[3];
        
        product[0] = new Towar();
        product[1] = new Towar(29.0, "Video Kurs Java");
        product[2] = new Towar(39.0, "Video Kurs C++", 2008, 11, 21);
        
        try
        {
            PrintWriter writer = new PrintWriter(new FileWriter("database.txt"));
            
            Towar.saveToFile(product, writer);
            
            writer.close();
            
            BufferedReader reader = new BufferedReader(new FileReader("database.txt"));
            
            Towar[] towar2 = Towar.readFromFile(reader);
            
            for(int i = 0; i < towar2.length; i++)
            {
                System.out.println(towar2[i]);
            }
            
            
            reader.close();
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
