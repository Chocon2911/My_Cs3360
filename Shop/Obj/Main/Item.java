package Obj.Main;

import HuySystem.HuyFloat;
import HuySystem.HuyString;
import Obj.ItemType;

public class Item extends BaseMainObj
{
    //==========================================Variable==========================================
    private HuyFloat price;
    private ItemType itemType;
    private int expireDate;
    private int expireMonth;
    private int expireYear;
    private HuyString description;

    //========================================Constructor=========================================
    public Item()
    {
        super();
        this.price = new HuyFloat();
        this.itemType = ItemType.None;
        this.expireDate = -1;
        this.expireMonth = -1;
        this.expireYear = -1;
        this.description = new HuyString();
    }

    public Item(HuyString id, HuyString name, HuyFloat price, ItemType itemType,
                int expireDate, int expireMonth, int expireYear, HuyString description)
    {
        super(id, name);
        this.price = price;
        this.itemType = itemType;
        this.expireDate = expireDate;
        this.expireMonth = expireMonth;
        this.expireYear = expireYear;
        this.description = description;
    }

    //============================================Get=============================================
    public HuyFloat getPrice() { return this.price; }
    public ItemType getItemType() { return this.itemType; }
    public int getExpireDate() { return this.expireDate; }
    public int getExpireMonth() { return this.expireMonth; }
    public int getExpireYear() { return this.expireYear; }
    public HuyString getDescription() { return this.description; }

    //===========================================Modify===========================================
    public void setPrice(HuyFloat price) { this.price = price; }
    public void setItemType(ItemType itemType) { this.itemType = itemType; }
    public void setExpireDate(int expireDate) { this.expireDate = expireDate; }
    public void setExpireMonth(int expireMonth) { this.expireMonth = expireMonth; }
    public void setExpireYear(int expireYear) { this.expireYear = expireYear; }
    public void setDescription(HuyString description) { this.description = description; }
}
