import java.util.ArrayList;

public class Floor {
    private String floorName;
    private String enterMessage;
    private String exitMessage;
    private ArrayList<Fighter> enemies;

    public Floor(String floorName, String enterMsg, String exitMsg) {
        this.floorName = floorName;
        this.enterMessage = enterMsg;
        this.exitMessage = exitMsg;
        this.enemies = new ArrayList<>();
    }

    public Floor(String floorName, String enterMsg, String exitMsg, Fighter e1) {
        this(floorName, enterMsg, exitMsg);
        this.enemies.add(e1);
    }

    public Floor(String floorName, String enterMsg, String exitMsg, Fighter e1, Fighter e2) {
        this(floorName, enterMsg, exitMsg, e1);
        this.enemies.add(e2);
    }

    public Floor(String floorName, String enterMsg, String exitMsg, Fighter e1, Fighter e2, Fighter e3) {
        this(floorName, enterMsg, exitMsg, e1, e2);
        this.enemies.add(e3);
    }

    public Fighter getEnemy(int index) {
        return enemies.get(index);
    }

    public String getName() {
        return floorName;
    }

    public String getEnterMessage() {return enterMessage;}

    public String getExitMessage() {return  exitMessage;}

}

