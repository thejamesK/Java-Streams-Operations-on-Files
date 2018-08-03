package abstrakcyjneklasystrumieni;

import java.io.*;

public class Abstrakcyjneklasystrumieni 
{

    public static void main(String[] args) throws IOException 
    {
        File plik = new File("fileName.txt");
        
        InputStream inS;
        OutputStream outS;
        
        Reader reader;
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(plik)));
        
        
        ((BufferedWriter)writer).write("lala");
        
        ((BufferedWriter)writer).newLine();
        ((BufferedWriter)writer).write("next line");
        
        ((BufferedWriter)writer).close();                                      
    }
    
    void nazwaF(InputStream inS)
    {
        
    }
    
}
