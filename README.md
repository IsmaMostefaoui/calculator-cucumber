[![BCH compliance](https://bettercodehub.com/edge/badge/tommens/calculator-cucumber?branch=master)](https://bettercodehub.com/)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/6856a0f94d25446ca346cbc15a701d43)](https://www.codacy.com/gh/tommens/calculator-cucumber/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=tommens/calculator-cucumber&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/6856a0f94d25446ca346cbc15a701d43)](https://www.codacy.com/gh/tommens/calculator-cucumber/dashboard?utm_source=github.com&utm_medium=referral&utm_content=tommens/calculator-cucumber&utm_campaign=Badge_Coverage)

# Calculating arithmetic expressions

## About

This repository contains Java 11 code for computing arithmetic expressions. It is deliberately incomplete as it serves
to be the basis of all kinds of extensions, such as a more sophisticated Calculator application. The code was written to
be used for educational purposes at the University of Mons, Belgium.

### calculator.Unit testing and BDD

* All tests can be found in the src\test directory. They serve as executable documentation of the source code.
* The source code is accompanied by a set of JUnit 5 unit tests. These tests can be written and run in the usual way. If
  you are not familiar with unit testing or JUnit 5, please refer to https://junit.org/junit5/.
* The source code is accompanied by a set of Cucumber BDD scenarios, also running in Junit. If you are not familiar with
  Cucumber and BDD, please refer to https://cucumber.io/docs/cucumber/. The BDD scenarios are specified as .feature
  files in the src\test\resources directory. Some classes defined in src\test take care of converting these scenarios to
  executable JUnit tests.

### Prerequisites

* You will need to have a running version of Java 11 on your machine in order to be able to compile and execute this
  code.
* You will also need to have a running version of Maven, since this project is accompanied by a pom.xml file so that it
  can be installed, compiled, tested and run using Maven.

### Installation and testing instructions

* Upon first use of the code in this repository, you will need to run "mvn install" to ensure that all required project
  dependencies (e.g. for Java, JUnit, Cucumber, and Maven) will be downloaded and installed locally.
* Assuming you have a sufficiently recent version of Maven installed (the required versions are specified as properties
  in the POM file), you can compile the source code using "mvn compile"
* Once the code is compiled, you can execute the main class of the Java code using "mvn exec:java"
* The tests and BDD scenarios are executable with Maven using "mvn test"
* Note that the tests are also executed when you do a "mvn install". It is possible to skip those tests by providing an
  extra parameter. For details of more advanced uses of Maven, please refer to its official
  documentation https://maven.apache.org/guides/.

### Test coverage and JavaDoc reporting

* In addition to testing the code, "mvn test" will also generate a test coverage report (in HTML format) using JaCoCo.
  This test coverage is generated in target/site/jacoco.
* When packaging the code using "mvn package" the JavaDoc code documentation will be generated and stored in
  target/site/apidocs.


## Usage
We have a list of the following operations:

### CLI Commands
```exit``` exit the cli.
### Basic arithmetic
positive number: ```1``` 

negative number: ```-1```

number radix: ```2x010```  radix "x" number in the provided radix

datetime:
* ```'2020-12-12 10:10 PM UTC+2'```
* ```'10:10 PM'``` today at 10:10 pm

duration: ```'1d 2h 1m 15s'``` 1 day 2 hours 1 minute and 15 seconds

addition: ```1+2```

subtraction: ```1-2```

multiplication : ```1*2```

division: ```1/2```

power: ```1^2```


### Boolean Operations
Boolean expressions work with number. 0 is false and everything else is true (including negative numbers).

conjunction : ```1 & 1```

disjunction : ```1 | 1```

negation : ```!1```

exclusive disjunction  : ```1 || 1```

implication: ```1=>1```

equivalence: ```1<=>1```


### Time Operation
addition: ```'2020-12-12 10:10 PM UTC+2' + '1d'```

subtraction: ```'2020-12-12 10:10 PM UTC+2' - '1d'```

unary minus : ``` - '1d 2h'``` today minus 1 day and 2 hours
unary plus : ``` + '1d 2h'``` today plus 1 day and 2 hours


### Unit Conversion
```unit 1ft to m``` convert 1 feet to meters

### Currency Conversion
```curr 15 usd to eur``` convert 15 usd to euros


### Modular Arithmetic Operations
modulo: ```1 % 2```

inverse modulo: ```1 !% 2``` or ```1 modinv 2```

congruence: ```11 =%(8) 3``` gives TRUE and ```11 =%(8) 4``` gives FALSE (8 is the modulo operator number)

### Functions
minimum: ```min(1,2,...) ```

maximum: ```max(1,2,...) ```

greatest common divisor: ```gcd(1,2)```

random generation: ```rand(2)``` returns random number between 0 and the provided parameter.

number radix conversion: ```conv(1+2, 3)``` return the result of the provided expression converted to the provided radix


### Memory Functionalities

```savem 1+2 M1```: save the given expression in the given memory socket (denoted by the number after 'M') 

```loadm M1```:  evaluate the expression saved in the given memory socket (denoted by the number after 'M')

```history```: print the history of the calculator

```undo```: removes the last  element from history

```redo```: cancel the last undo command

```save```: save the history in a file called **history.bin**

```load```: load the history from **history.bin**



## Built With

* [Maven](https://maven.apache.org/) - an open source build automation and dependency management tool
* [JUnit5](https://junit.org/junit5/) - a unit testing framework for Java
* [Cucumber](https://cucumber.io/docs/cucumber/) - a tool for Behaviour-Driven Development
* [JaCoCo](https://www.jacoco.org) - a code coverage library for Java

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see
the [tags on this repository](https://github.com/tommens/calculator-cucumber/tags).

## Contributors

* Tom Mens
* Gauvain Devillez @GauvainD
* Damien Legay @DamienLegay
* Maxime Daniels
* Ismaïl Mostefaoui
* Serge Nshimiyimana

## Licence

This code is licensed as CC BY-SA 4.0 (Creative Commons Attribution-ShareAlike 4.0 International)
https://creativecommons.org/licenses/by-sa/4.0/

## Acknowledgments

* Software Engineering Lab, Faculty of Sciences, University of Mons, Belgium.
