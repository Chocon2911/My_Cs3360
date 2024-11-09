package Obj;

import HuySystem.HuyString;
import Obj.Main.User.Customer;
import Obj.Main.User.Staff;
import com.sun.net.httpserver.Request;

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

    public CustomerRequest(HuyString id, Customer requestedCustomer, Staff handledStaff,
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

    //============================================Set=============================================
    public void setRequestedCustomer(Customer requestedCustomer)
    { this.requestedCustomer = requestedCustomer; }

    public void setHandledStaff(Staff handledStaff)
    { this.handledStaff = handledStaff; }

    public void setRequestedItemAmounts(List<ItemAmount> requestedItemAmounts)
    { this.requestedItemAmounts = requestedItemAmounts; }
}
