# Misc Concepts

- [Misc Concepts](#misc-concepts)
  - [UNICODE](#unicode)
  - [Co-Primes](#co-primes)
  - [Bit-Manipulation](#bit-manipulation)

## UNICODE

- Java uses **unicode** character set
- _[65-90]_ -> **A-Z**
- _[97-122]_ -> **a-z**
- To convert lowercase char to uppercase, sub 32
- To convert uppercase chat to lowercase, add 32

## Co-Primes

- Two numbers `a` and `b` (both are not zero simultaneously) are said to be co-primes if `gcd(a, b) = 1`
- Exceptionally, `0` is co-prime only with `1` and `-1`
- `1` and `1` are co-prime as well

## Bit-Manipulation

```python
# Left shift: Shift everything to the left by 2 positions
>>> bin(0b0001 << 2)
'0b100' OR '0b0100'

# Right shift: Shift everything to the right by 1 positions
>>> bin(0b1000 >> 1)
'0b100' OR '0b0100'
```

- Check if ith bit is on/off (0 based indexing)
  1. `(x&(1<<i)) != 0;` Will give either get 2^i or 0 if *i*th bit is 1 or 0 respectively
     1. _1<<i_ is called **bit mask**. When apply this mask like `x&(1<<i)`, it is called **bit masking**
     2. if above value is zero then *i*th bit is off

```java
// Application: convert int (32bit) to binary
int num = 0b11011;
String binary = "";
for(int i = 31; i >= 0; i--) {
    if((num & (1<<i)) != 0)
        binary += "1";
    else
        binary += "0";
}

// Application: Even or odd
boolean isEven = (x&1 == 0)
```

- Setting *i*th bit.
  1. `(x|(1<<i));` Will set the *i*th (will be set to 1)
- Clearing *i*th bit.
- 1. `(x& ~(1<<i));` Will clear the *i*th (will be set to 0)
- `XOR` or `^` or `Exclusive OR operator`
  - Def:
    1. Exclusively one of the operands is true OR
    2. `!=` operator OR
    3. If one operand is exactly one operand is true
  - Applications and properties:
    1. One of its applications is that it can be used to find how many bits are different b/w two numbers _(See leetcode problem 2220)_
    2. `x^x` is zero.
    3. `x^0` is x.
- In java, we have `Airthmetic right/left shift` and not `Logical right/left shift`

> TIP: Determine mask first and then operator or vise versa. Don't calculate both at same time
> TIP: Number of bits required to represent a decimal number in binary is: **ceil(log2 n)**
> TIP: Number of digits required to represent a binary number in decimal is: **ceil(log10 n)**
