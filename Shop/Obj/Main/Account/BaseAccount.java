package Obj.Main.Account;

import Obj.Main.BaseMainObj;

public class BaseAccount extends BaseMainObj
{
    //==========================================Variable==========================================
    private String password;

    //========================================Constructor=========================================
    public BaseAccount()
    {
        super();
        password = "";
    }

    public BaseAccount(String id, String name, String password)
    {
        super(id, name);
        this.password = password;
    }

    //==========================================Get Set===========================================
    public String getPassword() { return this.password; }
    public void setPassword(String password) { this.password = password; }
}
