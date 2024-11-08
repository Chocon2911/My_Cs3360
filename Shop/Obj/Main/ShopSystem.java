package Obj.Main;

import HuySystem.HuyString;

public class ShopSystem extends BaseMainObj
{
    //==========================================Variable==========================================
    private HuyString systemCode;

    //========================================Constructor=========================================
    public ShopSystem()
    {
        super();
        this.systemCode = new HuyString();
    }

    public ShopSystem(HuyString id, HuyString name, HuyString password, HuyString systemCode)
    {
        super(id, name, password);
        this.systemCode = systemCode;
    }

    //==========================================Get Set===========================================
    public HuyString getSystemCode() { return this.systemCode; }
    public void setSystemCode(HuyString systemCode) { this.systemCode = systemCode; }
}
