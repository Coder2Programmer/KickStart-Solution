import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int order = 1; order <= t; order++) {
            int sectionNumber = scanner.nextInt();
            int differenceLimit = scanner.nextInt();
            int[] sectionArray = new int[sectionNumber + 1];
            for (int i = 1; i <= sectionNumber; i++) {
                sectionArray[i] = scanner.nextInt();
            }

            int[][] needRemovals = new int[sectionNumber + 1][sectionNumber + 1];
            for (int i = 1; i <= sectionNumber; i++) {
                Map<Integer, Integer> heightCountMap = new HashMap<>();
                int maxSectionCount = 0;
                for (int j = i; j <= sectionNumber; j++) {
                    int sectionCount = heightCountMap.containsKey(sectionArray[j])
                            ? heightCountMap.get(sectionArray[j]) + 1 : 1;
                    heightCountMap.put(sectionArray[j], sectionCount);
                    maxSectionCount = maxSectionCount > sectionCount ? maxSectionCount : sectionCount;
                    needRemovals[i][j] = needRemovals[j][i] = j - i + 1 - maxSectionCount;
                }
            }

            int[][] dp = new int[sectionNumber + 1][differenceLimit + 1];
            for (int i = 1; i <= sectionNumber; i++) {
                dp[i][0] = needRemovals[1][i];
            }
            for (int i = 2; i <= sectionNumber; i++) {
                for (int j = 1; j <= differenceLimit; j++) {
                    dp[i][j] = needRemovals[1][i];
                    for (int k = 1; k <= i - 1; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[k][j-1] + needRemovals[k+1][i]);
                    }
                }
            }
            System.out.println("Case #" + order + ": " + dp[sectionNumber][differenceLimit]);
        }
    }
}
