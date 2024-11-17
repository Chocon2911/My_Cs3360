package Obj.Side;

import Obj.IShopChild;
import Obj.Main.Item;
import Obj.Main.Shop;

public class OrderedItem implements IShopChild
{
    //==========================================Variable==========================================
    // OrderedItem
    private Item item;
    private int amount;
    private boolean isSold;

    // IObj
    private String id;

    // IShopChild
    private Shop shop;

    //========================================Constructor=========================================
    public OrderedItem()
    {
        // OrderedItem
        this.item = new Item();
        this.amount = -1;
        this.isSold = false;

        // IObj
        this.id = "";

        // IShopChild
        this.shop = new Shop();
    }

    public OrderedItem(String id, Shop shop, Item item, int amount, boolean isSold)
    {
        // OrderedItem
        this.item = item;
        this.amount = amount;
        this.isSold = isSold;

        // IObj
        this.id = id;

        // IShopChild
        this.shop = shop;
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
