package Obj.Main.Account;

public class Shop extends BaseAccount
{
    //==========================================Variable==========================================
    private String systemCode;

    //========================================Constructor=========================================
    public Shop()
    {
        super();
        this.systemCode = "";
    }

    public Shop(String id, String name, String password, String systemCode)
    {
        super(id, name, password);
        this.systemCode = systemCode;
    }

    //==========================================Get Set===========================================
    public String getSystemCode() { return this.systemCode; }
    public void setSystemCode(String systemCode) { this.systemCode = systemCode; }
}
