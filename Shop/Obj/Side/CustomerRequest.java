package Obj.Side;

import Obj.IShopChild;
import Obj.Main.Shop;
import Obj.Main.User;
import com.sun.net.httpserver.Request;

import java.util.ArrayList;
import java.util.List;

public class CustomerRequest implements IShopChild
{
    //==========================================Variable==========================================
    private static final String KEY = "This is Private Key";
    private String id;
    private Shop shop;
    private User requestedCustomer;
    private User handledStaff;
    private List<OrderedItem> requestedOrderedItems;



    //========================================Constructor=========================================
    public CustomerRequest()
    {
        this.id = "";
        this.shop = new Shop();
        this.requestedCustomer = new User();
        this.handledStaff = new User();
        this.requestedOrderedItems = new ArrayList<OrderedItem>();

    }

    public CustomerRequest(String id, Shop shop, User requestedCustomer, User handledStaff,
                           List<OrderedItem> requestedOrderedItems)
    {
        this.id = id;
        this.shop = shop;
        this.requestedCustomer = requestedCustomer;
        this.handledStaff = handledStaff;
        this.requestedOrderedItems = requestedOrderedItems;
    }



    //============================================Get=============================================
    //=====================================Get DataBase Data======================================
    // Main
    @Override
    public String getId()
    {
        return this.id;
    }
    @Override
    public Shop getShop()
    {
        this.queryInfo();
        return this.shop;
    }
    public User getRequestedCustomer()
    {
        this.queryInfo();
        return this.requestedCustomer;
    }
    public User getHandledStaff()
    {
        this.queryInfo();
        return this.handledStaff;
    }
    public List<OrderedItem> getRequestedItemAmounts()
    {
        this.queryInfo();
        return this.requestedOrderedItems;
    }

    // Addition
    public float getTotalPrice()
    {
        this.queryInfo();
        float totalMoney = 0;
        for (OrderedItem requestedOrderedItem : this.requestedOrderedItems)
        {
            totalMoney += requestedOrderedItem.getAmount();
        }

        return totalMoney;
    }

    //=======================================Get Curr Data========================================
    @Override
    public Shop getShop(String privateKey)
    {
        if (!KEY.equals(privateKey)) return null;
        return this.shop;
    }
    @Override
    public String getId(String privateKey)
    {
        if (!KEY.equals(privateKey)) return null;
        return this.id;
    }
    public User getRequestedCustomer(String privateKey)
    {
        if (!KEY.equals(privateKey)) return null;
        return this.requestedCustomer;
    }
    public User getHandledStaff(String privateKey)
    {
        if (!KEY.equals(privateKey)) return null;
        return this.handledStaff;
    }
    public List<OrderedItem> getRequestedItemAmounts(String privateKey)
    {
        if (!KEY.equals(privateKey)) return null;
        return this.requestedOrderedItems;
    }



    //===========================================Modify===========================================
    public void setRequestedCustomer(User requestedCustomer)
    {
        this.queryInfo();
        this.requestedCustomer = requestedCustomer;
        this.updateInfo();
    }
    public void setHandledStaff(User handledStaff)
    {
        this.queryInfo();
        this.handledStaff = handledStaff;
        this.updateInfo();
    }
    public void setRequestedItemAmounts(List<OrderedItem> requestedOrderedItems)
    {
        this.queryInfo();
        this.requestedOrderedItems = requestedOrderedItems;
        this.updateInfo();
    }



    //==========================================DataBase==========================================
    private void queryInfo()
    {
        CustomerRequest customerRequest = this.shop.getDataBase().queryCustomerRequestData(this.id);
        if (customerRequest == null)
        {
            System.out.println("Error: Can't query CustomerRequest " + this.id);
            return;
        }

        this.shop = customerRequest.getShop(KEY);
        this.requestedCustomer = customerRequest.getRequestedCustomer(KEY);
        this.handledStaff = customerRequest.getHandledStaff(KEY);
        this.requestedOrderedItems = customerRequest.getRequestedItemAmounts(KEY);
    }

    private void updateInfo()
    {
        this.shop.getDataBase().updateCustomerRequestData(this);
    }
}
