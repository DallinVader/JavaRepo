//Simple horse race made by Dallin Whitaker.
//for software and programming dev 1.
//instructor Mr Gross.
//Shows how to use Objects in java.

//Imports usfil packages.
import java.util.ArrayList;
import java.lang.Math;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class ObjectScript {

    //Variables.
    public static int NumberOfHorses;
    public static int HorseRaceDistance = 20;

    public static ArrayList<SimpleObj> HorsesThatWon = new ArrayList<>();
    public static ArrayList<SimpleObj> ObjList = new ArrayList<>();

    public static Timer timer = new Timer();
    public static boolean LeaderBoardCompleat = false;

    //Class for a simple object also know as the horse.
    public static class SimpleObj {
        //Object Variables.
        String ObjName;
        int[] Position = {0, 0};
        int Age = (int) Math.round(Math.random() * 30);
        
        //Sets Object Variables when it is created.
        public SimpleObj(String Name, int XStartPos, int YStartPos){
            ObjName = Name;

            Position[0] = XStartPos;
            Position[1] = YStartPos;
        }

        //Returns all data from the object to a string.
        public String AllDataToString(){
            String TempString = ObjName + " X" + Position[0] + " Y" + Position[1];
            return TempString;
        }

        //Changes the X and Y position variables.
        public void ChangePos(int NewXAxis, int NewYAxis){
            Position[0] = NewXAxis;
            Position[1] = NewYAxis;
        }
    }

    //Function that runs at the start.
    public static void main(String[] args){
        //Starts the Horse Race.
        GameStart();
    }

    //Function that is used to start the race.
    public static void GameStart(){
        Scanner scan = new Scanner(System.in);
        
        //Cancels and resets the timer when starting or restarting.
        timer.cancel();
        timer = new Timer();

        System.err.println("Enter the amount of horses you would like to race?");

        //Gets number of horses from terminal.
        NumberOfHorses = Integer.parseInt(scan.nextLine());
        CreateObjs(NumberOfHorses);

        //Prints all of the horses that were created.
        for (SimpleObj obj : ObjList) {

            System.err.println(obj.ObjName);
        }
        
        System.err.println("");
        System.err.println("Enter Start to start the race");
        //Starts the race when you enter Start.
        if (scan.nextLine().equals("Start")){
            LeaderBoardCompleat = false;
            ClearTerminal();
            System.err.println("Starting race!");
            //Starts a loop for RaceHorses() function.
            timer.scheduleAtFixedRate(new TimerTask(){public void run(){RaceHorses();}}, 150, 150);
            
        }
    }

    //Races the horses.
    public static void RaceHorses(){
        //Checks if all the horses have won and if not continues the race.
        if (HorsesThatWon.size() >= NumberOfHorses && !LeaderBoardCompleat) {
            Scanner scan = new Scanner(System.in);
            System.err.println(HorsesThatWon.get(0).ObjName + " WON THE RACE! Horse age is " + HorsesThatWon.get(0).Age);
            for (int i = 0; i < HorsesThatWon.size(); i++) {
                System.err.println(i + 1 + ": " + HorsesThatWon.get(i).ObjName);
            }
            LeaderBoardCompleat = true;
            //Restarts the race.
            System.err.println("Enter Restart to restart the race");
            if (scan.nextLine().equals("Restart")){
                ClearTerminal();
                NumberOfHorses = 0;
                ObjList = new ArrayList<>();
                HorsesThatWon = new ArrayList<>();
                GameStart();
            }
        }
        else if(!LeaderBoardCompleat){
            //Clears the terminal and then foreach horse tells you how far they are in the race.
            //NOTE. Breaks the race if you have more than 40 horses racing because the ClearTerminal() has issus with clearing large amounts of the terminal.
            ClearTerminal();
            for (int i = 0; i < ObjList.size(); i++) {
                System.err.print(i + 1);
                for (int j = 0; j < ObjList.get(i).Position[0]; j++) {
                    System.err.print("-");
                }
                System.err.print(">");
                System.err.println("");
                if (ObjList.get(i).Position[0] <= 50) {
                    ObjList.get(i).Position[0] += (int) Math.round(Math.random() * 1);
                }
                else{
                    if (!HorsesThatWon.contains(ObjList.get(i))) {
                        HorsesThatWon.add(ObjList.get(i));
                    }
                }
            }
        }
    }

    //Creates the objects with unique names.
    public static void CreateObjs(int NumberOfObjects){
        for (int i = 0; i < NumberOfObjects; i++) {
            SimpleObj Temp = new SimpleObj("Horse" + (i + 1), 0, 0);
            ObjList.add(Temp);
        }
    }

    //Clears the terminal.
    public static void ClearTerminal(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    //Converts a int to a string.
    public static String IntToString(int IntNumber){
        String Temp = IntNumber + "";
        return Temp;
    }

}
