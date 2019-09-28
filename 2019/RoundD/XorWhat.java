import java.util.*;

public class Solution {
    public static void main(String[] args) {
        System.out.println(isOddBitNumber(32));
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int order = 1; order <= t; order++) {
            int arrayLength = scanner.nextInt();
            int modificationNumber = scanner.nextInt();
            TreeSet<Integer> oddIndexSet = new TreeSet<>();
            for (int i = 0; i < arrayLength; i++) {
                int number = scanner.nextInt();
                if (isOddBitNumber(number)) {
                    oddIndexSet.add(i);
                }
            }

            System.out.print("Case #" + order + ":");
            for (int i = 0; i < modificationNumber; i++) {
                int index = scanner.nextInt();
                int number = scanner.nextInt();
                if (isOddBitNumber(number) && !oddIndexSet.contains(index)) {
                    oddIndexSet.add(index);
                } else if (!isOddBitNumber(number) && oddIndexSet.contains(index)) {
                    oddIndexSet.remove(index);
                }

                if ((oddIndexSet.size() & 1) == 1) {
                    int maxLength = oddIndexSet.last() > arrayLength - oddIndexSet.first() - 1
                            ? oddIndexSet.last() : arrayLength - oddIndexSet.first() - 1;
                    System.out.print(" " + maxLength);
                } else {
                    System.out.print(" " + arrayLength);
                }
            }
            System.out.println();
        }
    }

    private static boolean isOddBitNumber(int number) {
        int count = 0;
        while (number != 0) {
            number &= number - 1;
            count++;
        }
        return (count & 1) == 1;
    }
}
