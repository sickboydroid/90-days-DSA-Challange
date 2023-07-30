# collections

## ChainMap class

- Linking multiple mappings (dicts) so that they can be treated as a single unit
- Mappings are stored by reference, so if you change something in any of the added mappings, it will be reflected
  - They exist in a list called **maps**, you can even access and modify it
- **Lookups** search the underlying mappings until a key is found.
- In contrast to above, **writes**, **updates** and **deletions** only operate on the FIRST mapping
- The object acts like a normal dictionary. In addition to it, there is `maps` attribute, `new_child(...)` method and `parents` property
  - **new_child(...)** adds the mapping (if provided, otherwise an empty mapping is created) to the beginning and returns a new ChainMap.
    - It is used for creating sub-contexts that can be updated without altering values of any of the parent mapping because you can modify only first (see example below).
- **parents** returns a new ChainMap containing all the mappings except the first one
- An example use case of ChainMap is _managing configuration settings_ for different levels as follows:

```python
default = ChainMap()    # default settings
system = default.new_child() # settings for all users (maybe set by root)
user = system.new_chile() # settings for a particular user
```

- **default** settings cannot be modified by root. Similarly, **default** and **system** settings cannot be modified by user. If a user has not configured a specific part of the settings, the value will be looked up in the system settings. If it's not found there, it will fall back to the default settings.

## Counter (bag/multiset in other languages)

- Subclass of dict for counting hashable objects. The key represents the element and its value represents its count
- It can be initialized from an iterable or any other mapping (or from other Counter)
- Returns zero instead of KeyError in case requested key DNE
- `most_common(n=all)`: Returns n most common elements
- `elements()`: Return an iterator over elements repeating each as many times as its count (ignores elements with count <= 0)
- `total()`: sum of all counts
- `subtract([iterable/mapping])`: subtracts iterable from current counter

## DefaultDict

- Dict that never raises a KeyError if you define the **default_factory** method

```python
defaultdict(list) # default value is list() or []
defaultdict(int)  # default value is int()  or 0
defaultdict(str)  # default value is str()  or ''
defaultdict(SomeClass) # default value is SomeClass()
```

## NamedTuple

- Returns a tuple with names for each position
- `namedtuple(typename, field_names)` where typename is the type of tuple and field_names are the positions you want to name
- In the example below, we are creating a new class named Point which is a direct subclass of inbuilts.tuple and contains only few extra attributes to deal with x and y attributes. Note the typename is Point and returned value is a class object of 'Point'. Usually return value is also named the typename

```python
Point = namedtuple('Point', ['x', 'y', 'z'])
origin = Point(0,0)
origin.x # OR
origin[0]
```

## Deque (Doubly Ended Queue)

- Deque is a generalization of stacks and queues. It is essentially an optimized list for quicker appends and pop operations from both sides (both O(1))
- I would recommend **lists for stacks** and **deque for queues** because according to my tests, lists are slightly faster than deques in terms of poping and appending from right
- If **maxlen** is specified: "when queue is full and you add items from one side, same number of items are discarded from the other side"
- Most methods are similar to that of list. Here are some important ones:
  - `append(x)`, `appendleft(x)`, `pop()`, `popleft()`, `extend(iterable)`, `extendleft(iterable)`,
  - `rotate(n=1)`: rotate the deque n steps to the right. If n is negative then rotate n steps to the left
- Indexed access is O(1) at both ends but slows to O(n) in the middle. (indexed lookup is far more slower than lists)
  - `in` operation is also slower on deques than lists
