package com.legoaggelos.objects;

import com.legoaggelos.objects.rating.Rating;

import java.util.ArrayList;
import java.util.List;

public class RatedCar extends Car {
    private Rating reliabilityRating;
    private Rating mileageRating;
    private Rating powerRating;
    private Rating priceRating;
    private Rating functionalityRating;
    private Rating fuelEfficiencyRating;
    private Rating ageRating;
    private Rating damageRating;
    private List<Rating> ratings;
    private double overallRating;
    public RatedCar(String model, Manufacturer make, int mileage, double horsePowerPerKilo, int priceInEuro, int malfunctions, double lPer100km, int firstRegistrationYear, boolean hasMajorExternalDamages) {
        super(model, make, mileage, horsePowerPerKilo, priceInEuro, malfunctions, lPer100km, firstRegistrationYear, hasMajorExternalDamages);
        reliabilityRating = Rating.getRatingFromRatingTypeAndValue(Rating.RatingType.RELIABILITY, make);
        mileageRating = Rating.getRatingFromRatingTypeAndValue(Rating.RatingType.MILEAGE, mileage);
        powerRating = Rating.getRatingFromRatingTypeAndValue(Rating.RatingType.POWER, horsePowerPerKilo);
        priceRating = Rating.getRatingFromRatingTypeAndValue(Rating.RatingType.PRICE, priceInEuro);
        functionalityRating = Rating.getRatingFromRatingTypeAndValue(Rating.RatingType.FUNCTIONALITY, malfunctions);
        fuelEfficiencyRating = Rating.getRatingFromRatingTypeAndValue(Rating.RatingType.FUEL_EFFICIENCY, lPer100km);
        ageRating = Rating.getRatingFromRatingTypeAndValue(Rating.RatingType.AGE, firstRegistrationYear);
        damageRating = Rating.getRatingFromRatingTypeAndValue(Rating.RatingType.EXTERNAL_DAMAGE, hasMajorExternalDamages);
        ratings = new ArrayList<>(List.of(reliabilityRating, mileageRating, powerRating, priceRating, functionalityRating, fuelEfficiencyRating, ageRating, damageRating));
        overallRating = calculateOverallRating();
    }
    private double calculateOverallRating() {
        var stream = ratings.stream().map(Rating::rating);
        return (double) stream.reduce((byte) 0, (a, b) -> (byte) (a + b))/ratings.size();
    }

    public double getOverallRating() {
        return overallRating;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(getMake().toString().toLowerCase()).append(" ").append(getModel()).append("\n");
        sb.append(reliabilityRating);
        sb.append(", ").append(mileageRating);
        sb.append(", ").append(powerRating);
        sb.append(", ").append(priceRating);
        sb.append(", ").append(functionalityRating);
        sb.append(", ").append(fuelEfficiencyRating);
        sb.append(", ").append(ageRating);
        sb.append(", ").append(damageRating);
        sb.append("\noverallRating = ").append(overallRating);
        return sb.toString();
    }
}
