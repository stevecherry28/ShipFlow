public class DomesticCourierShipment extends ShipmentOrder {
private double packageWeightKg;
private boolean weekendDelivery;

public DomesticCourierShipment(String orderNumber, String customerName, double distanceKm,
                               double baseFee, boolean insured, double packageWeightKg,
                               boolean weekendDelivery) {
    super(orderNumber, customerName, distanceKm, baseFee, insured);
    this.packageWeightKg = packageWeightKg;
    this.weekendDelivery = weekendDelivery;
}

public double getPackageWeightKg() {
    return packageWeightKg;
}

public void setPackageWeightKg(double packageWeightKg) {
    if (packageWeightKg <= 0) {
        throw new IllegalArgumentException("Waga paczki nie może być ujemna!");
    }
    this.packageWeightKg = packageWeightKg;
}

public boolean isWeekendDelivery() {
    return weekendDelivery;
}

@Override
    public String getShipmentType() {
    return "Domestic courier";
    }

@Override
    protected double calculateBasePrice() {
    return getBaseFee() + getDistanceKm() * 1.20;
}

    @Override
    protected double calculateAdditionalFee() {
        double additionalFee = getPackageWeightKg() * 4.00;
        if (weekendDelivery) {
            additionalFee += 25;
        }
        return additionalFee;
    }

    @Override
    protected double applyBusinessDiscount (double price) {
    if (getDistanceKm() < 300) {
        return price;
    }
    return price - price * 0.05;
    }
}
