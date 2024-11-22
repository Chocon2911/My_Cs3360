package Obj.Side;

import Obj.AbstractKey;
import Obj.IShopChild;
import Obj.Main.Shop;
import Obj.Main.User;

import java.util.ArrayList;
import java.util.List;

public class ActiveShop extends AbstractKey implements IShopChild
{
    //==========================================Variable==========================================
    private String id;
    private Shop shop;
    private List<CustomerRequest> customerRequests;
    private List<User> activeUsers;



    //========================================Constructor=========================================
    public ActiveShop()
    {
        this.id = "";
        this.shop = new Shop();
        this.customerRequests = new ArrayList<>();
        this.activeUsers = new ArrayList<>();
    }

    public ActiveShop(String id, Shop shop, List<CustomerRequest> customerRequest,
                      List<User> activeUsers)
    {
        this.id = id;
        this.shop = shop;
        this.customerRequests = customerRequest;
        this.activeUsers = activeUsers;
    }



    //============================================Get=============================================
    //=====================================Get DataBase Data======================================
    // Main
    @Override
    public String getId()
    {
        this.queryInfo();
        return this.id;
    }
    @Override
    public Shop getShop()
    {
        this.queryInfo();
        return this.shop;
    }
    public List<User> getActiveUsers()
    {
        this.queryInfo();
        return this.activeUsers;
    }
    public List<CustomerRequest> getCustomerRequests()
    {
        this.queryInfo();
        return this.customerRequests;
    }

    // Additional
    public List<String> getCustomerRequestIds(String privateKey)
    {
        if (!this.getKey().equals(privateKey)) return null;

        List<String> results = new ArrayList<String>();
        for (CustomerRequest customerRequest : this.customerRequests)
        {
            results.add(customerRequest.getId());
        }

        return results;
    }

    public List<String> getActiveUserIds(String privateKey)
    {
        if (!this.getKey().equals(privateKey)) return null;

        List<String> results = new ArrayList<>();
        for (User activeUser : this.activeUsers)
        {
            results.add(activeUser.getId());
        }

        return results;
    }

    //=======================================Get Curr Data========================================
    @Override
    public String getId(String privateKey)
    {
        if (!this.getKey().equals(privateKey)) return null;
        return this.id;
    }
    @Override
    public Shop getShop(String privateKey)
    {
        if (!this.getKey().equals(privateKey)) return null;
        return this.shop;
    }
    public List<User> getActiveUsers(String privateKey)
    {
        if (!this.getKey().equals(privateKey)) return null;
        return this.activeUsers;
    }
    public List<CustomerRequest> getCustomerRequests(String privateKey)
    {
        if (!this.getKey().equals(privateKey)) return null;
        return this.customerRequests;
    }



    //===========================================Modify===========================================
    public void setActiveUsers(List<User> activeUsers)
    {
        this.queryInfo();
        this.activeUsers = activeUsers;
        this.updateInfo();
    }
    public void setCustomerRequests(List<CustomerRequest> customerRequests)
    {
        this.queryInfo();
        this.customerRequests = customerRequests;
        this.updateInfo();
    }



    //==========================================DataBase==========================================
    private void queryInfo()
    {
        ActiveShop activeShop = this.shop.getDataBase().queryActiveShopData(this.id);
        if (activeShop == null)
        {
            System.out.println("ERROR: Can't query ActiveShop");
            return;
        }

        this.shop = activeShop.getShop(this.getKey());
        this.activeUsers = activeShop.getActiveUsers(this.getKey());
        this.customerRequests = activeShop.getCustomerRequests(this.getKey());
    }

    private void updateInfo()
    {
        this.shop.getDataBase().updateActiveShopData(this);
    }
}
