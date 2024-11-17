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
    // CustomerRequest
    private User requestedCustomer;
    private User handledStaff;
    private List<OrderedItem> requestedOrderedItems;

    // IObj
    private String id;

    // IShopChild
    private Shop shop;


    //========================================Constructor=========================================
    public CustomerRequest()
    {
        // CustomerRequest
        this.requestedCustomer = new User();
        this.handledStaff = new User();
        this.requestedOrderedItems = new ArrayList<OrderedItem>();

        // IObj
        this.id = "";

        // IShopChild
        this.shop = new Shop();
    }

    public CustomerRequest(String id, Shop shop, User requestedCustomer, User handledStaff,
                           List<OrderedItem> requestedOrderedItems)
    {
        // CustomerRequest
        this.requestedCustomer = requestedCustomer;
        this.handledStaff = handledStaff;
        this.requestedOrderedItems = requestedOrderedItems;

        // IObj
        this.id = id;

        // IShopChild
        this.shop = shop;
    }

    //============================================Get=============================================
    public User getRequestedCustomer() { return this.requestedCustomer; }
    public User getHandledStaff() { return this.handledStaff; }
    public List<OrderedItem> getRequestedItemAmounts() { return this.requestedOrderedItems; }

    public float getTotalPrice()
    {
        float totalMoney = 0;
        for (int i = 0; i < this.requestedOrderedItems.size(); i++)
        {
            totalMoney += this.requestedOrderedItems.get(i).getAmount();
        }

        return totalMoney;
    }

    //===========================================Modify===========================================
    public void setRequestedCustomer(User requestedCustomer)
    { this.requestedCustomer = requestedCustomer; }

    public void setHandledStaff(User handledStaff)
    { this.handledStaff = handledStaff; }

    public void setRequestedItemAmounts(List<OrderedItem> requestedOrderedItems)
    { this.requestedOrderedItems = requestedOrderedItems; }

    //=========================================IShopChild=========================================
    @Override
    public Shop getShop() {
        return null;
    }

    @Override
    public void setShop(Shop shop) {

    }

    //============================================IObj============================================
    @Override
    public String getId() {
        return "";
    }

    @Override
    public void setId(String id) {

    }
}
