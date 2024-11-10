package Obj.Main.User;

import Obj.ActiveShopSystem;

public class Staff extends BaseUser
{
    //==========================================Variable==========================================
    private int workHour;
    private float moneyPerHour;

    //========================================Constructor=========================================
    public Staff()
    {
        super();
        this.workHour = -1;
        this.moneyPerHour = -1;
    }

    public Staff(String id, String name, String password,
                 ActiveShopSystem activeShopSystem, int workHour, float moneyPerHour)
    {
        super(id, name, password, activeShopSystem);
        this.workHour = workHour;
        this.moneyPerHour = moneyPerHour;
    }

    //============================================Get=============================================
    public int getWorkHour() { return this.workHour; }
    public float getMoneyPerHour() { return this.moneyPerHour; }

    //===========================================Modify===========================================
    public void setWorkHour(int workHour) { this.workHour = workHour; }
    public void setMoneyPerHour(float moneyPerHour) { this.moneyPerHour = moneyPerHour; }
}
