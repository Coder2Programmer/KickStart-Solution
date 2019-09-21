import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int order = 1; order <= t; order++) {
            int days = scanner.nextInt();
            int slots = scanner.nextInt();
            List<Pair> codeEatPairs = new ArrayList<>(slots);
            for (int i = 0; i < slots; i++) {
                int code = scanner.nextInt();
                int eat = scanner.nextInt();
                codeEatPairs.add(new Pair(code, eat));
            }
            Collections.sort(codeEatPairs, new Comparator<Pair>() {
                @Override
                public int compare(Pair t1, Pair t2) {
                    return  t1.getValue() * t2.getKey() - t1.getKey() * t2.getValue();
                }
            });

            long[] codeSum = new long[slots+1];
            long[] eatSum = new long[slots+2];
            for (int i = 0; i < slots; i++) {
                codeSum[i+1] = codeSum[i] + codeEatPairs.get(i).getKey();
                eatSum[slots-i] = eatSum[slots-i+1] + codeEatPairs.get(slots-i-1).getValue();
            }

            StringBuilder stringBuilder = new StringBuilder("Case #" + order + ": ");
            for (int i = 0; i < days; i++) {
                int targetCode = scanner.nextInt();
                int targetEat = scanner.nextInt();
                if (codeSum[slots] < targetCode) {
                    stringBuilder.append('N');
                    continue;
                }
                int index = binarySearch(codeSum, 1, codeSum.length, targetCode);
                double remainsEat = ((double)(codeSum[index] - targetCode) / codeEatPairs.get(index-1).getKey()) * codeEatPairs.get(index-1).getValue();
                if (remainsEat + eatSum[index+1] >= targetEat) {
                    stringBuilder.append('Y');
                } else {
                    stringBuilder.append('N');
                }
            }
            System.out.println(stringBuilder.toString());
        }
    }

    static class Pair {
        private int key;
        private int value;

        Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }
    }

    private static int binarySearch(long[] array, int fromIndex, int toIndex, int key) {
        int low = fromIndex;
        int high = toIndex;
        while (low < high) {
            int mid = low + high >>> 1;
            if (array[mid] < key) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
