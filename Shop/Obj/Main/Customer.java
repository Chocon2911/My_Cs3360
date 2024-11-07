package Obj.Main;

import Obj.RankType;

public class Customer extends BaseMainObj
{
    //==========================================Variable==========================================
    private RankType rankType;
    // List<UnSoldItem> unSoldItem;

    //========================================Constructor=========================================
    public Customer()
    {
        this.rankType = RankType.None;
    }

    public Customer(RankType rankType)
    {
        this.rankType = rankType;
    }

    //============================================Get=============================================
    public RankType getRankType() { return rankType; }

    //===========================================Modify===========================================
    public void setRankType(RankType rankType) { this.rankType = rankType; }
}
