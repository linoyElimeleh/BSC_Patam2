package tests.test5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q2 {


    public static int H(String a, String b) {
        char[] aChar = a.toCharArray();
        char[] bChar = b.toCharArray();
        int aLen = aChar.length;
        int bLen = bChar.length;
        int sum = 0;
        int size;

        if (aLen > bLen) {
            sum = aLen - bLen;
            size = bLen;
        } else if (aLen < bLen) {
            sum = bLen - aLen;
            size = aLen;
        } else {
            size = aLen;
        }

        for (int i = 0; i < size; i++) {
            if (aChar[i] != bChar[i])
                sum++;
        }


        return sum;
    }

    // inefficent code. re-implement.
    public static int findMinH(List<String> array) {
        int min = Integer.MAX_VALUE;
        int arrayLen = array.size();
        for (int i = 0; i < arrayLen - 1; i++) {
            String wordI = array.get(i);
            for (int j = i + 1; j < arrayLen; j++) {
                String wordJ = array.get(j);
                if (wordI != wordJ) {
                    int respond = H(wordI, wordJ);
                    if (min > respond)
                        min = respond;
                }
            }
        }
        return min;
    }
}
