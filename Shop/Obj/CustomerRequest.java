package Obj;

import Obj.Main.User.Customer;
import Obj.Main.User.Staff;

import java.util.ArrayList;
import java.util.List;

public class CustomerRequest extends BaseObj
{
    //==========================================Variable==========================================
    private Customer requestedCustomer;
    private Staff handledStaff;
    private List<ItemAmount> requestedItemAmounts;

    //========================================Constructor=========================================
    public CustomerRequest()
    {
        super();
        this.requestedCustomer = new Customer();
        this.handledStaff = new Staff();
        this.requestedItemAmounts = new ArrayList<ItemAmount>();
    }

    public CustomerRequest(String id, Customer requestedCustomer, Staff handledStaff,
                           List<ItemAmount> requestedItemAmounts)
    {
        super(id);
        this.requestedCustomer = requestedCustomer;
        this.handledStaff = handledStaff;
        this.requestedItemAmounts = requestedItemAmounts;
    }

    //============================================Get=============================================
    public Customer getRequestedCustomer() { return this.requestedCustomer; }
    public Staff getHandledStaff() { return this.handledStaff; }
    public List<ItemAmount> getRequestedItemAmounts() { return this.requestedItemAmounts; }

    public float getTotalPrice()
    {
        float totalMoney = 0;
        for (int i = 0; i < this.requestedItemAmounts.size(); i++)
        {
            totalMoney += this.requestedItemAmounts.get(i).getAmount();
        }

        return totalMoney;
    }

    //============================================Set=============================================
    public void setRequestedCustomer(Customer requestedCustomer)
    { this.requestedCustomer = requestedCustomer; }

    public void setHandledStaff(Staff handledStaff)
    { this.handledStaff = handledStaff; }

    public void setRequestedItemAmounts(List<ItemAmount> requestedItemAmounts)
    { this.requestedItemAmounts = requestedItemAmounts; }
}
