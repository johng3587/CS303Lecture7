import com.mymethods.MyArrayDeque;

public class Functions {
    public static void testArrayDeque1(){
        MyArrayDeque<Integer> d = new MyArrayDeque<>();

        System.out.println("\nEmpty? " + d.isEmpty()); // true

         //TEST TASK 1:  Fill & resize
        for (int i = 1; i <= 6; i++) {
            d.addLast(i);
        }       
        d.addLast(20);
        d.addLast(30);
        System.out.println("\n" + d);

        System.out.println("getFirst (expect 1):     " + d.getFirst());
        System.out.println("removeFirst (expect 1):  " + d.removeFirst());

        System.out.println("\nTASK 1: " + d);

        System.out.println("Empty? " + d.isEmpty()); // true

        //TEST TASK 2: ADD FIRST
        System.out.println("\nTESTING TASK 2: ");
        for (int i = 1; i <= 10; i++) {
            d.addFirst(i*100);
        }  
        System.out.println("AFTER TASK 2: " + d);
        
        //TEST TASK 3: REMOVELAST
        System.out.println("\nTESTING TASK 3: ");
        System.out.println("removeLast  (expect 30):   " + d.removeLast());
        System.out.println("removeLast  (expect 20):   " + d.removeLast());
        System.out.println("                           " + d);
        System.out.println("removeFirst (expect 1000): " + d.removeFirst());
        System.out.println("removeLast  (expect 6):    " + d.removeLast());
        System.out.println("removeFirst (expect 900):  " + d.removeFirst());
        System.out.println("AFTER TASK 3:              " + d);

               
        //TEST TASK 4: PRIORITY QUEUE
        System.out.println("\nTESTING TASK 4a: ");
        MyArrayDeque<Integer> p1 = new MyArrayDeque<>();        
        p1.addPriority(60);
        p1.addPriority(50);
        p1.addPriority(90);
        p1.addPriority(80);   
        p1.addPriority(10); 
        p1.addPriority(-40);
        System.out.println("AFTER TASK 4a       " + p1);

        //TEST TASK 4: PRIORITY QUEUE
        System.out.println("\nTESTING TASK 4b: ");
        MyArrayDeque<Student> p2 = new MyArrayDeque<>();
        p2.addPriority(new Student(45, "Sam", "Yosemite", "Minor"));  
        p2.addPriority(new Student(21, "Suzuki", "Keiko", "Chemistry"));      
        p2.addPriority(new Student(15, "Smith", "Bob", "Chemistry"));
        p2.addPriority(new Student(16, "Smith", "Alex", "Music"));

        System.out.println("AFTER TASK 4b       " + p2);

                
        //TEST TASK 5: REMOVEITEM
        System.out.println("\nTESTING TASK 5: ");
        d.removeItem(600);
        System.out.println("removeItem(600):   " + d);

        d.addLast(300);
        d.addFirst(300);
        System.out.println("after adds:        " + d);

        d.removeItem(300);
        System.out.println("removeItem(300):   " + d); 

    }

}