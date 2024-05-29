import java.util.Random;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Math;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
public class Main {
    private Pokemon current;
    private Pokemon oCurrent;
    private boolean terraUsed = false;
    private ArrayList<Pokemon> trainerList = new ArrayList<>();
    private ArrayList<Pokemon> rivalList = new ArrayList<>();
    private String name;
    private String rival;


    public void console() throws FileNotFoundException, InterruptedException{
        File movesList = new File ("Database_Moves");
        File pokemonList = new File ("Database_Pokemon");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name");
         name = scanner.nextLine();

        TimeUnit.MILLISECONDS.sleep(500);


        System.out.println("Enter your rivals name");
         rival = scanner.nextLine();
        try{
            for (int i = 0; i < 3; i++){
                trainerList.add(Pokemonselection(pokemonList, movesList));
            }

            for (int i = 0; i < 3; i++){
                rivalList.add(Pokemonselection(pokemonList, movesList));
            }

            TimeUnit.MILLISECONDS.sleep(500);

            System.out.println("Your Pokemon are " + trainerList.get(0).getName() + ", " + trainerList.get(1).getName()+
                    " and " + trainerList.get(2).getName());
            String [] Types = {"Normal", "Fire", "Water", "Electric", "Grass", "Ice", "Fighting", "Poison","Ground",
                    "Flying", "Psychic", "Bug", "Rock", "Ghost", "Dragon", "Dark", "Steel", "Fairy"};
            String typeList = "";
            for (int i = 0; i < Types.length; i++){
                int test = i+1;
                if (i != Types.length-1){
                    typeList += "[" + test + "] " + Types[i] + ", ";
                }
                else{
                    typeList += "[" + test + "] " + Types[i];
                }
            }
            for (int i = 0; i < trainerList.size(); i++){
                scanner = new Scanner(System.in);
                System.out.println("Set " + trainerList.get(i).getName() + "s terra type.");
                System.out.println("Possible Types: " + typeList);
                TimeUnit.MILLISECONDS.sleep(500);
                int terratype = scanner.nextInt()-1;
                if (terratype > 17){
                    System.out.println("Thats not an option");
                    i--;
                }
                else{
                    trainerList.get(i).setTeraType(Types[terratype]);
                }

            }
            battle();

        }
        catch(FileNotFoundException e){
            System.out.println("error");
        }
    }
    public static Pokemon Pokemonselection( File Database_Pokemon, File moveslist) throws FileNotFoundException {
        String[] Pokemon = new String[9];
        Random random = new Random();
        int randomnum = random.nextInt(49) + 1;
        randomnum *= 9;
        randomnum -= 8;

        try (BufferedReader reader = new BufferedReader(new FileReader(Database_Pokemon))) {
            int lineCount = 0;
            String line;

            while (lineCount < randomnum - 1) {
                reader.readLine();
                lineCount++;
            }
            int x = 0;
            while (x < 9 && (line = reader.readLine()) != null) {
                Pokemon[x] = line;
                x++;
                }
        } catch (IOException e) {
            System.out.println("Error");
        }
        return new Pokemon(Pokemon[0], Pokemon[1], Pokemon[2], Integer.parseInt(Pokemon[3]), Integer.parseInt(Pokemon[4]),
                Integer.parseInt(Pokemon[5]), Integer.parseInt(Pokemon[6]), Integer.parseInt(Pokemon[7]),
                Integer.parseInt(Pokemon[8]), moveslist );
    }
    public static void test(File file) throws FileNotFoundException {
        Pokemon pokemon = new Pokemon ("Gengar", "Ghost", "Poison", 100, 100, 100, 100, 100, 100,file);
        System.out.println(pokemon.getName());
        System.out.println(pokemon.getLevel());
        System.out.println(pokemon.getHp());
        System.out.println(pokemon.getAtk());
        System.out.println(pokemon.getDef());
        System.out.println(pokemon.getSpa());
        System.out.println(pokemon.getSpd());
        System.out.println(pokemon.getSpe());



    }
    public void battle () throws InterruptedException {
        current = trainerList.get(0);
        oCurrent = rivalList.get(0);

        System.out.println("Battle Start");
        TimeUnit.MILLISECONDS.sleep(500);

        System.out.println("You sent out " + current.getName());
        TimeUnit.MILLISECONDS.sleep(500);

        System.out.println("Opponent sent out " + oCurrent.getName());
        TimeUnit.MILLISECONDS.sleep(500);

        int Pokemonalive = 6;
        while (Pokemonalive > 1){
            System.out.println(current.getName() + " | Level " + current.getLevel() + " | Types:" + current.getType1() + " | " + current.getType2());
            System.out.println("HP: " + current.getCurrentHp() + " / " + current.getHp());
            System.out.println("Speed: " + current.getSpe());
            System.out.println(oCurrent.getName() + ". Level " + oCurrent.getLevel()+ " | Types:" + oCurrent.getType1() + " | " + oCurrent.getType2());
            System.out.println("HP: " + oCurrent.getCurrentHp() + " / " + oCurrent.getHp());
            System.out.println("Speed: " + oCurrent.getSpe());
            Scanner scanner = new Scanner(System.in);

            System.out.println("Would you like to [1] Fight, [2] Switch, or [3] Terrastilise?");
            int response = scanner.nextInt();
            TimeUnit.MILLISECONDS.sleep(500);

            if (response > 3){
                System.out.println("That's not an option!");
            }
            else if (response==3){
                if (terraUsed){
                    System.out.println("Sorry, you can only terrastilise once a game!");
                }
                else{
                    System.out.println(current.getName() + " terrastilised into a " + current.getTeraType() + " type!");
                    current.setType1(current.getTeraType());
                    current.setType2("None");
                }
            }
            else if (response == 2){
                if (trainerList.size() == 1){
                    System.out.println("There are no more Pokemon to switch to.");
                }
                else if (trainerList.size()<=3){
                    System.out.println("Which Pokmeon would you like to switch to?");
                    for (int i = 0; i < trainerList.size(); i++){
                        if (trainerList.get(i).equals(current)) {
                                trainerList.remove(i);
                        }
                    }
                    scanner = new Scanner(System.in);
                    for (int i = 0; i < trainerList.size(); i++) {
                        int num = i+1;
                        System.out.println("[" + num + "] " + trainerList.get(i).getName());
                    }
                    System.out.println("[3] Back");
                    int switchChoice = scanner.nextInt();
                    if (switchChoice == 3){

                    }
                    else if (switchChoice > 3){
                        System.out.println("That's not an option.");
                    }
                    else{
                        trainerList.add(current);
                        Moves storedMove = enemyAI(current, oCurrent);
                        current = trainerList.get(switchChoice-1);
                        enemyMove(storedMove);
                        deathCheck();
                    }
                }
            }
            else if (response == 1){
                TimeUnit.MILLISECONDS.sleep(500);
                scanner = new Scanner (System.in);
                System.out.println("[1]: "+current.getMove1().toString());
                System.out.println("[2]: "+current.getMove2().toString());
                System.out.println("[3]: "+current.getMove3().toString());
                System.out.println("[4]: "+current.getMove4().toString());
                System.out.println("[5]: Back");

                int move = scanner.nextInt();
                if (move == 5){

                }
                else if (move > 5){
                    System.out.println("Thats not an option");
                }
                else{
                    Moves moveUsed;
                    if (move==1){
                        moveUsed = current.getMove1();
                    }
                    else if (move==2){
                        moveUsed = current.getMove2();
                    }
                    else if (move==3){
                        moveUsed = current.getMove3();
                    }
                    else if (move==4){
                        moveUsed = current.getMove4();
                    }
                    else{
                        moveUsed = current.getMove1();
                    }
                    if (current.getSpe()==oCurrent.getSpe()){
                        Random random = new Random();
                        int randomNumber = random.nextInt(2) + 1;
                        int yourSpe = current.getSpe();
                        int enemySpe = oCurrent.getSpe();
                        switch (randomNumber) {
                            case 1:
                                current.setSpe(yourSpe + 1);
                                break;
                            case 2:
                                oCurrent.setSpe(enemySpe + 1);
                                break;
                        }
                    }
                    if (current.getSpe()> oCurrent.getSpe()){
                        if (current.getPara()){
                            Random random = new Random();
                            int randint = random.nextInt(+4)+1;
                            if (randint!=1){
                                playerMove(moveUsed);
                            }
                            else{
                                System.out.println("Your " + current.getName() + " was paralyzed. It couldn't move");
                            }
                        }

                        else{
                            if (!current.getFreeze()){
                                playerMove(moveUsed);
                            }
                            else if (current.getFreeze()){
                                Random random = new Random();
                                int randint = random.nextInt(5)+1;
                                if (randint==1){
                                    System.out.println(current.getName() + " broke free from being frozen.");
                                    playerMove(moveUsed);
                                    current.setFreeze(false);
                                }
                                else{
                                    System.out.println(current.getName() + " was frozen and couldn't move. ");
                                }
                            }
                        }
                        TimeUnit.MILLISECONDS.sleep(500);
                        int death = deathCheck();
                        if (trainerList.size()==0 || rivalList.size() == 0){
                            break;
                        }

                        if (death == 1 && !oCurrent.getFlinch()&&!oCurrent.getFreeze()){
                            if (oCurrent.getPara()){
                                Random random = new Random();
                                int randint = random.nextInt(+4)+1;
                                if (randint!=1){
                                    enemyMove(enemyAI(current,oCurrent));
                                }
                                else{
                                    System.out.println("The enemy was paralyzed and couldn't mnove.");
                                }
                            }
                            else{
                                enemyMove(enemyAI(current, oCurrent));
                            }
                        }
                        else if (oCurrent.getFreeze()){
                            Random random = new Random();
                            int randint = random.nextInt(5)+1;
                            if (randint==1){
                                System.out.println(oCurrent.getName() + " broke free from being frozen.");
                                enemyMove(enemyAI(current, oCurrent));
                                oCurrent.setFreeze(false);
                            }
                            else{
                                System.out.println(oCurrent.getName() + " was frozen and couldn't move. ");
                            }
                        }
                        TimeUnit.MILLISECONDS.sleep(500);

                        deathCheck();
                        if (trainerList.size()==0 || rivalList.size() == 0){
                            break;
                        }

                        if (current.getToxic() || current.getBurn()){
                            current.setCurrentHp(current.getCurrentHp()-current.getCurrentHp()/8);
                            if (current.getToxic()){
                                System.out.println(current.getName() + " was hurt by poison. ");
                            }
                            else{
                                System.out.println(current.getName() + " was hurt by it's burn.");
                            }
                        }
                        if (oCurrent.getToxic() || oCurrent.getBurn()){
                            oCurrent.setCurrentHp(oCurrent.getCurrentHp()-oCurrent.getCurrentHp()/8);
                            if (oCurrent.getToxic()){
                                System.out.println(oCurrent.getName() + " was hurt by poison. ");
                            }
                            else{
                                System.out.println(oCurrent.getName() + " was hurt by it's burn.");
                            }
                        }
                        TimeUnit.MILLISECONDS.sleep(500);
                        deathCheck();
                        if (trainerList.size()==0 || rivalList.size() == 0){
                            break;
                        }
                    }
                    else if (current.getSpe() < oCurrent.getSpe()){

                        if (!oCurrent.getFreeze()){
                            if (oCurrent.getPara()){
                                Random random = new Random();
                                int randint = random.nextInt(+4)+1;
                                if (randint!=1){
                                    enemyMove(enemyAI(current,oCurrent));
                                }
                                else{
                                    System.out.println("The enemy was paralyzed and couldn't mnove.");
                                }
                            }
                            else{
                                enemyMove(enemyAI(current, oCurrent));
                            }
                        }
                        else if (oCurrent.getFreeze()){
                            Random random = new Random();
                            int randint = random.nextInt(5)+1;
                            if (randint==1){
                                System.out.println(oCurrent.getName() + " broke free from being frozen.");
                                enemyMove(enemyAI(current, oCurrent));
                                oCurrent.setFreeze(false);
                            }
                            else{
                                System.out.println(oCurrent.getName() + " was frozen and couldn't move. ");
                            }
                        }
                        int death = deathCheck();
                        if (trainerList.size()==0 || rivalList.size() == 0){
                            break;
                        }
                        if (death ==1){
                            if (!current.getFreeze() && !current.getPara() && !current.getFlinch()){
                                playerMove(moveUsed);
                            }
                            else if (current.getFreeze()){
                                Random random = new Random();
                                int randint = random.nextInt(5)+1;
                                if (randint==1){
                                    System.out.println(current.getName() + " broke free from being frozen.");
                                    playerMove(moveUsed);
                                    current.setFreeze(false);
                                }
                                else{
                                    System.out.println(current.getName() + " was frozen and couldn't move. ");
                                }
                            }
                            else if (current.getPara()){
                                Random random = new Random();
                                int randint = random.nextInt(+4)+1;
                                if (randint!=1){
                                    playerMove(moveUsed);
                                }
                                else{
                                    System.out.println("Your " + current.getName() + " was paralyzed and couldn't move.");
                                }
                            }
                        }
                        TimeUnit.MILLISECONDS.sleep(500);
                        deathCheck();
                        if (trainerList.size()==0 || rivalList.size() == 0){
                            break;
                        }
                        if (current.getToxic() || current.getBurn()){
                            current.setCurrentHp(current.getCurrentHp()-current.getCurrentHp()/8);
                            if (current.getToxic()){
                                System.out.println(current.getName() + " was hurt by poison. ");
                            }
                            else{
                                System.out.println(current.getName() + " was hurt by it's burn.");
                            }
                        }
                        if (oCurrent.getToxic() || oCurrent.getBurn()){
                            oCurrent.setCurrentHp(oCurrent.getCurrentHp()-oCurrent.getCurrentHp()/8);
                            if (oCurrent.getToxic()){
                                System.out.println(oCurrent.getName() + " was hurt by poison. ");
                            }
                            else{
                                System.out.println(oCurrent.getName() + " was hurt by it's burn.");
                            }
                        }
                        TimeUnit.MILLISECONDS.sleep(500);
                        deathCheck();
                        if (trainerList.size()==0 || rivalList.size() == 0){
                            break;
                        }
                    }
                    current.setFlinch(false);
                    oCurrent.setFlinch(false);
                }
            }
        }

        TimeUnit.MILLISECONDS.sleep(500);
        if (trainerList.size() > rivalList.size()){
            System.out.println(name + " won the battle");
        }
        else if (rivalList.size() > trainerList.size()){
            System.out.println(rival + " won the battle.");
        }
        else{
            System.out.println("The battle ended with a draw");
        }
    }

