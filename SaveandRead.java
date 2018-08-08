package zapisiodczyt;

import java.io.*;

public class Zapisiodczyt 
{

    public static void main(String[] args) 
    {
        try {
            PrintWriter printer = new PrintWriter(new FileWriter("dane.txt"));
            
            printer.println(1234);
            //printer.flush();
            printer.close();
            
            
            printer = new PrintWriter(new FileWriter("dane.txt", true));
            
            printer.append("lala");
            printer.println();
            printer.printf("On ma %d kilogramow oraz %.2f %s wzrostu", 74, 178.456, "cm");
            
            printer.close();
            
            
            BufferedReader reader = new BufferedReader(new FileReader("dane.txt"));
            
            //System.out.println((char)reader.read());
            
//            System.out.println(reader.readLine());
//            System.out.println(reader.readLine());
//            System.out.println(reader.readLine());
//            System.out.println(reader.readLine());
//            
            BufferedWriter writer = new BufferedWriter(new FileWriter("baza.txt"));
            
            
            String inside = "";
            while((inside = reader.readLine()) != null)
            {
                //System.out.println(inside);
                writer.write(inside);
                writer.newLine();
            }
            
            writer.close();
            reader.close();
            
            
        } 
        catch (IOException ex) 
        {
            System.out.println(ex.getMessage());
        }
        
        
        //Zapisiodczyt.test(1, 2, 4, 125, "lala", 4);
        
        
    }
    
    
    static void test(Object... a)
    {
        for(int i = 0; i < a.length; i++)
        {
            System.out.println(a[i]);
        }
    }
    
}
