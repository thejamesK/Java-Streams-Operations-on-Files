package archiwizacjadanychrozpakowanie;

import java.io.*;
import java.util.zip.*;

public class Archiwizacjadanychrozpakowanie 
{

    public static final int BUFFOR = 1024;
    public static void main(String[] args) 
    {
        File catalog =  new File(System.getProperty("user.dir") + File.separator + "myZip");
        ZipEntry record = null;
        
        byte[] tmpData = new byte[BUFFOR];
        try
        {
          if(!catalog.exists())
              catalog.mkdir();
            
          ZipInputStream zInS = new ZipInputStream(new BufferedInputStream(new FileInputStream("myZip.zip"), BUFFOR));
          
          
                  while((record = zInS.getNextEntry()) != null)
                  {
                      BufferedOutputStream fOutS = new BufferedOutputStream(new FileOutputStream(catalog + File.separator + record.getName()), BUFFOR);
                      
                      int counter;
                      while((counter = zInS.read(tmpData, 0, BUFFOR))!= -1)
                          fOutS.write(tmpData, 0, counter);
                      
                      
                      fOutS.close();
                      zInS.closeEntry();
                  }
          
          
          
          
          zInS.close();
          
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
        
        
    }
    
}
