# Step Builder
Step Builder is also an extension to builder pattern which add two more functionalities. They are 
1. Building an object step by step in a particular order.
2. Good support for required and optional fields without any checking.

## Steps
1. We divide the entire object construction into steps.
2. Then create interfaces for each step and link them to each other.
3. In the last step build the object and return to the caller.