package Obj;

import HuySystem.HuyUtil;


public abstract class BaseObj extends HuyUtil
{
    private String id;

    //========================================Constructor=========================================
    public BaseObj()
    {
        this.id = "";
    }

    public BaseObj(String id)
    {
        this.id = id;
    }

    //==========================================Get Set===========================================
    public String getId() { return this.id; }
    public void setId(String id) { this.id = id; }
}
