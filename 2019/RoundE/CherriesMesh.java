import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int order = 1; order <= t; order++) {
            int n = scanner.nextInt();
            UnionFindSet unionFindSet = new UnionFindSet(n);
            int m = scanner.nextInt();
            int minSugar = 0;
            for (int i = 0; i < m; i++) {
                int firstNumber = scanner.nextInt();
                int secondNumber = scanner.nextInt();
                if (unionFindSet.unionSet(firstNumber, secondNumber)) {
                    minSugar++;
                }
            }
            System.out.println("Case #" + order + ": " + (2 * n - 2 - minSugar));
        }
    }

    static class UnionFindSet {
        private int[] setArray;
        private int[] rankArray;

        UnionFindSet(int size) {
            setArray = new int[size+1];
            rankArray = new int[size+1];
            for (int i = 1; i < size + 1; i++) {
                setArray[i] = i;
            }
        }

        public int find(int number) {
            if (setArray[number] != number) {
                setArray[number] = find(setArray[number]);
            }
            return setArray[number];
        }

        public boolean unionSet(int firstNumber, int secondNumber) {
            int firstRoot = find(firstNumber);
            int secondRoot = find(secondNumber);
            if (firstRoot == secondRoot) {
                return false;
            }
            if (rankArray[firstRoot] < rankArray[secondRoot]) {
                setArray[firstRoot] = secondRoot;
            } else if (rankArray[firstRoot] > rankArray[secondRoot]) {
                setArray[secondRoot] = firstRoot;
            } else {
                setArray[firstRoot] = secondRoot;
                rankArray[secondRoot]++;
            }
            return true;
        }
    }
}
