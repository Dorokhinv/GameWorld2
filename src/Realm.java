import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Realm {
    private static BufferedReader br;
    private static FantasyCharacter player = null;

    private static BattleScene battleScene = null;

    public static void main(String[] args) {
        br= new BufferedReader(new InputStreamReader(System.in));
        battleScene=new BattleScene();
        System.out.println("Enter name");

        try {
            command(br.readLine());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private static void command(String string) throws IOException{
        if (player == null){
            player = new Hero(
                    string,
                    100,
                    20,
                    20,
                    0,
                    0
            );
            System.out.println(String.format("Rescue our world without dracon %s! His armor strong",
                    player.getName()));
            printNavigation();
        }switch (string){
            case "1":{
                System.out.println("Merchant isn't arrive");
                command(br.readLine());
            }break;
            case "2":{
                commitFight();
            }break;
            case "3":
                System.exit(1);
                break;
            case "yes":
                command("2");
                break;
            case "no":{
                printNavigation();
                command(br.readLine());
            }
        }
        command(br.readLine());
    }
    private static void printNavigation(){
        System.out.println("When do oyu want go?");
        System.out.println("1.To Merchant");
        System.out.println("2.Deep forest");
        System.out.println("3.Exit");
    }
    private static void commitFight(){
        battleScene.fight(player, createMonster(), new FightCallback(){
            @Override
            public void fightWin(){
                System.out.println(String.format("%s win! Now you have %d experience %d gold, and %d health",
                        player.getName(),player.getExperience(),player.getGold(),player.getHealth()));
                System.out.println("Do you could continue or return in town?(yes/no)");
                try {
                    command(br.readLine());
                }catch (IOException e){
                    e.printStackTrace();}
            }
            @Override
            public void fightLost() {}
        });
    }
    interface FightCallback {
        void fightWin();
        void fightLost();
    }
    private static FantasyCharacter createMonster(){
        int random=(int) (Math.random()*10);
        if (random %2 ==0) return new Goblin(
                "Goblin",
                50,
                10,
                10,
                100,
                20
        );else return new Skeleton(
                "Sceleton",
                25,
                20,
                20,
                100,
                10
        );
    }
}

