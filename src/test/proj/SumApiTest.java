package proj;

import org.junit.*;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class SumApiTest {
    Map<Long, Transaction> transactions;

    @Before
    public void setUp() {
       transactions = new HashMap<Long, Transaction>();
    }

    @Test
    public void test_singleTransaction() {
        transactions.put(1l, new Transaction(100, "abc"));
        SumApi api = new SumApi(transactions);

        assertEquals(100, api.getSum(1), 0.0);
    }

    @Test
    public void test_transactionWithParent() {
        transactions.put(1l, new Transaction(100, "abc"));
        transactions.put(2l, new Transaction(200, "abc", 1l));
        SumApi api = new SumApi(transactions);

        assertEquals(300, api.getSum(2), 0.0);
    }

    @Test
    public void test_transactionWithParentAndGrandparent() {
        transactions.put(1l, new Transaction(100, "abc"));
        transactions.put(2l, new Transaction(200, "abc", 1l));
        transactions.put(3l, new Transaction(300, "abc", 2l));
        SumApi api = new SumApi(transactions);

        assertEquals(600, api.getSum(3), 0.0);
    }

}
