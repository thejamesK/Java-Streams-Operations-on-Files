package filezipper;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


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
        
        list.setBorder(BorderFactory.createEtchedBorder());
        GroupLayout layout = new GroupLayout(this.getContentPane());
        
        
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);
        
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addComponent(list, 100, 150, Short.MAX_VALUE)
                .addContainerGap(0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup().addComponent(addButton).addComponent(deleteButton).addComponent(zipButton))
                );
        
        layout.setVerticalGroup(
                layout.createParallelGroup()
                .addComponent(list, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup().addComponent(addButton).addComponent(deleteButton).addGap(5, 40, Short.MAX_VALUE).addComponent(zipButton))
                );
        
        this.getContentPane().setLayout(layout);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.pack();
    }
    
    private JList list = new JList();
    private JButton addButton;
    private JButton deleteButton;
    private JButton zipButton;
    
    private JMenuBar menuBar = new JMenuBar();
     
    
            
            
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
                System.out.println("Add");
            else if(ae.getActionCommand().equals("Delete"))
                System.out.println("Delete");
            else if(ae.getActionCommand().equals("Zip"))
                System.out.println("Zip");
           
        }
    }
        
}
