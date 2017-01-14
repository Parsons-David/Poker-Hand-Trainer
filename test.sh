# Compile all Java files into the build folder.
javac -d build/ClassFiles src/*.java

# Enter build directory. *I couldn't get it to run from a higher directory*
cd build/ClassFiles

# Run Train, which contains the main method for the project.
java Train
