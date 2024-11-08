package Obj;

import HuySystem.HuyInt;
import HuySystem.HuyString;
import Obj.Main.Item;

public class ItemAmount extends BaseObj
{
    //==========================================Variable==========================================
    private Item item;
    private HuyInt amount;
    private ItemStateType stateType;

    //========================================Constructor=========================================
    public ItemAmount()
    {
        super();
        this.item = new Item();
        this.stateType = null;
        this.amount = new HuyInt();
    }

    public ItemAmount(HuyString id, Item item, ItemStateType stateType, HuyInt amount)
    {
        super(id);
        this.item = item;
        this.stateType = stateType;
        this.amount = amount;
    }

    //============================================Get=============================================
    public Item getItem() { return this.item; }
    public HuyInt getAmount() { return this.amount; }
    public ItemStateType getStateType() { return this.stateType; }

    //===========================================Modify===========================================
    public void setItem(Item item) { this.item = item; }
    public void setAmount(HuyInt amount) { this.amount = amount; }
    public void setStateType(ItemStateType stateType) { this.stateType = stateType; }
}
