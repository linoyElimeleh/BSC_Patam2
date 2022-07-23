package tests.test9_2021_a_a;

import java.util.HashMap;

public class BadCode {
	

	public static int common(int[] grades) {
		HashMap<Integer, Integer> map=new HashMap<Integer, Integer>();
		for(int g : grades) {
			if(!map.containsKey(g))
				map.put(g, 1);
			else
				map.put(g, map.get(g)+1);
		}
		int common=0;
		int count=0;
		for(int k : map.keySet())
			if(map.get(k)>count) {
				count=map.get(k);
				common=k;
			}
		return common;
	}

}
