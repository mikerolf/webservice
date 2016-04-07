#!/usr/bin/env bash

compile() {
  rm -fr out
  mkdir -p out/production/untitled
  javac -cp "lib/*" -d out/production/untitled src/main/*.java
}

run_unit_tests() {
  javac -cp "out/production/untitled:lib/*" -d out/production/untitled src/test/*.java
  java -cp  "out/production/untitled:lib/*" org.junit.runner.JUnitCore TypesApiTest
}

start_server() {
  java -cp "out/production/untitled:lib/*" Main &
  pID=$!

  RET=1
  until [ ${RET} -eq 0 ]; do
      curl http://localhost:8000/test > /dev/null 2>&1
      RET=$?
      printf "."
  done
}

stop_server() {
  echo "Kill process ID ${pID}"
  kill ${pID}
}

call_api() {
  echo $1
  eval $1
  echo
}

main() {
  echo "1 Compile Server"
  compile
  echo

  echo "2 Run Unit Tests"
  run_unit_tests
  echo

  echo "3 Start Server"
  start_server
  echo

  echo
  echo "4 Run API Tests"
  call_api "curl -s http://localhost:8000/test"
  call_api "curl -s http://localhost:8000/transaction/10 -d \"{\\\"amount\\\": 5000, \\\"type\\\": \\\"cars\\\"}\""
  call_api "curl -s http://localhost:8000/transaction/11 -d \"{\\\"amount\\\": 10000, \\\"type\\\": \\\"shopping\\\", \\\"parent_id\\\": 10}\""
  call_api "curl -s http://localhost:8000/transaction/10"
  call_api "curl -s http://localhost:8000/types/cars"
  call_api "curl -s http://localhost:8000/sum/10"
  echo

  echo "5 Stop Server"
  stop_server
}

main


