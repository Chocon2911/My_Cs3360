package Obj;

import DataBase.DataBase;
import Obj.Main.Account.ShopSystem;
import Obj.Main.Account.User.Customer;
import Obj.Main.Account.User.Manager;
import Obj.Main.Account.User.Staff;

import java.util.ArrayList;
import java.util.List;

public class ActiveShopSystem extends BaseObj
{
    //==========================================Variable==========================================
    private ShopSystem shopSystem;
    private List<Manager> activeManagers;
    private List<Staff> activeStaffs;
    private List<Customer> activeCustomers;
    private List<CustomerRequest> customerRequests;
    private DataBase dataBase;

//    private String dataBaseUrl = "jdbc:sqlite:./DataBase/Shop.db";

    //========================================Constructor=========================================
    public ActiveShopSystem()
    {
        super();
        this.shopSystem = new ShopSystem();
        this.activeManagers = new ArrayList<Manager>();
        this.activeStaffs = new ArrayList<Staff>();
        this.activeCustomers = new ArrayList<Customer>();
        this.customerRequests = new ArrayList<>();
        this.dataBase = new DataBase();
    }

    public ActiveShopSystem(String id, ShopSystem shopSystem, List<Manager> activeManager,
                            List<Staff> activeStaff, List<Customer> activeCustomer,
                            List<CustomerRequest> customerRequest, DataBase dataBase)
    {
        super(id);
        this.shopSystem = shopSystem;
        this.activeManagers = activeManager;
        this.activeStaffs= activeStaff;
        this.activeCustomers = activeCustomer;
        this.customerRequests = customerRequest;
        this.dataBase = dataBase;
    }

    //============================================Get=============================================
    public ShopSystem getShopSystem() { return this.shopSystem; }
    public List<Manager> getActiveManagers() { return this.activeManagers; }
    public List<Staff> getActiveStaff() { return this.activeStaffs; }
    public List<Customer> getActiveCustomer() { return this.activeCustomers; }
    public List<CustomerRequest> getCustomerRequests() { return this.customerRequests; }
    public DataBase getDataBase() { return this.dataBase; }

    public List<String> getActiveMangerIds()
    {
        List<String> results = new ArrayList<String>();
        for (Manager manager : this.activeManagers)
        {
            results.add(manager.getId());
        }

        return results;
    }

    public List<String> getActiveStaffIds()
    {
        List<String> results = new ArrayList<String>();
        for (Staff staff : this.activeStaffs)
        {
            results.add(staff.getId());
        }

        return results;
    }

    public List<String> getActiveCustomerIds()
    {
        List<String> results = new ArrayList<String>();
        for (Customer customer : this.activeCustomers)
        {
            results.add(customer.getId());
        }

        return results;
    }

    public List<String> getCustomerRequestIds()
    {
        List<String> results = new ArrayList<String>();
        for (CustomerRequest customerRequest : this.customerRequests)
        {
            results.add(customerRequest.getId());
        }

        return results;
    }

    //===========================================Modify===========================================
    public void setActiveManagers(List<Manager> activeManagers)
    { this.activeManagers = activeManagers; }

    public void setActiveStaffs(List<Staff> activeStaffs)
    { this.activeStaffs = activeStaffs; }

    public void setActiveCustomers(List<Customer> activeCustomers)
    { this.activeCustomers = activeCustomers; }

    public void setCustomerRequests(List<CustomerRequest> customerRequests)
    { this.customerRequests = customerRequests; }

    public void setDataBase(DataBase dataBase)
    { this.dataBase = dataBase; }
}
