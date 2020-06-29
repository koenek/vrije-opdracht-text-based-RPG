public abstract class Ability {
    protected String type;
    protected String title;
    protected int potency;
    protected int mpCost;
    protected String effect;

    public Ability(){};

    public Ability(String type, String title, int potency, int mpCost, String effect){
        this.type = type;
        this.title = title;
        this.potency = potency;
        this.mpCost = mpCost;
        this.effect = effect;
    }

    public void setPotency(int potency) {
        this.potency = potency;
    }

    public int getPotency() {
        return potency;
    }

    public int getMpCost() {
        return mpCost;
    }

    public String toString() {
        return this.title;
    }
}
