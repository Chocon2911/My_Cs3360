package Obj;

import DataBase.DataBase;
import Obj.Main.Account.Shop;
import Obj.Main.Account.User.User;

import java.util.ArrayList;
import java.util.List;

public class ActiveShop extends BaseObj
{
    //==========================================Variable==========================================
    private Shop shop;
    private List<CustomerRequest> customerRequests;
    private List<User> activeUsers;
    private DataBase dataBase;

    //========================================Constructor=========================================
    public ActiveShop()
    {
        super();
        this.shop = new Shop();
        this.customerRequests = new ArrayList<>();
        this.activeUsers = new ArrayList<>();
        this.dataBase = new DataBase();
    }

    public ActiveShop(String id, Shop shop, List<CustomerRequest> customerRequest,
                      List<User> activeUsers, DataBase dataBase)
    {
        super(id);
        this.shop = shop;
        this.customerRequests = customerRequest;
        this.activeUsers = activeUsers;
        this.dataBase = dataBase;
    }

    //============================================Get=============================================
    public Shop getShop() { return this.shop; }
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
    public void setShop(Shop shop)
    { this.shop = shop; }

    public void setActiveUsers(List<User> activeUsers)
    { this.activeUsers = activeUsers; }

    public void setCustomerRequests(List<CustomerRequest> customerRequests)
    { this.customerRequests = customerRequests; }

    public void setDataBase(DataBase dataBase)
    { this.dataBase = dataBase; }
}
