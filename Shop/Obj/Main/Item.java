package Obj.Main;

import Obj.ItemType;

public class Item extends BaseMainObj
{
    //==========================================Variable==========================================
    private float price;
    private ItemType itemType;
    private int expireDate;
    private int expireMonth;
    private int expireYear;
    private int unSoldAmount;
    private int soldAmount;
    private String description;

    //========================================Constructor=========================================
    public Item()
    {
        super();
        this.price = -1;
        this.itemType = null;
        this.expireDate = -1;
        this.expireMonth = -1;
        this.expireYear = -1;
        this.unSoldAmount = -1;
        this.soldAmount = -1;
        this.description = "";
    }

    public Item(String id, String name, String password, float price,
                ItemType itemType, int expireDate, int expireMonth, int expireYear,
                int unSoldAmount, int soldAmount, String description)
    {
        super(id, name, password);
        this.price = price;
        this.itemType = itemType;
        this.expireDate = expireDate;
        this.expireMonth = expireMonth;
        this.expireYear = expireYear;
        this.unSoldAmount = unSoldAmount;
        this.soldAmount = soldAmount;
        this.description = description;
    }

    //============================================Get=============================================
    public float getPrice() { return this.price; }
    public ItemType getItemType() { return this.itemType; }
    public int getExpireDate() { return this.expireDate; }
    public int getExpireMonth() { return this.expireMonth; }
    public int getExpireYear() { return this.expireYear; }
    public int getUnSoldAmount() { return this.unSoldAmount; }
    public int getSoldAmount() { return this.soldAmount; }
    public String getDescription() { return this.description; }

    //===========================================Modify===========================================
    public void setPrice(float price) { this.price = price; }
    public void setItemType(ItemType itemType) { this.itemType = itemType; }
    public void setExpireDate(int expireDate) { this.expireDate = expireDate; }
    public void setExpireMonth(int expireMonth) { this.expireMonth = expireMonth; }
    public void setExpireYear(int expireYear) { this.expireYear = expireYear; }
    public void setUnSoldAmount(int unSoldAmount) { this.unSoldAmount = unSoldAmount; }
    public void setSoldAmount(int soldAmount) { this.soldAmount = soldAmount; }
    public void setDescription(String description) { this.description = description; }
}
