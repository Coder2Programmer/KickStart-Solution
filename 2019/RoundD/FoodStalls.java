import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        PriorityQueue<Long> pq = new PriorityQueue<>((t1, t2) -> t1 < t2 ? 1 : -1);
        for (int order = 1; order <= t; order++) {
            int stallsNumber = scanner.nextInt();
            int spotsNumber = scanner.nextInt();
            Elem[] elemArray = new Elem[spotsNumber];
            for (int i = 0; i < spotsNumber; i++) {
                elemArray[i] = new Elem();
                elemArray[i].distance = scanner.nextInt();
            }
            for (int i = 0; i < spotsNumber; i++) {
                elemArray[i].cost = scanner.nextInt();
            }

            long[] leftRemainCost = new long[spotsNumber];
            long[] rightRemainCost = new long[spotsNumber];
            for (int i = 0; i < spotsNumber; i++) {
                leftRemainCost[i] = rightRemainCost[i] = Long.MAX_VALUE / 2;
            }
            Arrays.sort(elemArray, (t1, t2) -> t1.distance < t2.distance ? -1 : 1);
            int leftNumber = stallsNumber / 2;
            long costSum = 0;
            pq.clear();
            for (int i = 0; i < leftNumber; i++) {
                long currentCost = getCost(elemArray[i], elemArray[spotsNumber-1]);
                costSum += currentCost;
                pq.add(currentCost);
            }
            for (int i = leftNumber; i < spotsNumber; i++) {
                leftRemainCost[i] = costSum - leftNumber * (elemArray[spotsNumber-1].distance - elemArray[i].distance);
                if (!pq.isEmpty()) {
                    long currentMaxCost = pq.peek();
                    long currentCost = getCost(elemArray[i], elemArray[spotsNumber-1]);
                    if (currentMaxCost > currentCost) {
                        pq.poll();
                        costSum -= currentMaxCost -  currentCost;
                        pq.add(currentCost);
                    }
                }
            }

            pq.clear();
            costSum = 0;
            int rightNumber = stallsNumber - stallsNumber / 2;
            for (int i = 0; i < rightNumber; i++) {
                long currentCost = getCost(elemArray[spotsNumber-i-1], elemArray[0]);
                costSum += currentCost;
                pq.add(currentCost);
            }
            for (int i = spotsNumber - rightNumber - 1; i >= 0; i--) {
                rightRemainCost[i] = costSum - rightNumber * (elemArray[i].distance - elemArray[0].distance);
                if (!pq.isEmpty()) {
                    long currentMaxCost = pq.peek();
                    long currentCost = getCost(elemArray[i], elemArray[0]);
                    if (currentMaxCost > currentCost) {
                        pq.poll();
                        costSum -= currentMaxCost - currentCost;
                        pq.add(currentCost);
                    }
                }
            }

            long minCost = Long.MAX_VALUE;
            for (int i = 0; i < spotsNumber; i++) {
                minCost = Math.min(minCost, leftRemainCost[i] + rightRemainCost[i] + elemArray[i].cost);
            }
            System.out.println("Case #" + order + ": " + minCost);
        }
    }

    private static long getCost(Elem elemOne, Elem elemTwo) {
        return elemOne.cost + Math.abs(elemTwo.distance - elemOne.distance);
    }

    static class Elem {
        long distance;
        long cost;
        Elem() {
        }
    }
}
