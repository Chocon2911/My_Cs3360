package Obj.Main.Account.User;

import Obj.ActiveShop;
import Obj.Main.Account.BaseAccount;
import Obj.UserType;

public class User extends BaseAccount
{
    //==========================================Variable==========================================
    private ActiveShop activeShop;
    private UserType userType;

    //========================================Constructor=========================================
    public User()
    {
        super();
        this.activeShop = new ActiveShop();
        this.userType = null;
    }

    public User(String id, String name, String password,
                ActiveShop activeShop, UserType userType)
    {
        super(id, name, password);
        this.activeShop = activeShop;
        this.userType = userType;
    }

    public User(String id, String name, String password,
                ActiveShop activeShop)
    {
        super(id, name, password);
        this.activeShop = activeShop;
    }

    //==========================================Get Set===========================================
    public ActiveShop getActiveShopSystem() { return this.activeShop; }
    public UserType getUserType() { return this.userType; }

    //===========================================Modify===========================================
    public void setActiveShopSystem(ActiveShop activeShop)
    { this.activeShop = activeShop; }

    public void setUserType(UserType userType)
    { this.userType = userType; }
}
