import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int order = 1; order <= t; order++) {
            int shiftNumber = scanner.nextInt();
            long happyValue = scanner.nextLong();
            long[] firstHappyArray = new long[shiftNumber];
            long[] secondHappyArray = new long[shiftNumber];
            for (int i = 0; i < shiftNumber; i++) {
                firstHappyArray[i] = scanner.nextLong();
            }
            for (int i = 0; i < shiftNumber; i++) {
                secondHappyArray[i] = scanner.nextLong();
            }
            long assignmentNumber = computeAssignmentNumber(firstHappyArray, secondHappyArray,happyValue);
            System.out.println("Case #" + order + ": " + assignmentNumber);
        }
    }

    private static long computeAssignmentNumber(long[] firstHappyArray, long[] secondHappyArray, long happyValue) {
        long upperLimit = 1 << firstHappyArray.length;
        long count = 0;
        for (int i = 0; i < upperLimit; i++) {
            int index = i;
            int position = 0;
            long tempHappySum = 0;
            boolean[] used = new boolean[firstHappyArray.length];
            while (index > 0) {
                if ((index & 1) == 1) {
                    tempHappySum += firstHappyArray[position];
                    used[position] = true;
                }
                position++;
                index >>= 1;
            }
            if (tempHappySum >= happyValue) {
                count += helper(secondHappyArray, used, happyValue);
            }
        }
        return count;
    }

    private static long helper(long[] secondHappyArray, boolean[] used, long happyValue) {
        long happySum = 0;
        List<Long> remainList = new ArrayList<>();
        for (int i = 0; i < secondHappyArray.length; i++) {
            happySum += used[i] ? 0 : secondHappyArray[i];
            if (used[i]) {
                remainList.add(secondHappyArray[i]);
            }
        }
        if (happyValue <= happySum) {
            return 1 << remainList.size();
        }
        long needHappyValue = happyValue - happySum;
        long upperLimit = 1 << remainList.size();
        long count = 0;
        for (int i = 0; i < upperLimit; i++) {
            int index = i;
            int position = 0;
            long tempHappySum = 0;
            while (index > 0) {
                if ((index & 1) == 1) {
                    tempHappySum += remainList.get(position);
                }
                position++;
                index >>= 1;
            }
            count += tempHappySum >= needHappyValue ? 1 : 0;
        }
        return count;
    }
}
