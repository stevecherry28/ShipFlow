public class PickupPointShipment extends ShipmentOrder {
    private String lockerSize;
    private boolean fragile;

    public PickupPointShipment (String orderNumber, String customerName, double distanceKm,
                                double baseFee, boolean insured, String lockerSize, boolean fragile) {
        super(orderNumber, customerName, distanceKm, baseFee, insured);
        this.lockerSize = lockerSize;
        this.fragile = fragile;
    }

    public String getLockerSize() {
        return lockerSize;
    }
    public void setLockerSize(String lockerSize) {
        if (!lockerSize.equals("S") && !lockerSize.equals("M") && !lockerSize.equals("L")) {
        throw  new IllegalArgumentException("Rozmiar należy wpisać duża literą i w przedziale: S, M i L");
        }
        this.lockerSize = lockerSize;
    }

    public boolean isFragile() {
        return fragile;
    }

    @Override
    public String getShipmentType() {
        return "Pickup point";
}

@Override
protected double calculateBasePrice() {
return getBaseFee() + getDistanceKm() * 0.75;
    }

    @Override
    protected double calculateAdditionalFee() {
        double additionalFee = 0;
        if (lockerSize.equals("S")) {
            additionalFee += 5;
        } else if (lockerSize.equals("M")) {
        additionalFee += 10;
        }  else if (lockerSize.equals("L")) {
            additionalFee += 18;
        }
        if (fragile) {
            additionalFee += 12;
        }

        return additionalFee;
    }

    @Override
    protected void validateSpecificRules() {
        if (!lockerSize.equals("S") && !lockerSize.equals("M") && !lockerSize.equals("L")) {
            throw new IllegalArgumentException("Rozmiar nie zgodny ze schematem!");
        }
    }





}

