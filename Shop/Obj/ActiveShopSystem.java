package Obj;

import Obj.Main.ShopSystem;
import Obj.Main.User.Customer;
import Obj.Main.User.Manager;
import Obj.Main.User.Staff;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

    private String dataBaseUrl = "jdbc:sqlite:./DataBase/Shop.db";

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

    public ActiveShopSystem(String id, String name, List<Manager> activeManager,
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

    //==========================================DataBase==========================================
    //===========================================Staff============================================
    // Connection
    public Connection getConnectionToStaffDb()
    {
        try (Connection conn = DriverManager.getConnection(this.dataBaseUrl))
        {
            if (conn == null)
            {
                System.out.println("Connection is null");
            }

            return conn;
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Create Table
    public void createStaffTable()
    {
        Connection conn = getConnectionToStaffDb();
        if (conn == null)
        {
            System.out.println("Connection is null");
            return;
        }

        String createTable = "CREATE TABLE IF NOT EXISTS Staffs "
                + "("
                + "Id INTEGER UNIQUE NOT NULL, "
                + "Name TEXT NOT NULL, "
                + "Password TEXT NOT NULL, "
                + "WorkHour INTEGER NOT NULL,"
                + "MoneyPerHour FLOAT NOT NULL,"
                + ");";
        try (Statement statement = conn.createStatement())
        {
            statement.execute(createTable);
            System.out.println("Created new StaffTable");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Insert Data
    public void insertStaffData(int id, String name, String password, int workHour, int moneyPerHour)
    {
        Connection conn = getConnectionToStaffDb();
        if (conn == null)
        {
            System.out.println("Connection is null");
            return;
        }

        String insertData = "INSERT INTO Staffs (Id, Name, Password, WorkHour, MoneyPerHour) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preStatement = conn.prepareStatement(insertData))
        {
            preStatement.setInt(1, id);
            preStatement.setString(2, name);
            preStatement.setString(3, password);
            preStatement.setInt(4, workHour);
            preStatement.setInt(5, moneyPerHour);
            preStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Query Data
    public Staff queryStaffData(int id)
    {
        String query = "SELECT * FROM Staffs WHERE Id = ?";
        Connection conn = getConnectionToStaffDb();
        if (conn == null)
        {
            System.out.println("Connection is null");
            return null;
        }

        return null;
        // TODO: Conitnue here
    }
}
