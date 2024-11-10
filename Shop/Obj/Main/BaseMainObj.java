package Obj.Main;

public class BaseMainObj extends Obj.BaseObj
{
    //==========================================Variable==========================================
    private String name;
    private String password;

    //========================================Constructor=========================================
    public BaseMainObj()
    {
        super();
        this.name = "";
        this.password = "";
    }

    public BaseMainObj(String id, String name, String password)
    {
        super(id);
        this.name = name;
        this.password = password;
    }

    //==========================================Get Set===========================================
    public String getName() { return this.name; }
    public String getPassword() { return this.password; }

    public void setName(String name) { this.name = name; }
    public void setPassword(String password) { this.password = password; }
}
