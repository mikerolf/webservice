class Transaction {
    double amount;
    String type;

    public Transaction() {};

    public Transaction(double amount, String type) {
        this.amount = amount;
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "[" + this.amount + ", " + this.type + "]";
    }
}
