package com.legoaggelos.objects.rating;

import com.legoaggelos.objects.Car;
import com.legoaggelos.objects.RatedCar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CarEvaluator {
    private List<RatedCar> cars;
    public static RatedCar parseLineToCar(String line) {
        var data = line.split(";");
        if (data.length != 9) {
            throw new LineParsingException("Invalid amount of data");
        }
        var car = new RatedCar(
                data[0],
                Car.Manufacturer.fromString(data[1]),
                Integer.parseInt(data[2]),
                Double.parseDouble(data[3]),
                Integer.parseInt(data[4]),
                Integer.parseInt(data[5]),
                Double.parseDouble(data[6]),
                Integer.parseInt(data[7]),
                Boolean.parseBoolean(data[8])
        );
        if (car.getMake()==null) {
            throw new LineParsingException("Invalid manufacturer");
        }
        return car;
    }
    private static List<RatedCar> readAllCarsFromFile(Path file) throws IOException, LineParsingException {
        List<String> lines = Files.readAllLines(file);
        List<RatedCar> cars = new ArrayList<>();
        for (String line : lines) {
            cars.add(parseLineToCar(line));
        }
        return cars;
    }
    public CarEvaluator(String filePath) throws IOException {
        cars = readAllCarsFromFile(Path.of(filePath));
    }
    public List<RatedCar> getCars() {
        return cars;
    }
    public List<RatedCar> getInOrderOfRating() {
        return cars.stream().sorted(Comparator.comparingDouble(RatedCar::getOverallRating)).toList();
    }
    public List<RatedCar> getInOrderOfRatingExcludingPower() {
        return cars.stream().sorted(Comparator.comparingDouble(RatedCar::calculateRatingExcludingPower)).toList();
    }
    public RatedCar getHighestRating() {
        return getInOrderOfRating().getLast();
    }
    public List<RatedCar> getInOrderOfFuelEfficiency() {
        return cars.stream().sorted(Comparator.comparingDouble(RatedCar::getlPer100km)).toList();
    }
    public List<RatedCar> getInOrderOfMileage() {
        return cars.stream().sorted(Comparator.comparingDouble(RatedCar::getMileage)).toList();
    }
    public List<RatedCar> getInOrderOfPower() {
        return cars.stream().sorted(Comparator.comparingDouble(RatedCar::getHorsePowerPerKilo)).toList().reversed();
    }
    public List<RatedCar> getInOrderOfPrice() {
        return cars.stream().sorted(Comparator.comparingDouble(RatedCar::getPriceInEuro)).toList();
    }
}
