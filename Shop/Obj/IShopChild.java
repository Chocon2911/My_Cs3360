package Obj;

import Obj.Main.Shop;

public interface IShopChild extends IObj
{
    public Shop getShop();
    public Shop getShop(String privateKey);
}
