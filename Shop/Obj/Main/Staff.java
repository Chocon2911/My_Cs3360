package Obj.Main;

import HuySystem.HuyString;
import HuySystem.HuyFloat;
import HuySystem.HuyInt;

public class Staff extends BaseMainObj
{
    //==========================================Variable==========================================
    private HuyInt workHour;
    private HuyFloat moneyPerHour;

    //========================================Constructor=========================================
    public Staff()
    {
        super();
        this.workHour = new HuyInt();
        this.moneyPerHour = new HuyFloat();
    }

    public Staff(HuyString id, HuyString name, HuyInt workHour, HuyFloat moneyPerHour)
    {
        super(id, name);
        this.workHour = workHour;
        this.moneyPerHour = moneyPerHour;
    }

    //============================================Get=============================================
    public HuyInt getWorkHour() { return this.workHour; }
    public HuyFloat getMoneyPerHour() { return this.moneyPerHour; }

    //===========================================Modify===========================================
    public void setWorkHour(HuyInt workHour) { this.workHour = workHour; }
    public void setMoneyPerHour(HuyFloat moneyPerHour) { this.moneyPerHour = moneyPerHour; }
}
