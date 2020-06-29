public interface Fighter {
    void attack(PlayerCharacter pc, Enemy e);
    void takeDamage(int dmg);

    String getName();

}
