package DataBase;

import HuySystem.HuyUtil;
import Obj.*;
import Obj.Main.Item;
import Obj.Main.Account.ShopSystem;
import Obj.Main.Account.User.Customer;
import Obj.Main.Account.User.Manager;
import Obj.Main.Account.User.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.google.gson.Gson;

public class DataBase extends HuyUtil
{
    //===========================================Other============================================
    // Connection
    private Connection getConnection()
    {
        String dataBaseUrl = "jdbc:sqlite:./DataBase/Shop.db";
        try (Connection conn = DriverManager.getConnection(dataBaseUrl))
        {
            if (conn == null)
            {
                System.out.println("Connection is null");
            }

            return conn;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Create Table
    private boolean createTable(String executeLine)
    {
        Connection conn = getConnection();
        if (conn == null) return false;

        try (Statement statement = conn.createStatement())
        {
            statement.execute(executeLine);
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Delete
    private boolean deleteData(String id, String tableName)
    {
        String delete = "DELETE FROM " + tableName + " WHERE Id = ?";
        Connection conn = getConnection();
        if (conn == null) return false;

        try (PreparedStatement preStatement = conn.prepareStatement(delete))
        {
            preStatement.setString(1, id);
            preStatement.executeUpdate();
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //===========================================Staff============================================
    // Create Table
    public void createStaffTable()
    {
        String createTable = "CREATE TABLE IF NOT EXISTS Staffs "
                + "("
                + "Id TEXT UNIQUE PRIMARY KEY NOT NULL, "
                + "Name TEXT NOT NULL, "
                + "Password TEXT NOT NULL, "
                + "WorkHour INTEGER NOT NULL, "
                + "MoneyPerHour FLOAT NOT NULL"
                + ");";

        if (this.createTable(createTable))
        {
            System.out.println("Create Staffs Table Success");
            return;
        }

        System.out.println("Create Staffs Table Fail");
    }

    // Insert Data
    public void insertStaffData(Staff staff)
    {
        Connection conn = getConnection();
        if (conn == null) return;

        String id = staff.getId();
        String name = staff.getName();
        String password = staff.getPassword();
        int workHour = staff.getWorkHour();
        float moneyPerHour = staff.getMoneyPerHour();

        String insertData = "INSERT INTO Staffs (Id, Name, Password, WorkHour, MoneyPerHour) "
                + "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preStatement = conn.prepareStatement(insertData))
        {
            preStatement.setString(1, id);
            preStatement.setString(2, name);
            preStatement.setString(3, password);
            preStatement.setInt(4, workHour);
            preStatement.setFloat(5, moneyPerHour);
            preStatement.executeUpdate();

            System.out.println("Insert Staff: " + id + " - " + name + " - " + password + " - " + workHour + " - " + moneyPerHour);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Query Data
    public Staff queryStaffData(String id, ActiveShopSystem activeShopSystem)
    {
        String query = "SELECT * FROM Staffs WHERE Id = ?";
        Connection conn = getConnection();
        if (conn == null) return null;

        try (PreparedStatement preStatement = conn.prepareStatement(query))
        {
            preStatement.setString(1, id);
            try (ResultSet data = preStatement.executeQuery(query);)
            {
                String name = data.getString("Name");
                String password = data.getString("Password");
                int workHour = data.getInt("WorkHour");
                float moneyPerHour = data.getFloat("MoneyPerHour");

                System.out.println("Query Staff: " + id + " - " + name + " - " + password + " - " + workHour + " - " + moneyPerHour);
                return new Staff(id, name, password, activeShopSystem, workHour, moneyPerHour);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Query All Data
    public List<Staff> queryAllStaffData(ActiveShopSystem activeShopSystem)
    {
        Connection conn = getConnection();
        if (conn == null) return null;

        String query = "SELECT * FROM Staffs";
        try (Statement statement = conn.createStatement();
             ResultSet data = statement.executeQuery(query);)
        {
            List<Staff> staffs = new ArrayList<>();
            while (data.next())
            {
                String id = data.getString("Id");
                String name = data.getString("Name");
                String password = data.getString("Password");
                int workHour = data.getInt("WorkHour");
                float moneyPerHour = data.getFloat("MoneyPerHour");

                staffs.add(new Staff(id, name, password, activeShopSystem, workHour, moneyPerHour));
            }

            System.out.println("Query All Staff");
            return staffs;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void updateStaffData(Staff staff)
    {
        Connection conn = getConnection();
        if (conn == null) return;

        String id = staff.getId();
        String name = staff.getName();
        String password = staff.getPassword();
        int workHour = staff.getWorkHour();
        float moneyPerHour = staff.getMoneyPerHour();

        String update = "UPDATE Staffs SET"
                + " Name = ?, "
                + "Password = ?, "
                + "WorkHour = ?, "
                + "MoneyPerHour = ? "
                + "WHERE Id = ?";

        try (PreparedStatement preStatement = conn.prepareStatement(update))
        {
            // Update Data
            preStatement.setString(1, name);
            preStatement.setString(2, password);
            preStatement.setInt(3, workHour);
            preStatement.setFloat(4, moneyPerHour);

            // Where id is
            preStatement.setString(5, id);
            preStatement.executeUpdate();

            System.out.println("Update Staff with id (" + id + "): " + name + " - " + password + " - " + workHour + " - " + moneyPerHour);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Delete Data
    public void deleteStaffData(String id)
    {
        String delete = "Staffs";
        if (this.deleteData(id, delete))
        {
            System.out.println("Delete Staff success");
            return;
        }

        System.out.println("Delete Staff with id (" + id + ") failed");
    }

    //==========================================Customer==========================================
    // Create Table
    public void createCustomerTable()
    {
        String createTable = "CREATE TABLE IF NOT EXISTS Customers"
                + "("
                + "Id TEXT UNIQUE PRIMARY KEY NOT NULL, "
                + "Name TEXT NOT NULL, "
                + "Password TEXT NOT NULL, "
                + "RankType INTEGER NOT NULL"
                + ");";

        if (this.createTable(createTable))
        {
            System.out.println("Create Customers Table Success");
            return;
        }

        System.out.println("Create Customers Table Failed");
    }

    // Insert Data
    public void insertCustomerData(Customer customer)
    {
        Connection conn = getConnection();
        if (conn == null) return;

        String id = customer.getId();
        String name = customer.getName();
        String password = customer.getPassword();
        RankType rankType = customer.getRankType();

        int rankTypeInt = this.getIntFromRankType(rankType);

        String insertData = "INSERT INTO Customers (Id, Name, Password, RankType)"
                + " VALUES (?, ?, ?, ?)";

        try (PreparedStatement preStatement = conn.prepareStatement(insertData))
        {
            preStatement.setString(1, id);
            preStatement.setString(2, name);
            preStatement.setString(3, password);
            preStatement.setInt(4, rankTypeInt);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Query Data
    public Customer queryCustomerData(String id, ActiveShopSystem activeShopSystem)
    {
        Connection conn = getConnection();
        if (conn == null) return null;

        String query = "SELECT * FROM Customers WHERE Id = ?";
        try (PreparedStatement preStatement = conn.prepareStatement(query))
        {
            preStatement.setString(1, id);
            try (ResultSet data = preStatement.executeQuery(query))
            {
                String name = data.getString("Name");
                String password = data.getString("Password");
                int rankTypeInt = data.getInt("RankType");

                RankType rankType = this.getRankTypeFromInt(rankTypeInt);
                String rankTypeStr = getRankTypeStr(rankType);
                System.out.println("Query Customer: " + id + " - " + name + " - " + password + " - " + rankTypeStr);

                return new Customer(id, name, password, activeShopSystem, getRankTypeFromInt(rankTypeInt));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Query All
    public List<Customer> queryAllCustomerData(ActiveShopSystem activeShopSystem)
    {
        String query = "SELECT * FROM Customers ";
        Connection conn = getConnection();
        if (conn == null) return null;

        try (Statement statement = conn.createStatement();
             ResultSet data = statement.executeQuery(query);)
        {
            List<Customer> customers = new ArrayList<>();
            while (data.next())
            {
                String id = data.getString("Id");
                String name = data.getString("Name");
                String password = data.getString("Password");
                int rankTypeInt = data.getInt("RankType");

                customers.add(new Customer(id, name, password, activeShopSystem, getRankTypeFromInt(rankTypeInt)));
            }

            System.out.println("Query All Customer");
            return customers;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Update
    public void updateCustomerData(Customer customer)
    {
        Connection conn = getConnection();
        if (conn == null) return;

        String id = customer.getId();
        String name = customer.getName();
        String password = customer.getPassword();
        RankType rankType = customer.getRankType();

        String update = "UPDATE Customers SET"
                + " Name = ?, "
                + "Password = ?, "
                + "RankType = ?, "
                + "WHERE Id = ?";

        try (PreparedStatement preStatement = conn.prepareStatement(update))
        {
            // Update Data
            preStatement.setString(1, name);
            preStatement.setString(2, password);
            preStatement.setInt(3, getIntFromRankType(rankType));

            // Where id is
            preStatement.setString(4, id);
            preStatement.executeUpdate();

            System.out.println("Update Customer with id (" + id + "): " + name + " - " + password + " - " + getIntFromRankType(rankType));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Delete Data
    public void deleteCustomerData(String id)
    {
        String delete = "Customers";
        if (this.deleteData(id, delete))
        {
            System.out.println("Delete Customer success");
            return;
        }

        System.out.println("Delete Customer with id (" + id + ") failed");
    }

    //==========================================Manager===========================================
    // Create Table
    public void createManagerTable()
    {
        String createTable = "CREATE TABLE IF NOT EXISTS Managers"
                + "("
                + "Id TEXT UNIQUE PRIMARY KEY NOT NULL, "
                + "Name TEXT NOT NULL, "
                + "Password TEXT NOT NULL"
                + ");";

        if (this.createTable(createTable))
        {
            System.out.println("Create Managers Table Success");
            return;
        }

        System.out.println("Create Managers Table Failed");
    }

    // Insert Data
    public void insertManagerData(Manager manager)
    {
        Connection conn = getConnection();
        if (conn == null) return;

        String id = manager.getId();
        String name = manager.getName();
        String password = manager.getPassword();

        String insertData = "INSERT INTO Managers (Id, Name, Password) VALUES (?, ?, ?)";
        try (PreparedStatement preStatement = conn.prepareStatement(insertData))
        {
            preStatement.setString(1, id);
            preStatement.setString(2, name);
            preStatement.setString(3, password);
            preStatement.executeUpdate();

            System.out.println("Insert Manager data: " + id + " - " + name + " - " + password);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Query Data
    public Manager queryManagerData(String id, ActiveShopSystem activeShopSystem)
    {
        String query = "SELECT * FROM Managers WHERE Id = ?";
        Connection conn = getConnection();
        if (conn == null) return null;

        try (PreparedStatement preStatement = conn.prepareStatement(query))
        {
            preStatement.setString(1, id);
            try (ResultSet data = preStatement.executeQuery())
            {
                String name = data.getString("Name");
                String password = data.getString("Password");

                System.out.println("Query Manager: " + id + " - " + name + " - " + password);
                return new Manager(id, name, password, activeShopSystem);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Query All Data
    public List<Manager> queryAllManagerData(ActiveShopSystem activeShopSystem)
    {
        String query = "SELECT * FROM Managers";
        Connection conn = getConnection();
        if (conn == null) return null;

        try (Statement statement = conn.createStatement();
             ResultSet data = statement.executeQuery(query);)
        {
            List<Manager> managers = new ArrayList<>();
            while (data.next())
            {
                String id = data.getString("Id");
                String name = data.getString("Name");
                String password = data.getString("Password");

                managers.add(new Manager(id, name, password, activeShopSystem));
            }

            System.out.println("Query All Managers");
            return managers;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Update
    public void updateManagerData(Manager manager)
    {
        Connection conn = getConnection();
        if (conn == null) return;

        String id = manager.getId();
        String name = manager.getName();
        String password = manager.getPassword();

        String update = "UPDATE Managers SET"
                + "Name = ?, "
                + "Password = ? "
                + "WHERE Id = ?";

        try (PreparedStatement preStatement = conn.prepareStatement(update))
        {
            // Update Data
            preStatement.setString(1, name);
            preStatement.setString(2, password);

            // Where id is
            preStatement.setString(3, id);
            preStatement.executeUpdate();

            System.out.println("Update Manager data: " + id + " - " + name + " - " + password);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void deleteManagerData(String id)
    {
        String delete = "Managers";
        if (this.deleteData(id, delete))
        {
            System.out.println("Delete Manager success");
            return;
        }

        System.out.println("Delete Manager with id (" + id + ") failed");
    }

    //======================================CustomerRequest=======================================
    // Create Table
    public void createCustomerRequestTable()
    {
        String create = "CREATE TABLE IF NOT EXISTS CustomerRequests"
                + "("
                + "Id TEXT UNIQUE PRIMARY KEY NOT NULL, "
                + "CustomerId TEXT NOT NULL, "
                + "StaffId TEXT NOT NULL, "
                + "ItemAmounts_Json TEXT NOT NULL"
                + ");";

        if (this.createTable(create))
        {
            System.out.println("Create Customer Requests Table Success");
            return;
        }

        System.out.println("Create Customer Requests Table Failed");
    }

    // Insert
    public void insertCustomerRequestData(CustomerRequest customerRequest)
    {
        Connection conn = getConnection();
        if (conn == null) return;

        Gson gson = new Gson();

        String id = customerRequest.getId();
        String staffId = customerRequest.getHandledStaff().getId();
        String customerId = customerRequest.getRequestedCustomer().getId();
        String json = gson.toJson(customerRequest.getRequestedItemAmounts());

        String insert = "INSERT INTO CustomerRequests "
                + "(Id, CustomerId,StaffId, ItemAmounts_Json) "
                + "VALUES (?, ?, ?, ?)";

        try (PreparedStatement preStatement = conn.prepareStatement(insert))
        {
            preStatement.setString(1, id);
            preStatement.setString(2, staffId);
            preStatement.setString(3, customerId);
            preStatement.setString(4, json);
            preStatement.executeUpdate();

            System.out.println("Insert CustomerRequest Data");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Query
    public CustomerRequest queryCustomerRequestData(String id, ActiveShopSystem activeShopSystem)
    {
        Connection conn = getConnection();
        if (conn == null) return null;

        String query = "SELECT * FROM CustomerRequests WHERE Id = ?";
        try (PreparedStatement preStatement = conn.prepareStatement(query))
        {
            preStatement.setString(1, id);
            try (ResultSet data = preStatement.executeQuery())
            {
                String customerId = data.getString("CustomerId");
                String staffId = data.getString("StaffId");
                String itemAmounts_Json = data.getString("ItemAmounts_Json");

                System.out.println("Query Customer Request Data");
                return getCustomerRequestFromRawData(id, customerId, staffId, itemAmounts_Json, activeShopSystem);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Query All
    public List<CustomerRequest> queryAllCustomerRequestData(ActiveShopSystem activeShopSystem)
    {
        Connection conn = getConnection();
        if (conn == null) return null;

        String query = "SELECT * FROM CustomerRequests";
        try (PreparedStatement preStatement = conn.prepareStatement(query);
            ResultSet data = preStatement.executeQuery())
        {
            List<CustomerRequest> customerRequests = new ArrayList<>();
            while (data.next())
            {
                String id = data.getString("Id");
                String customerId = data.getString("CustomerId");
                String staffId = data.getString("StaffId");
                String itemAmounts_Json = data.getString("ItemAmounts_Json");

                customerRequests.add(this.getCustomerRequestFromRawData(id, customerId, staffId, itemAmounts_Json, activeShopSystem));
            }
            System.out.println("Query All CustomerRequests");
            return customerRequests;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Update
    public void updateCustomerRequestData(CustomerRequest customerRequest)
    {
        Connection conn = getConnection();
        if (conn == null) return;

        Gson gson = new Gson();

        String id = customerRequest.getId();
        String customerId = customerRequest.getRequestedCustomer().getId();
        String staffId = customerRequest.getHandledStaff().getId();
        String itemAmounts_Json = gson.toJson(customerRequest.getRequestedItemAmounts());

        String update = "UPDATE CustomerRequests SET"
                + "CustomerId = ?, "
                + "StaffId = ?, "
                + "ItemAmounts_Json = ? "
                + "WHERE Id = ?";

        try (PreparedStatement preStatement = conn.prepareStatement(update))
        {
            preStatement.setString(1, customerId);
            preStatement.setString(2, staffId);
            preStatement.setString(3, itemAmounts_Json);
            preStatement.setString(4, id);
            preStatement.executeUpdate();

            System.out.println("Update CustomerRequest Data");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Delete
    public void deleteCustomerRequestData(String id)
    {
        String delete = "DELETE FROM CustomerRequests WHERE Id = ?";

        if (this.deleteData(id, delete))
        {
            System.out.println("Delete Customer Request Data Success");
            return;
        }

        System.out.println("Delete Customer Request Data Failed");
    }

    // Other
    private CustomerRequest getCustomerRequestFromRawData(String id, String customerId, String staffId, String itemAmounts_Json, ActiveShopSystem activeShopSystem)
    {
        Gson gson = new Gson();

        Staff staff = this.queryStaffData(staffId, activeShopSystem);
        Customer customer = this.queryCustomerData(customerId, activeShopSystem);

        List<String> itemAmountIds = gson.fromJson(itemAmounts_Json, List.class);
        List<ItemAmount> itemAmounts = new ArrayList<>();
        for (String itemAmountId : itemAmountIds)
        {
            itemAmounts.add(this.queryItemAmountData(itemAmountId));
        }

        return new CustomerRequest(id, customer, staff, itemAmounts);
    }
    //============================================Item============================================
    // Create Table
    public void createItemTable()
    {
        String create = "CREATE TABLE IF NOT EXISTS Items"
                + "("
                + "Id TEXT UNIQUE PRIMARY KEY NOT NULL, "
                + "Name TEXT NOT NULL, "
                + "Price FLOAT NOT NULL, "
                + "ItemType INTEGER NOT NULL, "
                + "InitAmount INTEGER NOT NULL, "
                + "ItemAmounts_Json TEXT NOT NULL, "
                + "Description TEXT NOT NULL"
                + ");";

        if (this.createTable(create))
        {
            System.out.println("Create Items Table Success");
            return;
        }

        System.out.println("Create Items Table Failed");
    }

    // Insert
    public void insertItemData(Item item)
    {
        Connection conn = getConnection();
        if (conn == null) return;

        String id = item.getId();
        String name = item.getName();
        float price = item.getPrice();
        int itemTypeInt = getIntFromItemType(item.getItemType());
        int initAmount = item.getInitAmount();

        Gson gson = new Gson();
        String ItemAmounts_Json = gson.toJson(item.getItemAmounts());

        String description = item.getDescription();

        String insert = "INSERT INTO Items (Id, Name, Price, ItemType, ItemAmounts_Json, Description) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preStatement = conn.prepareStatement(insert))
        {
            preStatement.setString(1, id);
            preStatement.setString(2, name);
            preStatement.setFloat(3, price);
            preStatement.setInt(4, itemTypeInt);
            preStatement.setInt(5, initAmount);
            preStatement.setString(6, ItemAmounts_Json);
            preStatement.setString(7, description);
            preStatement.executeUpdate();

            System.out.println("Insert Item Data");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Query
    public Item queryItemData(String id)
    {
        Connection conn = getConnection();
        if (conn == null) return null;

        String query = "SELECT * FROM Items WHERE Id = ?";
        try (PreparedStatement preStatement = conn.prepareStatement(query))
        {
            Gson gson = new Gson();
            preStatement.setString(1, id);
            try (ResultSet data = preStatement.executeQuery())
            {
                String name = data.getString("Name");
                float price = data.getFloat("Price");
                int itemTypeInt = data.getInt("ItemType");
                int initAmount = data.getInt("ItemAmounts");
                String ItemAmounts_Json = data.getString("ItemAmounts_Json");
                String description = data.getString("Description");
                ItemType itemType = getItemTypeFromInt(itemTypeInt);
                List<ItemAmount> itemAmounts = gson.fromJson(ItemAmounts_Json, List.class);

                System.out.println("Query Item Data");
                return new Item(id, name, price, itemType, initAmount, itemAmounts, description);

            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Query All
    public List<Item> queryAllItemData()
    {
        Connection conn = getConnection();
        if (conn == null) return null;

        String query = "SELECT * FROM Items";
        Gson gson = new Gson();

        try (PreparedStatement preStatement = conn.prepareStatement(query);
            ResultSet data = preStatement.executeQuery())
        {
            List<Item> items = new ArrayList<>();
            while (data.next())
            {
                String id = data.getString("Id");
                String name = data.getString("Name");
                float price = data.getFloat("Price");
                int itemTypeInt = data.getInt("ItemType");
                int initAmount = data.getInt("ItemAmounts");
                String ItemAmounts_Json = data.getString("ItemAmounts_Json");
                String description = data.getString("Description");

                ItemType itemType = getItemTypeFromInt(itemTypeInt);
                List<ItemAmount> itemAmounts = gson.fromJson(ItemAmounts_Json, List.class);

                Item newItem = new Item(id, name, price, itemType, initAmount, itemAmounts, description);
                items.add(newItem);
            }

            System.out.println("Query All Items");
            return items;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Update
    public void updateItemData(Item item)
    {
        Connection conn = getConnection();
        if (conn == null) return;

        Gson gson = new Gson();

        String id = item.getId();
        String name = item.getName();
        float price = item.getPrice();
        int itemTypeInt = getIntFromItemType(item.getItemType());
        int initAmount = item.getInitAmount();
        String ItemAmounts_Json = gson.toJson(item.getItemAmounts());
        String description = item.getDescription();

        String update = "UPDATE Items SET "
                + "Name = ?, "
                + "Price = ?, "
                + "ItemType = ?, "
                + "ItemAmounts_Json = ?, "
                + "Description = ? "
                + "WHERE Id = ?";

        try (PreparedStatement preStatement = conn.prepareStatement(update))
        {
            preStatement.setString(1, name);
            preStatement.setFloat(2, price);
            preStatement.setInt(3, itemTypeInt);
            preStatement.setInt(4, initAmount);
            preStatement.setString(5, ItemAmounts_Json);
            preStatement.setString(6, description);
            preStatement.setString(7, id);
            preStatement.executeUpdate();

            System.out.println("Update Item Data");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // delete
    public void deleteItemData(String id)
    {
        String delete = "Items";
        if (this.deleteData(id, delete))
        {
            System.out.println("Delete Item success");
            return;
        }

        System.out.println("Delete Item with id (" + id + ") failed");
    }

    //=========================================ItemAmount=========================================
    // Create Table
    public void createItemAmountTable()
    {
        String create = "CREATE TABLE ItemAmounts"
                + "("
                + "Id TEXT UNIQUE PRIMARY KEY NOT NULL, "
                + "ItemId TEXT NOT NULL, "
                + "Amount INTEGER NOT NULL, "
                + "IsSold BOOLEAN NOT NULL"
                + ");";

        if (this.createTable(create))
        {
            System.out.println("Create ItemAmounts Table Success");
            return;
        }

        System.out.println("Create ItemAmounts Table Failed");
    }

    // Insert
    public void insertItemAmountData(ItemAmount itemAmount)
    {
        Connection conn = getConnection();
        if (conn == null) return;

        String insert = "INSERT INTO ItemAmounts (Id, ItemId, Amount, IsSold) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preStatement = conn.prepareStatement(insert))
        {
            preStatement.setString(1, itemAmount.getId());
            preStatement.setString(2, itemAmount.getItem().getId());
            preStatement.setInt(3, itemAmount.getAmount());
            preStatement.setBoolean(4, itemAmount.getIsSold());
            preStatement.executeUpdate();

            System.out.println("Insert Item Amounts Data");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Query
    public ItemAmount queryItemAmountData(String id)
    {
        Connection conn = getConnection();
        if (conn == null) return null;

        String query = "SELECT * FROM ItemAmounts WHERE Id = ?";
        try (PreparedStatement preStatement = conn.prepareStatement(query))
        {
            preStatement.setString(1, id);
            try (ResultSet data = preStatement.executeQuery())
            {
                String itemId = data.getString("ItemId");
                int amount = data.getInt("Amount");
                boolean isSold = data.getBoolean("IsSold");

                Item item = this.queryItemData(itemId);

                System.out.println("Query Item Amount Data");
                return new ItemAmount(id, item, amount, isSold);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<ItemAmount> queryAllItemAmountData()
    {
        Connection conn = getConnection();
        if (conn == null) return null;

        String query = "SELECT * FROM ItemAmounts";

        try(PreparedStatement preStatement = conn.prepareStatement(query);
            ResultSet data = preStatement.executeQuery())
        {
            List<ItemAmount> itemAmounts = new ArrayList<>();
            while (data.next())
            {
                String id = data.getString("Id");
                String itemId = data.getString("ItemId");
                int amount = data.getInt("Amount");
                boolean isSold = data.getBoolean("IsSold");

                Item item = this.queryItemData(itemId);
                itemAmounts.add(new ItemAmount(id, item, amount, isSold));
            }

            System.out.println("Query All ItemAmounts");
            return itemAmounts;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Update
    public void updateItemAmountData(ItemAmount itemAmount)
    {
        Connection conn = getConnection();
        if (conn == null) return;

        String update = "UPDATE ItemAmounts SET "
                + "ItemId = ?, "
                + "Amount = ?, "
                + "IsSold = ? "
                + "WHERE Id = ?";

        try (PreparedStatement preStatement = conn.prepareStatement(update))
        {
            preStatement.setString(1, itemAmount.getItem().getId());
            preStatement.setInt(2, itemAmount.getAmount());
            preStatement.setBoolean(3, itemAmount.getIsSold());
            preStatement.setString(4, itemAmount.getId());
            preStatement.executeUpdate();

            System.out.println("Update Item Amounts Data");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Delete
    public void deleteItemAmountData(String id)
    {
        String delete = "DELETE FROM ItemAmounts WHERE Id = ?";

        if (this.deleteData(id, delete))
        {
            System.out.println("Delete ItemAmounts Success");
            return;
        }

        System.out.println("Delete ItemAmounts with id (" + id + ") failed");
    }

    //=========================================ShopSystem=========================================
    // Create Table
    public void createShopTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS Shops "
                + "("
                + "Id TEXT UNIQUE PRIMARY KEY NOT NULL, "
                + "Name TEXT NOT NULL, "
                + "Password TEXT NOT NULL, "
                + "SystemCode TEXT NOT NULL"
                + ")";

        if (this.createTable(createTable))
        {
            System.out.println("Create Shops Table Success");
            return;
        }

        System.out.println("Create Shops Table Failed");
    }

    // Insert Data
    public void insertShopData(ShopSystem shop)
    {
        Connection conn = getConnection();
        if (conn == null) return;

        String id = shop.getId();
        String name = shop.getName();
        String password = shop.getPassword();
        String systemCode = shop.getSystemCode();

        String insertData = "INSERT INTO Shops (Id, Name, Password, SystemCode) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preStatement = conn.prepareStatement(insertData))
        {
            preStatement.setString(1, id);
            preStatement.setString(2, name);
            preStatement.setString(3, password);
            preStatement.setString(4, systemCode);
            preStatement.executeUpdate();

            System.out.println("Insert Shop data: " + id + " - " + name + " - " + password + " - " + systemCode);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Query Data
    public ShopSystem queryShopData(String id)
    {
        Connection conn = getConnection();
        if (conn == null) return null;

        String query = "SELECT * FROM Shops WHERE Id = ?";
        try (PreparedStatement preStatement = conn.prepareStatement(query))
        {
            preStatement.setString(1, id);
            try (ResultSet data = preStatement.executeQuery())
            {
                String name = data.getString("Name");
                String password = data.getString("Password");
                String systemCode = data.getString("SystemCode");

                System.out.println("Query Shop data: " + id + " - " + name + " - " + password + " - " + systemCode);
                return new ShopSystem(id, name, password, systemCode);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Query All Data
    public List<ShopSystem> queryAllShopData()
    {
        String query = "SELECT * FROM Shops";
        Connection conn = getConnection();
        if (conn == null) return null;

        try (Statement statement = conn.createStatement();
             ResultSet data = statement.executeQuery(query);)
        {
            List<ShopSystem> shopSystems = new ArrayList<>();
            while (data.next())
            {
                String id = data.getString("Id");
                String name = data.getString("Name");
                String password = data.getString("Password");
                String systemCode = data.getString("SystemCode");

                shopSystems.add(new ShopSystem(id, name, password, systemCode));
            }

            System.out.println("Query All Shops");
            return shopSystems;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Update Data
    public void updateShopData(ShopSystem shop)
    {
        Connection conn = getConnection();
        if (conn == null) return;

        String id = shop.getId();
        String name = shop.getName();
        String password = shop.getPassword();
        String systemCode = shop.getSystemCode();

        String update = "UPDATE Shops SET "
                + "Name = ?, "
                + "Password = ?, "
                + "SystemCode = ? "
                + "WHERE Id = ?";
        try (PreparedStatement preStatement = conn.prepareStatement(update))
        {
            preStatement.setString(1, name);
            preStatement.setString(2, password);
            preStatement.setString(3, systemCode);
            preStatement.setString(4, id);
            preStatement.executeUpdate();

            System.out.println("Update Shop data: " + id + " - " + name + " - " + password + " - " + systemCode);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Delete
    public void deleteShopData(String id)
    {
        String delete = "Shops";
        if (this.deleteData(id, delete))
        {
            System.out.println("Delete Shop success");
            return;
        }

        System.out.println("Delete Shop with id (" + id + ") failed");
    }

    //======================================ActiveShopSystem======================================
    // Create Table
    public void createActiveShopTable()
    {
        String create = "CREATE TABLE IF NOT EXISTS ActiveShops"
                + "("
                + "Id TEXT UNIQUE PRIMARY KEY NOT NULL, "
                + "ShopId TEXT NOT NULL, "
                + "Json TEXT NOT NULL"
                + ");";

        if (this.createTable(create))
        {
            System.out.println("Create ActiveShops Table Success");
            return;
        }

        System.out.println("Create ActiveShops Table Failed");
    }

    // Insert
    public void insertActiveShopData(ActiveShopSystem activeShop)
    {
        Connection conn = getConnection();
        if (conn == null) return;

        ActiveShopSystemRawData rawData = this.getRawDataFromActiveShop(activeShop);
        String insert = "INSERT INTO ActiveShops (Id, ShopId, Json) VALUES (?, ?, ?)";
        try (PreparedStatement preStatement = conn.prepareStatement(insert))
        {
            preStatement.setString(1, rawData.id);
            preStatement.setString(2, rawData.shopId);
            preStatement.setString(3, rawData.json);
            preStatement.executeUpdate();

            System.out.println("Insert ActiveShop Data");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Query
    public ActiveShopSystem queryActiveShopData(String id)
    {
        Connection conn = getConnection();
        if (conn == null) return null;

        String query = "SELECT * FROM ActiveShops WHERE Id = ?";
        try (PreparedStatement preStatement = conn.prepareStatement(query))
        {
            preStatement.setString(1, id);
            try (ResultSet data = preStatement.executeQuery())
            {
                String shopId = data.getString("ShopId");
                String json = data.getString("Json");

                System.out.println("Query ActiveShop data");
                return this.getActiveShopFromRawData(id, shopId, json);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Query All
    public List<ActiveShopSystem> queryAllActiveShopData()
    {
        Connection conn = getConnection();
        if (conn == null) return null;

        String query = "SELECT * FROM ActiveShops";
        try (PreparedStatement preStatement = conn.prepareStatement(query);
            ResultSet data = preStatement.executeQuery())
        {
            List<ActiveShopSystem> activeShops = new ArrayList<>();
            while (data.next())
            {
                String id = data.getString("Id");
                String shopId = data.getString("ShopId");
                String json = data.getString("Json");

                activeShops.add(this.getActiveShopFromRawData(id, shopId, json));
            }

            System.out.println("Query All ActiveShops");
            return activeShops;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Update
    public void updateActiveShopData(ActiveShopSystem activeShop)
    {
        Connection conn = getConnection();
        if (conn == null) return;

        ActiveShopSystemRawData rawData = getRawDataFromActiveShop(activeShop);

        String update = "UPDATE ActiveShops SET ShopId = ?, Json = ? WHERE Id = ?";
        try (PreparedStatement preStatement = conn.prepareStatement(update))
        {
            preStatement.setString(1, rawData.shopId);
            preStatement.setString(2, rawData.json);
            preStatement.setString(3, rawData.id);
            preStatement.executeUpdate();

            System.out.println("Update ActiveShop Data");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Delete
    public void deleteActiveShopData(String id)
    {
        String delete = "ActiveShops";
        if (this.deleteData(id, delete))
        {
            System.out.println("Delete ActiveShop success");
            return;
        }

        System.out.println("Delete ActiveShop with id (" + id + ") failed");
    }

    // Other
    private ActiveShopSystemRawData getRawDataFromActiveShop(ActiveShopSystem activeShop)
    {
        String id = activeShop.getId();
        String shopId = activeShop.getShopSystem().getId();
        HashMap<String, List<String>> dataMap = new HashMap<>();
        dataMap.put("activeManagerIds", activeShop.getActiveMangerIds());
        dataMap.put("activeStaffIds", activeShop.getActiveStaffIds());
        dataMap.put("activeCustomerIds", activeShop.getActiveCustomerIds());
        dataMap.put("customerRequestIds", activeShop.getCustomerRequestIds());

        Gson gson = new Gson();
        String json = gson.toJson(dataMap);

        return new ActiveShopSystemRawData(id, shopId, json);
    }

    private ActiveShopSystem getActiveShopFromRawData(String id, String shopId, String json)
    {
        Gson gson = new Gson();
        ActiveShopSystem activeShop = new ActiveShopSystem();

        ShopSystem shop = this.queryShopData(shopId);

        HashMap<String, List<String>> dataMap = gson.fromJson(json, HashMap.class);
        List<String> activeManagerIds = dataMap.get("activeManagerIds");
        List<String> activeStaffIds = dataMap.get("activeStaffIds");
        List<String> activeCustomerIds = dataMap.get("activeCustomerIds");
        List<String> customerRequestIds = dataMap.get("customerRequestIds");

        List<Manager> activeManagers = new ArrayList<>();
        List<Staff> activeStaffs = new ArrayList<>();
        List<Customer> activeCustomers = new ArrayList<>();
        List<CustomerRequest> customerRequests = new ArrayList<>();

        for (String activeManagerId : activeManagerIds)
        {
            activeManagers.add(this.queryManagerData(activeManagerId, activeShop));
        }

        for (String activeStaffId : activeStaffIds)
        {
            activeStaffs.add(this.queryStaffData(activeStaffId, activeShop));
        }

        for (String activeCustomerId : activeCustomerIds)
        {
            activeCustomers.add(this.queryCustomerData(activeCustomerId, activeShop));
        }

        for (String customerRequestId : customerRequestIds)
        {
            customerRequests.add(this.queryCustomerRequestData(customerRequestId, activeShop));
        }

        ActiveShopSystem activeShopSystem = new ActiveShopSystem(id, shop, activeManagers, activeStaffs, activeCustomers, customerRequests, this);
        return activeShop;
    }

    //========================================Nested Class========================================
    public class ActiveShopSystemRawData
    {
        private String id;
        private String shopId;
        private String json;

        public ActiveShopSystemRawData(String id, String shopId, String json)
        {
            this.id = id;
            this.shopId = shopId;
            this.json = json;
        }
    }
}
