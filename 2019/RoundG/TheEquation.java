import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        long[] twoPowerTable = createTwoPowerTable();
        for (int order = 1; order <= t; order++) {
            int lawNumber = scanner.nextInt();
            long limit = scanner.nextLong();

            int[] onesCountArray = new int[64];
            for (int i = 0; i < lawNumber; i++) {
                long law = scanner.nextLong();
                updateBitArray(onesCountArray, law);
            }

            long[] minSum = new long[64];
            minSum[0] = Math.min(onesCountArray[0], lawNumber - onesCountArray[0]);
            for (int i = 1; i < 64; i++) {
                minSum[i] = minSum[i - 1] + Math.min(onesCountArray[i], lawNumber - onesCountArray[i]) * twoPowerTable[i];
            }

            long maxValue = -1;
            if (minSum[63] <= limit) {
                maxValue = 0;
                for (int i = 52; i >= 1; i--) {
                    long tempValue = (lawNumber - onesCountArray[i]) * twoPowerTable[i];
                    if (limit - tempValue >= minSum[i-1]) {
                        limit -= tempValue;
                        maxValue += twoPowerTable[i];
                        continue;
                    }
                    limit -= onesCountArray[i] * twoPowerTable[i];
                }
                long tempValue = lawNumber - onesCountArray[0];
                if (limit - tempValue >= 0) {
                    maxValue += 1;
                }
            }
            System.out.println("Case #" + order + ": " + maxValue);
        }
    }

    private static void updateBitArray(int[] bitArray, long law) {
        int position = 0;
        while (law > 0) {
            if ((law & 1) == 1) {
                bitArray[position]++;
            }
            position++;
            law >>>= 1;
        }
    }

    private static long[] createTwoPowerTable() {
        long[] twoPowerTable = new long[64];
        twoPowerTable[0] = 1;
        for (int i = 1; i < 63; i++) {
            twoPowerTable[i] = twoPowerTable[i-1] * 2;
        }
        return twoPowerTable;
    }
}
