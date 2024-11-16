package Obj.Main;

import Obj.BaseObj;

public class BaseMainObj extends BaseObj
{
    //==========================================Variable==========================================
    private String name;

    //========================================Constructor=========================================
    public BaseMainObj()
    {
        super();
        this.name = "";
    }

    public BaseMainObj(String id, String name)
    {
        super(id);
        this.name = name;
    }

    //==========================================Get Set===========================================
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
}
