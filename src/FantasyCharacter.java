public abstract class FantasyCharacter implements Fighter {
    String name;
    int health,power;
    int agility;
    int experience, gold;

    public FantasyCharacter(String name, int health, int power, int agility, int experience, int gold) {
        this.name=name;
        this.health=health;
        this.power=power;
        this.agility=agility;
        this.experience=experience;
        this.gold=gold;
    }
    @Override
    public int attack(){
        if (agility*3> getRandomValue()) return power;
        else return 0;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){this.name=name;}
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getPower() {
        return power;
    }
    public void setPower(int power) {
        this.power = power;
    }
    public int getAgility() {
        return agility;
    }
    public void setAgility(int agility) {
        this.agility = agility;
    }
    public int getExperience() {
        return experience;
    }
    public void setExperience(int experience) {
        this.experience = experience;
    }
    public int getGold() {
        return gold;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }
    private int getRandomValue(){return(int) Math.random()*100;}
    public String toString(){
        return String.format( "%sздоровье;%d",name,health );
    }
}

