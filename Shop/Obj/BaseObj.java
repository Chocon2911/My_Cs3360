package Obj;

import HuySystem.HuyUtil;
import HuySystem.HuyString;
import HuySystem.HuyInt;
import HuySystem.HuyFloat;


public abstract class BaseObj extends HuyUtil
{
    private HuyString id;

    //========================================Constructor=========================================
    public BaseObj()
    {
        this.id = new HuyString();
    }

    public BaseObj(HuyString id)
    {
        this.id = id;
    }

    //==========================================Get Set===========================================
    public HuyString getId() { return this.id; }
    public void setId(HuyString id) { this.id = id; }
}
