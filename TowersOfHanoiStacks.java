package CS240Assignments;

import java.util.*;

//Towers of Hanoi but with Stacks.
public class TowersOfHanoiStacks {
    private static int disks;
    private static int counter = 1;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a number of disks: ");
        disks = input.nextInt();//prompt user for number of disks

        Towers(disks);
        System.out.printf("Number of moves: %d%n", counter);

        input.close();
    }

    public static void Towers(int disk){
        //intialize the towers as stacks so that they can hold the disks
        //fromTower will hold [3,2,1] while the others hold nothing to start but they will be used to move disks around
        //until all three disks are on the toTower
        Stack<Integer> fromTower = new Stack<>();
        Stack<Integer> toTower = new Stack<>();
        Stack<Integer> auxTower = new Stack<>();

        //Use the for loop to put the disks on the fromTower from biggest to smallest, hence why the loop is deincrementing
        for(int i = disks; i >= 1; i--){
            fromTower.push(i);
        }

        Towers(disk, fromTower, "A", toTower, "C", auxTower, "B");

    }

    //helper method, handles disk moving logic, and has distnict tower names for naming of towers output.
    private static void Towers(int disk, Stack<Integer> fromTower,String fromTowerName, Stack<Integer> toTower, String toTowerName, Stack<Integer> auxTower, String auxTowerName){
            if(disk == 1){//base case, simplest case, pop the 1 disk to final destination.
                Integer diskMoved = fromTower.pop();//pop the disk on top off
                toTower.push(diskMoved);//push the disk onto the toTower
                System.out.printf("Move disk %d from tower %s to tower %s%n", disk, fromTowerName, toTowerName);
            }else{
                //first call uses n-1 disks to move the disks to temporary destinations until the largest disk is on it's final desination
                Towers(disk - 1, fromTower, fromTowerName, auxTower, auxTowerName, toTower, toTowerName);
                counter++;
                System.out.printf("Move disk %d from tower %s to tower %s%n", disk, fromTowerName, toTowerName);
                //second uses n-1 disks to move smaller disks on top of largest disks in their final destination of the toTower.
                Towers(disk - 1, auxTower, auxTowerName,  toTower, toTowerName,  fromTower, fromTowerName);
                counter++;
        }
    }
}
