package Obj.Main.User;

import HuySystem.HuyString;
import Obj.ActiveShopSystem;
import Obj.Main.BaseMainObj;

public class Manager extends BaseUser
{
    public Manager()
    {
        super();
    }

    public Manager(HuyString id, HuyString name, HuyString password, ActiveShopSystem activeShopSystem)
    { super(id, name, password, activeShopSystem); }
}
