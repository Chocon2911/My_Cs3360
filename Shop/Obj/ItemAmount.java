package Obj;

import Obj.Main.Item;

public class ItemAmount extends BaseObj
{
    //==========================================Variable==========================================
    private Item item;
    private int amount;
    private ItemStateType stateType;

    //========================================Constructor=========================================
    public ItemAmount()
    {
        super();
        this.item = new Item();
        this.stateType = null;
        this.amount = -1;
    }

    public ItemAmount(String id, Item item, ItemStateType stateType, int amount)
    {
        super(id);
        this.item = item;
        this.stateType = stateType;
        this.amount = amount;
    }

    //============================================Get=============================================
    public Item getItem() { return this.item; }
    public int getAmount() { return this.amount; }
    public ItemStateType getStateType() { return this.stateType; }

    public float getTotalPrice()
    {
        return this.item.getPrice() * this.amount;
    }

    //===========================================Modify===========================================
    public void setItem(Item item) { this.item = item; }
    public void setAmount(int amount) { this.amount = amount; }
    public void setStateType(ItemStateType stateType) { this.stateType = stateType; }
}
