# JAVA FILE PROCESSOR

This program takes an input file, a search operation and a searchkey and writes the output to STDOUT. The input ﬁle represents a list of places and people at different times. There are two distinct formats(F1 and F2) mixed in the same ﬁle.

#### Sample input file

```
F1 
D Erica Burns,BARCELONA,93654902Y
D Lucy Mcgee,LONDON,51011156P
D Mitchell Newton,SAN FRANCISCO,25384390A
D Margarita Richards,LAS VEGAS,09877359D
F2
D Mitchell Newton ; LAS VEGAS ; 25384390-A
D Margarita Richards ; NEW YORK ; 09877359-D
D Rhonda Hopkins ; BARCELONA ; 54315871-Z
D Taylor Matthews ; LISBOA ; 58202263-G
```

#### Commands Supported

1. ``` 
java -jar application.jar {FILE} CITY {CITY_NAME} 
   ``` 
   → Will print the list of names and ID's belonging to people that have been in that city
2. ```
java -jar application.jar {FILE} ID {ID_VALUE} (ID format example for input: 12345678Z)
   ``` 
   → Will print the list of cities that this person has been to.

**Note: Commands are case-insensitive**

#### Output examples

1. java -jar application.jar input.txt CITY CARTAGENA must output an unordered unrepeated list of people and IDs that have been to CARTAGENA.
```
Mitchell Newton,25384390A
Rhonda Hopkins,54315871Z
Alexander Arnold,21743514G
Susan Holland,04810023X
Jake Salazar,38399984N
```

2. java -jar application.jar ID 54808168L must output an unordered unrepeated list of cities that Shelley Payne have been to.
```
MADRID
BARCELONA
OVIEDO
```

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

1. You need to have JDK 1.8 or higher installed on your system.
2. You need to set your system variables
   Check online on how to set JAVA_HOME and Path variable for your OS.
3. A robust IDE for development(IntelliJ or netbeans)[optional].

### Usage

- Simply clone or download this repository.
- cd to project folder
- Compile using 
``` 
javac {path for applicaion.java} 
```
and run any of the supported commands without *-jar* flag.

**OR**

build to jar with your IDE and run any of the supported commands


## Built With
* [Maven](https://maven.apache.org/) - Dependency Management


## Author

**Ogunlusi Victor**
