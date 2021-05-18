## Thinking process steps and some code snippets

#### 1. Import file

```java
@Value("classpath:data/resourcedata.txt")
Resource file;
```

#### 2. Read from file

```java
new String(Files.readAllBytes(file.getFile().toPath()))
```

#### 3. Create array of word from String

```java
String[] resourceStringArray = Arrays.stream(
        resourceString
            .replaceAll("[^a-zA-Z0-9\\s+]", "")
            .replaceAll("[\\n\\r]", " ")
            .split(" "))
        .filter(e -> e.trim().length() > 0)
        .toArray(String[]::new);
```

#### 4. Iterate over array and count words, print and return results

- Two approaches here, two methods for doing this
~~~java
printAndReturnTop50ArrayList(String[] resourceStringArray)
~~~
~~~java
printAndReturnTop50TreeSet(String[] resourceStringArray)
~~~

## Additional work
- added integration test for engine() method
- added Api method and postman collection for testing it

When you run the application it will print the results taken from file from resources/data folder.
You can also run test which covers four different scenarios and assert the results.




