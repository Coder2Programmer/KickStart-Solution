import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int order = 1; order <= t; order++) {
            int employeeNumber = scanner.nextInt();
            int skillNumber = scanner.nextInt();
            Map<List, Integer> skillSetCount = new HashMap<>();
            for (int i = 0; i < employeeNumber; i++) {
                int currentSkillNumber = scanner.nextInt();
                List<Integer> skillArrayList = new ArrayList<>();
                for (int j = 0; j < currentSkillNumber; j++) {
                    skillArrayList.add(scanner.nextInt());
                }
                Collections.sort(skillArrayList);
                Integer currentSkillSetCount = skillSetCount.containsKey(skillArrayList)
                        ? skillSetCount.get(skillArrayList) + 1 : 1;
                skillSetCount.put(skillArrayList, currentSkillSetCount);
            }

            Map<List, Integer> subSetCounts = computeSubSetCount(skillSetCount);

            long mentorSum = 0;
            for (Map.Entry<List, Integer> entry : skillSetCount.entrySet()) {
                mentorSum += entry.getValue() * (employeeNumber - entry.getValue());
                mentorSum -= entry.getValue() * (subSetCounts.get(entry.getKey()) - entry.getValue());
            }

            System.out.println("Case #" + order + ": " + mentorSum);
        }
    }

    private static Map<List, Integer> computeSubSetCount(Map<List, Integer> skillSetCount) {
        Map<List, Integer> subArrayListCounts = new HashMap<>();
        for (Map.Entry<List, Integer> entry : skillSetCount.entrySet()) {
            List skillArray = entry.getKey();
            int subSetNumber = 1 << skillArray.size();
            for (int i = 1; i < subSetNumber; i++) {
                List<Integer> subArrayList = new ArrayList<>();
                int index = i;
                for (Object skill : skillArray) {
                    if ((index & 1) == 1) {
                        subArrayList.add((Integer) skill);
                    }
                    index >>= 1;
                }
                Integer subSetCount = subArrayListCounts.containsKey(subArrayList)
                        ? subArrayListCounts.get(subArrayList) + entry.getValue() : entry.getValue();
                subArrayListCounts.put(subArrayList, subSetCount);
            }
        }
        return subArrayListCounts;
    }
}
