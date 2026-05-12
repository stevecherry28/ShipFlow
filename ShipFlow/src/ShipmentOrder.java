public abstract class ShipmentOrder implements SummaryPrintable {
    private int orderNumber;
    private String customerName;
    private double distanceKm;
    private double baseFee;
    private boolean insured;
    private double lastCalculatedPrice;

    public ShipmentOrder(int orderNumber, String customerName, double distanceKm,
                         double baseFee, boolean insured) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.distanceKm = distanceKm;
        this.baseFee = baseFee;
        this.insured = insured;
        this.lastCalculatedPrice = 0.0;
    }

    public int getOrderNumber() {
        return orderNumber;
    }
    public String getCustomerName() {
        return customerName;
    }
    public double getDistanceKm() {
        return distanceKm;
    }
    public double getBaseFee() {
        return baseFee;
    }
    public boolean isInsured() {
        return insured;
    }
    public double getLastCalculatedPrice() {
        return lastCalculatedPrice;
    }

    private void validateOrder() {
        if (orderNumber <0) {
            throw new IllegalArgumentException("Numer zamówenia nie może być ujemny");
        }
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Nazwa klienta nie może być pusta!");
        }
        if (distanceKm <= 0) {
            throw new IllegalArgumentException("Odległość nie może być ujemna!");
        }
        if (baseFee <= 0) {
            throw new IllegalArgumentException("Bazowa opłata za przesyłkę nie może być ujemna!");
        }
    }

    protected void validateSpecificRules() {}

    private double applyInsurance(double price) {
        if (isInsured()) {
        price += (price * 0.07);
       }
        return price;
    }

    protected double applyBusinessDiscount (double price) {
        return price;
    }

    private void printProcessingResult() {
        System.out.println("Zamówienie utworzone pomyślnie");
    }

    public String buildSummaryLine() {
        return "Numer zamówienia: " +  getOrderNumber() +
         "\nNazwa klienta: " +  getCustomerName() +
         "\nOdległość: " +  getDistanceKm() +
         "\nStawka bazowa: " +  getBaseFee() +
         "\nCzy ubezpieczony? : " +  isInsured() +
         "\nOstatnio wyliczona cena: " + getLastCalculatedPrice();
    }

    protected abstract double calculateBasePrice();
    protected abstract double calculateAdditionalFee();
    public abstract String getShipmentType();

    public final void processOrder() {
        validateOrder();
        validateSpecificRules();

        double price = calculateBasePrice();
        price += calculateAdditionalFee();
        price = applyInsurance(price);
        price = applyBusinessDiscount(price);

        lastCalculatedPrice = price;
        printProcessingResult();
    }

}
