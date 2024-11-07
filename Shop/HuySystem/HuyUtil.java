package HuySystem;

import java.util.Scanner;

public abstract class HuyUtil
{
    private static final Scanner scanner = new Scanner(System.in);

    //==========================================GetInput==========================================
    public boolean getInput(HuyString input)
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

    public boolean getInput(HuyInt input)
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

    public boolean getInput(HuyFloat input)
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
}
