package Obj.Main;

import HuySystem.HuyString;
import HuySystem.HuyInt;
import HuySystem.HuyFloat;

public class BaseMainObj extends Obj.BaseObj
{
    //==========================================Variable==========================================
    private HuyString name;

    //========================================Constructor=========================================
    public BaseMainObj()
    {
        super();
        this.name = new HuyString();
    }

    public BaseMainObj(HuyString id, HuyString name)
    {
        super(id);
        this.name = name;
    }

    //==========================================Get Set===========================================
    public HuyString getName() { return this.name; }
    public void setName(HuyString name) { this.name = name; }
}
