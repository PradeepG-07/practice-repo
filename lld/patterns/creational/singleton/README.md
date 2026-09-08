## Singleton Pattern
This pattern is used to restrict the number of instances/objects of a class to one.

## Key Steps
1. Make the default constructor private, so that direct initialisation using `new` keyword is not possible.
2. Create a `public` `static` method to return the instance/object.
3. Use a private static variable inside the class to hold the instance/object.

### Make it thread safe
1. Add `synchronized` keyword for the static method which returns the instance/object.
2. Use double locking with `synchronized` block instead of declaring the entire method to be synchronized.

## Why can't we simply do the following
```java
    public class Singleton{
        private static Singleton instance = new Singleton();
        private Singleton(){}

        public static Singleton getInstance(){
            return instance;
        }
    }
```
This can also be done, but this is called as eager initialisation. If there are multiple singleton classes and we don't use all of them, using this approach creates the objects early and memory consumption increases.

## UseCases
1. Loggers
2. Application configuration classes
3. In-Memory Caching
4. A Database Connection Manager