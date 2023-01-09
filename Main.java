import java.util.Scanner;

public class Main {
    public static Scanner input = new Scanner(System.in);

    public static Node<Integer> arrToList(int[] arr) {  // ex1-1
        Node<Integer> p = new Node<Integer>(arr[0]);
        Node<Integer> t = p;
        for (int i = 1; i < arr.length; i++) {
            p.setNext(new Node<Integer>(arr[i]));
            p  = p.getNext();
        }
        return t;
    }

    public static Node<Integer> inputlist() {  //ex1-3
        Node<Integer> p = new Node<Integer>(input.nextInt());
        Node<Integer> t = p;
        while (true) {
            int num = input.nextInt();
            if (num == -1) {
                break;
            }
            p.setNext(new Node<Integer>(num));
            p  = p.getNext();
        }
        return t;
    }

    public static void printEven(Node<Integer> p) {  // ex1-4
        while(p != null) {
            if (p.getValue() % 2 == 0) {
                System.out.println(p.getValue());
            }
            p = p.getNext();
        }
    }

    public static boolean findNum(Node<Integer> p, int num, boolean hasFound) {  // ex1-5
        if (p.getValue() == num) return true;
        if (p.getNext() == null) return false;
        return findNum(p.getNext(), num, false);
    }

    public static Node<Integer> removeNum(Node<Integer> p, int num) {  //ex1-6
        if (!findNum(p, num, false)) return null;
        if (p.getValue() == num) return p.getNext();
        Node<Integer> t = p;
        while(p.getNext() != null && p.getNext().getNext() != null) {
            if (p.getNext().getValue() == num) {
                p.setNext(p.getNext().getNext());
            }
            p = p.getNext();
        }
        if (p.getNext() != null && p.getNext().getValue() == num) {
            p.setNext(null);
        }
        return t;
    }

    public static boolean isAllInList(Node<Integer> L1, Node<Integer> L2) {  // ex1-7
        if(L1 == null) return true;
        Node<Integer> t2 = L2;
        boolean isFound = false;
        while(L2 != null && !isFound) {
            if (L1.getValue() == L2.getValue()) {
                isFound = true;
            }
            L2 = L2.getNext();
        }
        if (!isFound) return false;
        return isAllInList(L1.getNext(), t2);
    }

    public static boolean printInBoth(Node<Integer> L1, Node<Integer> L2) {  // ex1-8
        if(L1 == null) return true;
        Node<Integer> t2 = L2;
        boolean isFound = false;
        while(L2 != null && !isFound) {
            if (L1.getValue() == L2.getValue()) {
                System.out.println(L1.getValue());
                isFound = true;
            }
            L2 = L2.getNext();
        }
        return printInBoth(L1.getNext(), t2);
    }

    public static Node<Integer> listWithBoth(Node<Integer> L1, Node<Integer> L2) {  //ex1-9
        if(L1 == null) return null;
        Node<Integer> t1 = new Node<Integer>(0);
        Node<Integer> t2 = L2;
        boolean isFound = false;
        while(L2 != null && !isFound) {
            if (L1.getValue() == L2.getValue()) {
                isFound = true;
                t1.setValue(L1.getValue());
                t1.setNext(listWithBoth(L1.getNext(), t2));
            }
            L2 = L2.getNext();
        }
        if (!isFound) {
            return listWithBoth(L1.getNext(), t2);
        }
        return t1;
    }

    public static Node<Integer> removeInBoth(Node<Integer> p1, Node<Integer> p2) {  //ex1-10
        Node<Integer> finalP = p1;
        while(p1 != null) {
            int num = p1.getValue();
            if (findNum(p2, num, false)) {
                finalP = removeNum(finalP, num);
            }
            p1 = p1.getNext();
        }
        return finalP;
    }

    public static Node<Integer> mergeList(Node<Integer> p1, Node<Integer> p2) {  //ex2-1
        Node<Integer> finalP;
        if (p2.getValue() < p1.getValue()) {
            finalP = new Node<Integer>(p2.getValue());
            p2 = p2.getNext();
        } else {
            finalP = new Node<Integer>(p1.getValue());
            p1 = p1.getNext();
        }
        Node<Integer> returnP = finalP;
        while(p1 != null || p2 != null) {
            if (p1 == null || p2.getValue() < p1.getValue()) {
                finalP.setNext(new Node<Integer>(p2.getValue()));
                finalP = finalP.getNext();
                p2 = p2.getNext();
            } else {
                finalP.setNext(new Node<Integer>(p1.getValue()));
                finalP = finalP.getNext();
                p1 = p1.getNext();
            }
        }
        return returnP;
    }

    public static Node<Integer> selectionSort(Node<Integer> p1) {  // ex2-2
        Node<Integer> finalP = p1;
        while(p1 != null) {
            Node<Integer> p2 = p1;
            int min = Integer.MAX_VALUE;
            Node<Integer> minLoc = p2;
            while(p2 != null) {
                if (p2.getValue() < min) {
                    min = p2.getValue();
                    minLoc = p2;
                }
                p2 = p2.getNext();
            }
            minLoc.setValue(p1.getValue());
            p1.setValue(min);
            p1 = p1.getNext();
        }
        return finalP;
    }

    public static int sumDistance(Node<Integer> p1, int value) {  // ex2-3
        if (!findNum(p1, value, 8)) {
            return -1;
        }
        int length = p1.len();
        int sum = 0, index = 0;
        while(p1 != null) {
            if (p1.getValue() == value) {
                sum += Math.min(Math.abs(index), Math.abs(length - index - 1));
            }
            index++;
            p1 = p1.getNext();
        }
        return sum;
    }

    public static void printNewExe(String str){
        System.out.println("\n**********************************************\n" + str);
    }

    public static void main(String[] args) {
        printNewExe("Initializing lists:");
        int[] arr =  {1, 2, 3, 4, 5, 6, 7};
        int[] arr2 =  {0, 1, 2, 8, 9};
        Node<Integer> p1 = arrToList(arr);
        System.out.println(p1);
        Node<Integer>  p2 = arrToList(arr2);
        System.out.println(p2);

        printNewExe("Printing even numbers only in p2:");
        printEven(p2);

        printNewExe("Finding nums in p2:");
        System.out.println("2: " + findNum(p2, 2, false));
        System.out.println("5: " + findNum(p2, 5, false));
        System.out.println("100: " + findNum(p2, 1000, false));

        printNewExe("Removing 8 from p2:");
        System.out.println(removeNum(p2, 8));

        printNewExe("Is p1 in p2");
        System.out.println(isAllInList(p1, p2));

        printNewExe("Printing values in both lists:");
        printInBoth(p1, p2);

        printNewExe("list with values only in both lists:");
        System.out.println(listWithBoth(p1, p2));

        printNewExe("remove from p1 values in both lists:");
        System.out.println(p1);
        System.out.println(removeInBoth(p1, p2));

        printNewExe("Merged list:");
        System.out.println(mergeList(p1, p2));
        int[] arr3 = {9, 2, 8, 3, 8, 5};
        Node<Integer> p3 = arrToList(arr3);

        printNewExe("New p3:");
        System.out.println(p3);

        printNewExe("Sum distances on p3 from 8:");
        System.out.println(sumDistance(p3, 8));

        printNewExe("Sort p3:");
        System.out.println(selectionSort(p3));
    }
}
