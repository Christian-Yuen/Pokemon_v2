import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;
import java.lang.Math;

public class Pokemon {
    private String name;
    private String type1;
    private String type2;
    private int hp;
    private int currentHp;
    private int atk;
    private int def;
    private int spa;
    private int spd;
    private int spe;
    private int level;
    private Moves move1;
    private Moves move2;
    private Moves move3;
    private Moves move4;
    private String Status;
    private String teraType;
    private boolean burn;
    private boolean para;
    private boolean freeze;
    private boolean toxic;
    private boolean flinch;
    public Pokemon(String name, String type1, String type2, int hp, int atk, int def, int spa, int spd, int spe, File movelist) throws FileNotFoundException {
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.hp = hp;
        this.atk= atk;
        this.def = def;
        this.spa = spa;
        this.spd = spd;
        this.spe = spe;
        moveselection();
        Random random = new Random();
        level = random.nextInt(21)+80;
        statConversion();
        currentHp = this.hp;
    }

    public String getName(){
        return name;
    }
    public void setSpe(int spe){
        this.spe=spe;
    }
    public void setCurrentHp(int currentHp){
        this.currentHp = currentHp;
    }
    public void setDef(int def){
        this.def = def;
    }

    public String getType1(){
        return type1;
    }
    public String getType2(){
        return type2;
    }
    public int getHp(){
        return hp;
    }
    public int getAtk(){
        return atk;
    }
    public int getDef(){
        return def;
    }
    public int getSpa(){
        return spa;
    }
    public void setSpa(int spa){
        this.spa = spa;
    }
    public int getSpd(){
        return spd;
    }
    public int getSpe(){
        return spe;
    }
    public void setMove1(Moves move){
        move1 = move;
    }
    public void setMove2(Moves move){
        move2 = move;
    }
    public void setMove3(Moves move){
        move3 = move;
    }
    public void setMove4(Moves move){
        move4 = move;
    }
    public Moves getMove1(){
        return move1;
    }
    public Moves getMove2(){
        return move2;
    }
    public Moves getMove3(){
        return move3;
    }
    public Moves getMove4(){
        return move4;
    }
    public int getLevel(){
        return level;
    }
    public int getCurrentHp(){
        return currentHp;
    }
    public String getStatus(){
        return Status;
    }
    public void setStatus(String Status){
        this.Status = Status;
    }
    public String getTeraType(){
        return teraType;
    }

    public void setTeraType(String type){
        teraType = type;
    }
    public void setType1 (String type){
        type1 = type;
    }

    public void setType2 (String type){
        type2 = type;
    }

    public void setBurn (boolean burn){
        this.burn = burn;
    }
    public boolean getBurn(){
        return burn;
    }
    public void setFreeze (boolean freeze){
        this.freeze = freeze;
    }
    public boolean getFreeze(){
        return freeze;
    }

