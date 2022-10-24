public class Hero {
    private String name;
    private int stamina;
    private boolean isGood;
    public int getStamina() { return stamina; }
    public String getName() { return name; }
    public boolean getIsGood() { return isGood; }

     public Hero(String name, int stamina, boolean isGood) {
        this.stamina = stamina;
        this.name = name;
        this.isGood = isGood;
     }

     public boolean canWin(Hero h) {
        return this.isGood != h.getIsGood() && this.stamina > h.getStamina();
     }

     public String toString() {
        return this.name + " is a hero. It is " + this.stamina + " power points. Is it good? " + this.isGood;
     }
}
