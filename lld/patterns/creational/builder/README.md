# Builder Pattern
Builder pattern is used to create complex objects with multiple optional fields.

## Problems without Builder
### 1. Constructor Explosion/Telescoping
- Every new optional parameter requires a new constructor overload.
- Calls become unreadable as you pass empty/dummy values for skipped fields.

### 2. Inconsistent object states
- Partially built objects may be used before all required data is set.

### 3. Mutable Objects
- Exposing setters means client can change the object at any time.

### 4. Difficulty in validations
- No central place for validating all the properties of the object.

## Types of Builder
### 1. Normal Builder
- Clear, Readable Object Construction.
- Single centralized validation.
- Immutable Objects.
- No Constructor Overload.

### 2. Director Builder
- Reusable Builds

### 3. Step Builder
- Incremental / Compile time enforcement of all required fields.
- Separation of mandatory and optional fields.
- IDE Friendly.

## Key Steps
1. Declare constructor as `private` for target class.
2. Create a `Builder` class which has access to target class `private` variables.
3. Declare setters for all the fields of target class in `Builder` class.
4. Use these setters to build the target object and return the target object from `build` method.