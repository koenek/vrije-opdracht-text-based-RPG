import java.util.ArrayList;
import java.util.Scanner;

public class Dungeon implements FindableByName {
    Scanner scanner = new Scanner(System.in);
    private Validator validator = new Validator();
    private ArrayList<Floor> floors = new ArrayList<>();
    private EnemyController enemyCtrl = new EnemyController();
    private BattleController battleCtrl = new BattleController();
    private PlayerCharacter player;


    public Dungeon(PlayerCharacter player) {
        this.player = player;
        Floor floor1 = new Floor(
                "First", "As you exit the cell, it becomes clear that this place is some sort of ancient underground dungeon.\n" +
                "You walk along along a winding tunnel.",
                "\nYou see a flight of stairs ahead of you. You ascend it, hoping this brings you closer to the exit...\n",
                enemyCtrl.returnObj("Feral Goblin")
        );
        Floor floor2 = new Floor(
                "Second", "After ascending the stairs, you realize that the exit is not yet close by...",
                "\nYou see a flight of stairs ahead of you. You ascend it, hoping this brings you closer to the exit...\n",
                enemyCtrl.returnObj("Skeletal Warrior"),
                enemyCtrl.returnObj("Green-scaled Kobold"),
                enemyCtrl.returnObj("Ghoul")
        );
        Floor floor3 = new Floor(
                "Third", "After ascending the stairs, you realize that the exit is not yet close by...",
                "\nYou see a flight of stairs ahead of you. You ascend it, hoping this brings you closer to the exit...\n",
                enemyCtrl.returnObj("Gargoyle"),
                enemyCtrl.returnObj("Basilisk")
        );
        Floor floor4 = new Floor(
                "Fourth", "After ascending the stairs, you realize that the exit is not yet close by...",
                "\nYou see a flight of stairs ahead of you. You ascend it, hoping this brings you closer to the exit...\n",
                enemyCtrl.returnObj("Stone Golem"),
                enemyCtrl.returnObj("Fire Demon"),
                enemyCtrl.returnObj("Frost Giant")
        );
        Floor floorFinal = new Floor(
                "Final", "After ascending the stairs, you catch a whiff of fresh air. Is this real or are your senses playing tricks on you?",
                "\nYou see light at the end of the Tunnel! Maybe your mind is not playing tricks on you. Is it finally over?\n",
                enemyCtrl.returnObj("Six-headed Hydra"),
                enemyCtrl.returnObj("Undead Dragon")
        );
        floors.add(floor1);
        floors.add(floor2);
        floors.add(floor3);
        floors.add(floor4);
        floors.add(floorFinal);
    }

