class Item {
    private final String name;
    private final double price;
    private final int quantity;
    private final DiscountType discountType;
    private final double discountAmount;

    public Item(String name, double price, int quantity, DiscountType discountType, double discountAmount) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.discountType = discountType;
        this.discountAmount = discountAmount;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }
}