    public void enemyAttackingStatus (Moves moveUsed, int damage){ //Used to apply status to player
        if (moveUsed.getSecondary().equals("None")){
            return;
        }
        else if (moveUsed.getSecondary().equals("Burn") && !current.getFreeze() && !current.getPara() && !current.getToxic() && !current.getBurn()){
            current.setBurn(true);
            System.out.println(current.getName() + " was burned.");
        }
        else if (moveUsed.getSecondary().equals("Para") && !current.getFreeze() && !current.getPara() && !current.getToxic() && !current.getBurn()){
            current.setPara(true);
            System.out.println(current.getName() + " was paralysed.");
            current.setSpe(current.getSpe()/2);
        }
        else if (moveUsed.getSecondary().equals("Toxic") && !current.getFreeze() && !current.getPara() && !current.getToxic() && !current.getBurn()){
            current.setToxic(true);
            System.out.println(current.getName() + " was badly poisoned.");
        }
        else if (moveUsed.getSecondary().equals("Freeze") && !current.getFreeze() && !current.getPara() && !current.getToxic() && !current.getBurn()){
            current.setFreeze(true);
            System.out.println(current.getName() + " was frozen.");
        }
        else if (moveUsed.getSecondary().equals("Recoil")){
            oCurrent.setCurrentHp(oCurrent.getCurrentHp()-(damage/3));
            System.out.println("The opponent " + oCurrent.getName() + " took some recoil damage.");
        }
        else if (moveUsed.getSecondary().equals("DOSPA")){
            current.setSpa(current.getSpa() / 3);
            System.out.println(current.getName() + " special attack was dropped");
        }
        else if (moveUsed.getSecondary().equals("Heal")){
            oCurrent.setCurrentHp(oCurrent.getCurrentHp() + damage/3 );
            System.out.println(oCurrent + " recovered some HP");
        }
        else if (moveUsed.getSecondary().equals("DODEF")){
            current.setDef(current.getDef()/2);
            System.out.println("Your defense has been decreased");
        }
        else if (moveUsed.getSecondary().equals("DSPA")){
            oCurrent.setSpa(oCurrent.getSpa()/2);
            System.out.println("The opponents " + oCurrent.getName() + " special attack sharply dropped");
        }
        else if (moveUsed.getSecondary().equals("Flinch")&& oCurrent.getSpe() > current.getSpe()){
            current.setFlinch(true);
            System.out.println("Your pokemon flinched and couldn't move. ");

        }
        else if (moveUsed.getSecondary().equals("BSPA")){
            int boost = (int) Math.floor(oCurrent.getSpa() * 1.5);
            oCurrent.setSpa(boost);
            System.out.println("The opponents " + oCurrent.getName() + " special attack was boosted");
        }
        else if (moveUsed.getSecondary().equals("DOSPD")){
            current.setSpd(current.getSpd() / 2);
            System.out.println("Your " + current.getName() + " special defense was dropped");
        }
        else if (moveUsed.getSecondary().equals("DOSPE")){
            current.setSpe(current.getSpe() / 2);
            System.out.println("Your " + current.getName() + " speed was dropped");
        }
        else if (moveUsed.getSecondary().equals("DOATK")){
            current.setAtk(current.getAtk()/2);
            System.out.println("Your " + current.getName() + " attack  was dropped");

        }
        else if (moveUsed.getSecondary().equals("BATK")){
            int boost = (int)(oCurrent.getAtk()*1.5);
            oCurrent.setAtk(boost);
            System.out.println("The opponents attack was raised");
        }
        else if (moveUsed.getSecondary().equals("BDEF")){
            int boost = (int)(oCurrent.getDef()*1.5);
            oCurrent.setDef(boost);
            System.out.println("The opponents defense was raised");
        }
        else if (moveUsed.getSecondary().equals("DATK")){
            oCurrent.setAtk(oCurrent.getAtk()/2);
            System.out.println("The opponents attack  was dropped");

        }
        else if (moveUsed.getSecondary().equals("BSPE")){
            int boost = (int)(oCurrent.getSpe()*1.5);
            oCurrent.setSpe(boost);
            System.out.println("The opponents speed was raised");
        }
    }

