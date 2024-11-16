package GUI;

import Obj.ActiveShopSystem;

import java.util.ArrayList;
import java.util.List;

public class App1UI
{
    //==========================================Variable==========================================
    private List<ActiveShopSystem> activeShopSystems;

    //========================================Constructor=========================================
    public App1UI()
    {
        this.activeShopSystems = new ArrayList<ActiveShopSystem>();
    }

    //==========================================Get Set===========================================
    public List<ActiveShopSystem> getActiveShopSystems() { return activeShopSystems; }

    public void setActiveShopSystems(List<ActiveShopSystem> activeShopSystems)
    { this.activeShopSystems = activeShopSystems; }

    //======================================ChooseShopSystem======================================
    private void displayChooseShopSystem()
    {
        
    }
}
