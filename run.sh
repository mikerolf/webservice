#!/usr/bin/env bash

compile() {
  rm -fr out
  mkdir -p out/
  javac -cp "lib/*" -d out/ src/main/proj/*.java
}

run_unit_tests() {
  javac -cp "out:lib/*" -d out/ src/test/proj/*.java
  java -cp  "out:lib/*" org.junit.runner.JUnitCore proj.TypesApiTest proj.SumApiTest
}

start_server() {
  java -cp "out:lib/*" proj.Main &
  pID=$!

  RET=1
  until [ ${RET} -eq 0 ]; do
      curl http://localhost:8000/test > /dev/null 2>&1
      RET=$?
      printf "."
  done

  printf "UP"
}

stop_server() {
  echo "Kill process ID ${pID}"
  kill ${pID}
}

call_api() {
  echo $1
  eval $1
  echo
  echo
}

main() {
  echo "1) Compile Server"
  compile
  echo

  echo "2) Run Unit Tests"
  run_unit_tests

  echo "3) Start Server"
  start_server
  echo

  echo
  echo "4) Run API Calls"
  call_api "curl -s http://localhost:8000/transactionservice/test"
  call_api "curl -s http://localhost:8000/transactionservice/transaction/10 -d \"{\\\"amount\\\": 5000, \\\"type\\\": \\\"cars\\\"}\""
  call_api "curl -s http://localhost:8000/transactionservice/transaction/11 -d \"{\\\"amount\\\": 10000, \\\"type\\\": \\\"shopping\\\", \\\"parent_id\\\": 10}\""
  call_api "curl -s http://localhost:8000/transactionservice/transaction/10"
  call_api "curl -s http://localhost:8000/transactionservice/types/cars"
  call_api "curl -s http://localhost:8000/transactionservice/sum/10"
  call_api "curl -s http://localhost:8000/transactionservice/sum/11"

  echo "5) Stop Server"
  stop_server
}

main


