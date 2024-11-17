package Obj.Main;

import Obj.OrderedItem;
import Obj.ItemType;

import java.util.ArrayList;
import java.util.List;

public class Item extends BaseMainObj
{
    //==========================================Variable==========================================
    private float price;
    private ItemType itemType;
    private int initAmount;
    private List<OrderedItem> orderedItems;
    private String description;

    //========================================Constructor=========================================
    public Item()
    {
        super();
        this.price = -1;
        this.itemType = null;
        this.initAmount = -1;
        this.orderedItems = new ArrayList<>();
        this.description = "";
    }

    public Item(String id, String name, float price, ItemType itemType,
                int unSoldAmount, List<OrderedItem> orderedItems, String description)
    {
        super(id, name);
        this.price = price;
        this.itemType = itemType;
        this.initAmount = unSoldAmount;
        this.orderedItems = orderedItems;
        this.description = description;
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
}
