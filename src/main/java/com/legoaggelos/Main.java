package com.legoaggelos;

import com.legoaggelos.objects.Car;
import com.legoaggelos.objects.RatedCar;
import com.legoaggelos.objects.rating.CarEvaluator;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void printList(List<?> list) {
        for(Object o : list) {
            System.out.println(o);
        }
    }
    public static void main(String[] args) {
        //file format is model;make;km;hpPerKilo;price;malfunctions;fuelEfficiency;releaseYear;hasAnyMajorDamages
        Scanner reader = new Scanner(System.in);
        System.out.println("File to read from: ");
        String path = reader.nextLine();
        CarEvaluator evaluator = null;
        try {
            evaluator = new CarEvaluator(path);
        } catch (IOException e) {
            System.out.println("File doesn't exist or is invalid!");
            e.printStackTrace();
            return;
        }
        System.out.println("Sorted by rating: ");
        printList(evaluator.getInOrderOfRating().reversed());
        System.out.println("Sorted by rating excluding power: ");
        printList(evaluator.getInOrderOfRatingExcludingPower().reversed());
        System.out.println();
        System.out.println("Sorted by fuel efficiency: ");
        printList(evaluator.getInOrderOfFuelEfficiency());
        System.out.println();
        System.out.println("Sorted by price: ");
        printList(evaluator.getInOrderOfPrice());
        System.out.println();
        System.out.println("Sorted by power: ");
        printList(evaluator.getInOrderOfPower());
        System.out.println();
        System.out.println("Sorted by mileage: ");
        printList(evaluator.getInOrderOfMileage());
        System.out.println();
        System.out.println("Best overall: ");
        System.out.println(evaluator.getHighestRating());
    }
}