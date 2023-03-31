# Doomsday algorithm

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
