package HuySystem;

import Obj.ItemStateType;
import Obj.ItemType;
import Obj.RankType;

import java.util.Scanner;

public abstract class HuyUtil
{
    private static final Scanner scanner = new Scanner(System.in);

    //==========================================GetInput==========================================
    protected boolean getInput(HuyString input)
    {
        try 
        {
            input.setValue(scanner.nextLine());
            return true;
        }

        catch (Exception e)
        {
            return false;
        }
    }

    protected boolean getInput(HuyInt input)
    {
        try
        {
            input.setValue(Integer.parseInt(scanner.nextLine()));
            return true;
        }

        catch (Exception e)
        {
            return false;
        }
    }

    protected boolean getInput(HuyFloat input)
    {
        try
        {
            input.setValue(Float.parseFloat(scanner.nextLine()));
            return true;
        }

        catch (Exception e)
        {
            return false;
        }
    }

    //==========================================Convert===========================================
    protected RankType getRankTypeFromInt(int value)
    {
        if (value == 1) return RankType.Bronze;
        else if (value == 2) return RankType.Silver;
        else if (value == 3) return RankType.Gold;
        else if (value == 4) return RankType.Platinum;
        else return null;
    }

    protected int getIntFromRankType(RankType rankType)
    {
        if (rankType == RankType.Bronze) return 1;
        else if (rankType == RankType.Silver) return 2;
        else if (rankType == RankType.Gold) return 3;
        else if (rankType == RankType.Platinum) return 4;
        else return 0;
    }

    protected String getRankTypeStr(RankType rankType)
    {
        if (rankType == RankType.Bronze) return "Bronze";
        else if (rankType == RankType.Silver) return "Silver";
        else if (rankType == RankType.Gold) return "Gold";
        else if (rankType == RankType.Platinum) return "Platinum";
        else return "No RankType";
    }

    protected ItemType getItemTypeFromInt(int value)
    {
        if (value == 1) return ItemType.Food;
        else if (value == 2) return ItemType.Cloth;
        else if (value == 3) return ItemType.Tool;
        else return null;
    }

    protected int getIntFromItemType(ItemType itemType)
    {
        if (itemType == ItemType.Food) return 1;
        else if (itemType == ItemType.Cloth) return 2;
        else if (itemType == ItemType.Tool) return 3;
        else return 0;
    }

    protected ItemStateType getItemStateTypeFromInt(int value)
    {
        if (value == 1) return ItemStateType.UnSold;
        else if (value == 2) return ItemStateType.Sold;
        else return null;
    }

    protected int getIntFromItemStateType(ItemStateType itemStateType)
    {
        if (itemStateType == ItemStateType.UnSold) return 1;
        else if (itemStateType == ItemStateType.Sold) return 2;
        else return 0;
    }
}
