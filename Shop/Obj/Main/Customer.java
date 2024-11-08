package Obj.Main;

import HuySystem.HuyString;
import Obj.RankType;

public class Customer extends BaseMainObj
{
    //==========================================Variable==========================================
    private RankType rankType;
    // List<UnSoldItem> unSoldItem;

    //========================================Constructor=========================================
    public Customer()
    {
        super();
        this.rankType = null;
    }

    public Customer(HuyString id, HuyString name, HuyString password, RankType rankType)
    {
        super(id, name, password);
        this.rankType = rankType;
    }

    //============================================Get=============================================
    public RankType getRankType() { return rankType; }

    //===========================================Modify===========================================
    public void setRankType(RankType rankType) { this.rankType = rankType; }
}
