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
        if (!findNum(p1, value, false)) {
            return -1;
        }
        int length = p1.len();
        int sum = -1, index = 0;
        while(p1 != null && findNum(p1, value, false)) {
            if (p1.getValue() == value) {
                if (sum == -1) {
                    sum = index;
                }
                if (p1.hasNext() && !findNum(p1.getNext(), value, false)) {
                    sum += length - index - 1;
                }
            }
            index++;
            p1 = p1.getNext();
        }
        return sum;
    }

    public static boolean areAllDif(Node<Integer> p1) {  // ex2-4
        while(p1.hasNext()) {
            if (findNum(p1.getNext(), p1.getValue(), false)) {
                return false;
            }
            p1 = p1.getNext();
        }
        return true;
    }

    public static Node<Integer> listAllUnique(Node<Integer> p1) {  // ex2-5
        Node<Integer> uniqueList = null;
        Node<Integer> returnList = null;
        while(p1 != null) {
            if (!p1.hasNext() || !findNum(p1.getNext(), p1.getValue(), false)) {
                if (uniqueList == null) {
                    uniqueList = new Node<Integer>(p1.getValue());
                    returnList = uniqueList;
                } else {
                    uniqueList.setNext(new Node<Integer>(p1.getValue()));
                    uniqueList = uniqueList.getNext();
                }
            }
            p1 = p1.getNext();
        }
        return returnList;
    }

    public static int maxSeriesLength(Node<Integer> p1) {  // ex2-6
        int maxSeriesLen = 0;
        int currentSeries = 1;
        while(p1 != null) {
            if (p1.hasNext() && p1.getValue() <= p1.getNext().getValue()) {
                currentSeries++;
            } else {
                if (currentSeries > maxSeriesLen) {
                    maxSeriesLen = currentSeries;
                }
                currentSeries = 1;
            }
            p1 = p1.getNext();
        }
        return maxSeriesLen;
    }

    public static void printMaxSeriesInList(Node<Integer> p1) {  // ex2-7
        int maxSeriesLen = 0;
        Node<Integer> longestSeries = new Node<Integer>(p1.getValue());
        int currentSeriesLen = 1;
        Node<Integer> currentSeries = new Node<Integer>(p1.getValue());
        Node<Integer> currentSeriesFirst = currentSeries;
        while(p1 != null) {
            if (p1.hasNext() && p1.getValue() <= p1.getNext().getValue()) {
                currentSeriesLen++;
                currentSeries.setNext(new Node<Integer>(p1.getNext().getValue()));
                currentSeries = currentSeries.getNext();
            } else {
                if (currentSeriesLen > maxSeriesLen) {
                    maxSeriesLen = currentSeriesLen;
                    longestSeries = currentSeriesFirst;
                }
                if (p1.hasNext()) {
                    currentSeries = new Node<Integer>(p1.getNext().getValue());
                    currentSeriesFirst = currentSeries;
                    currentSeriesLen = 1;
                }
            }
            p1 = p1.getNext();
        }
        System.out.println(longestSeries);
    }

    public static boolean isFromReputations(Node<Integer> p1, Node<Integer> p2) {  // ex2-8
        Node<Integer> firstOfP1 = p1;
        boolean isFullRepeatation = false;
        while (p2 != null) {
            isFullRepeatation = false;
            if (p1.getValue() != p2.getValue()) {
                return false;
            }
            if (!p1.hasNext()) {
                isFullRepeatation = true;
                p1 = firstOfP1;
            } else {
                p1 = p1.getNext();
            }
            p2 = p2.getNext();
        }
        return isFullRepeatation;
    }

    public static boolean isContaining(Node<Integer> p1, Node<Integer> p2) { //ex2-9
        Node<Integer> firstOfP1 = p1;
        while (p2 != null) {
            if (p1.getValue() != p2.getValue()) {
                p1 = firstOfP1;
            } else if (!p1.hasNext()) {
                return true;
            } else {
                p1 = p1.getNext();
            }
            p2 = p2.getNext();
        }
        return false;
    }

    public static boolean isContainingAllOneDigits(Node<Integer> p1) {  //ex2-10
        int[] digits = new int[10];
        while(p1 != null) {
            if (p1.getValue() >= 0 && p1.getValue() < 10) {
                digits[p1.getValue()]++;
            }
            p1 = p1.getNext();
        }
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] == 0) {
                return false;
            }
        }
        return true;
    }
    
    public static void printNewExe(String str){
        System.out.println("\n**********************************************\n" + str);
    }

    public static void main(String[] args) {
        printNewExe("Initializing lists:");
        Node<Integer> p1 = arrToList(new int[]{1, 2, 3, 4, 5, 6, 7});
        System.out.println(p1);
        Node<Integer>  p2 = arrToList(new int[]{0, 1, 2, 8, 9});
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
        Node<Integer> p3 = arrToList(new int[]{9, 2, 8, 3, 8, 5});

        printNewExe("New p3:");
        System.out.println(p3);

        printNewExe("Sum distances on p3 from 8:");
        System.out.println(sumDistance(p3, 8));

        printNewExe("Sort p3:");
        System.out.println(selectionSort(p3));

        printNewExe("Are all p2 and p3 values different:");
        System.out.println(areAllDif(p2));
        System.out.println(areAllDif(p3));

        Node<Integer> p4 = arrToList(new int[]{1, 2, 3, 3, 2, 1, 5, 6, 7, 8, 10});

        printNewExe("List of p4 values with only different values:");
        System.out.println(p4);
        System.out.println(listAllUnique(p4));

        printNewExe("longest growing series length in p4:");
        p4 = arrToList(new int[]{1, 2, 3, 3, 2, 1, 5, 6, 7, 8, 10});
        System.out.println(p4);
        System.out.println(maxSeriesLength(p4));

        printNewExe("longest growing series length in p4:");
        p4 = arrToList(new int[]{1, 2, 3, 3, 2, 1, 5, 6, 7, 8, 10});
        System.out.println(p4);
        printMaxSeriesInList(p4);

        Node<Integer> p5 = arrToList(new int[]{1, 2, 3, 1, 2, 3, 1, 2, 3,});
        Node<Integer> p6 = arrToList(new int[]{1, 2, 3});

        printNewExe("Is p5 repeatations of p6?");
        System.out.println(p5);
        System.out.println(p6);
        System.out.println(isFromReputations(p6, p5));

        printNewExe("Is p5 contaning fully p6?");
        p5 = arrToList(new int[]{1, 2, 3, 4, 5});
        p6 = arrToList(new int[]{2, 3, 4});
        System.out.println(p5);
        System.out.println(p6);
        System.out.println(isContaining(p6, p5));

        printNewExe("Is p4 containing all one-digits numbers:");
        System.out.println(p4);
        System.out.println(isContainingAllOneDigits(p4));
        Node<Integer> p7 = arrToList(new int[]{1, 0, 2, 9, 3, 8, 4, 7, 5, 6});
        System.out.println(p7);
        System.out.println(isContainingAllOneDigits(p7));
    }
}
