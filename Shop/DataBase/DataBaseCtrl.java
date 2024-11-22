package DataBase;

import HuySystem.HuyUtil;
import Obj.*;
import Obj.Main.User;
import Obj.Main.Item;
import Obj.Main.Shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Obj.Side.ActiveShop;
import Obj.Side.CustomerRequest;
import Obj.Side.OrderedItem;
import com.google.gson.Gson;

public class DataBaseCtrl extends AbstractKey
{

    public DataBaseCtrl()
    {
        this.createActiveShopTable();
        this.createShopTable();
        this.createUserTable();
        this.createItemTable();
        this.createCustomerRequestTable();
        this.createOrderedItemTable();
    }

    //===========================================Other============================================
    // Connection
    private Connection getConnection()
    {
        String dataBaseUrl = "jdbc:sqlite:./DataBase/ShopDataBase.db";
        try
        {
            Connection conn = DriverManager.getConnection(dataBaseUrl);
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

    //============================================User============================================
    // Create Table
    public void createUserTable()
    {
        String create = "CREATE TABLE IF NOT EXISTS Users"
                + "("
                + "Id TEXT UNIQUE PRIMARY KEY NOT NULL, "
                + "ShopId TEXT NOT NULL, "
                + "Name TEXT NOT NULL, "
                + "Password TEXT NOT NULL, "
                + "UserType INTEGER NOT NULL"
                + ");";

        if (this.createTable(create))
        {
            System.out.println("Create User Table Success");
            return;
        }

        System.out.println("Create User Table Failed");
    }

    // Insert
    public void insertUserData(User user)
    {
        Connection conn = this.getConnection();
        if (conn == null) return;

        String insert = "INSERT INTO Users (Id, ShopId, Name, Password, UserType) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preStatement = conn.prepareStatement(insert))
        {
            String privateKey = this.getKey();
            preStatement.setString(1, user.getId(privateKey));
            preStatement.setString(2, user.getShop().getId(privateKey));
            preStatement.setString(3, user.getName(privateKey));
            preStatement.setString(4, user.getPassword(privateKey));
            preStatement.setInt(5, this.getIntFromUserType(user.getUserType(privateKey)));
            preStatement.executeUpdate();

            System.out.println("Insert User Data");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Query
    public User queryUserData(String id)
    {
        Connection conn = this.getConnection();
        if (conn == null) return null;

        String query = "SELECT * FROM Users WHERE Id = ?";
        try (PreparedStatement preStatement = conn.prepareStatement(query))
        {
            preStatement.setString(1, id);
            try (ResultSet data = preStatement.executeQuery())
            {
                String name = data.getString("Name");
                String shopId = data.getString("ShopId");
                String password = data.getString("Password");
                int userTypeInt = data.getInt("UserType");

                Shop shop = this.queryShopData(shopId);
                UserType userType = this.getUserTypeFromInt(userTypeInt);
                return new User(id, name, password, shop, userType);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Query All
    public List<User> queryAllUserData()
    {
        Connection conn = this.getConnection();
        if (conn == null) return null;

        String query = "SELECT * FROM Users";
        try (PreparedStatement preStatement = conn.prepareStatement(query);
            ResultSet data = preStatement.executeQuery())
        {
            List<User> users = new ArrayList<>();
            while (data.next())
            {
                String id = data.getString("Id");
                String shopId = data.getString("ShopId");
                String name = data.getString("Name");
                String password = data.getString("Password");
                int userTypeInt = data.getInt("UserType");

                Shop shop = this.queryShopData(shopId);
                UserType userType = this.getUserTypeFromInt(userTypeInt);
                users.add(new User(id, name, password, shop, userType));
            }

            System.out.println("Query All Users Data");
            return users;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Update
    public void updateUserData(User user)
    {
        Connection conn = this.getConnection();
        if (conn == null) return;

        String update = "UPDATE Users SET Name = ?, Password = ? WHERE Id = ?";
        try (PreparedStatement preStatement = conn.prepareStatement(update))
        {
            preStatement.setString(1, user.getName());
            preStatement.setString(2, user.getPassword());
            preStatement.setString(3, user.getId());
            preStatement.executeUpdate();

            System.out.println("Update User Data");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Delete
    public void deleteUserData(String id)
    {
        String delete = "DELETE FROM Users WHERE Id = ?";
        if (this.deleteData(id, delete))
        {
            System.out.println("Delete User Data Success");
            return;
        }

        System.out.println("Delete User Data Failed");
    }

    //======================================CustomerRequest=======================================
    // Create Table
    public void createCustomerRequestTable()
    {
        String create = "CREATE TABLE IF NOT EXISTS CustomerRequests"
                + "("
                + "Id TEXT UNIQUE PRIMARY KEY NOT NULL, "
                + "ShopId TEXT NOT NULL, "
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
        String privateKey = this.getKey();

        String id = customerRequest.getId(privateKey);
        String shopId = customerRequest.getShop().getId(privateKey);
        String staffId = customerRequest.getHandledStaff().getId(privateKey);
        String customerId = customerRequest.getRequestedCustomer().getId(privateKey);
        String json = gson.toJson(customerRequest.getRequestedItemAmounts(privateKey));

        String insert = "INSERT INTO CustomerRequests "
                + "(Id, ShopId, CustomerId, StaffId, ItemAmounts_Json) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preStatement = conn.prepareStatement(insert))
        {
            preStatement.setString(1, id);
            preStatement.setString(2, shopId);
            preStatement.setString(3, staffId);
            preStatement.setString(4, customerId);
            preStatement.setString(5, json);
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
    public CustomerRequest queryCustomerRequestData(String id)
    {
        Connection conn = getConnection();
        if (conn == null) return null;

        String query = "SELECT * FROM CustomerRequests WHERE Id = ?";
        try (PreparedStatement preStatement = conn.prepareStatement(query))
        {
            preStatement.setString(1, id);
            try (ResultSet data = preStatement.executeQuery())
            {
                String shopId = data.getString("ShopId");
                String customerId = data.getString("CustomerId");
                String staffId = data.getString("StaffId");
                String itemAmounts_Json = data.getString("ItemAmounts_Json");

                System.out.println("Query Customer Request Data");
                return getCustomerRequestFromRawData(id, shopId, customerId, staffId, itemAmounts_Json);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Query All
    public List<CustomerRequest> queryAllCustomerRequestData()
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
                String shopId = data.getString("ShopId");
                String customerId = data.getString("CustomerId");
                String staffId = data.getString("StaffId");
                String itemAmounts_Json = data.getString("ItemAmounts_Json");

                customerRequests.add(this.getCustomerRequestFromRawData(id, shopId, customerId, staffId, itemAmounts_Json));
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
        String shopId = customerRequest.getShop().getId();
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
    private CustomerRequest getCustomerRequestFromRawData(String id, String shopId, String customerId, String staffId, String itemAmounts_Json)
    {
        Gson gson = new Gson();

        Shop shop = this.queryShopData(shopId);
        User staff = this.queryUserData(staffId);
        User customer = this.queryUserData(customerId);

        List<String> itemAmountIds = gson.fromJson(itemAmounts_Json, List.class);
        List<OrderedItem> orderedItems = new ArrayList<>();
        for (String itemAmountId : itemAmountIds)
        {
            orderedItems.add(this.queryOrderedItemData(itemAmountId));
        }

        return new CustomerRequest(id, shop, customer, staff, orderedItems);
    }
    //============================================Item============================================
    // Create Table
    public void createItemTable()
    {
        String create = "CREATE TABLE IF NOT EXISTS Items"
                + "("
                + "Id TEXT UNIQUE PRIMARY KEY NOT NULL, "
                + "ShopId TEXT NOT NULL, "
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

        String privateKey = this.getKey();

        String id = item.getId(privateKey);
        String shopId = item.getShop().getId(privateKey);
        String name = item.getName(privateKey);
        float price = item.getPrice(privateKey);
        int itemTypeInt = getIntFromItemType(item.getItemType(privateKey));
        int initAmount = item.getInitAmount(privateKey);

        Gson gson = new Gson();
        String ItemAmounts_Json = gson.toJson(item.getItemAmounts());

        String description = item.getDescription();

        String insert = "INSERT INTO Items "
                + "(Id, ShopId, Name, Price, ItemType, InitAmount, ItemAmounts_Json, Description) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preStatement = conn.prepareStatement(insert))
        {
            preStatement.setString(1, id);
            preStatement.setString(2, shopId);
            preStatement.setString(3, name);
            preStatement.setFloat(4, price);
            preStatement.setInt(5, itemTypeInt);
            preStatement.setInt(6, initAmount);
            preStatement.setString(7, ItemAmounts_Json);
            preStatement.setString(8, description);
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
                String shopId = data.getString("ShopId");
                String name = data.getString("Name");
                float price = data.getFloat("Price");
                int itemTypeInt = data.getInt("ItemType");
                int initAmount = data.getInt("InitAmount");
                String ItemAmounts_Json = data.getString("ItemAmounts_Json");
                String description = data.getString("Description");

                Shop shop = this.queryShopData(shopId);
                ItemType itemType = getItemTypeFromInt(itemTypeInt);
                List<OrderedItem> orderedItems = gson.fromJson(ItemAmounts_Json, List.class);

                System.out.println("Query Item Data");
                return new Item(id, name, shop, price, itemType, initAmount, orderedItems, description);

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
                String shopId = data.getString("ShopId");
                String name = data.getString("Name");
                float price = data.getFloat("Price");
                int itemTypeInt = data.getInt("ItemType");
                int initAmount = data.getInt("InitAmount");
                String ItemAmounts_Json = data.getString("ItemAmounts_Json");
                String description = data.getString("Description");

                Shop shop = this.queryShopData(shopId);
                ItemType itemType = getItemTypeFromInt(itemTypeInt);
                List<OrderedItem> orderedItems = gson.fromJson(ItemAmounts_Json, List.class);

                Item newItem = new Item(id, name, shop, price, itemType, initAmount, orderedItems, description);
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
        String privateKey = this.getKey();

        String id = item.getId(privateKey);
        String name = item.getName(privateKey);
        float price = item.getPrice(privateKey);
        int itemTypeInt = getIntFromItemType(item.getItemType(privateKey));
        int initAmount = item.getInitAmount(privateKey);
        String ItemAmounts_Json = gson.toJson(item.getItemAmounts(privateKey));
        String description = item.getDescription(privateKey);

        String update = "UPDATE Items SET "
                + "Name = ?, "
                + "Price = ?, "
                + "ItemType = ?, "
                + "InitAmount = ?"
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

    //=========================================OrderedItem=========================================
    // Create Table
    public void createOrderedItemTable()
    {
        String create = "CREATE TABLE IF NOT EXISTS OrderedItems"
                + "("
                + "Id TEXT UNIQUE PRIMARY KEY NOT NULL, "
                + "ShopId TEXT NOT NULL, "
                + "ItemId TEXT NOT NULL, "
                + "Amount INTEGER NOT NULL, "
                + "IsSold BOOLEAN NOT NULL"
                + ");";

        if (this.createTable(create))
        {
            System.out.println("Create OrderedItems Table Success");
            return;
        }

        System.out.println("Create OrderedItems Table Failed");
    }

    // Insert
    public void insertOrderedItemData(OrderedItem orderedItem)
    {
        Connection conn = getConnection();
        if (conn == null) return;

        String insert = "INSERT INTO OrderedItems "
                + "(Id, ShopId, ItemId, Amount, IsSold) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preStatement = conn.prepareStatement(insert))
        {
            String privateKey = this.getKey();

            preStatement.setString(1, orderedItem.getId(privateKey));
            preStatement.setString(2, orderedItem.getShop().getId(privateKey));
            preStatement.setString(3, orderedItem.getItem().getId(privateKey));
            preStatement.setInt(4, orderedItem.getAmount(privateKey));
            preStatement.setBoolean(5, orderedItem.getIsSold(privateKey));
            preStatement.executeUpdate();

            System.out.println("Insert OrderedItems Data");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Query
    public OrderedItem queryOrderedItemData(String id)
    {
        Connection conn = getConnection();
        if (conn == null) return null;

        String query = "SELECT * FROM OrderedItems WHERE Id = ?";
        try (PreparedStatement preStatement = conn.prepareStatement(query))
        {
            preStatement.setString(1, id);
            try (ResultSet data = preStatement.executeQuery())
            {
                String shopId = data.getString("ShopId");
                String itemId = data.getString("ItemId");
                int amount = data.getInt("Amount");
                boolean isSold = data.getBoolean("IsSold");

                Shop shop = this.queryShopData(shopId);
                Item item = this.queryItemData(itemId);

                System.out.println("Query OrderedItems Data");
                return new OrderedItem(id, shop, item, amount, isSold);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<OrderedItem> queryAllOrderedItemData()
    {
        Connection conn = getConnection();
        if (conn == null) return null;

        String query = "SELECT * FROM OrderedItems";

        try(PreparedStatement preStatement = conn.prepareStatement(query);
            ResultSet data = preStatement.executeQuery())
        {
            List<OrderedItem> orderedItems = new ArrayList<>();
            while (data.next())
            {
                String id = data.getString("Id");
                String shopId = data.getString("ShopId");
                String itemId = data.getString("ItemId");
                int amount = data.getInt("Amount");
                boolean isSold = data.getBoolean("IsSold");

                Shop shop = this.queryShopData(shopId);
                Item item = this.queryItemData(itemId);
                orderedItems.add(new OrderedItem(id, shop, item, amount, isSold));
            }

            System.out.println("Query All OrderedItems");
            return orderedItems;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Update
    public void updateOrderedItemData(OrderedItem orderedItem)
    {
        Connection conn = getConnection();
        if (conn == null) return;

        String update = "UPDATE OrderedItems SET "
                + "ItemId = ?, "
                + "Amount = ?, "
                + "IsSold = ? "
                + "WHERE Id = ?";

        try (PreparedStatement preStatement = conn.prepareStatement(update))
        {
            preStatement.setString(1, orderedItem.getItem().getId());
            preStatement.setInt(2, orderedItem.getAmount());
            preStatement.setBoolean(3, orderedItem.getIsSold());
            preStatement.setString(4, orderedItem.getId());
            preStatement.executeUpdate();

            System.out.println("Update OrderedItems Data");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Delete
    public void deleteOrderedItemData(String id)
    {
        String delete = "DELETE FROM OrderedItems WHERE Id = ?";

        if (this.deleteData(id, delete))
        {
            System.out.println("Delete OrderedItems Success");
            return;
        }

        System.out.println("Delete OrderedItems with id (" + id + ") failed");
    }

    //============================================Shop============================================
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
    public void insertShopData(Shop shop)
    {
        Connection conn = getConnection();
        if (conn == null) return;

        String privateKey = this.getKey();

        String id = shop.getId(privateKey);
        String name = shop.getName(privateKey);
        String password = shop.getPassword(privateKey);
        String systemCode = shop.getSystemCode(privateKey);

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
    public Shop queryShopData(String id)
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
                return new Shop(id, name, password, systemCode, this);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Query All Data
    public List<Shop> queryAllShopData()
    {
        String query = "SELECT * FROM Shops";
        Connection conn = getConnection();
        if (conn == null) return null;

        try (Statement statement = conn.createStatement();
             ResultSet data = statement.executeQuery(query);)
        {
            List<Shop> shops = new ArrayList<>();
            while (data.next())
            {
                String id = data.getString("Id");
                String name = data.getString("Name");
                String password = data.getString("Password");
                String systemCode = data.getString("SystemCode");

                shops.add(new Shop(id, name, password, systemCode, this));
            }

            System.out.println("Query All Shops");
            return shops;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Update Data
    public void updateShopData(Shop shop)
    {
        Connection conn = getConnection();
        if (conn == null) return;

        String privateKey = this.getKey();

        String id = shop.getId(privateKey);
        String name = shop.getName(privateKey);
        String password = shop.getPassword(privateKey);
        String systemCode = shop.getSystemCode(privateKey);

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

    //=========================================ActiveShop=========================================
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
    public void insertActiveShopData(ActiveShop activeShop)
    {
        Connection conn = getConnection();
        if (conn == null) return;

        ActiveShopRawData rawData = this.getRawDataFromActiveShop(activeShop);
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
    public ActiveShop queryActiveShopData(String id)
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
    public List<ActiveShop> queryAllActiveShopData()
    {
        Connection conn = getConnection();
        if (conn == null) return null;

        String query = "SELECT * FROM ActiveShops";
        try (PreparedStatement preStatement = conn.prepareStatement(query);
            ResultSet data = preStatement.executeQuery())
        {
            List<ActiveShop> activeShops = new ArrayList<>();
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
    public void updateActiveShopData(ActiveShop activeShop)
    {
        Connection conn = getConnection();
        if (conn == null) return;

        ActiveShopRawData rawData = getRawDataFromActiveShop(activeShop);

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
    private ActiveShopRawData getRawDataFromActiveShop(ActiveShop activeShop)
    {
        String privateKey = this.getKey();

        String id = activeShop.getId(privateKey);
        String shopId = activeShop.getShop().getId(privateKey);

        List<String> customerRequestIds = activeShop.getCustomerRequestIds(privateKey);
        List<String> activeUserIds = activeShop.getActiveUserIds(privateKey);

        HashMap<String, List<String>> dataMap = new HashMap<>();
        dataMap.put("CustomerRequestIds", customerRequestIds);
        dataMap.put("ActiveUserIds", activeUserIds);

        Gson gson = new Gson();
        String json = gson.toJson(dataMap);

        return new ActiveShopRawData(id, shopId, json);
    }

    private ActiveShop getActiveShopFromRawData(String id, String shopId, String json)
    {
        Gson gson = new Gson();
        ActiveShop activeShop = new ActiveShop();

        Shop shop = this.queryShopData(shopId);
        HashMap<String, List<String>> dataMap = gson.fromJson(json, HashMap.class);
        List<String> customerRequestIds = dataMap.get("CustomerRequestIds");
        List<String> activeUserIds = dataMap.get("ActiveUserIds");

        List<CustomerRequest> customerRequests = new ArrayList<>();
        for (String customerRequestId : customerRequestIds)
        {
            customerRequests.add(this.queryCustomerRequestData(customerRequestId));
        }

        List<User> activeUsers = new ArrayList<>();
        for (String userid : activeUserIds)
        {
            activeUsers.add(this.queryUserData(userid));
        }

        activeShop = new ActiveShop(id, shop, customerRequests, activeUsers);
        return activeShop;
    }

    //========================================Nested Class========================================
    public class ActiveShopRawData
    {
        private String id;
        private String shopId;
        private String json;

        public ActiveShopRawData(String id, String shopId, String json)
        {
            this.id = id;
            this.shopId = shopId;
            this.json = json;
        }
    }
}
