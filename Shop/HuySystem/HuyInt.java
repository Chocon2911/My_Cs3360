package HuySystem;

public class HuyInt
{
    //==========================================Variable==========================================
    private int value;

    //========================================Constructor=========================================
    public HuyInt() { this.value = 0; }
    public HuyInt(int value) { this.value = value; }

    //=========================================Get Modify=========================================
    public int getValue() { return this.value; }
    public void setValue(int value) { this.value = value; }
    public void increaseBy1() { this.value++; }
    public void decreaseBy1() { this.value--; }
    public void increase(int value) { this.value += value; }
    public void multiply(int value) { this.value *= value; }
    public void divide(int value) { this.value /= value; }
}