    public void playerAttackingStatus (Moves moveUsed, int damage){ //used to apply status to rival
        if (moveUsed.getSecondary().equals("None")){
            return;
        }
        else if (moveUsed.getSecondary().equals("Burn") && !oCurrent.getFreeze() && !oCurrent.getPara() && !current.getToxic() && !current.getBurn()){
            oCurrent.setBurn(true);
            System.out.println(oCurrent.getName() + " was burned.");
        }
        else if (moveUsed.getSecondary().equals("Para") && !oCurrent.getFreeze() && !oCurrent.getPara() && !current.getToxic() && !current.getBurn()){
            oCurrent.setPara(true);
            System.out.println(oCurrent.getName() + " was paralysed.");
            oCurrent.setSpe(oCurrent.getSpe()/2);
        }
        else if (moveUsed.getSecondary().equals("Toxic") && !oCurrent.getFreeze() && !oCurrent.getPara() && !current.getToxic() && !current.getBurn()){
            oCurrent.setToxic(true);
            System.out.println(oCurrent.getName() + " was badly poisoned.");
        }
        else if (moveUsed.getSecondary().equals("Freeze") && !oCurrent.getFreeze() && !oCurrent.getPara() && !current.getToxic() && !current.getBurn()){
            oCurrent.setFreeze(true);
            System.out.println(oCurrent.getName() + " was frozen.");
        }
        else if (moveUsed.getSecondary().equals("Recoil")){
            current.setCurrentHp(current.getCurrentHp()-(damage/3));
            System.out.println("The opponent " + current.getName() + " took some recoil damage.");
        }
        else if (moveUsed.getSecondary().equals("DOSPA")){
            oCurrent.setSpa(oCurrent.getSpa() / 2);
            System.out.println(oCurrent.getName() + " special attack was dropped");
        }
        else if (moveUsed.getSecondary().equals("Heal")){
            current.setCurrentHp(current.getCurrentHp() + damage/3 );
            System.out.println(current + " recovered some HP");
        }
        else if (moveUsed.getSecondary().equals("DODEF")){
            oCurrent.setDef(oCurrent.getDef()/2);
            System.out.println("Your opponents defense has been decreased");
        }
        else if (moveUsed.getSecondary().equals("DSPA")){
            current.setSpa(current.getSpa()/2);
            System.out.println("Your" + current.getName() + " special attack sharply dropped");
        }
        else if (moveUsed.getSecondary().equals("Flinch")&& current.getSpe() > oCurrent.getSpe()){
            current.setFlinch(true);
            System.out.println("Your opponents pokemon flinched and couldn't move. ");

        }
        else if (moveUsed.getSecondary().equals("BSPA")){
            int boost = (int) Math.floor(current.getSpa() * 1.5);
            current.setSpa(boost);
            System.out.println("Your" + current.getName() + " special attack was boosted");
        }
        else if (moveUsed.getSecondary().equals("DOSPD")){
            oCurrent.setSpd(oCurrent.getSpd() / 2);
            System.out.println("The opponents " + oCurrent.getName() + " special defense was dropped");
        }
        else if (moveUsed.getSecondary().equals("DOSPE")){
            oCurrent.setSpe(oCurrent.getSpe() / 2);
            System.out.println("Your opponents " + oCurrent.getName() + " speed was dropped");
        }
        else if (moveUsed.getSecondary().equals("DOATK")){
            oCurrent.setAtk(oCurrent.getAtk()/2);
            System.out.println("Your opponent" + oCurrent.getName() + " attack  was dropped");

        }
        else if (moveUsed.getSecondary().equals("BATK")){
            int boost = (int)(current.getAtk()*1.5);
            current.setAtk(boost);
            System.out.println("Your" + current.getName() + " attack was raised");
        }
        else if (moveUsed.getSecondary().equals("BDEF")){
            int boost = (int)(current.getDef()*1.5);
            current.setDef(boost);
            System.out.println("Your" + current.getName() + " defense was raised");
        }
        else if (moveUsed.getSecondary().equals("DATK")){
            current.setAtk(current.getAtk()/2);
            System.out.println("Your " + current.getName() + " attack  was dropped");

        }
        else if (moveUsed.getSecondary().equals("BSPE")){
            int boost = (int)(current.getSpe()*1.5);
            current.setSpe(boost);
            System.out.println("Your " + current.getName() +" speed  was raised");
        }
    }
    public void playerMove (Moves move){
        System.out.println("Your Pokemon used " + move.getName());
        int damage = damageCalc(current, oCurrent, move, false);
        oCurrent.setCurrentHp(oCurrent.getCurrentHp() - damage);
    }
    public void enemyMove (Moves move){
        System.out.println("The enemy Pokemon used " + move.getName());
        int damage = damageCalc(oCurrent, current, move, false);
        current.setCurrentHp(current.getCurrentHp() - damage);
    }
    public int deathCheck(){ //1 is everything is fine, 2 is your pokemon fainted, 3 is opponents fainted, 4 is game over.
        if (current.getCurrentHp() <= 0){
            System.out.println(current.getName() + " fainted");
            trainerList.remove(current);
            if (trainerList.size()>0){
                System.out.println("Switch to:");
                for (int i = 0; i < trainerList.size(); i++){
                    int switching = i+1;
                    System.out.println("[" + switching + "]" + trainerList.get(i).getName());
                }
                Scanner scanner = new Scanner (System.in);
                int switchChoise = scanner.nextInt();
                current = trainerList.get(switchChoise-1);
                System.out.println("You sent out " + current.getName());
                return 2;
            }
            else{
                return 4;
            }
        }
        else if (oCurrent.getCurrentHp() <= 0){
            System.out.println(oCurrent.getName() + " fainted");
            rivalList.remove(oCurrent);
            if (rivalList.size()>0){
                oCurrent = rivalList.get(0);
                return 3;
            }
            else{
                return 4;
            }

        }


        else {
            return 1;
        }
    }

