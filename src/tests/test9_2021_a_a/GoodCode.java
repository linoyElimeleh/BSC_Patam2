package tests.test9_2021_a_a;

import java.util.HashMap;

public class GoodCode {

    // fix this bad code
    public static int common(int[] grades) {
        int map[] = new int[101];
        for (int g : grades) {
            map[g] += 1;
        }
        int common = 0, count = 0;
        for (int i = 0; i < map.length; i++)
            if (count < map[i]) {
                common = i;
                count = map[i];
            }
        return common;
    }
}
