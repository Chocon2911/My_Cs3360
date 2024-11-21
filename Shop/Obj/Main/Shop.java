package Obj.Main;

import DataBase.DataBaseCtrl;
import Obj.IAccount;
import Obj.IMainObj;

public class Shop implements IMainObj, IAccount
{
    //==========================================Variable==========================================
    private static final String KEY = "This is Private Key";
    private String id;
    private String name;
    private String password;
    private String systemCode;
    private DataBaseCtrl dataBase;



    //========================================Constructor=========================================
    public Shop()
    {
        this.id = "";
        this.name = "";
        this.password = "";
        this.systemCode = "";
    }

    public Shop(String id, String name, String password, String systemCode, DataBaseCtrl dataBase)
    {
        this.id = id;
        this.name = name;
        this.password = password;
        this.systemCode = systemCode;
        this.dataBase = dataBase;
    }



    //============================================Get=============================================
    //=====================================Get DataBase Data======================================
    @Override
    public String getId()
    {
        this.queryInfo();
        return this.id;
    }
    @Override
    public String getName()
    {
        this.queryInfo();
        return this.name;
    }
    @Override
    public String getPassword()
    {
        this.queryInfo();
        return this.password;
    }
    public String getSystemCode()
    {
        this.queryInfo();
        return this.systemCode;
    }
    public DataBaseCtrl getDataBase()
    {
        return this.dataBase;
    }

    //=======================================Get Curr Data========================================
    @Override
    public String getId(String privateKey)
    {
        if (!KEY.equals(privateKey)) return null;
        return this.id;
    }
    @Override
    public String getName(String privateKey)
    {
        if (!KEY.equals(privateKey)) return null;
        return this.name;
    }
    @Override
    public String getPassword(String privateKey)
    {
        if (!KEY.equals(privateKey)) return null;
        return this.password;
    }
    public String getSystemCode(String privateKey)
    {
        if (!KEY.equals(privateKey)) return null;
        return this.systemCode;
    }

    public DataBaseCtrl getDataBase(String privateKey)
    {
        if (!KEY.equals(privateKey)) return null;
        return this.dataBase;
    }



    //===========================================Modify===========================================
    @Override
    public void setName(String name)
    {
        this.queryInfo();
        this.name = name;
        this.updateInfo();
    }
    @Override
    public void setPassword(String password)
    {
        this.queryInfo();
        this.password = password;
        this.updateInfo();
    }
    public void setSystemCode(String systemCode)
    {
        this.queryInfo();
        this.systemCode = systemCode;
        this.updateInfo();
    }



    //==========================================DataBase==========================================
    private void queryInfo()
    {
        Shop shop = this.dataBase.queryShopData(this.id);
        if (shop == null)
        {
            System.out.println("ERROR: Can't query Shop");
            return;
        }

        this.name = shop.getName(KEY);
        this.password = shop.getPassword(KEY);
        this.systemCode = shop.getSystemCode(KEY);
        this.dataBase = shop.getDataBase(KEY);
    }

    private void updateInfo()
    {
        this.dataBase.updateShopData(this);
    }
}
