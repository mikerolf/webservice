rm -fr out
mkdir -p out/production/untitled
javac -d out/production/untitled src/Program.java

echo "Server running..."
java -cp out/production/untitled Program




