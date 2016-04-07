class Transaction {
    double amount;
    String type;
    long parentId;

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

    public long getParentId() {
        return parentId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setParentId(long parentId) {this.parentId = parentId;}

    @Override
    public String toString() {
        return "[" + this.amount + ", " + this.type + "]";
    }
}
