package Obj.Main.Account;

public class ShopSystem extends BaseAccount
{
    //==========================================Variable==========================================
    private String systemCode;

    //========================================Constructor=========================================
    public ShopSystem()
    {
        super();
        this.systemCode = "";
    }

    public ShopSystem(String id, String name, String password, String systemCode)
    {
        super(id, name, password);
        this.systemCode = systemCode;
    }

    //==========================================Get Set===========================================
    public String getSystemCode() { return this.systemCode; }
    public void setSystemCode(String systemCode) { this.systemCode = systemCode; }
}