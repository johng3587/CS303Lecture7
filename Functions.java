import java.util.Iterator;

public class Functions {
 
   public static void testMyList(){

        // Create a list for strings
        MyList<String> list = new MyArrayList<String>();

        // Add elements to the list
        list.add("America"); // Add it to the list
        System.out.println("(1) " + list);

        list.add(0, "Canada"); // Add it to the beginning of the list
        System.out.println("(2) " + list);

        list.add("Russia"); // Add it to the end of the list
        System.out.println("(3) " + list);

        list.add("France"); // Add it to the end of the list
        System.out.println("(4) " + list);

        list.add(2, "Germany"); // Add it to the list at index 2
        System.out.println("(5) " + list);

        list.add(5, "Norway"); // Add it to the list at index 5
        System.out.println("(6) " + list);

        list.add(0, "Poland"); // Same as list.addFirst("Poland")
        System.out.println("(7) " + list);

        // Remove elements from the list
        list.remove(0); // Same as list.remove("Germany") in this case
        System.out.println("(8) " + list);

        list.remove(2); // Remove the element at index 2
        System.out.println("(9) " + list);

        list.remove(list.size() - 1); // Remove the last element
        System.out.print("(10) " + list + "\n(11) ");
        
        //add steps to test iterator
        System.out.print("(11) Print using iterator:  {");
        Iterator<String> it1 = list.iterator();
        while (it1.hasNext()){ 
            System.out.print(it1.next() + " ");
        }
        System.out.println("}");

        System.out.println("(12a) contains Japan:  " + list.contains("Japan"));

        System.out.println("(13a) Index of Japan:  " + list.indexOf("Japan"));   
        System.out.println("(13b) Index of France: " + list.indexOf("France"));

        System.out.println("(14a) Value at 0:      " + list.get(0));   
        System.out.println("(14b) Value at 3:      " + list.get(3)); 
        //System.out.println("(14c) Value at 18:     " + list.get(18));   
        System.out.println("(14d) Value at size:   " + list.get(list.size() - 1));   

        list.set(3, "Canada");
        list.set(0, "Finland");
        System.out.println("(15) " + list); 

        System.out.println("(16a) last index of Canada: " + list.lastIndexOf("Canada"));       
        System.out.println("(16b) last index of Norway: " + list.lastIndexOf("Norway"));      

        System.out.println("(17) " + list);  

        MyList<String> list2 = new MyArrayList<String>();
        list2.add("America");
        list2.add("France");
        list2.add("South Korea");
        list2.add("Canada");
        list2.add("Mexico");
        list.addAll(list2);
        System.out.println("(20) List adding List2:          " + list);      

        MyList<String> list3 = new MyArrayList<String>();
        list3.add("America");
        list3.add("France");
        list3.add("South Korea");
        System.out.println("(18) List contains all of list3: " + list.containsAll(list3));

        list3.add("Portugal");
        System.out.println("(19) List contains all of list3: " + list.containsAll(list3));

        MyList<String> list4 = new MyArrayList<String>();
        list4.add("Canada");
        list4.add("America");
        list.removeAll(list4);
        System.out.println("(20) List removing all of list4: " + list );

        list.addAll(list4);
        Object[] list5 = list.toArray();

        System.out.print("(20) List5 using toArray:        ");
        for (Object o : list5)
            System.out.print(o + " ");

        list.clear();
        System.out.println("\nAfter clearing the list, the list size is " + list.size());
    }
}
