import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {
    private static final int MAX = 1000000001;
    private static final int SQRT_MAX = 32000;
    public static void main(String[] args) {
        List<Integer> primeTable = createPrimeTable();
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int order = 1; order <= t; order++) {
            int left = scanner.nextInt();
            int right = scanner.nextInt();

            int count = countPrimes(left, right, primeTable);
            count += right / 2 - (left + 1) / 2 - right / 4 + (left + 3) / 4;
            if (right >= 2 && left <= 2) {
                count -= 1;
            }
            count += countPrimes((left + 3) / 4, right / 4, primeTable);
            System.out.println("Case #" + order + ": " + count);
        }
    }

    private static List<Integer> createPrimeTable() {
        List<Integer> primeTable = new ArrayList<>();
        boolean[] comTable = new boolean[SQRT_MAX];
        for (int i = 2; i * i < MAX; i++) {
            if (!comTable[i]) {
                primeTable.add(i);
            }
            for (int j = 0; j < primeTable.size() && primeTable.get(j) * i < SQRT_MAX; j++) {
                comTable[primeTable.get(j)*i] = true;
                if (i % primeTable.get(j) == 0) {
                    break;
                }
            }
        }
        return primeTable;
    }

    private static int countPrimes(int left, int right, List<Integer> primeTable) {
        int count = 0;
        boolean[] isPrime = new boolean[right-left+2];
        for (int i = 0; i < right - left + 2; i++) {
            isPrime[i] = true;
        }
        for (int j = 0; j < primeTable.size() && primeTable.get(j) * primeTable.get(j) <= right; j++) {
            int prime = primeTable.get(j);
            System.out.println(prime);
            int primeSquare = prime * prime;
            if (primeSquare < left) {
                primeSquare = (left + prime - 1) / prime * prime;
            }
            while (primeSquare <= right) {
                isPrime[primeSquare-left] = false;
                primeSquare += prime;
            }
        }
        for (int i = 0; left + i <= right; i++) {
            if (isPrime[i]) {
                count++;
            }
        }
        return count;
    }
}
