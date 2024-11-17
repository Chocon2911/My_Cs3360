package Obj.Main;

import Obj.IMainObj;
import Obj.IShopChild;
import Obj.ItemType;
import Obj.Side.OrderedItem;

import java.util.ArrayList;
import java.util.List;

public class Item implements IMainObj, IShopChild
{
    //==========================================Variable==========================================
    // Item
    private float price;
    private ItemType itemType;
    private int initAmount;
    private List<OrderedItem> orderedItems;
    private String description;

    // IObj
    private String id;

    // IMainObj
    private String name;

    // IShopChild
    private Shop shop;

    //========================================Constructor=========================================
    public Item()
    {
        // Item
        this.price = -1;
        this.itemType = null;
        this.initAmount = -1;
        this.orderedItems = new ArrayList<>();
        this.description = "";

        // IObj
        this. id = "";

        // IMainObj
        this.name = "";

        // IShopChild
        this.shop = null;
    }

    public Item(String id, String name, Shop shop, float price, ItemType itemType,
                int unSoldAmount, List<OrderedItem> orderedItems, String description)
    {
        // Item
        this.price = price;
        this.itemType = itemType;
        this.initAmount = unSoldAmount;
        this.orderedItems = orderedItems;
        this.description = description;

        // IObj
        this.id = id;

        // IMainObj
        this.name = name;

        // IShopChild
        this.shop = shop;
    }

    //============================================Get=============================================
    public float getPrice() { return this.price; }
    public ItemType getItemType() { return this.itemType; }
    public int getInitAmount() { return this.initAmount; }
    public List<OrderedItem> getItemAmounts() { return this.orderedItems; }
    public String getDescription() { return this.description; }

    public int getSoldAmount()
    {
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
        int unSoldAmount = 0;
        for (OrderedItem orderedItem : this.orderedItems)
        {
            if (!orderedItem.getIsSold()) continue;
            unSoldAmount += orderedItem.getAmount();
        }

        return unSoldAmount;
    }

    //===========================================Modify===========================================
    public void setPrice(float price) { this.price = price; }
    public void setItemType(ItemType itemType) { this.itemType = itemType; }
    public void setInitAmount(int initAmount) { this.initAmount = initAmount; }
    public void setItemAmounts(List<OrderedItem> orderedItems) { this.orderedItems = orderedItems; }
    public void setDescription(String description) { this.description = description; }

    //==========================================IMainObj==========================================
    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    //=========================================IShopChild=========================================
    @Override
    public Shop getShop()
    {
        return this.shop;
    }

    @Override
    public void setShop(Shop shop)
    {
        this.shop = shop;
    }

    //============================================IObj============================================
    @Override
    public String getId()
    {
        return this.id;
    }

    @Override
    public void setId(String id)
    {
        this.id = id;
    }
}