    public void navigateFloors(Floor f1, Floor f2, Floor f3, Floor f4, Floor fFinal) {

        System.out.println("\n***********PROLOGUE************");
        System.out.println("\nYou wake up in, what seems like, an empty prison cell. You don't seem to be hurt, but it is unclear how you got here.\n" +
                "The structure you are in seems to be underground. You decide to get up to find a way out of here...\n");

        advanceText();

        // First floor

        System.out.println("**********" + f1.getName() + " Floor**********\n");
        System.out.println(f1.getEnterMessage());
        String firstFloorInput = getLeftOrRight();
        if (firstFloorInput.equals("R")) {
            System.out.println("\nYou decided to go along the path on your right side.\nSuddenly, you see something move from the corner of your eye!");
            battleCtrl.doBattle(player, (Enemy) f1.getEnemy(0));
        } else if (firstFloorInput.equals("L")) {
            System.out.println("\nYou decided to go along the path on your left side.\nSuddenly, you see something move from the corner of your eye!");
            battleCtrl.doBattle(player, (Enemy) f1.getEnemy(0));
        }
        System.out.println(f1.getExitMessage());

        advanceText();

        // Second floor

        System.out.println("**********" + f2.getName() + " Floor**********\n");
        System.out.println(f2.getEnterMessage());
        String secondFloorInput = getLeftOrRight();
        if (secondFloorInput.equals("R")) {
            System.out.println("\nYou decided to go along the path on your right side.\nSuddenly, you see something move from the corner of your eye!");
            battleCtrl.doBattle(player, (Enemy) f2.getEnemy(0));
        } else if (secondFloorInput.equals("L")) {
            System.out.println("\nYou decided to go along the path on your left side.\nSuddenly, you see something move from the corner of your eye!");
            battleCtrl.doBattle(player, (Enemy) f2.getEnemy(1));
        }
        System.out.println("\"What is that foul smell? - You ask yourself.\nIgnoring the smell, you walk to the stairs that are at the end of path that you are currently on.\n" +
                "As you place your foot on the first step of the stairs. You realize that the foul smell is coming from the growling creature that is standing right benhind you!");
        battleCtrl.doBattle(player, (Enemy) f2.getEnemy(2));
        System.out.println(f2.getExitMessage());

        advanceText();

        // Third floor

        System.out.println("**********" + f3.getName() + " Floor**********\n");
        System.out.println(f3.getEnterMessage());
        String thirdFloorInput = getLeftOrRight();
        if (thirdFloorInput.equals("R")) {
            System.out.println("\nYou decided to go along the path on your right side.\nSuddenly, you see something move from the corner of your eye!");
            battleCtrl.doBattle(player, (Enemy) f3.getEnemy(0));
        } else if (thirdFloorInput.equals("L")) {
            System.out.println("\nYou decided to go along the path on your left side.\nSuddenly, you see something move from the corner of your eye!");
            battleCtrl.doBattle(player, (Enemy) f3.getEnemy(1));
        }
        System.out.println(f3.getExitMessage());

        advanceText();

        // Fourth floor
        System.out.println("**********" + f4.getName() + " Floor**********\n");
        System.out.println(f4.getEnterMessage());
        System.out.println("As you enter the next floor. A humongous stone statue suddenly moves toward you and attacks!");
        battleCtrl.doBattle(player, (Enemy) f4.getEnemy(0));
        String fourthFloorInput = getLeftOrRight();
        if (fourthFloorInput.equals("R")) {
            System.out.println("\nYou decided to go along the path on your right side.\nYou begin to sweat like crazy. The temperature here is through the roof!\n" +
                    "A fireball is hurled toward you! You barely dodge it, before being attacked!");
            battleCtrl.doBattle(player, (Enemy) f4.getEnemy(1));
        } else if (fourthFloorInput.equals("L")) {
            System.out.println("\nYou decided to go along the path on your left side.\nAn icy cold breeze makes the hairs on the back of your neck stand up.\n" +
                    "A sharp ice spike is hurled toward you! You barely dodge it, before being attacked!");
            battleCtrl.doBattle(player, (Enemy) f4.getEnemy(2));
        }
        System.out.println(f4.getExitMessage());

        advanceText();

        // Final floor
        System.out.println("**********" + fFinal.getName() + " Floor**********\n");
        System.out.println(fFinal.getEnterMessage());
        System.out.println("As you enter the next floor. A six-headed monstrosity suddenly moves toward you and attacks!");
        battleCtrl.doBattle(player, (Enemy) fFinal.getEnemy(0));
        String finalFloorInput = getLeftOrRight();
        if (finalFloorInput.equals("R")) {
            System.out.println("\nYou decided to go along the path on your right side.\nThe smell of death fills the room...");
            battleCtrl.doBattle(player, (Enemy) fFinal.getEnemy(1));
        } else if (finalFloorInput.equals("L")) {
            System.out.println("\nYou decided to go along the path on your left side.\nThe smell of death fills the room...");
            battleCtrl.doBattle(player, (Enemy) fFinal.getEnemy(2));
        }
        System.out.println(fFinal.getExitMessage());
        System.out.println("                                                                                                                                                                \n" +
                "                                                                                                                                                                \n" +
                "                                                                                                                                                                \n" +
                "                                                                                                                                                                \n" +
                "                                                                                                                                                                \n" +
                "                                                                                                                                                                \n" +
                "                                                                                                                                                                \n" +
                "                                                                                                                                                                \n" +
                "                                                                                                                                                                \n" +
                "                                                                                                                                                                \n" +
                "                                                                                                                                                                \n" +
                "                                                                                                                                                                \n" +
                "                                                                    .....................                                                                       \n" +
                "                                                               ............................                                                                     \n" +
                "                                                             .........,,*//(((((((////*,.....                                                                   \n" +
                "                                                            .......,*((##%%&&@@@@@&&&#(//**, ..                                                                 \n" +
                "                                                           .....,,//#%&@@@@@@@@@@@@@@@@&%#(///..                                                                \n" +
                "                                                          .....,*(#%&@@@@@@@@@@@@@@@@@@@@@&#(//.                                                                \n" +
                "                                                          ....,/(#&@@@@@@@@@@@@@@@@@@@@@@@@&#(///                                                               \n" +
                "                                                        ......*/#%@@@@@@@@@@@@@@@@@@@@@@@@@@@%//*.                                                              \n" +
                "                                                        .....,*(%&@@@@@@@@@@@@@@@@@@@@@@@@@@@&(//..                                                             \n" +
                "                                                        .....,*(%&@@@@@@@@@@@@@@@@@@@@@@@@@@@&((*...                                                            \n" +
                "                                                         ....,*/#%@@@@@@@@@@@@@@@@@@@@@@@@@@@&#(/...                                                            \n" +
                "                                                         .....*/#%@@@@@@@@@@@@@@@@@@@@@@@@@@@&#(*...                                                            \n" +
                "                                                         .....*/(%@@@@@@@@@@@@@@@@@@@@@@@@@@@@#(*...                                                            \n" +
                "                                                            ,/#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&(,.                                                           \n" +
                "                                                       .*###%%&&(%&@@@@@@@@@@@@@@@@@@@@@@@@@&%#/#@%&&%&*.                                                       \n" +
                "                                                  .,*,,,,,(/,,*/(%&&@@@@@@@@@@@@@@@@@@@@&&%%%#(**,,.,(/((/**,.                                                  \n" +
                "                                             ..........   ..,,*/((#&@@@@@@@@@@@@@@@@@@&&&%##((/*,,,... .,,,,,,,,,.                                              \n" +
                "                                        ..  .    ..     ......,,*/(#%&@&@@@@@@@@&&%%%%##(//***,,,,......    ..,,,,,..,,                                         \n" +
                "                                                        .......,,*/(####%%&&&%#####%####((//*,,..........        ....,,.....                                    \n" +
                "                                            .             .....,,*/(((///(######((//////**,,,.......                  ....                                      \n" +
                "                                                           ......,**/////(((/((/*/////**,,,,....                                                                \n" +
                "                                                             .....,,**///**,,,,,,.,,,,,........                                                                 \n" +
                "                                                               ....,,,,,,,,,,,*,,,,........                                                                     \n" +
                "                                                                  ......,,,.*,,.,,,..........                                                                   \n" +
                "                                                                 ........,,,........                                                                            \n" +
                "                                                                    . .......  ....                                                                             \n" +
                "                                                                                                                                                                \n" +
                "                                                                                                                                                                \n" +
                "                                                                                                                                                                \n" +
                "                                                                                                                                                                \n" +
                "                                                                                                                                                                " +
                "\n===========================================================================THE END===========================================================================\n" +
                "\n===================================================================THANK YOU FOR PLAYING=====================================================================\n");
    }

    public String getLeftOrRight() {
        boolean leftOrRightPressed = false;
        String lOrRInput = scanner.nextLine().toUpperCase();
        while (true) {
            System.out.println("\nThe path splits! You have an important decision to make:\n" +
                    "\nGo right?[R] ----- Go left?[L]");
            lOrRInput = scanner.nextLine().toUpperCase();
            try {
                validator.validate(lOrRInput, "L", "R");
                leftOrRightPressed = true;
            } catch (InvalidInputException ie) {
                System.out.println(ie.getMessage() + "Please enter [L] or [R]");
            }
            if (leftOrRightPressed) break;
        }
        return lOrRInput;
    }

    public void advanceText() {
        boolean enterPressed = false;
        while (true) {
            System.out.println("\nPress [ENTER] to continue.\n");
            String readString = scanner.nextLine();
            try {
                validator.validate(readString, "");
                enterPressed = true;
            } catch (InvalidInputException ie) {
                System.out.println(ie.getMessage() + "Please use the [Enter] key to continue.");
            }
            if (enterPressed) break;
        }
    }

    @Override
    public Floor returnObj(String name) {
        for (Floor f : floors) {
            if (f.getName().equals(name)) {
                return f;
            }
        }
        return null;
    }

}
