class Transaction {
    int amount;
    String type;

    public int getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public void setAmount(int amount) {
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
