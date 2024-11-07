package HuySystem;

public class HuyString
{
    //==========================================Variable==========================================
    private String value;

    //========================================Constructor=========================================
    public HuyString()
    {
        this.value = "";
    }
    public HuyString(String value)
    {
        this.value = value;
    }

    //=========================================Get Modify=========================================
    public String getValue() { return this.value; }
    public void setValue(String value) { this.value = value; }
}
