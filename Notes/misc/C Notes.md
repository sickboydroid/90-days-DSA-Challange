# C

## Printing

```c
// prints 20 sig figs
printf("%.20f", num);

// prints long integer
printf("%li", longNum);
```

## DATA TYPES

- bool (1 byte)
- char (1 byte)
- int  (4 bytes) (signed 2B and unsigned 4B)
- long (8 bytes)
- float (4 bytes)
- double (8 bytes)
- string (? bytes)

## Functions

```C
int get_name(void)
{
   return num;
}

void set_name(string* name)
{
   myName = name
}
```

## Strings

> As we don't know its end but only start. So to create its end we follow it by null char as *\0*. \0 is also call NUL.
---
> Strings are basically an array of characters.

```c
// one way
char str[10] = "Hello!";
str[0] =?;
str[1] =?;
...
printf("%s", str);

// other way
char *str = "Hey World!";
```
