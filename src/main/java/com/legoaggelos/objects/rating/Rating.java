package com.legoaggelos.objects.rating;

import com.legoaggelos.objects.Car;

public class Rating {
    public enum RatingType {
        RELIABILITY,
        MILEAGE,
        POWER,
        PRICE,
        FUNCTIONALITY,
        FUEL_EFFICIENCY,
        AGE,
        EXTERNAL_DAMAGE
    }
    private final RatingType thingRated;
    private final byte rating;

    public Rating(byte rating, RatingType thingRated) {
        if (rating > 10 || rating < 0) {
            throw new RatingOutOfBoundsException(rating+" rating out of bounds 0-10");
        }
        this.rating = rating;
        this.thingRated=thingRated;
    }

    public byte rating() {
        return rating;
    }

    public static Rating getRatingFromRatingTypeAndValue(RatingType type, Number value) throws IncorrectValueTypeForRatingException {
        if (type==RatingType.EXTERNAL_DAMAGE || type==RatingType.RELIABILITY) {
            throw new IncorrectValueTypeForRatingException("Number values cannot be used for external damage rating or reliability rating!");
        }
        switch(type) {
            case MILEAGE -> {
                return getRatingFromMileage(value.intValue());
            }
            case POWER -> {
                return getRatingFromPower(value.doubleValue());
            }
            case PRICE -> {
                return getRatingFromPrice(value.intValue());
            }
            case FUNCTIONALITY -> {
                return getRatingFromMalfunctions(value.intValue());
            }
            case FUEL_EFFICIENCY -> {
                return getRatingFromLPer100Km(value.doubleValue());
            }
            case AGE -> {
                return getRatingFromAge(value.intValue());
            }
            default -> throw new IncorrectValueTypeForRatingException("Number values cannot be used for external damage rating or reliability rating!");
        }
    }

    public static Rating getRatingFromRatingTypeAndValue(RatingType type, Boolean value) throws IncorrectValueTypeForRatingException {
        if (type!=RatingType.EXTERNAL_DAMAGE) {
            throw new IncorrectValueTypeForRatingException("Only external damage rating accepts booleans!");
        }
        byte rating = value ? (byte) 0 : 10;
        return new Rating(rating, type);
    }

    public static Rating getRatingFromRatingTypeAndValue(RatingType type, Car.Manufacturer value) throws IncorrectValueTypeForRatingException {
        if (type!=RatingType.RELIABILITY) {
            throw new IncorrectValueTypeForRatingException("Only reliability rating accepts manufacturer!");
        }
        byte rating = value.reliabilityRating.rating();
        return new Rating(rating, type);
    }

    private static Rating getRatingFromAge(int firstRegistrationYear) {
        //1995 or older=bad, 2005 or newer = good
        if (firstRegistrationYear <= 1995) {
            return new Rating((byte) 0, RatingType.AGE);
        } else if (firstRegistrationYear >= 2005) {
            return new Rating((byte) 10, RatingType.AGE);
        }
        return new Rating((byte) (firstRegistrationYear-1995), RatingType.AGE);//if it is 1996, it is 1, which is correct, and if it is 2004, it is 9, which is also correct
    }

    private static Rating getRatingFromLPer100Km(double lPer100Km) {
        //this is for the average of out of city/in city.
        if (lPer100Km>12) {
            return new Rating((byte) 0, RatingType.FUEL_EFFICIENCY);
        } else if (lPer100Km <= 2) {
            return new Rating((byte) 10, RatingType.FUEL_EFFICIENCY);
        }
        return new Rating((byte) Math.floor(12-lPer100Km), RatingType.FUEL_EFFICIENCY); //3 is 9, which is correct, and 11 is 1, which is also correct.
    }

    private static Rating getRatingFromMalfunctions(int numberOfMalfunctions) {
        if (numberOfMalfunctions==0) {
            return new Rating((byte) 10, RatingType.FUNCTIONALITY);
        }
        return new Rating((byte) Math.max(0, 10 - numberOfMalfunctions), RatingType.FUNCTIONALITY); //-1 for every malfunction, if it goes under 0, we set it to 0, we dotn want negative ratings.
    }

    private static Rating getRatingFromPrice(int priceInEuro) {
        if (priceInEuro > 5_000) {
            return new Rating((byte) 0, RatingType.PRICE);
        }
        return new Rating((byte) (10 - Math.floor(priceInEuro/500.0)), RatingType.PRICE); //if it is 4.999 it is 0, if it is 1500, it is 7, which is correct.
    }

    private static Rating getRatingFromPower(double horsePowerPerKilo) {
        //Average family car is about 0.1 hp/kg. So, let's say that is 5 points, every 0.02 under that is -1 point, every 0.02 over that is +1 point. That means, horsePowerPerKilo/0.02 is the rating.
        if (horsePowerPerKilo > 0.2) {
            return new Rating((byte)10, RatingType.POWER);
        }
        return new Rating((byte) Math.round(horsePowerPerKilo/0.02), RatingType.POWER);
    }

    private static Rating getRatingFromMileage(int kmTravelled) {
        if (kmTravelled > 400_000) {
            return new Rating((byte) 0, RatingType.MILEAGE); //more than 400'000km is way too many kms
        } else if (kmTravelled <= 50_000) {
            return new Rating((byte) 10, RatingType.MILEAGE); //less than 50'000 km is basically new
        }
        return new Rating((byte) Math.floor(10.0 - kmTravelled/40_000.0), RatingType.MILEAGE); // 399'999 is 10 - 9 so 1, which is bad, 80'000 is 10 - 2, so 8, which is good.
    }

    @Override
    public String toString() {
        return this.thingRated.toString() + " " + this.rating;
    }
}
