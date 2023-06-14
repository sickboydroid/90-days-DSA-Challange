# CSharp

- `static`: Making class static enforces all methods, variables and inner classes to be static
- `using System`: It is equivalent to `import System.*`
  
## struct v/s class

1. `class` is ref type and `struct` is a value type.
2. `primitive types` are pre-defined struct
3. Inheritance is not possible with structs
4. If you declare a constructor in struct then you have initialize all the variables in it

```c#
Employee emp1;
emp1.name = "First";

Employee emp2 = new(); // fills variables with default values
emp2.name = "Second";

emp2 = emp1;
emp2.name = "WHAT?";

Debug.Log(emp1.name); // output: First
```