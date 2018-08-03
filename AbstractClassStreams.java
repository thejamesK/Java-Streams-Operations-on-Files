package abstrakcyjneklasystrumieni;

import java.io.*;

public class Abstrakcyjneklasystrumieni 
{

    public static void main(String[] args) throws IOException 
    {
        InputStream inS;
        OutputStream outS;
        
        Reader reader;
        Writer writer = new FileWriter("fileName.txt");
        
        writer.write("lala");
        
        writer.close();
        
                               
    }
    
    void nazwaF(InputStream inS)
    {
        
    }
    
}
