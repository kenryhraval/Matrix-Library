# Matrix Library
Custom Java library for basic matrix operations (determinant, multiplication, etc.)

## Structure
- `src/` - source code
- `test/` - test cases
- `out/` - compiled classes

## Option (A) 
Compile & run manually
```bash
javac -d out -cp src $(find src test -name "*.java")
java -cp out matrix.Main
```

## Option (B)
Use automated build script
```bash
source build.sh
```