package Obj;

import HuySystem.HuyString;
import Obj.Main.ShopSystem;
import Obj.Main.User.Customer;
import Obj.Main.User.Manager;
import Obj.Main.User.Staff;

import java.util.ArrayList;
import java.util.List;

public class ActiveShopSystem extends BaseObj
{
    //==========================================Variable==========================================
    private ShopSystem shopSystem;
    private List<Manager> activeManagers;
    private List<Staff> activeStaffs;
    private List<Customer> activeCustomers;
    private List<ItemAmount> unSoldItemAmount;
    private List<ItemAmount> soldItemAmount;
    private List<CustomerRequest> customerRequests;

    //========================================Constructor=========================================
    public ActiveShopSystem()
    {
        super();
        this.activeManagers = new ArrayList<Manager>();
        this.activeStaffs = new ArrayList<Staff>();
        this.activeCustomers = new ArrayList<Customer>();
        this.unSoldItemAmount = new ArrayList<ItemAmount>();
        this.soldItemAmount = new ArrayList<ItemAmount>();
        this.customerRequests = new ArrayList<>();
    }

    public ActiveShopSystem(HuyString id, HuyString name, List<Manager> activeManager,
                            List<Staff> activeStaff, List<Customer> activeCustomer,
                            List<ItemAmount> unSoldItem, List<ItemAmount> soldItem,
                            List<CustomerRequest> customerRequest)
    {
        super(id);
        this.activeManagers = activeManager;
        this.activeStaffs= activeStaff;
        this.activeCustomers = activeCustomer;
        this.unSoldItemAmount = unSoldItem;
        this.soldItemAmount = soldItem;
        this.customerRequests = customerRequest;
    }

    //============================================Get=============================================
    public List<Manager> getActiveManagers() { return this.activeManagers; }
    public List<Staff> getActiveStaff() { return this.activeStaffs; }
    public List<Customer> getActiveCustomer() { return this.activeCustomers; }
    public List<ItemAmount> getUnSoldItemAmount() { return this.unSoldItemAmount; }
    public List<ItemAmount> getSoldItemAmount() { return this.soldItemAmount; }
    public List<CustomerRequest> getCustomerRequests() { return this.customerRequests; }

    //===========================================Modify===========================================
    public void setActiveManagers(List<Manager> activeManagers)
    { this.activeManagers = activeManagers; }

    public void setActiveStaffs(List<Staff> activeStaffs)
    { this.activeStaffs = activeStaffs; }

    public void setActiveCustomers(List<Customer> activeCustomers)
    { this.activeCustomers = activeCustomers; }

    public void setUnSoldItemAmount(List<ItemAmount> unSoldItemAmount)
    { this.unSoldItemAmount = unSoldItemAmount; }

    public void setSoldItemAmount(List<ItemAmount> soldItemAmount)
    { this.soldItemAmount = soldItemAmount; }

    public void setCustomerRequests(List<CustomerRequest> customerRequests)
    { this.customerRequests = customerRequests; }
}
