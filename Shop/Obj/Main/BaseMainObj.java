package Obj.Main;

import HuySystem.HuyString;
import HuySystem.HuyInt;
import HuySystem.HuyFloat;

public class BaseMainObj extends Obj.BaseObj
{
    //==========================================Variable==========================================
    private HuyString name;
    private HuyString password;

    //========================================Constructor=========================================
    public BaseMainObj()
    {
        super();
        this.name = new HuyString();
        this.password = new HuyString();
    }

    public BaseMainObj(HuyString id, HuyString name, HuyString password)
    {
        super(id);
        this.name = name;
        this.password = password;
    }

    //==========================================Get Set===========================================
    public HuyString getName() { return this.name; }
    public HuyString getPassword() { return this.password; }

    public void setName(HuyString name) { this.name = name; }
    public void setPassword(HuyString password) { this.password = password; }
}
