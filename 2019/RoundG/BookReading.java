import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int order = 1; order <= t; order++) {
            int pageNumber = scanner.nextInt();
            int tornNumber = scanner.nextInt();
            int readerNumber = scanner.nextInt();
            Set<Integer> tornSet = new HashSet<>();
            for (int i = 0; i < tornNumber; i++) {
                tornSet.add(scanner.nextInt());
            }

            int[] remainPagesNumber = new int[pageNumber + 1];
            for (int i = 1; i <= pageNumber; i++) {
                for (int j = 1; i * j <= pageNumber; j++) {
                    remainPagesNumber[i] += tornSet.contains(i * j) ? 0 : 1;
                }
            }

            long readPageSum = 0;
            for (int i = 0; i < readerNumber; i++) {
                readPageSum += remainPagesNumber[scanner.nextInt()];
            }
            System.out.println("Case #" + order + ": " + readPageSum);
        }
    }
}
