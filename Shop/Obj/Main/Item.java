package Obj.Main;

import HuySystem.HuyFloat;
import HuySystem.HuyInt;
import HuySystem.HuyString;
import Obj.ItemType;

public class Item extends BaseMainObj
{
    //==========================================Variable==========================================
    private HuyFloat price;
    private ItemType itemType;
    private HuyInt expireDate;
    private HuyInt expireMonth;
    private HuyInt expireYear;
    private HuyInt unSoldAmount;
    private HuyInt soldAmount;
    private HuyString description;

    //========================================Constructor=========================================
    public Item()
    {
        super();
        this.price = new HuyFloat();
        this.itemType = null;
        this.expireDate = new HuyInt();
        this.expireMonth = new HuyInt();
        this.expireYear = new HuyInt();
        this.unSoldAmount = new HuyInt();
        this.soldAmount = new HuyInt();
        this.description = new HuyString();
    }

    public Item(HuyString id, HuyString name, HuyString password, HuyFloat price,
                ItemType itemType, HuyInt expireDate, HuyInt expireMonth, HuyInt expireYear,
                HuyInt unSoldAmount, HuyInt soldAmount, HuyString description)
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
    public HuyFloat getPrice() { return this.price; }
    public ItemType getItemType() { return this.itemType; }
    public HuyInt getExpireDate() { return this.expireDate; }
    public HuyInt getExpireMonth() { return this.expireMonth; }
    public HuyInt getExpireYear() { return this.expireYear; }
    public HuyInt getUnSoldAmount() { return this.unSoldAmount; }
    public HuyInt getSoldAmount() { return this.soldAmount; }
    public HuyString getDescription() { return this.description; }

    //===========================================Modify===========================================
    public void setPrice(HuyFloat price) { this.price = price; }
    public void setItemType(ItemType itemType) { this.itemType = itemType; }
    public void setExpireDate(HuyInt expireDate) { this.expireDate = expireDate; }
    public void setExpireMonth(HuyInt expireMonth) { this.expireMonth = expireMonth; }
    public void setExpireYear(HuyInt expireYear) { this.expireYear = expireYear; }
    public void setUnSoldAmount(HuyInt unSoldAmount) { this.unSoldAmount = unSoldAmount; }
    public void setSoldAmount(HuyInt soldAmount) { this.soldAmount = soldAmount; }
    public void setDescription(HuyString description) { this.description = description; }
}
