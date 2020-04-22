package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Set<Integer> ofInteger = new Set<>(Integer[]::new, 10);

        ofInteger.Add(5);
        ofInteger.Add(124214);
        ofInteger.Add(9);
        ofInteger.Add(7);
        ofInteger.Add(5467);
        ofInteger.Add(345);
        ofInteger.Add(6);

        ofInteger.Print();

    }
}
