package filezipper;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.*;
import sun.font.LayoutPathImpl;


public class FileZipper extends JFrame
{

    public FileZipper()
    {
        this.setTitle("Zipper");
        this.setBounds(275, 300, 250, 300);
        this.setJMenuBar(menuBar);
        
        JMenu menuFile = menuBar.add(new JMenu("File"));
         
                
        Action addAction = new Action("Add", "Add new file to zip", "ctrl Q", new ImageIcon("add.png"));
        Action deleteAction = new Action("Delete", "Delete selected file from zip", "ctrl D", new ImageIcon("delete.png"));
        Action zipAction = new Action("Zip", "Zip all files from list", "ctrl Z");
        
        JMenuItem menuOpen = menuFile.add(addAction);
        JMenuItem menuDelete = menuFile.add(deleteAction);
        JMenuItem menuZip = menuFile.add(zipAction);
        
        
        
        addButton = new JButton(addAction);
        deleteButton = new JButton(deleteAction);
        zipButton = new JButton(zipAction);
        JScrollPane scroll = new JScrollPane(list);
        
        list.setBorder(BorderFactory.createEtchedBorder());
        GroupLayout layout = new GroupLayout(this.getContentPane());
        
        
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);
        
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addComponent(scroll, 100, 150, Short.MAX_VALUE)
                .addContainerGap(0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup().addComponent(addButton).addComponent(deleteButton).addComponent(zipButton))
                );
        
        layout.setVerticalGroup(
                layout.createParallelGroup()
                .addComponent(scroll, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup().addComponent(addButton).addComponent(deleteButton).addGap(5, 40, Short.MAX_VALUE).addComponent(zipButton))
                );
        
        this.getContentPane().setLayout(layout);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.pack();
    }
    
    private DefaultListModel listModel = new DefaultListModel()
    {
        @Override
        public void addElement(Object obj) 
        {
            listA.add(obj);
            super.addElement(((File)obj).getName());
        }
        
        @Override
        public Object get(int index) 
        {
            return listA.get(index);
        }
        
        @Override
        public Object remove(int index) 
        {
            listA.remove(index);
            return super.remove(index);
        }
            
        
        ArrayList listA = new ArrayList();
    };
    private JList list = new JList(listModel);
    private JButton addButton;
    private JButton deleteButton;
    private JButton zipButton;
    
    private JMenuBar menuBar = new JMenuBar();
    private JFileChooser chooser = new JFileChooser();
    
            
            
    public static void main(String[] args) 
    {
        
        new FileZipper().setVisible(true);
        
    }
    
    
    private class Action extends AbstractAction
    {
        
        public Action(String name, String description, String keyShortcut)
        {
           this.putValue(Action.NAME, name);
           this.putValue(Action.SHORT_DESCRIPTION, description);
           this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(keyShortcut));
        }
       
        public Action(String name, String description, String keyShortcut, Icon icon)
        {
            this(name, description, keyShortcut);
            this.putValue(Action.SMALL_ICON, icon);
          
        }  
       
        public void actionPerformed(ActionEvent ae) 
        {
            if(ae.getActionCommand().equals("Add"))
                addFilesToZip();
            else if(ae.getActionCommand().equals("Delete"))
                deleteFilesFromZip();
            else if(ae.getActionCommand().equals("Zip"))
                makeZipFile();
           
        }
        
        
        private void addFilesToZip()
        {
            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            chooser.setMultiSelectionEnabled(enabled);
            
            int tmp = chooser.showDialog(rootPane, "Add to zip");
            
            if (tmp == JFileChooser.APPROVE_OPTION)
            {
                File[] path = chooser.getSelectedFiles();
                
                for(int i = 0; i < path.length; i++)
                    if(!isFileRepeated(path[i].getPath()))
                        listModel.addElement(path[i]);
                
            }
        }
        
        private boolean isFileRepeated(String testedFile)
        {
            for(int i = 0; i < listModel.getSize(); i++)
            {
                if(((File)listModel.get(i)).getPath().equals(testedFile))
                    return true;
                    
            }
            
            return false;
        }
        
        private void deleteFilesFromZip()
        {
            int[] tmp = list.getSelectedIndices();
            
            for(int i = 0; i < tmp.length; i++)
                listModel.remove(tmp[i]-i);
        }
        
        private void makeZipFile()
        {
            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            chooser.setSelectedFile(new File(System.getProperty("user.dir")+File.separator+"*.zip"));
            int tmp = chooser.showDialog(rootPane, "Make Zip!");
            
            if(tmp == JFileChooser.APPROVE_OPTION)
            {
            
                byte tmpData[] = new byte[BUFFOR];
                try 
                {
                    ZipOutputStream zOutS = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(chooser.getSelectedFile()), BUFFOR));

                    for(int i = 0; i < listModel.getSize(); i++)
                    {
                        if(!((File)listModel.get(i)).isDirectory())
                            zip(zOutS, (File)listModel.get(i), tmpData, ((File)listModel.get(i)).getPath());
                        else
                        {
                            printPaths((File)listModel.get(i));
                            
                            for (int j = 0; j < pathList.size(); j++)
                                zip(zOutS, (File)pathList.get(j), tmpData, ((File)listModel.get(i)).getPath());
                            
                            pathList.removeAll(pathList);
                        }
                        
                    }

                    zOutS.close();
                } 
                catch (IOException ex) 
                {
                    System.out.println(ex.getMessage());
                }
            
            }
            
            
        }
        private void zip(ZipOutputStream zOutS, File filePath, byte[] tmpData, String defaultPath) throws IOException
        {
            BufferedInputStream inS = new BufferedInputStream(new FileInputStream(filePath), BUFFOR);

            zOutS.putNextEntry(new ZipEntry(filePath.getPath().substring(defaultPath.lastIndexOf(File.separator)+1)));

            int counter;
            while ((counter = inS.read(tmpData, 0, BUFFOR)) != -1)
                zOutS.write(tmpData, 0, counter);


            zOutS.closeEntry();

            inS.close();
        }
        public static final int BUFFOR = 1024;
        
        private void printPaths(File pathName)
        {
            String[] fileAndDirectoryName = pathName.list();
            
            for(int i = 0; i < fileAndDirectoryName.length; i++)
            {
                File p = new File(pathName.getPath(), fileAndDirectoryName[i]);


                if(p.isFile())
                    pathList.add(p);


                if(p.isDirectory())
                {
                    printPaths(new File (p.getPath()));

                }
            }
        }
        
        ArrayList pathList = new ArrayList();
    }
        
}
