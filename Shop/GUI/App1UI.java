package GUI;

import Obj.Side.ActiveShop;

import java.util.ArrayList;
import java.util.List;

public class App1UI
{
    //==========================================Variable==========================================
    private List<ActiveShop> activeShops;

    //========================================Constructor=========================================
    public App1UI()
    {
        this.activeShops = new ArrayList<ActiveShop>();
    }

    //==========================================Get Set===========================================
    public List<ActiveShop> getActiveShopSystems() { return activeShops; }

    public void setActiveShopSystems(List<ActiveShop> activeShops)
    { this.activeShops = activeShops; }

    //======================================ChooseShopSystem======================================
    private void displayChooseShopSystem()
    {
        
    }
}