    public void setPara (boolean para){
        this.para = para;
    }
    public boolean getPara(){
        return para;
    }
    public void setToxic (boolean toxic){
        this.toxic = toxic;
    }
    public boolean getToxic(){
        return toxic;
    }
    public void setFlinch(boolean flinch){
        this.flinch = flinch;
    }
    public void setSpd (int spd){
        this.spd = spd;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public boolean getFlinch() {
        return flinch;
    }

    public void moveselection()throws FileNotFoundException { //Selects move
        try{
            String[] moves = {null, null, null, "0", "0", null};
            String [] array = readFile();
            //Move 1
            int moveIndex = Movetype(1); //Chooses first 2 moves based on type
            moveIndex*=6;
            moveIndex-=6;
            for (int i = 0; i < 6; i++) {
                moves[i] = array[moveIndex];
                moveIndex++;
            }
            setMove1(new Moves(moves[0], moves[1],moves[2], Integer.parseInt(moves[3]),
                    Integer.parseInt(moves[4]), moves[5]));

            //Move 2
            moveIndex = Movetype(2);
            moveIndex*=6;
            moveIndex-=6;
            for (int i = 0; i < 6; i++) {
                moves[i] = array[moveIndex];
                moveIndex++;
            }
            Moves move = new Moves(moves[0], moves[1],moves[2], Integer.parseInt(moves[3]),
                    Integer.parseInt(moves[4]), moves[5]);

            setMove2(move);
            int num = 0; //Chooses next 2 moves based on the individual Pokemon
            //move 3
            if (type1.equals("Fire") || type2.equals("Fire") && !name.equals("Cinderace")
                    && !name.equals("Ceruledge")) { /*Certain pokemon will be guranteed to have certain moves, which is
                    why this code is needed instead of randomly deciding the rest of the moves */
                num = 654;

            } else if (type1.equals("Poison") || type2.equals("Poison") && !name.equals("Sneasler")) {
                num = 666;

            } else if (type1.equals("Electric") || type2.equals("Electric")) {
                num = 660;

            } else if (type1.equals("Ice") || type2.equals("Ice")) {
                num = 672;

            } else if (type1.equals("Steel") || type2.equals("Steel")) {
                num = 648;

            } else {
                if (name.equals("Gardevoir") || name.equals("Darkrai") || name.equals("Gengar") ||
                        name.equals("Flutter Mane")) {
                    num = 612;

                } else if (name.equals("Urishifu Multi")) {
                    num = 102;

                } else if (name.equals("Ceruledge")) {
                    num = 48;

                } else if (name.equals("Cinderace")) {
                    num = 42;

                } else if (name.equals("Lucario")) {
                    num = 540;

                } else if (name.equals("Scizor") || name.equals("Sneasler") || name.equals("Crustle") || name.equals("Abomasnow")) {
                    num = 618;

                } else if (name.equals("Theos Degenerate Squirtle")){
                    num = 690;
                }
                else {

                    Random random = new Random();
                    int randomnum = random.nextInt(+5) + 1;
                    if (randomnum == 1) {
                        num = 624;

                    } else if (randomnum == 2) {
                        num = 630;

                    } else if (randomnum == 3) {
                        num = 636;

                    } else if (randomnum == 4) {
                        num = 642;

                    } else {
                        num = 678;

                    }
                }
            }
            for (int i = 0; i < 6; i++) {
                moves[i] = array[num];
                num++;
            }
            setMove3(new Moves(moves[0], moves[1],moves[2], Integer.parseInt(moves[3]),
                    Integer.parseInt(moves[4]), moves[5]));

            // move 4
            if (name.equals("Excadrill") || name.equals("Ursaring") || name.equals("Machamp") || name.equals("Trevanent")
                    || name.equals("Ursaluna") || name.equals("Mamoswine")) {
                num = 192;

            } else if (name.equals("Blaziken") || name.equals("Sneasler") || name.equals("Chestnaught")) {
                num = 606;

            } else if (name.equals("Darkrai") || name.equals("Gengar") || name.equals("Primarina")
                    || name.equals("Ninetails - Alola") || name.equals("Flutter Mane")) {
                num = 486;

            } else if (name.equals("Garchomp") || name.equals("Arcanine") || name.equals("Gyarados")
                    || name.equals("Steelix")) {
                num = 378;

            } else if (name.equals("Dragonite") || name.equals("Espeon")) {
                num = 438;


            } else if (name.equals("Alakazam") || name.equals("Gardevoir")) {
                num = 594;
            } else if (name.equals("Blastoise")) {
                num = 258;

            } else if (name.equals("Scizor")) {
                num = 552;
            } else if (name.equals("Dracozolt") || name.equals("Venusaur") || name.equals("Tyranitar") ||
                    name.equals("Aerodactyl") || name.equals("Abomasnow") || name.equals("Crustle")) {
                num = 216;

            } else if (name.equals("Chien Pao") || name.equals("Tyrantrum") || name.equals("Sharpedo")) {
                num = 492;

            } else if (name.equals("Magnezone") || name.equals("Iron Bundle") || name.equals("Porgon-Z")) {
                num = 684;

            } else if (name.equals("Snorlax") || name.equals("Urishifu Multi") || name.equals("Lucario")) {
                num = 510;

            } else if (name.equals("Accelegor")) {
                num = 72;

            } else if (name.equals("Galvantula") || name.equals("Jolteon") || name.equals("Cinderace")) {
                num = 108;

            } else if (name.equals("Charizard") || name.equals("Ceruledge") || name.equals("Christian's Holy Magikarp")
                    ||name.equals("Theos Degenerate Squirtle")) {
                num = 318;

            }
            else {
                num = 66;
            }
            for (int i = 0; i < 6; i++) {
                moves[i] = array[num];
                num++;
            }
            setMove4(new Moves(moves[0], moves[1],moves[2], Integer.parseInt(moves[3]),
                    Integer.parseInt(moves[4]), moves[5]));

        }
        catch(FileNotFoundException e){
            System.out.println("this sucks balls");
        }
    }
    public int Movetype (int type ){ //Deciding move based on pokemons primary types
        String search = "";
        if (type == 1){
            search = type1;
        }
        else{
            search = type2;
        }
        int x = 0;
        Random random = new Random();

        if (search.equals("Fire")){
            x = random.nextInt(7) + 1;
        }

        else if (search.equals("Water")){
            x = random.nextInt(8)+10;
        }

        else if (search.equals("Grass")){
            x = random.nextInt(6)+19;
        }

        else if (search.equals("Flying")){
            x = random.nextInt(4)+24;
        }

        else if (search.equals("Normal")){
            x = random.nextInt(4)+28;
        }

        else if (search.equals("Rock")){
            x = random.nextInt(4)+32;
        }

        else if (search.equals("Ground")){
            x = random.nextInt(6)+36;
        }

        else if (search.equals("Ice")){
            x = random.nextInt(7)+42;
        }

        else if (search.equals("Poison")){
            x = random.nextInt(5)+49;
        }

        else if (search.equals("Dragon")){
            x = random.nextInt(6)+54;
        }

        else if (search.equals("Fairy")){
            x = random.nextInt(4)+60;
        }

        else if (search.equals("Dark")){
            x = random.nextInt(6)+64;
        }

        else if (search.equals("Electric")){
            x = random.nextInt(6)+70;
        }

        else if (search.equals("Steel")){
            x = random.nextInt(5)+76;
        }

        else if (search.equals("Psychic")){
            x = random.nextInt(6)+81;
        }

        else if (search.equals("Bug")){
            x = random.nextInt(4)+87;
        }

        else if (search.equals("Fighting")){
            x = random.nextInt(8)+92;
        }

        else if (search.equals("Ghost")){
            x = random.nextInt(3)+100;
        }
        //Each move is organised in the text file based on type, which is the reason im able to easily randomly select them
        return x;
    }
    private String[] readFile() throws FileNotFoundException {

        File file = new File("Database_Moves");
        Scanner scanner = new Scanner(file);

        int linesInFile = countLinesInFile(file);
        String[] array = new String[linesInFile];

        int index = 0;

        while (scanner.hasNextLine()) {
            array[index] = scanner.nextLine();
            index++;
        }

        scanner.close();
        return array;
    }
    private int countLinesInFile(File file) throws FileNotFoundException {

        Scanner scanner = new Scanner(file);
        int count = 0;

        while (scanner.hasNextLine()) {
            scanner.nextLine();
            count++;
        }

        scanner.close();
        return count;
    }
    public void main(File file, File moveslist) throws FileNotFoundException { //A test method
        Pokemon pokemon = Main.Pokemonselection(file, moveslist);
        Moves move1 = pokemon.getMove1();
        System.out.println(move1.getName());
    }

    public void statConversion(){ //Converting base statistics to in game ones based on pokemons level
        hp = (int) Math.floor(0.01*(2*hp+31+Math.floor(0.25*84))*level)+level+10;
        atk = ingamestat(atk, level);
        def =  ingamestat(def, level);
        spa =  ingamestat(spa, level);
        spd = ingamestat(spd, level);
        spe = ingamestat(spe, level);

    }
    public static int ingamestat (int base, int level){
        double stat = 2*base;
        stat +=52;
        stat*=level;
        stat /=100;
        stat+=5;
        stat = Math.floor(stat);
        return (int) stat;
    }
}
