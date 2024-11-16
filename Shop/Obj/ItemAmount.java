package Obj;

import Obj.Main.Item;

public class ItemAmount extends BaseObj
{
    //==========================================Variable==========================================
    private Item item;
    private int amount;
    private boolean isSold;

    //========================================Constructor=========================================
    public ItemAmount()
    {
        super();
        this.item = new Item();
        this.amount = -1;
        this.isSold = false;
    }

    public ItemAmount(String id, Item item, int amount, boolean isSold)
    {
        super(id);
        this.item = item;
        this.amount = amount;
        this.isSold = isSold;
    }

    //============================================Get=============================================
    public Item getItem() { return this.item; }
    public int getAmount() { return this.amount; }
    public boolean getIsSold() { return this.isSold; }

    public float getTotalPrice()
    {
        return this.item.getPrice() * this.amount;
    }

    //===========================================Modify===========================================
    public void setItem(Item item) { this.item = item; }
    public void setAmount(int amount) { this.amount = amount; }
    public void setIsSold(boolean isSold) { this.isSold = isSold; }
}
