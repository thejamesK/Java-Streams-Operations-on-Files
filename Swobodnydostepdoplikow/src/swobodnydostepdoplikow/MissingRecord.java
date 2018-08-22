package swobodnydostepdoplikow;

public class MissingRecord extends Exception
{
    public MissingRecord()
    {
        super();
    }
    
    public MissingRecord(String error)
    {
        super(error);
    }
    
}
