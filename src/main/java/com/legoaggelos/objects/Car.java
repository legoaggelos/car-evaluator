package com.legoaggelos.objects;
import com.legoaggelos.objects.rating.Rating;

public class Car {
    public static enum Manufacturer {
        TOYOTA(10),
        HONDA(10),
        ACURA(10),
        LEXUS(10),
        MAZDA(9),
        FIAT(7),
        MERCEDES(8),
        OPEL(8),
        VW(8),
        BMW(6),
        FORD(4),
        LAMBORGHINI(2),
        PORSCHE(2),
        RENAULT(6),
        PEUGEOT(4);
        public final Rating reliabilityRating;
        Manufacturer(int i) {
            reliabilityRating = new Rating((byte) i, Rating.RatingType.RELIABILITY);
        }
        private Rating getReliabilityRating() {
            return reliabilityRating;
        }
        public static Manufacturer fromString(String str) {
            for (Manufacturer manu : Manufacturer.values()) {
                if (manu.toString().equalsIgnoreCase(str)) {
                    return manu;
                }
            }
            return null;
        }
    }
    private final String model;
    private final Manufacturer make;
    private final int mileage;
    private final double horsePowerPerKilo;
    private final int priceInEuro;
    private final int malfunctions;
    private final double lPer100km;
    private final int firstRegistrationYear;
    private final boolean hasMajorExternalDamages;

    public Car(String model, Manufacturer make, int mileage, double horsePowerPerKilo, int priceInEuro, int malfunctions, double lPer100km, int firstRegistrationYear, boolean hasMajorExternalDamages) {
        this.model = model;
        this.make = make;
        this.mileage = mileage;
        this.horsePowerPerKilo = horsePowerPerKilo;
        this.priceInEuro = priceInEuro;
        this.malfunctions = malfunctions;
        this.lPer100km = lPer100km;
        this.firstRegistrationYear = firstRegistrationYear;
        this.hasMajorExternalDamages = hasMajorExternalDamages;
    }

    public String getModel() {
        return model;
    }

    public Manufacturer getMake() {
        return make;
    }

    public int getMileage() {
        return mileage;
    }

    public double getHorsePowerPerKilo() {
        return horsePowerPerKilo;
    }

    public int getPriceInEuro() {
        return priceInEuro;
    }

    public int getMalfunctions() {
        return malfunctions;
    }

    public double getlPer100km() {
        return lPer100km;
    }

    public int getFirstRegistrationYear() {
        return firstRegistrationYear;
    }

    public boolean isHasMajorExternalDamages() {
        return hasMajorExternalDamages;
    }
}
