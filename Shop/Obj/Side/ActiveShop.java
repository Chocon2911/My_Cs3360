package Obj.Side;

import DataBase.DataBase;
import Obj.IShopChild;
import Obj.Main.Shop;
import Obj.Main.User;

import java.util.ArrayList;
import java.util.List;

public class ActiveShop implements IShopChild
{
    //==========================================Variable==========================================
    // ActiveShop
    private List<CustomerRequest> customerRequests;
    private List<User> activeUsers;
    private DataBase dataBase;

    // IObj
    private String id;

    // IShopChild
    private Shop shop;

    //========================================Constructor=========================================
    public ActiveShop()
    {
        // ActiveShop
        this.shop = new Shop();
        this.customerRequests = new ArrayList<>();
        this.activeUsers = new ArrayList<>();
        this.dataBase = new DataBase();

        // IObj
        this.id = "";

        // IShopChild
        this.shop = new Shop();
    }

    public ActiveShop(String id, Shop shop, List<CustomerRequest> customerRequest,
                      List<User> activeUsers, DataBase dataBase)
    {
        // ActiveShop
        this.customerRequests = customerRequest;
        this.activeUsers = activeUsers;
        this.dataBase = dataBase;

        // IObj
        this.id = id;

        // IShopChild
        this.shop = shop;
    }

    //============================================Get=============================================
    public List<User> getActiveUsers() { return this.activeUsers; }
    public List<CustomerRequest> getCustomerRequests() { return this.customerRequests; }
    public DataBase getDataBase() { return this.dataBase; }

    public List<String> getCustomerRequestIds()
    {
        List<String> results = new ArrayList<String>();
        for (CustomerRequest customerRequest : this.customerRequests)
        {
            results.add(customerRequest.getId());
        }

        return results;
    }

    public List<String> getActiveUserIds()
    {
        List<String> results = new ArrayList<>();
        for (User activeUser : this.activeUsers)
        {
            results.add(activeUser.getId());
        }

        return results;
    }

    //===========================================Modify===========================================

    public void setActiveUsers(List<User> activeUsers)
    { this.activeUsers = activeUsers; }

    public void setCustomerRequests(List<CustomerRequest> customerRequests)
    { this.customerRequests = customerRequests; }

    public void setDataBase(DataBase dataBase)
    { this.dataBase = dataBase; }

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

    //=========================================IShopChild=========================================
    @Override
    public Shop getShop()
    {
        return this.shop;
    }

    @Override
    public void setShop(Shop shop)
    {
        this.shop = shop;
    }
}
