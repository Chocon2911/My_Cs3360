package Obj.Main.Account.User;

import Obj.ActiveShopSystem;
import Obj.RankType;

public class Customer extends BaseUser
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

    public Customer(String id, String name, String password,
                    ActiveShopSystem activeShopSystem, RankType rankType)
    {
        super(id, name, password, activeShopSystem);
        this.rankType = rankType;
    }

    //============================================Get=============================================
    public RankType getRankType() { return rankType; }

    //===========================================Modify===========================================
    public void setRankType(RankType rankType) { this.rankType = rankType; }
}
