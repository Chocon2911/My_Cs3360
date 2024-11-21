package HuySystem;

import Obj.ItemType;
import Obj.RankType;
import Obj.UserType;

import java.util.Random;
import java.util.Scanner;

public abstract class HuyUtil
{
    private static final Scanner scanner = new Scanner(System.in);

    //===========================================Other============================================
    protected String getRandomStr(int length)
    {
        if (length <= 0)
        {
            System.out.println("ERROR: getRandomStr(): length is <= 0");
            return null;
        }

        Random rand = new Random();
        String randChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randStr = new StringBuilder();

        for (int i = 0; i < length; i++)
        {
            int index = rand.nextInt(randChar.length());
            randStr.append(randChar.charAt(index));
        }

        return randStr.toString();
    }

    //==========================================Convert===========================================
    // RankType
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

    // ItemType
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

    // UserType
    protected int getIntFromUserType(UserType userType)
    {
        if (userType == UserType.Manager) return 1;
        else if (userType == UserType.Staff) return 2;
        else if (userType == UserType.Customer) return 3;
        else return 0;
    }

    protected UserType getUserTypeFromInt(int value)
    {
        if (value == 1) return UserType.Manager;
        else if (value == 2) return UserType.Staff;
        else if (value == 3) return UserType.Customer;
        else return null;
    }

    protected String getStrFromUserType(UserType userType)
    {
        if (userType == UserType.Manager) return "Manager";
        else if (userType == UserType.Staff) return "Staff";
        else if (userType == UserType.Customer) return "Customer";
        else return "No Type";
    }
}
