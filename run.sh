#!/usr/bin/env bash

compile() {
  rm -fr out
  mkdir -p out/production/untitled
  javac -cp "lib/*" -d out/production/untitled src/Program.java
}

start_server() {
  echo "Start server."
  java -cp "out/production/untitled:lib/*" Program &
  pID=$!

  RET=1
  until [ ${RET} -eq 0 ]; do
      curl http://localhost:8000/test > /dev/null 2>&1
      RET=$?
      printf "."
  done
}

stop_server() {
  echo "Stop server."
  kill ${pID}
}

call_api() {
  echo
  echo $1
  eval $1
  echo
}

main() {
  compile
  start_server
  echo
  call_api "curl -s http://localhost:8000/test"
  call_api "curl -s http://localhost:8000/transaction/10 -d \"{\\\"amount\\\": 5000, \\\"type\\\": \\\"cars\\\"}\""
  echo
  stop_server
}

main


