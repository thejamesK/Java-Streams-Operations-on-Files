package swobodnydostepdoplikow;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;


public class Towar 
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
    
    @Override
    public String toString()
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(this.dateOut);
        return this.price + " zł; Nazwa: " + this.name + " " + calendar.get(Calendar.YEAR) + " rok " + (calendar.get(Calendar.MONTH)+1) + " miesiac " + calendar.get(Calendar.DAY_OF_MONTH) + " dzien";
        
    }
    
    public static void saveToFile(Towar[] product, DataOutput outS) throws IOException
    {
        for(int i = 0 ; i < product.length ; i++)
        {
            product[i].saveData(outS);
        }
    }
    
    public static Towar[] readFromFile(RandomAccessFile RAF) throws  IOException
    {
        int howManyRecords = (int) (RAF.length() / Towar.RECORD_LENGHT);
        Towar[] product = new Towar[howManyRecords];
        
        
        for (int i = 0; i < howManyRecords; i++)
        {
            product[i] = new Towar();
            product[i].readData(RAF);
        }
        return product;
    }
    
    public void saveData(DataOutput outS) throws IOException
    {
        outS.writeDouble(this.price);
        
        StringBuffer stringB = new StringBuffer(Towar.NAME_LENGHT);
        stringB.append(this.name);
        stringB.setLength(Towar.NAME_LENGHT);
        
        outS.writeChars(stringB.toString());
        
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(this.dateOut);
        
        
        outS.writeInt(calendar.get(Calendar.YEAR));
        outS.writeInt(calendar.get(Calendar.MONTH)+1);
        outS.writeInt(calendar.get(Calendar.DAY_OF_MONTH));
    }
    
    public void readData(DataInput inS) throws IOException
    {
        this.price = inS.readDouble();
        
        StringBuffer tString = new StringBuffer(Towar.NAME_LENGHT);
        
        for(int i = 0; i < Towar.NAME_LENGHT; i++)
        {
            char tCh = inS.readChar();
            
            if (tCh != '\0')
                tString.append(tCh);
            
        }
        
        this.name = tString.toString();
        
        int year = inS.readInt();
        int month = inS.readInt();
        int day = inS.readInt();
        
        GregorianCalendar calendar = new GregorianCalendar(year, month-1, day);
        this.dateOut = calendar.getTime();
        
    }
    
    public void readRecord(RandomAccessFile RAF, int n) throws IOException, MissingRecord
    {
        if(n <= RAF.length() / Towar.RECORD_LENGHT)
        {
            RAF.seek((n - 1)* Towar.RECORD_LENGHT);
            this.readData(RAF);
        }
        else
            throw new MissingRecord("Brak rekordu");
    }
    
    public static final int NAME_LENGHT = 30;
    public static final int RECORD_LENGHT = (Character.SIZE * NAME_LENGHT + Double.SIZE + 3 * Integer.SIZE)/8;
    private double price; // 8 bajtow
    private String name; // NAME_LENGHT * 2 bajtow
    private Date dateOut; // 4 bajty + 4 + 4  RAZEM = 80 bajtow
    
}

