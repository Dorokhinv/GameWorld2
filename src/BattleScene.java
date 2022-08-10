public class BattleScene {
    public void fight(FantasyCharacter hero, FantasyCharacter monster, Realm.FightCallback fightCallback){
        Runnable runnable= ()->{
            int turn =1;
            boolean isFightEnded= false;
            while (!isFightEnded){
                System.out.println("---Step:"+turn+"----");
                if (turn++ %2 !=0){
                    isFightEnded =makeHit(monster,hero,fightCallback);
                }else {
                    isFightEnded=makeHit(monster,hero,fightCallback);
                }
                try {
                    Thread.sleep( 1000 );
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        Thread thread=new Thread(runnable);
        thread.start();
    }
    private boolean makeHit(FantasyCharacter defender, FantasyCharacter attacker, Realm.FightCallback fightCallback)
    { int hit=attacker.attack();
        int defenderHealth= defender.getHealth() - hit;
        if (hit !=0){
            System.out.println(String.format("%s Нанес удар %d едуниц!",attacker.getName(),hit));
            System.out.println(String.format("points of health...",defender.getName(),defenderHealth));
        }else {
            System.out.println(String.format( "%s is false!",attacker.getName()));
        }
        if (defenderHealth <=0 && defender instanceof Hero){
            System.out.println("Excuse me, you was dead!");
            fightCallback.fightLost();
            return true;
        }else if(defenderHealth <= 0){
            System.out.println(String.format("Enemy was destroyed! You has %d experience and %d gold",defender.getExperience(),defender.getGold()));
            attacker.setExperience(attacker.getExperience()+defender.getExperience());
            attacker.setGold(attacker.getGold()+defender.getGold());
            fightCallback.fightWin();
            return true;
        }else {
            defender.setHealth(defenderHealth);
            return false;
        }
    }
}
