public class Main {
    public static int biggestMat(int[][] m, int n) {
        int maxSum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maxSum += m[i][j];
            }
        }
        for (int i = 0; i < m.length - n; i++) {
            for (int j = 0; j < m[0].length - n; j++) {
                int sum = 0;
                for (int x = i; x < i + n; x++) {
                    for (int y = j; y < j + n; y++) {
                        sum += m[x][y];
                    }
                }
                if (sum > maxSum) {
                    maxSum = sum;
                }
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        Hero h1 = new Hero("Ofek", 10, true);
        Hero h2 = new Hero("Ido", 5, false);
        Hero h3 = new Hero("Tahel", 7, true);
        System.out.println(h1);
        System.out.println(h2);
        System.out.println(h3);
        Bunch b1 = new Bunch("Bunch1", 5);
        System.out.println(b1.canAddHero(h1));
        b1.addHero(h1);
        System.out.println(b1.canAddHero(h2));
        b1.addHero(h2);
        System.out.println(b1.canAddHero(h3));
        b1.addHero(h3);
        System.out.println(b1);
        System.out.println(b1.stronger());

        int[][] mat = {
                {2, 4, 1, 3, 5},
                {1, 6, 9, 7, 3},
                {7, 5, 8, 8, 2},
                {3, 8, 4, 5, 1},
                {5, 2, 9, 3, 4},
                {4, 1, 5, 2, 5}
        };

        System.out.println(biggestMat(mat, 3));
    }
}