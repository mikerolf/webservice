import org.junit.*;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class TypesApiTest {
    @Test
    public void test() {
        HashMap<Long, Transaction> transactions = new HashMap<Long, Transaction>();
        transactions.put(1l, new Transaction(1000, "cars"));
        transactions.put(2l, new Transaction(1000, "cars"));
        transactions.put(3l, new Transaction(5000, "shopping"));

        Set<Long> ids = new TypesApi(transactions).get("cars");

        assertEquals(2, ids.size());
    }

}
