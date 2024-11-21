package Obj.Side;

import Obj.IShopChild;
import Obj.Main.Shop;
import Obj.Main.User;

import java.util.ArrayList;
import java.util.List;

public class ActiveShop implements IShopChild
{
    //==========================================Variable==========================================
    // ActiveShop
    private static final String KEY = "This is Private Key";
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
    public List<String> getCustomerRequestIds()
    {
        this.queryInfo();
        List<String> results = new ArrayList<String>();
        for (CustomerRequest customerRequest : this.customerRequests)
        {
            results.add(customerRequest.getId());
        }

        return results;
    }

    public List<String> getActiveUserIds()
    {
        this.queryInfo();
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
        if (!KEY.equals(privateKey)) return null;
        return this.id;
    }
    @Override
    public Shop getShop(String privateKey)
    {
        if (!KEY.equals(privateKey)) return null;
        return this.shop;
    }
    public List<User> getActiveUsers(String privateKey)
    {
        if (!KEY.equals(privateKey)) return null;
        return this.activeUsers;
    }
    public List<CustomerRequest> getCustomerRequests(String privateKey)
    {
        if (!KEY.equals(privateKey)) return null;
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

        this.shop = activeShop.getShop(KEY);
        this.activeUsers = activeShop.getActiveUsers(KEY);
        this.customerRequests = activeShop.getCustomerRequests(KEY);
    }

    private void updateInfo()
    {
        this.shop.getDataBase().updateActiveShopData(this);
    }
}
