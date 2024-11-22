package Obj.Main;

import Obj.AbstractKey;
import Obj.IMainObj;
import Obj.IShopChild;
import Obj.ItemType;
import Obj.Side.OrderedItem;

import java.util.ArrayList;
import java.util.List;

public class Item extends AbstractKey implements IMainObj, IShopChild
{
    //==========================================Variable==========================================
    private String id;
    private String name;
    private Shop shop;
    private float price;
    private ItemType itemType;
    private int initAmount;
    private List<OrderedItem> orderedItems;
    private String description;



    //========================================Constructor=========================================
    public Item()
    {
        this.id = "";
        this.name = "";
        this.shop = null;
        this.price = -1;
        this.itemType = null;
        this.initAmount = -1;
        this.orderedItems = new ArrayList<>();
        this.description = "";

    }

    public Item(String id, String name, Shop shop, float price, ItemType itemType,
                int unSoldAmount, List<OrderedItem> orderedItems, String description)
    {
        this.id = id;
        this.name = name;
        this.shop = shop;
        this.price = price;
        this.itemType = itemType;
        this.initAmount = unSoldAmount;
        this.orderedItems = orderedItems;
        this.description = description;
    }



    //============================================Get=============================================
    //=====================================Get DataBase Data======================================
    // Main Data
    @Override
    public String getId()
    {
        return this.id;
    }
    @Override
    public String getName()
    {
        this.queryInfo();
        return this.name;
    }
    @Override
    public Shop getShop()
    {
        this.queryInfo();
        return this.shop;
    }
    public float getPrice()
    {
        this.queryInfo();
        return this.price;
    }
    public ItemType getItemType()
    {
        this.queryInfo();
        return this.itemType;
    }
    public int getInitAmount()
    {
        this.queryInfo();
        return this.initAmount;
    }
    public List<OrderedItem> getItemAmounts()
    {
        this.queryInfo();
        return this.orderedItems;
    }
    public String getDescription()
    {
        this.queryInfo();
        return this.description;
    }

    // Addition Data
    public int getSoldAmount()
    {
        this.queryInfo();
        int soldAmount = 0;
        for (OrderedItem orderedItem : this.orderedItems)
        {
            if (!orderedItem.getIsSold()) continue;
            soldAmount += orderedItem.getAmount();
        }

        return soldAmount;
    }
    public int getUnSoldAmount()
    {
        this.queryInfo();
        int unSoldAmount = 0;
        for (OrderedItem orderedItem : this.orderedItems)
        {
            if (!orderedItem.getIsSold()) continue;
            unSoldAmount += orderedItem.getAmount();
        }

        return unSoldAmount;
    }

    //=======================================Get Curr Data========================================
    @Override
    public String getId(String privateKey)
    {
        if (!this.getKey().equals(privateKey)) return null;
        return this.id;
    }
    @Override
    public String getName(String privateKey)
    {
        if (!this.getKey().equals(privateKey)) return null;
        return this.name;
    }
    @Override
    public Shop getShop(String privateKey)
    {
        if (!this.getKey().equals(privateKey)) return null;
        return this.shop;
    }
    public float getPrice(String privateKey)
    {
        if (!this.getKey().equals(privateKey)) return -1;
        return this.price;
    }
    public ItemType getItemType(String privateKey)
    {
        if (!this.getKey().equals(privateKey)) return null;
        return this.itemType;
    }
    public int getInitAmount(String privateKey)
    {
        if (!this.getKey().equals(privateKey)) return -1;
        return this.initAmount;
    }
    public List<OrderedItem> getItemAmounts(String privateKey)
    {
        if (!this.getKey().equals(privateKey)) return null;
        return this.orderedItems;
    }
    public String getDescription(String privateKey)
    {
        if (!this.getKey().equals(privateKey)) return null;
        return this.description;
    }



    //===========================================Modify===========================================
    @Override
    public void setName(String name)
    {
        this.queryInfo();
        this.name = name;
        this.updateInfo();
    }
    public void setPrice(float price)
    {
        this.queryInfo();
        this.price = price;
        this.updateInfo();
    }
    public void setItemType(ItemType itemType)
    {
        this.queryInfo();
        this.itemType = itemType;
        this.updateInfo();
    }
    public void setInitAmount(int initAmount)
    {
        this.queryInfo();
        this.initAmount = initAmount;
        this.updateInfo();
    }
    public void setItemAmounts(List<OrderedItem> orderedItems)
    {
        this.queryInfo();
        this.orderedItems = orderedItems;
        this.updateInfo();
    }
    public void setDescription(String description)
    {
        this.queryInfo();
        this.description = description;
        this.updateInfo();
    }



    //==========================================Database==========================================
    private void queryInfo()
    {
        Item item = this.shop.getDataBase().queryItemData(this.id);
        if (item == null)
        {
            System.out.println("ERROR: Can't query Item " + this.id);
            return;
        }

        this.shop = item.getShop(this.getKey());
        this.name = item.getName(this.getKey());
        this.price = item.getPrice(this.getKey());
        this.itemType = item.getItemType(this.getKey());
        this.initAmount = item.getInitAmount(this.getKey());
        this.description = item.getDescription(this.getKey());
    }

    private void updateInfo()
    {
        this.shop.getDataBase().updateItemData(this);
    }
}
