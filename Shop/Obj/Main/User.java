package Obj.Main;

import Obj.*;

public class User extends AbstractKey implements IMainObj, IAccount, IShopChild
{
    //==========================================Variable==========================================
    private String id;
    private String name;
    private String password;
    private Shop shop;
    private UserType userType;



    //========================================Constructor=========================================
    public User()
    {
        this.id = "";
        this.name = "";
        this.password = "";
        this.shop = null;
        this.userType = null;

    }

    public User(String id, String name, String password,
                Shop shop, UserType userType)
    {
        this.id = id;
        this.name = name;
        this.password = password;
        this.shop = shop;
        this.userType = userType;
    }



    //============================================Get=============================================
    //=====================================Get DataBase Data======================================
    @Override
    public String getId()
    {
        return this.id;
    }
    @Override
    public String getName()
    {
        this.queryInfo();
        return this.name;
    }
    @Override
    public Shop getShop()
    {
        queryInfo();
        return this.shop;
    }
    @Override
    public String getPassword()
    {
        this.queryInfo();
        return this.password;
    }
    public UserType getUserType()
    {
        this.queryInfo();
        return this.userType;
    }

    //=======================================Get Curr Data========================================
    @Override
    public String getId(String privateKey)
    {
        if (!this.getKey().equals(privateKey)) return null;
        return this.id;
    }
    @Override
    public String getName(String privateKey)
    {
        if (!this.getKey().equals(privateKey)) return null;
        return this.name;
    }
    @Override
    public String getPassword(String privateKey)
    {
        if (!this.getKey().equals(privateKey)) return null;
        return this.password;
    }
    @Override
    public Shop getShop(String privateKey)
    {
        if (!this.getKey().equals(privateKey)) return null;
        return this.shop;
    }
    public UserType getUserType(String privateKey)
    {
        if (!this.getKey().equals(privateKey)) return null;
        return this.userType;
    }



    //===========================================Modify===========================================
    @Override
    public void setPassword(String password)
    {
        this.queryInfo();
        this.password = password;
        this.updateInfo();
    }
    @Override
    public void setName(String name)
    {
        this.queryInfo();
        this.name = name;
        this.updateInfo();
    }
    public void setUserType(UserType userType)
    {
        this.queryInfo();
        this.userType = userType;
        this.updateInfo();
    }



    //==========================================DataBase==========================================
    private void queryInfo()
    {
        User user = this.shop.getDataBase().queryUserData(this.id);
        if (user == null)
        {
            System.out.println("ERROR: can't query User " + this.id);
            return;
        }

        this.shop = user.getShop(this.getKey());
        this.name = user.getName(this.getKey());
        this.password = user.getPassword(this.getKey());
        this.userType = user.getUserType(this.getKey());
    }

    private void updateInfo()
    {
        this.shop.getDataBase().updateUserData(this);
    }
}
