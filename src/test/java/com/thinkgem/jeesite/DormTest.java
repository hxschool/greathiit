package com.thinkgem.jeesite;

public class DormTest {

	public static void main(String[] args) {
		String[] buildDorm = { "1A", "1B", "2A", "2B", "3", "4", "5A", "5B" };
		for (String b : buildDorm) {
			for (int j = 1; j < 7; j++) {
				for (int i = 101; i < 137; i++) {

					String d = j + "" + String.valueOf(i).substring(1);
					System.out.println(b + "," + d + "," + j);
				}

			}

		}
	}

}
