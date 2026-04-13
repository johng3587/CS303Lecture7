import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.mymethods.MyPriorityQueue;
import com.mymethods.HuffmanTree;

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

        System.out.println("\nEXAMPLE 1: TEST MY PRIORITY QUEUE USING PEOPLE ORDERING");

        MyPriorityQueue<Person> people = new MyPriorityQueue<>();
        loadData(people);

        System.out.println("\nEXAMPLE 1: PERSON LIST PRIORITY QUEUE LAST, FIRST");
        while (people.getSize() > 0)
            System.out.print(people.dequeue() + " ");

    }

    public static void loadData(MyPriorityQueue<Person> people) {
        try {
            Scanner scanner = new Scanner(new File("src\\test\\java\\people.txt"));

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
                    // people.add(tempP);
                    people.enqueue(tempP);
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

    }

    public static void example2() {

        System.out.println("\nEXAMPLE 2: LAMBDA FUNCTIONS: UPDATING A LIST");
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3));
        nums.replaceAll(x -> 3 * x);
        System.out.println(nums);

        System.out.println("\nEXAMPLE 2: LAMBDA FUNCTIONS: UPDATING THOSE WITH 40 <= AGE <= 55");
        MyPriorityQueue<Person> people = new MyPriorityQueue<>();
        loadData(people);
        people.updateAll(
                p -> p.getAge() >= 40 && p.getAge() <= 55,
                p -> {
                    p.setAge(p.getAge() + 25);
                    return p;
                });

        while (people.getSize() > 0)
            System.out.print(people.dequeue() + " ");

    }

    public static void example3() {
        System.out.println("\nEXAMPLE 3: TEST MY PRIORITY QUEUE USING PATIENT PRIORITY");

        Patient patient1 = new Patient("John", 2);
        Patient patient2 = new Patient("Jim", 1);
        Patient patient3 = new Patient("Tim", 4);
        Patient patient4 = new Patient("Cindy", 7);

        MyPriorityQueue<Patient> pQueue = new MyPriorityQueue<>();
        pQueue.enqueue(patient1);
        pQueue.enqueue(patient2);
        pQueue.enqueue(patient3);
        pQueue.enqueue(patient4);
        pQueue.enqueue(new Patient("Ralph", 8));

        // CHANGING priority for patient '3' from 1 to 99
        int patientsChecked = 0, patientsTotal = pQueue.getSize();

        // UPDATE PRIORITY - OPTION 1
        System.out.println("\nEXAMPLE 3: PRIORITY UPDATE OPTION 1");
        MyPriorityQueue<Patient> newQueue = new MyPriorityQueue<>();
        while (patientsChecked < patientsTotal) {
            Patient temp = pQueue.dequeue();
            System.out.println(temp);
            if (temp.getId() == 3) {
                temp.setPriority(1);
                System.out.println("\t -> " + temp);
            }
            newQueue.enqueue(temp);
            patientsChecked++;
        }
        pQueue = newQueue;

        // OPTION 3
        // chat gpt suggestion to update patient priority
        System.out.println("\nEXAMPLE 3: PRIORITY UPDATE OPTION 2");
        pQueue.update(
                p -> p.getId() == 4,
                p -> {
                    p.setPriority(100);
                    return p;
                });

        while (pQueue.getSize() > 0)
            System.out.print(pQueue.dequeue() + " ");

    }

    public static void example4() {
        System.out.println("\nEXAMPLE 4: TEST HUFFMAN");

        String[] encodeStrings = new String[] {
                "this is it",
                "happy hip hop",
                "this is an example for huffman encoding" };

        String[] decodeStrings = new String[] {
                "10111011000011111101000",
                "0111101011010111010",
                "101010101001011101101111110111010101010100010111100101100000111000110010100" };

        for (int i = 0; i < encodeStrings.length; i++) {
            String text = encodeStrings[i];
            System.out.printf("\n%3d %-2s %-30s\n", i + 1, ":", text);

            // get frequency count & create Huffman Tree
            int[] counts = HuffmanTree.getCharacterFrequency(text);
            HuffmanTree tree = HuffmanTree.getHuffmanTree(counts);

            System.out.println("   Encoded String: " + tree.encode(text));
            System.out.println("   Decoded String: " + decodeStrings[i] + " = " + tree.decode(decodeStrings[i]));

            System.out.printf("\n%-15s%-15s%-15s%-15s\n",
                    "ASCII Code", "Character", "Frequency", "Code");
            tree.printHuffmanTree(counts);
        }

    }

}