    public int damageCalc(Pokemon attacking, Pokemon defending, Moves move, boolean enemyAi){
        if (move.getPower()==0){ /* Some moves are guaranteed to apply status, and are balanced by the fact they do
        no damage. This is used to check whether or not one of these moves is being used. */
            if (attacking.equals(current)){
                playerAttackingStatus(move, 0);
            }
            else{
                enemyAttackingStatus(move, 0);
            }
            return 0;
        }
        Random random = new Random();
        int hitchance = random.nextInt(100)+1;
        /*Used to check whether a move hits based on accuracy of the move given. We check for enemyAI as we don't want
        a move missing to influence how the enemy makes decsisions. For example, a 4x super effective move might not be used
        because it missed during the enemies calculations. We avoid this by checking if enemyai is true.
         */

        if (hitchance>move.getAccuracy() && !enemyAi){
            System.out.println("The move missed");
            return 0;
        }

        double result = 0;
        result = ((double)2*attacking.getLevel()/5+2)* move.getPower();
        String AttackingClass = move.getAttackType();
        if (AttackingClass.equals("S")){
            double multiplier = (double) attacking.getSpa()/ defending.getSpd();
            result *= multiplier;
        }
        else if (AttackingClass.equals("P")){
            double multipler = (double) attacking.getAtk() / defending.getDef();
            result *= multipler;
        }
        result/=50;
        result+=2;



        if (attacking.getType1().equals(move.getType()) || attacking.getType2().equals(move.getType())){
            result *= 1.5;

        }
        //Checking to see the move is the same type as attacking pokemon, because of the STAB design spec

        random = new Random();
        double multipler = random.nextInt(16)+85;
        multipler /= 100;
        result *= multipler;

        random = new Random();
        int crit = random.nextInt(12)+1;
        if (crit==12){
            result*=1.5;
            System.out.println("It was a critical hit");
        }

        String [] Types = {"Normal", "Fire", "Water", "Electric", "Grass", "Ice", "Fighting", "Poison","Ground",
                "Flying", "Psychic", "Bug", "Rock", "Ghost", "Dragon", "Dark", "Steel", "Fairy", "None"};
        /* This is used to check type charts. The collums of the array are the attacking type (from up to down), and the defending
        types are the rows. The index of each type is based on the array above.
         */


        double[][] typechart = {/*Normal attacking*/{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5,  0.0, 1.0, 1.0, 0.5, 1.0, 1.0},

                /*Fire*/ {1.0, 0.5, 0.5, 1.0, 2.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 0.5, 1.0, 2.0, 1.0, 1.0},


                /*Water*/{1.0, 2.0, 0.5, 1.0, 0.5, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 2.0,  1.0, 0.5, 1.0, 1.0, 1.0, 1.0},

                /*Electric*/{1.0, 1.0, 2.0, 0.5, 0.5, 1.0, 1.0, 1.0, 0.0, 2.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0},

                /*Grass*/{1.0, 0.5, 2.0, 1.0, 0.5, 1.0, 1.0, 0.5, 2.0, 0.5, 1.0, 0.5, 2.0, 1.0, 0.5, 1.0, 0.5, 1.0, 1.0},

                /*Ice*/{1.0, 0.5, 0.5, 1.0, 2.0, 0.5, 1.0, 1.0, 2.0, 2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.5, 1.0, 1.0},

                /*Fighting*/{2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.5, 1.0, 0.5, 0.5, 0.5, 2.0, 0.0, 1.0, 2.0, 2.0, 0.5, 1.0},

                /*Poison*/ {1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 0.5, 0.5, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 0.0, 2.0, 1.0},

                /*Ground*/{1.0, 2.0, 1.0, 2.0, 0.5, 1.0, 1.0, 2.0, 1.0, 0.0, 1.0, 0.5, 2.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0},

                /*Flying*/{1.0, 1.0, 1.0, 0.5, 2.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0},

                /*Psychic*/{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 2.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 0.0, 0.5, 1.0, 1.0},

                /*Bug*/ {1.0, 0.5, 1.0, 1.0, 2.0, 1.0, 0.5, 0.5, 1.0, 0.5, 2.0, 1.0, 1.0, 0.5, 1.0, 2.0, 0.5, 0.5, 1.0},

                /*Rock*/{1.0, 2.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 0.5, 2.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0},

                /*Ghost*/{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 2.0, 1.0, 0.5, 1.0, 1.0, 1.0},

                /*Dragon*/{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.5, 0.0, 1.0},

                /*Dark*/{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 2.0, 1.0, 0.5, 1.0, 0.5, 1.0},

                /*Steel*/{ 1.0, 0.5, 0.5, 0.5, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0,  1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 0.5, 2.0, 1.0},

                /*Fairy*/{ 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 2.0, 0.5, 1.0, 1.0}
        };

        int atk = 0;
        int firstDef = 0;
        int secondDef = 0;


        for (int i =0; i<Types.length; i++){ /*This code is used to find the type of the move and the 2 types of the
        defending pokemon, and convert them to integers based on the index of the above array*/
            if (move.getType().equals(Types[i])){
                atk = i;
            } if (Types[i].equals(defending.getType1())){
                firstDef = i;
            }
            else if (Types[i].equals(defending.getType2())) {
                secondDef = i;
            }
        }

        double damageMulti = typechart[atk][firstDef];
        double damageMulti2 = typechart[atk][secondDef];
        if (!enemyAi && damageMulti*damageMulti2==0.5){
            System.out.println("It wasn't very effective");
        }
        else if (!enemyAi && damageMulti*damageMulti2==0){
            System.out.println("The attack did no damage!.");
        }
        else if (!enemyAi && damageMulti*damageMulti2 >= 2){
            System.out.println("It was super effective");
        }
        result *= typechart[atk][firstDef];
        result *= typechart[atk][secondDef];

        Random random1 = new Random();
        int randint = random1.nextInt(10)+1;
        if (randint==1 && !enemyAi){
            if (current.equals(attacking)){
                playerAttackingStatus(move, (int) result);
            }
            else{
                enemyAttackingStatus(move, (int) result);
            }
        }
        return (int) result;
    }

