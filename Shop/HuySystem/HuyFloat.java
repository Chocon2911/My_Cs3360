package HuySystem;

public class HuyFloat
{
    //==========================================Variable==========================================
    private float value;

    //========================================Constructor=========================================
    public HuyFloat()
    {
        this.value = 0;
    }
    public HuyFloat(float value)
    {
        this.value = value;
    }

    //=========================================Get Modify=========================================
    public float getValue() { return this.value; }
    public void setValue(float value) { this.value = value; }
    public void increaseBy1() { this.value++; }
    public void decreaseBy1() { this.value--; }
    public void increase(float value) { this.value += value; }
    public void multiply(float value) { this.value *= value; }
    public void divide(float value) { this.value /= value; }
}
