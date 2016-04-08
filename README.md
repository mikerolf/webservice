# Web Service
A web service that stores transactions in memory. 

## Running
The project can by run by executing the <code>run.sh</code> script.
<pre><code>
$ ./run.sh 
1) Compile Server

2) Run Unit Tests
JUnit version 4.12
....
Time: 0.012

OK (4 tests)

3) Start Server
...............UP

4) Run API Calls
curl -s http://localhost:8000/transactionservice/test
Up and running!

curl -s http://localhost:8000/transactionservice/transaction/10 -d "{\"amount\": 5000...}"
{"status": "ok"}

curl -s http://localhost:8000/transactionservice/types/cars
[10]

5) Stop Server
Kill process ID 17080
</code></pre>

## API

| Method  | Path                                             | Asymtotic Behaviour        |
|---------|--------------------------------------------------|----------------------------|
| PUT     | /transactionservice/transaction/$transaction_id  | O(1)                       |
| GET     | /transactionservice/transaction/$transaction_id  | O(1)                       |
| GET     | /transactionservice/types/$type                  | O(N)                       |
| GET     | /transactionservice/sum/$transaction_id          | O(N)                       |

##Testing
1. End-to-End Tests - the <code>run.sh</code> script provides <code>curl</code> commands to test the API endpoints.
2. Unit Tests - the logic for the more complicated APIs has been extracted and has unit tests. 
