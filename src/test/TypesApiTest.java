import org.junit.*;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class TypesApiTest {
    @Test
    public void test() {
        HashMap<Integer, Transaction> transactions = new HashMap<Integer, Transaction>();
        transactions.put(1, new Transaction(1000, "cars"));
        transactions.put(2, new Transaction(1000, "cars"));
        transactions.put(3, new Transaction(5000, "shopping"));

        Set<Integer> ids = new TypesApi(transactions).get("cars");

        assertEquals(2, ids.size());
    }

}
