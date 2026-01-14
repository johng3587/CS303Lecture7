import java.util.Scanner;

public class Functions {
 
    public static String getMenuItem(Scanner input) {
        String choice = " ";
        System.out.println("\nMODIFY YOUR LIST");
        System.out.println("A: Add Number ");
        System.out.println("D: Delete (by Index) ");
        System.out.println("F: Find a Value");
        System.out.println("M: Modify a Value");
        System.out.println("P: Print Array ");
        System.out.println("T: Trim Array to Size ");
        System.out.println("Q: Quit ");

        while (!( choice.equals("A") || choice.equals("D") ||
                  choice.equals("M") || choice.equals("F") ||
                  choice.equals("P") || choice.equals("T") ||
                  choice.equals("Q"))){
            System.out.print("Enter your choice: ");
            choice = input.nextLine().toUpperCase().trim();
        }
        System.out.println();
        return choice;
    }

}
