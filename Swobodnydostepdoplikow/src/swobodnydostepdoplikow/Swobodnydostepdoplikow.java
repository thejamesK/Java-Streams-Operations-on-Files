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
            /*
            DataOutputStream outS = new DataOutputStream(new FileOutputStream("new.txt"));
            
            outS.writeDouble(12412.123);
            outS.close();
            
            DataInputStream inS = new DataInputStream(new FileInputStream("new.txt"));
            
            System.out.println(inS.readDouble());
            inS.close();
            */
            
            RandomAccessFile RAF = new RandomAccessFile("new.txt", "rw");
            
            RAF.writeDouble(123.42);
            RAF.writeDouble(41.23);
            RAF.writeChars("Aala   ");
            
            System.out.println(RAF.getFilePointer());
            
            RAF.seek(16);
            System.out.println(RAF.readChar());
            
            
            System.out.println(Double.SIZE/8 + Integer.SIZE/8);
            
            RAF.close();
        }
        
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
        
    }
    
}