    public Moves enemyAI (Pokemon player, Pokemon enemy){ //Used to decide how the enemy will move

        int move1 = damageCalc(enemy, player, enemy.getMove1(), true);
        int move2 = damageCalc(enemy, player, enemy.getMove2(), true);
        int move3 = damageCalc(enemy, player, enemy.getMove4(), true);

        // Following if statments calculate whether or not any of pokemons current mvoe are able to kill the player
        if (move1 > player.getCurrentHp()){
            return enemy.getMove1();
        }
        if (move2 > player.getCurrentHp()){
            return enemy.getMove2();
        }
        if (move3 > player.getCurrentHp()){
            return enemy.getMove4();
        }
        double pType1EType1 = 0;
        double pType1EType2 = 0;
        double pType2EType1 = 0;
        double pType2EType2 = 0;


        if (player.getCurrentHp() < player.getHp()*0.3){ //Calculates the most damaging move to a low hp opponent
            int attackOne = damageCalc(enemy, player, enemy.getMove1(), true);
            int attackTwo = damageCalc(enemy, player, enemy.getMove2(), true);
            int attackThree = damageCalc(enemy, player, enemy.getMove4(), true);
            if (attackTwo > attackOne && attackOne > attackThree){
                return enemy.getMove2();
            }
            else if (attackTwo > attackOne && attackTwo > attackThree){
                return enemy.getMove1();
            }
            else if (attackThree > attackOne && attackThree > attackTwo){
                return enemy.getMove4();

            }
        }

        double[][] typechart = {/*Normal attacking*/{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5,  0.0, 1.0, 1.0, 0.5, 1.0, 1.0},

                /*Fire*/ {1.0, 0.5, 0.5, 1.0, 2.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 0.5, 1.0, 2.0, 1.0, 1.0},


                /*Water*/{1.0, 2.0, 0.5, 1.0, 0.5, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 2.0,  1.0, 0.5, 1.0, 1.0, 1.0, 1.0},

                /*Electric*/{1.0, 1.0, 2.0, 0.5, 0.5, 1.0, 1.0, 1.0, 0.0, 2.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0},

                /*Grass*/{1.0, 0.5, 2.0, 1.0, 0.5, 1.0, 1.0, 0.5, 2.0, 0.5, 1.0, 0.5, 2.0, 1.0, 0.5, 1.0, 0.5, 1.0, 1.0},

                /*Ice*/{1.0, 0.5, 0.5, 1.0, 2.0, 0.5, 1.0, 1.0, 2.0, 2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.5, 1.0, 1.0},

                /*Fighting*/{2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.5, 1.0, 0.5, 0.5, 0.5, 2.0, 0.0, 1.0, 2.0, 2.0, 0.5, 1.0},

                /*Poison*/ {1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 0.5, 0.5, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 0.0, 2.0, 1.0},

                /*Ground*/{1.0, 2.0, 1.0, 2.0, 0.5, 1.0, 1.0, 2.0, 1.0, 0.0, 1.0, 0.5, 2.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0},

                /*Flying*/{1.0, 1.0, 1.0, 0.5, 2.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0},

                /*Psychic*/{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 2.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 0.0, 0.5, 1.0, 1.0},

                /*Bug*/ {1.0, 0.5, 1.0, 1.0, 2.0, 1.0, 0.5, 0.5, 1.0, 0.5, 2.0, 1.0, 1.0, 0.5, 1.0, 2.0, 0.5, 0.5, 1.0},

                /*Rock*/{1.0, 2.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 0.5, 2.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0},

                /*Ghost*/{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 2.0, 1.0, 0.5, 1.0, 1.0, 1.0},

                /*Dragon*/{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.5, 0.0, 1.0},

                /*Dark*/{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 2.0, 1.0, 0.5, 1.0, 0.5, 1.0},

                /*Steel*/{ 1.0, 0.5, 0.5, 0.5, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0,  1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 0.5, 2.0, 1.0},

                /*Fairy*/{ 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 2.0, 0.5, 1.0, 1.0},

                /*None*/ {1.0, 1.0, 1.0, 1.0, 1.0, 1.0 , 1.0, 1.0 , 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0}

        };
        String [] Types = {"Normal", "Fire", "Water", "Electric", "Grass", "Ice", "Fighting", "Poison","Ground",
                "Flying", "Psychic", "Bug", "Rock", "Ghost", "Dragon", "Dark", "Steel", "Fairy", "None"};

        int eTypeOne = 0;
        int eTypeTwo = 0;
        int pTypeOne = 0;
        int pTypeTwo = 0;

        for (int i =0; i<Types.length; i++){ /*This calculates the types of both on field pokemon, and
        finds out whether or not the enemy is in a favorable position or not*/
            if (enemy.getType1().equals(Types[i])){
                eTypeOne = i;
            }
            else if (enemy.getType2().equals(Types[i])){
                eTypeTwo = i;
            }
            else if (Types[i].equals(player.getType1())){
                pTypeOne = i;
            }
            else if (Types[i].equals(player.getType2())) {
                pTypeTwo = i;
            }
        }
        pType1EType1 = typechart[pTypeOne][eTypeOne];
        pType1EType2 =  typechart[pTypeOne][eTypeTwo];
        System.out.println(pTypeTwo);
        pType2EType1 = typechart[pTypeTwo][eTypeOne];
        pType2EType2 = typechart[pTypeTwo][eTypeTwo];

        if (pType1EType1 == 2 || pType1EType2 == 2 || pType2EType1 == 2 || pType2EType2 == 2){

            if (move1 > move2 && move1 > move3){
                return enemy.getMove1();
            }
            else if (move2 > move1 && move2 > move3){
                return enemy.getMove2();
            }
            else if (move3 > move1 && move3 > move1){
                return enemy.getMove3();
            }
        }

        Moves Will = new Moves ("Will'o'wisp", "Fire", "Status", 0, 85, "DOT");
        Moves para = new Moves ("Thunder Wave", "Electric", "Status", 0, 90, "Para");
        Moves freeze = new Moves ("Freezing Aura", "Ice", "Status", 0, 90, "Freeze");
        Moves sword = new Moves ("Swords Dance", "Normal", "Status", 0, 100, "BATK");
        Moves tears = new Moves ("Fake Tears", "Dark", "Status", 0, 100, "DOSPD");
        Moves agility = new Moves ("Agility", "Psychic", "Status", 0, 100, "BSPE");
        // looks at the status move the enemy has, and checks whether or not any of them are favourable

        if (enemy.getCurrentHp() > enemy.getHp()*0.75){ //Decides whether or not it is an appropate situation to use the status moves
            if (enemy.getMove3().equals(Will) && !player.getType1().equals("Fire") && !player.getType2().equals("Fire")){
                return enemy.getMove3();
            }
            else if (enemy.getMove3().equals(para) && !player.getType1().equals("Electric") && !player.getType2().equals("Electric")){
                return enemy.getMove3();
            }
            else if (enemy.getMove3().equals(freeze) && !player.getType1().equals("Ice") && !player.getType2().equals("Ice")){
                return enemy.getMove3();
            }
            else if (enemy.getMove3().equals(sword)){
                return enemy.getMove3();
            }
            else if (enemy.getMove3().equals(tears)){
                return enemy.getMove3();
            }
            else if ( enemy.getSpe() < player.getSpe() && enemy.getMove3().equals(agility)){
                return enemy.getMove3();
            }
        }
        if (move1 > move2 && move1 > move3){
            return enemy.getMove1();
        }
        else if (move2 > move1 && move2 > move3){
            return enemy.getMove2();
        }
        else if (move3 > move1 && move3 > move1){
            return enemy.getMove4();
        }
        return enemy.getMove1();
    }
}