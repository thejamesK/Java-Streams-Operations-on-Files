package filesandfolders;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Filesandfolders 
{

    public static void main(String[] args) 
    {
        try 
        {
            File katalog = new File("video kurs" + File.separator + "php" + File.separator + "mysql");
            katalog.mkdirs();
            
            File plik2  = new File("video kurs" + File.separator + "php" + File.separator + "mysql", "lesson1.txt");
            if (!plik2.exists())
                plik2.createNewFile();
                       
            File plik = new File("test.txt");
            
            if (!plik.exists())
            {
                plik.createNewFile();
            }
            
            System.out.println("----------");
            System.out.println(plik.getCanonicalPath()); // zawsze prawdziwa
            System.out.println(plik.getAbsolutePath()); // absolutna doslowna sciezka
            System.out.println("----------");
            
            System.out.println("Can I write?: " + plik.canWrite());
            System.out.println("Can I run?: " + plik.canExecute());
            System.out.println("Can I read?: " + plik.canRead());
            System.out.println("Is this hidden?: " + plik.isHidden());
            System.out.println("Is this file?: " + plik.isFile());
            System.out.println("Last modification?: " + new Date(plik.lastModified()));
            System.out.println("File lenght in bites: " + plik.length());
            System.out.println("----------");
            
            Filesandfolders.printPaths(new File(System.getProperty("user.dir")));
            //plik.list();
            //plik.delete();
            
        } 
        catch (IOException ex) 
        {
            System.out.println(ex.getMessage());
        }
        
        
        
        
           //System.out.println(System.getProperty("user.dir"));
    }
    
    public static void printPaths(File pathName)
    {
        String[] fileAndDirectoryName = pathName.list();
        //System.out.println(pathName.getPath());
        for(int i = 0; i < fileAndDirectoryName.length; i++)
        {
            File p = new File(pathName.getPath(), fileAndDirectoryName[i]);
            
            
            //if(p.isFile()) to find just files
            //    System.out.println(p.getPath());
            
            
            if(p.isDirectory())
            {
                System.out.println(p.getPath()); //to find just directories
                printPaths(new File (p.getPath()));
                
            }
        }
    }
    
}
