import java.util.Random;
public class Moves {
    private String name;
    private String type;
    private String attackType; //p is physical, s is special, status is status
    private int power;
    private int accuracy;
    private String secondary; //Secondary effect
    public Moves(String name, String type, String ps, int power, int accuracy, String secondary){
        this.name = name;
        this.type = type;
        this.attackType = ps;
        this.power = power;
        this.accuracy = accuracy;
        this.secondary = secondary;
    }
    public void setAccuracy(int accuracy){
        this.accuracy = accuracy;
    }

    public int getPower(){
        return power;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getAccuracy(){
        return accuracy;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public String getSecondary(){
        return secondary;
    }
    public String getAttackType(){
        return attackType;
    }
    public boolean hitchance(){
        Random random = new Random();
        int randint = random.nextInt(100)+1;
        return randint <= accuracy;
    }


    public String toString (){
        String result = name + " | Type: " + type + " | Power: " + power + " | Accuracy:" + accuracy + " | Effect: " + secondary + " | ";
        if (attackType.equals("P")){
            result += "Physical";
        }
        else if (attackType.equals("S")){
            result += "Special";
        }
        else{
            result += "Status";
        }
        return result;
    }
}
