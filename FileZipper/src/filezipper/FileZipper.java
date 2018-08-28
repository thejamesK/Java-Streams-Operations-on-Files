package filezipper;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
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
                System.out.println("Zip");
           
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
    }
        
}
