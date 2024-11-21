package Obj.Side;

import Obj.IShopChild;
import Obj.Main.Item;
import Obj.Main.Shop;

public class OrderedItem implements IShopChild
{
    //==========================================Variable==========================================
    private static final String KEY = "This is Private Key";
    private String id;
    private Shop shop;
    private Item item;
    private int amount;
    private boolean isSold;



    //========================================Constructor=========================================
    public OrderedItem()
    {
        this.id = "";
        this.shop = new Shop();
        this.item = new Item();
        this.amount = -1;
        this.isSold = false;

    }

    public OrderedItem(String id, Shop shop, Item item, int amount, boolean isSold)
    {
        this.id = id;
        this.shop = shop;
        this.item = item;
        this.amount = amount;
        this.isSold = isSold;
    }



    //============================================Get=============================================
    //=====================================Get DataBase Data======================================
    @Override
    public String getId()
    {
        this.queryInfo();
        return this.id;
    }
    @Override
    public Shop getShop()
    {
        this.queryInfo();
        return this.shop;
    }
    public Item getItem()
    {
        this.queryInfo();
        return this.item;
    }
    public int getAmount()
    {
        this.queryInfo();
        return this.amount;
    }
    public boolean getIsSold()
    {
        this.queryInfo();
        return this.isSold;
    }

    // Addition
    public float getTotalPrice()
    {
        this.queryInfo();
        return this.item.getPrice() * this.amount;
    }

    //=======================================Get Curr Data========================================
    @Override
    public String getId(String privateKey)
    {
        if (!KEY.equals(privateKey)) return null;
        return this.id;
    }
    @Override
    public Shop getShop(String privateKey)
    {
        if (!KEY.equals(privateKey)) return null;
        return this.shop;
    }
    public Item getItem(String privateKey)
    {
        if (!KEY.equals(privateKey)) return null;
        return this.item;
    }
    public int getAmount(String privateKey)
    {
        if (!KEY.equals(privateKey)) return -1;
        return this.amount;
    }
    public Boolean getIsSold(String privateKey)
    {
        if (!KEY.equals(privateKey)) return null;
        return this.isSold;
    }



    //===========================================Modify===========================================
    public void setItem(Item item)
    {
        this.queryInfo();
        this.item = item;
        this.updateInfo();
    }
    public void setAmount(int amount)
    {
        this.queryInfo();
        this.amount = amount;
        this.updateInfo();
    }
    public void setIsSold(boolean isSold)
    {
        this.queryInfo();
        this.isSold = isSold;
        this.updateInfo();
    }



    //==========================================DataBase==========================================
    private void queryInfo()
    {
        OrderedItem orderedItem = this.shop.getDataBase().queryOrderedItemData(this.id);
        if (orderedItem == null)
        {
            System.out.println("ERROR: Can't query OrderedItem");
            return;
        }

        this.shop = orderedItem.getShop(KEY);
        this.item = orderedItem.getItem(KEY);
        this.amount = orderedItem.getAmount(KEY);
        this.isSold = orderedItem.getIsSold(KEY);
    }

    private void updateInfo()
    {
        this.shop.getDataBase().updateOrderedItemData(this);
    }
}
