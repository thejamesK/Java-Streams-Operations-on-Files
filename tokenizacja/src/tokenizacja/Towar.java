package tokenizacja;

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
    
    public static void saveToFile(Towar[] product, PrintWriter outS)
    {
        outS.println(product.length);
        GregorianCalendar calendar = new GregorianCalendar();
        for(int i = 0 ; i < product.length ; i++)
        {
            calendar.setTime(product[i].getDate());
            outS.println(product[i].getPrice()+"|"+product[i].getName()+"|"+calendar.get(Calendar.YEAR) + "|" + (calendar.get(Calendar.MONTH)+1) + "|" + calendar.get(Calendar.DAY_OF_MONTH));
        }
    }
    
    public static Towar[] readFromFile(BufferedReader inS) throws  IOException
    {
        int lenght = Integer.parseInt(inS.readLine());
        Towar[] product = new Towar[lenght];
        
        
        for (int i = 0; i < lenght; i++)
        {
            String line = inS.readLine();
            StringTokenizer tokens = new StringTokenizer(line, "|");
            double price = Double.parseDouble(tokens.nextToken());
            String name = tokens.nextToken();
            int year = Integer.parseInt(tokens.nextToken());
            int month = Integer.parseInt(tokens.nextToken());
            int day = Integer.parseInt(tokens.nextToken());
            
            product[i] = new Towar(price, name, year, month, day);
        }
        return product;
    }
    private double price;
    private String name;
    private Date dateOut;
    
}
