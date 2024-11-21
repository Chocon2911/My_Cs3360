package Obj;

public interface IAccount extends IObj
{
    public String getPassword();
    public void setPassword(String password);
    public String getPassword(String privateKey);
}
