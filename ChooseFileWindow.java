package oknowyboruplikow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;


public class Oknowyboruplikow extends JFrame
{
    
    public Oknowyboruplikow()
        {

            this.setTitle("Okno wyboru plikow");
            this.setBounds(250, 300, 300, 250);
            
            
            JFileChooser chooseFile = new JFileChooser();
            chooseFile.setCurrentDirectory(new File(System.getProperty("user.dir")));
            chooseFile.setMultiSelectionEnabled(true);
            
            chooseFile.setAcceptAllFileFilterUsed(true);
            chooseFile.setFileFilter(new FileEnlargementFilter ("Field text", TEXT_ENLARGEMENTS));
            chooseFile.setFileFilter(new FileFilter() 
            {
                @Override
                public boolean accept(File file) 
                {
                    return file.isDirectory();
                }

                @Override
                public String getDescription() 
                {
                    return "Catalogs";
                    
                }
            });
            
            button1.addActionListener((ActionEvent ae) -> 
            {
                int tmp = chooseFile.showOpenDialog(rootPane);
                if (tmp == 0)
                    createZipFile(chooseFile.getSelectedFiles());
            });
            
            
            button2.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    chooseFile.showDialog(rootPane, "Make Zip File");
                    
                }
            });
            
            
            this.buttonPanel.add(button1);
            this.buttonPanel.add(button2);


            this.getContentPane().add(buttonPanel);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    
    private final JPanel buttonPanel = new JPanel();
    private final JButton button1 = new JButton("Open");
    private final JButton button2 = new JButton("Save");
    private final String[] TEXT_ENLARGEMENTS = new String[] {".txt", ".xml", ".mf"};
    
    public void createZipFile(File[] files)
    {
        for(int i = 0; i < files.length; i++)
            System.out.println(files[i].getPath());
       
    }
    

    public static void main(String[] args) 
    {
        
        
        new Oknowyboruplikow().setVisible(true);
        
    }
    
    private class FileEnlargementFilter extends FileFilter
    {
        public FileEnlargementFilter (String description, String[] enlargements)
        {
            this.description = description;
            this.enlargements = enlargements;
            
        }
        
        @Override
        public boolean accept(File file) 
        {  
           for(int i = 0; i < this.enlargements.length; i++)
               if(file.getName().toLowerCase().endsWith(this.enlargements[i]) || file.isDirectory())
                   return true;
           
           
           
           
           return false;
        }

        @Override
        public String getDescription() 
        {
           return description;

        }
        
        private final String description;
        private final String[] enlargements;
        
    }
    
}
