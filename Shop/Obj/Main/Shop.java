package Obj.Main;

import Obj.IAccount;
import Obj.IMainObj;

public class Shop implements IMainObj, IAccount
{
    //==========================================Variable==========================================
    // Shop
    private String systemCode;

    // IObj
    private String id;

    // IMainObj
    private String name;

    // IAccount
    private String password;

    //========================================Constructor=========================================
    public Shop()
    {
        super();
        this.systemCode = "";
    }

    public Shop(String id, String name, String password, String systemCode)
    {
        // Shop
        this.systemCode = systemCode;

        // IObj
        this.id = id;

        // IMainObj
        this.name = name;

        // IAccount
        this.password = password;
    }

    //==========================================Get Set===========================================
    public String getSystemCode() { return this.systemCode; }
    public void setSystemCode(String systemCode) { this.systemCode = systemCode; }

    //==========================================IAccount==========================================
    @Override
    public String getPassword()
    {
        return this.password;
    }

    @Override
    public void setPassword(String password)
    {
        this.password = password;
    }

    //==========================================IMainObj==========================================
    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    //============================================IObj============================================
    @Override
    public String getId()
    {
        return this.id;
    }

    @Override
    public void setId(String id)
    {
        this.id = id;
    }
}
