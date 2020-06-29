public abstract class Item {
    protected String name;
    protected int potency;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPotency() {
        return potency;
    }

    public String toString() {
        return name;
    }
}
