import java.util.Scanner;
import java.io.IOException;
import java.io.File;

import com.mymethods.MyHeap;
import com.mymethods.MyHeapSort;

public class Functions {

    public static boolean isDigits(String inString) {
        if (inString == null || inString.isEmpty())
            return false;
        for (char c : inString.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }

    public static void example1() {
        System.out.println("\nEXAMPLE 1: TEST MYHEAP ADD(MAXIMUM)");
        boolean max = true;
        MyHeap<String> Heap1 = new MyHeap<>(max);
        Heap1.add("Fred");
        System.out.print("(1a) ");
        System.out.println(Heap1);
        Heap1.add("Alvin");
        System.out.print("(1b) ");
        System.out.println(Heap1);
        Heap1.add("Wilma");
        System.out.print("(1c) ");
        System.out.println(Heap1);
        Heap1.add("Theodore");
        System.out.print("(1d) ");
        System.out.println(Heap1);

        System.out.println("\nEXAMPLE 1: TEST MYHEAP REMOVE(MAXIMUM)");
        System.out.println("(1e) " + Heap1.top() + " :: " + Heap1.remove());
        System.out.println("(1f) " + Heap1.top() + " :: " + Heap1.remove());
        System.out.println("(1g) " + Heap1.top() + " :: " + Heap1.remove());
        System.out.println("(1h) " + Heap1.top() + " :: " + Heap1.remove());
        System.out.println("(1i) " + Heap1.top() + " :: " + Heap1.remove());
        System.out.println();

    }

    public static MyHeap<Person> loadData(MyHeap<Person> people) {
        try {
            Scanner scanner = new Scanner(new File("people.txt"));

            String inputLine;
            while (scanner.hasNextLine()) {
                inputLine = scanner.nextLine();
                String[] tokens = inputLine.split(",");
                try {
                    int tempAge = Integer.parseInt(tokens[3]);
                    char tempType = tokens[0].toUpperCase().charAt(0);
                    if (tempType != 'P' && tempType != 'T' && tempType != 'E' && tempType != 'S')
                        throw new TypeException(tempType);

                    Person tempP = new Person(tokens[0].charAt(0), tokens[1], tokens[2], tempAge);
                    people.add(tempP);
                } catch (TypeException e) {
                    System.out.println("Type exception: " + e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println("Number format exception: " + e.getMessage());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Not enough tokens in input file: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Oops...: " + e.getMessage());
                }
            }
            System.out.println("closing scanner");
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error opening the file: " + e.getMessage());
        }
        return people;

    }

    public static void example2() {
        // TASK 2: UPDATE MAX VALUE
        System.out.println("\nEXAMPLE 2: TEST MYHEAP ADD(MINIMUM)");
        boolean max = true;
        MyHeap<Person> people = new MyHeap<>(max);
        people = loadData(people);
        System.out.println("(2a) " + people);

        System.out.println("\nEXAMPLE2: TEST MYHEAP REMOVE(MINIMUM)");
        System.out.println("(2b) " + people.top() + " :: " + people.remove());
        System.out.println("(2c) " + people.top() + " :: " + people.remove());
        System.out.println("     " + people);

    }

    public static void example3() {
        System.out.println("\nEXAMPLE 3: TEST MYHEAPSORT");

        Integer[] list = { 2, 3, 2, 5, 6, 1, -2, 3, 14, 12 };
        //boolean max = true;
        MyHeapSort.heapSort(list);

        for (int i = 0; i < list.length; i++)
            System.out.print(list[i] + " ");
        System.out.println();
    }
}