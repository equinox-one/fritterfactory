[![Build Status](https://travis-ci.org/equinox-one/fritterfactory.svg?branch=master)](https://travis-ci.org/equinox-one/fritterfactory)
[![Coverage Status](https://coveralls.io/repos/equinox-one/fritterfactory/badge.svg?branch=master&service=github)](https://coveralls.io/github/equinox-one/fritterfactory?branch=master)
[![Download](https://api.bintray.com/packages/equinox-one/maven/fritterfactory/images/download.svg) ](https://bintray.com/equinox-one/maven/fritterfactory/_latestVersion)

# Fritter Factory
Library to automatically populate models, to be used in automatic tests or user made tests.

When performing automatic test we need to create models constantly.
Moreover when using hand made test, we may need to populate an application with fake data.
Fritter Factory has been developed to solve this problems.

# Android friendly
Fritter Factory is Android Friendly: it does not use any reflection methods not implemented in the Android libraries. Therefore, it can be used in Android applications without any problem.

# Show me an example
Let's imagine that we have the following model

```java
public class Person {
    String name;
    String surname;
    int age;
    Adress adress;
    String description;
    Category category;
    String image;
}
```

We can create a person with all the attributes set with the following:

```java
FritterFactory fritterFactory = new FritterFactory();
List<Person> persons = fritterFactory.buildList(Person.class, 3);
```


# Show me a better example

That's not all! FritterFactory allows the generated models to be configurable. In this example we will use [Symbols] but its not mandatory:

```java
    /**
     * Print 3 persons, using specific providers defined by a mold.
     */
    public void sampleWithMolds(){
        FritterFactory fritterFactory = createFactoryWithMolds();
        List<Person> persons = fritterFactory.buildList(Person.class, 3);
        System.out.println(persons);
    }

    /**
     * Creates a FritterFactory with defined providers for Person and Adress.
     * @return
     */
    public FritterFactory createFactoryWithMolds(){
        FritterFactory fritterFactory = new FritterFactory();
        fritterFactory.addProvider(Person.class, createPersonProvider(fritterFactory));
        fritterFactory.addProvider(Adress.class, createAdressProvider(fritterFactory));
        return fritterFactory;
    }

    public Provider<Person> createPersonProvider(FritterFactory fritterFactory){
        MapMold personMold = new MapMold();
        personMold.put(PersonSymbols.NAME, new FirstNameProvider());
        personMold.put(PersonSymbols.SURNAME, new FirstNameProvider());
        personMold.put(PersonSymbols.AGE, new IntegerProvider(0,110));
        personMold.put(PersonSymbols.DESCRIPTION, new WordProvider(50, 100));
        personMold.put(PersonSymbols.IMAGE, new PersonImageProvider());
        return new ModelProvider<Person>(fritterFactory, Person.class, personMold);
    }

    public Provider<Adress> createAdressProvider(FritterFactory fritterFactory){
        MapMold adressMold = new MapMold();
        adressMold.put(AdressSymbols.STREET, new WordProvider(1, 3));
        adressMold.put(AdressSymbols.CITY, new CityProvider());
        adressMold.put(AdressSymbols.COUNTRY, new CountryProvider());
        return new ModelProvider<Adress>(fritterFactory, Adress.class, adressMold);
    }
```

# Get full Example
You can download the [full example at github].

# Download

Grab via Gradle:
```groovy
repositories { jcenter() }

compile 'one.equinox.fritterfactory:fritterfactory:+'
```
or via Maven:
```xml
<repository>
  <id>jcenter</id>
  <url>http://jcenter.bintray.com</url>
</repository>

<dependency>
    <groupId>one.equinox.fritterfactory</groupId>
    <artifactId>fritterfactory</artifactId>
    <version>+</version>
</dependency>
```

# What's next
Some ideas on how to improve this library

 - Use annotation processing to create mold classes instead of using [Symbols].

License
=======

     Copyright 2016 Equinox.one

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.



[Symbols]: https://github.com/equinox-one/symbols
[full example at github]: https://github.com/equinox-one/fritterfactory-example