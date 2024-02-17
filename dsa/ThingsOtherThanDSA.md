# Misc Concepts

- [Misc Concepts](#misc-concepts)
  - [Co-Primes](#co-primes)
  - [Bit-Manipulation](#bit-manipulation)
  - [Doomsday algorithm](#doomsday-algorithm)

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
     1. _1<<i_ is called **bit mask**. Applying a mask is called **bit masking**
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
    2. `!=` operator XOR
    3. If bits are different, result is false. If they are different, result is true
  - Applications and properties:
    1. One of its applications is that it can be used to find how many bits are different b/w two numbers _(See leetcode problem 2220)_
    2. `x^x` is zero.
    3. `x^0` is x.
- In java, we have `Arithmetic right/left shift` and not `Logical right/left shift`

> TIP: Determine mask first and then operator or vise versa. Don't calculate both at same time
> TIP: Number of bits required to represent a decimal number in binary is: **ceil(log2 n)**
> TIP: Number of digits required to represent a binary number in decimal is: **ceil(log10 n)**

## Doomsday algorithm

- Video: [Numberphile: Doomsday algorithm](https://www.youtube.com/watch?v=z2x3SSBVGJU&t=339s&ab_channel=Numberphile)

1. Used for determining name of any weekday given the day, month and year
2. Doomsdays are days in year that all occur on same day
   - 4/4 6/6 8/8 10/10 12/12
   - 9/5 5/9 7/11 11/7 3/14
   - Leap year only: Jan 4th, Feb 29
   - Non-year only: Jan 3rd, Feb 28
3. Doomsdays:
   - 1700 `Sunday`
   - 1800 `Friday`
   - 1900 `Wednesday`
   - **2000** `Tuesday`
   - 2100 `Sunday`
   - 2200 `Friday`
   - 2300 `Wednesday` and so on
4. Doomsday of year = Doomsday of century + number of years + floor(number of years gone / 4)
5. Treating Weekdays as numbers can come handy:
   - `Sunday` -> **Noneday** -> 0
   - `Monday` -> **Oneday** -> 1
   - `Tuesday` -> **Twosday** -> 2
   - `Wednesday` -> **Threesday** -> 3
   - `Thursday` -> **Foursday** -> 4
   - `Friday` -> **Fiveday** -> 5
   - `Saturday` -> **Sixday** -> 6

> e.g: **21 Dec 2020:**
> Doomsday of 2020 = Tuesday + 20 + 5
> => 2 + 25
> => 27
> => 27 % 7
> => 6
> => Saturday
>
> Thus 12/12 or 12 Dec was Wednesday
> There are 9 days after 12 Dec.
> Day = Sat + 9
> => Sat + 2
> => 6 + 2
> => 8
> => 1
> => Monday

```java
    // Expected: Thursday
    int year = 2022;
    int month = 3;
    int day = 30;
    /**
     * @return doomsday for any year in 21st century
     */
    public int getDoomsday() {
        if (year < 2000 || year > 2100)
            throw new RuntimeException(new IllegalArgumentException("Year must be from 21st century"));
        int yearsGone = year % 100;
        // doomsday = (tuesday(3) + num of years from 2000 + number of leap years) % 7
        int doomsday = 2 + yearsGone + (yearsGone / 4);
        // if leap year then 4th of jan otherwise 3rd of jan is doomsday
        // from here we can guess the weekday name of jan 1 for current year
        if (year % 4 == 0)
            return doomsday + 3;
        return doomsday + 4;
    }

    /**
     * @return day of the week
     */
    public String dayOfTheWeek() {
        int daysGone = day;
        for (int i = 0; i < month - 1; i++) {
            daysGone += daysInMonths[i];
        }
        int dayOfWeek = (daysGone + getDoomsday()) % 7;
        return daysOfWeek[dayOfWeek];
    }
```
