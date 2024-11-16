package Obj.Main.Account.User;

import Obj.ActiveShopSystem;
import Obj.Main.Account.BaseAccount;
import Obj.Main.BaseMainObj;

public class BaseUser extends BaseAccount
{
    //==========================================Variable==========================================
    private ActiveShopSystem activeShopSystem;

    //========================================Constructor=========================================
    public BaseUser()
    {
        super();
        this.activeShopSystem = new ActiveShopSystem();
    }

    public BaseUser(String id, String name, String password,
                    ActiveShopSystem activeShopSystem)
    {
        super(id, name, password);
        this.activeShopSystem = activeShopSystem;
    }

    //==========================================Get Set===========================================
    public ActiveShopSystem getActiveShopSystem() { return this.activeShopSystem; }

    public void setActiveShopSystem(ActiveShopSystem activeShopSystem)
    { this.activeShopSystem = activeShopSystem; }
}
