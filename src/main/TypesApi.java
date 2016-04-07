import java.util.*;

public class TypesApi {
    Map<Integer, Transaction> transactions;

    public TypesApi(Map<Integer, Transaction> transactions) {
        this.transactions = transactions;
    }

    public Set<Integer> get(String type) {
        Set<Integer> ids = new HashSet<Integer>();

        for (Map.Entry<Integer, Transaction> entry : transactions.entrySet()) {

            if (entry.getValue().type.equals(type)) {
                ids.add(entry.getKey());
            }
        }
        return ids;
    }
}
