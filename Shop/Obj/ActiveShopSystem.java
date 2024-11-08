package Obj;

import HuySystem.HuyString;
import Obj.Main.Customer;
import Obj.Main.Manager;
import Obj.Main.Staff;

import java.util.ArrayList;
import java.util.List;

public class ActiveShopSystem extends BaseObj
{
    //==========================================Variable==========================================
    private List<Manager> activeManagers;
    private List<Staff> activeStaffs;
    private List<Customer> activeCustomers;
    private List<ItemAmount> unSoldItemAmount;
    private List<ItemAmount> soldItemAmount;

    //========================================Constructor=========================================
    public ActiveShopSystem()
    {
        super();
        activeManagers = new ArrayList<Manager>();
        activeStaffs = new ArrayList<Staff>();
        activeCustomers = new ArrayList<Customer>();
        unSoldItemAmount = new ArrayList<ItemAmount>();
        soldItemAmount = new ArrayList<ItemAmount>();
    }

    public ActiveShopSystem(HuyString id, HuyString name, List<Manager> activeManager,
                            List<Staff> activeStaff, List<Customer> activeCustomer,
                            List<ItemAmount> unSoldItem, List<ItemAmount> soldItem, HuyString systemCode)
    {
        super(id);
        this.activeManagers = activeManager;
        this.activeStaffs= activeStaff;
        this.activeCustomers = activeCustomer;
        this.unSoldItemAmount = unSoldItem;
        this.soldItemAmount = soldItem;
    }

    //============================================Get=============================================
    public List<Manager> getActiveManagers() { return this.activeManagers; }
    public List<Staff> getActiveStaff() { return this.activeStaffs; }
    public List<Customer> getActiveCustomer() { return this.activeCustomers; }
    public List<ItemAmount> getUnSoldItemAmount() { return this.unSoldItemAmount; }
    public List<ItemAmount> getSoldItemAmount() { return this.soldItemAmount; }

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
}
