#!/bin/bash

# 1. Setup - Create a 'bin' folder for compiled .class files
mkdir -p bin

echo "Compiling Java files..."

# 2. Find all .java files recursively and save to a temporary file
# This is the Bash equivalent of 'dir /s /b'
find . -name "*.java" >sources.txt

# 3. Compile all files listed in sources.txt into the 'bin' folder
javac -d bin @sources.txt

# 4. Clean up the temporary file
rm sources.txt

# 5. Check if compilation was successful ($? is the exit code)
if [ $? -ne 0 ]; then
    echo "Compilation failed."
    exit 1
fi

echo "Compilation successful. Running Main..."
echo "---------------------------------------"

# 6. Run the Main class from the 'bin' folder
java -cp bin Main
