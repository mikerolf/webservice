package proj;

import java.util.*;

public class SumApi {
    private Map<Long, Transaction> transactions;

    public SumApi(Map<Long, Transaction> transactions) {
        this.transactions = transactions;
    }

    double getSum(long id) {
        Transaction t = transactions.get(id);
        double sum = t.getAmount();
        long parentId = t.getParentId();

        while (parentId != 0) {
            Transaction parent = transactions.get(parentId);
            sum += parent.getAmount();
            parentId = parent.getParentId();
        }

        return sum;
    }
}
