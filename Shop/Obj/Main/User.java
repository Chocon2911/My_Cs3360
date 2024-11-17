package Obj.Main;

import Obj.IAccount;
import Obj.IMainObj;
import Obj.IShopChild;
import Obj.Side.ActiveShop;
import Obj.UserType;

public class User implements IMainObj, IAccount, IShopChild
{
    //==========================================Variable==========================================
    // User
    private UserType userType;

    // IObj
    private String id;

    // IMainObj
    private String name;

    // IAccount
    private String password;

    // IShopChild
    private Shop shop;

    //========================================Constructor=========================================
    public User()
    {
        // User
        this.userType = null;

        // IObj
        this.id = "";

        // IMainObj
        this.name = "";

        // IAccount
        this.password = "";

        // IShopChild
        this.shop = null;
    }

    public User(String id, String name, String password,
                Shop shop, UserType userType)
    {
        // User
        this.userType = userType;

        // IObj
        this.id = id;

        // IMainObj
        this.name = name;

        // IAccount
        this.password = password;

        // IShopChild
        this.shop = shop;
    }

    //==========================================Get Set===========================================
    public UserType getUserType() { return this.userType; }
    public void setUserType(UserType userType) { this.userType = userType; }

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

    //=========================================IShopChild=========================================
    @Override
    public Shop getShop()
    {
        return null;
    }

    @Override
    public void setShop(Shop shop)
    {
        this.shop = shop;
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
