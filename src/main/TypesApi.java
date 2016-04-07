import java.util.*;

public class TypesApi {
    Map<Long, Transaction> transactions;

    public TypesApi(Map<Long, Transaction> transactions) {
        this.transactions = transactions;
    }

    public Set<Long> get(String type) {
        Set<Long> ids = new HashSet<Long>();

        for (Map.Entry<Long, Transaction> entry : transactions.entrySet()) {

            if (entry.getValue().type.equals(type)) {
                ids.add(entry.getKey());
            }
        }
        return ids;
    }
}
