package Obj;

import Obj.Main.Account.User.User;

import java.util.ArrayList;
import java.util.List;

public class CustomerRequest extends BaseObj
{
    //==========================================Variable==========================================
    private User requestedCustomer;
    private User handledStaff;
    private List<OrderedItem> requestedOrderedItems;

    //========================================Constructor=========================================
    public CustomerRequest()
    {
        super();
        this.requestedCustomer = new User();
        this.handledStaff = new User();
        this.requestedOrderedItems = new ArrayList<OrderedItem>();
    }

    public CustomerRequest(String id, User requestedCustomer, User handledStaff,
                           List<OrderedItem> requestedOrderedItems)
    {
        super(id);
        this.requestedCustomer = requestedCustomer;
        this.handledStaff = handledStaff;
        this.requestedOrderedItems = requestedOrderedItems;
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
}
