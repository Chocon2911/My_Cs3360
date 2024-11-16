package Obj.Main;

import Obj.ItemAmount;
import Obj.ItemType;

import java.util.ArrayList;
import java.util.List;

public class Item extends BaseMainObj
{
    //==========================================Variable==========================================
    private float price;
    private ItemType itemType;
    private int initAmount;
    private List<ItemAmount> itemAmounts;
    private String description;

    //========================================Constructor=========================================
    public Item()
    {
        super();
        this.price = -1;
        this.itemType = null;
        this.initAmount = -1;
        this.itemAmounts = new ArrayList<>();
        this.description = "";
    }

    public Item(String id, String name, float price, ItemType itemType,
                int unSoldAmount, List<ItemAmount> itemAmounts, String description)
    {
        super(id, name);
        this.price = price;
        this.itemType = itemType;
        this.initAmount = unSoldAmount;
        this.itemAmounts = itemAmounts;
        this.description = description;
    }

    //============================================Get=============================================
    public float getPrice() { return this.price; }
    public ItemType getItemType() { return this.itemType; }
    public int getInitAmount() { return this.initAmount; }
    public List<ItemAmount> getItemAmounts() { return this.itemAmounts; }
    public String getDescription() { return this.description; }

    public int getSoldAmount()
    {
        int soldAmount = 0;
        for (ItemAmount itemAmount : this.itemAmounts)
        {
            if (!itemAmount.getIsSold()) continue;
            soldAmount += itemAmount.getAmount();
        }

        return soldAmount;
    }

    public int getUnSoldAmount()
    {
        int unSoldAmount = 0;
        for (ItemAmount itemAmount : this.itemAmounts)
        {
            if (!itemAmount.getIsSold()) continue;
            unSoldAmount += itemAmount.getAmount();
        }

        return unSoldAmount;
    }

    //===========================================Modify===========================================
    public void setPrice(float price) { this.price = price; }
    public void setItemType(ItemType itemType) { this.itemType = itemType; }
    public void setInitAmount(int initAmount) { this.initAmount = initAmount; }
    public void setItemAmounts(List<ItemAmount> itemAmounts) { this.itemAmounts = itemAmounts; }
    public void setDescription(String description) { this.description = description; }
}
