package serializacjaobiektow;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;


public class Towar implements Serializable
{
    
    public Towar()
    {
        this.price = 0.0;
        name = " ";
        this.dateOut = new GregorianCalendar().getTime();
    }
    public Towar(double price, String name)
    {
        this();
        this.price = price;
        this.name = name;
    }
    
    public Towar(double price, String name, int year, int month, int day)
    {
        this(price, name);
        GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
        this.dateOut = calendar.getTime();
    }
    
    public double getPrice()
    {
        return this.price;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public Date getDate()
    {
        return dateOut;
    }
    
    public void setPrice(double price)
    {
        this.price = price;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setDate(int year, int month, int day)
    {
        GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
        this.dateOut = calendar.getTime();
    }
    
    public void setDate(Date date)
    {
        this.dateOut = date;
    }
    
    public String getPassword()
    {
        return this.password;
    }
    
    @Override
    public String toString()
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(this.dateOut);
        return this.price + " zł; Nazwa: " + this.name + " " + calendar.get(Calendar.YEAR) + " rok " + (calendar.get(Calendar.MONTH)+1) + " miesiac " + calendar.get(Calendar.DAY_OF_MONTH) + " dzien";
        
    }
    
    private void readObject(ObjectInputStream inS) throws IOException, ClassNotFoundException
    {
        inS.defaultReadObject();
        if(password != null)
            if(!password.equals("secret"))
                throw new IOException("Wrong password");
        
        //System.out.println("Im from readobject metod");
    }
    
    private void writeObject(ObjectOutputStream outS) throws IOException
    {
        outS.defaultWriteObject();
        
    }
    
    public static final int NAME_LENGHT = 30;
    public static final int RECORD_LENGHT = (Character.SIZE * NAME_LENGHT + Double.SIZE + 3 * Integer.SIZE)/8;
    private double price; // 8 bajtow
    private String name; // NAME_LENGHT * 2 bajtow
    private Date dateOut; // 4 bajty + 4 + 4  RAZEM = 80 bajtow
    
    
    private transient String password = "secret";
}

