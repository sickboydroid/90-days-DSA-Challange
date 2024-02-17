# Java I/O

## Basic Overview

- `AutoClosable`: Resource that can be automatically closed by **try-with-resource** statement
- `Closable`: Same as above except that `close()` method throws **IOException** rather than **Exception**
- ASCII has 128 characters while as unicode has a lot more. But the mapping of first 128 characters in unicode is same as ASCII
  - This means that you can use a byte to read a text file if you are sure that it is pure ascii even if the encoding of file shows unicode encoding

```java
/* Converts a byte array into string */
// encoding: number of bytes used to represent 1 character
// utf-16: uses 2 bytes
// utf-8 and ascii: use 1 byte
public String convert(byte[] arr, int encoding) {
    if(arr == null || encoding <= 0)
        throw new IllegalArgumentException();
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < arr.length; i += encoding) {
        int res = 0;
        for (int j = 0; j < encoding; j++) {
            // '|' is equivalent to '+'
            // 0xFF makes byte unsigned
            res = (res << 8) | (arr[i + j] & 0xFF);
        }
        builder.append((char) res);
    }
    return builder.toString();
}
```

## Input and Output

- All **Byte Streams** are descended from `InputStream` and `OutputStream`
  - Most byte streams work in a similar way, the only difference lies in the way they are constructed
  - `FileInputStream` and `FileOutputStream` are I/O byte streams for the files
- All **Character Streams** are descended from `Reader` and `Writer`
  - `FileWriter` and `FileReader` specialize with file I/O character streams
  - Character streams are often 'wrapper' for byte streams. They only handle translation b/w bytes and characters
  - Most character I/O are **Line Oriented**. `BufferedReader` and `PrintWriter` can be used for line oriented I/O
- `InputStreamReader` and `OutputStreamReader` act as a bridge b/w character and byte streams. You can use them to create custom character streams that read/write to underlying byte streams
- Note that **read()** method in byte streams returns **int** which holds a byte in the last 8 bits. While as in character streams it holds a character in last 16 bits (as char is 2 byte long).
- In unbuffered I/O each read/write request is handled by OS (see benchmarks below).

  - Buffered I/O write data only when buffer is full (or empty in case of reading)
  - `BufferedInputStream` and `BufferedOutputStream` create buffered byte streams
  - `BufferedWriter` and `BufferedReader` create buffered character streams
  - To write out a buffer without waiting for it to fill is called **flushing**
  - Benchmarks:
    - Without buffer: **31** s (for each byte, we make an os call)
    - With buffer of size 8: **7.55** s
    - With buffer of size 32: **1.86** s
    - With buffer of size 128: **0.5598** s
    - With buffer of size 256: **0.2967** s
    - With buffer of size 8192: **0.049** s
    - With buffer of size whole file: **0.087** s
    - With BufferedReader: **0.519** s

- `available()`: returns number of bytes available to read
  - For example in file, it is the size of file
  - In terminal, it is the number of bytes user inputted
- `read()`: -1 represents end of stream

### Scanning and Formatting

- `scanner` api breaks input into individual tokens associated with bits of data
  - `Scanner` by default scanner uses white spaces to separate tokens.
    - It is not a stream because it does not extend one but still you need to call its close method for underlaying stream
      - `useDelimiter(String regex)` can be used to change the delimeter
      - It also supports tokens for all primitve types (except for char). See example below

```java
// Let input be "Hello this 123,122 is 12.5
// '123,122' is a toke and so is 'Hello' and 'this' because space is a delimeter by default
Scanner s = new Scanner(readable);
while(s.hasNext()) {
    if(s.hasNextInt())
        print(s.nextInt()); // 123,122
    else if(s.hasNextDouble())
        print(s.nextDouble()); // 12.5
    else
        print(s.next()); // Hello this is
}
```

- `formatting` api formats the data into human-readable form
  - Stream objects that implement formatting are instances of either `PrintWriter` (for character stream) or `PrintStream` (for byte stream)
    - `print` and `println` both call **toString** on the passed object
    - `format` (see formatting in Misc.md)

### Filter Streams

- A `FilterInputStream` wraps some other input stream, which it uses as its basic source of data, possibly transforming the data along the way or providing additional functionality.
  - It has a constructor which accepts `InputStream` object and then stores in protected field `in`
- `BufferedInputStream`, `DataInputStream`, `LineNumberInputStream`, `InflaterInputStream`, etc are some direct [subclasses of FilterInputStream](https://docs.oracle.com/en/java/javase/21/docs//api/java.base/java/io/FilterInputStream.html).
  - All above classes are wrappers around the bare **InputStream**. So for example if you want to use buffering and want to read primitive data from file, you can do that as follows:
    - `new DataInputStream(new BufferedInputStream(new FileInputStream("out.bin")))`

> Same can said for `FilterOutputStream`

#### Standard Streams

- Java provides three standard streams `System.in`, `System.out` and `System.err`. Due to historical reasons, they are byte streams rather than character streams
  - However `PrintStream` uses an internal character stream object to emulate character stream features.
  - Same cannot be said for `System.in`. You have to wrap it with **InputStreamReader** or **Scanner** in order to use it as a character stream

##### Console

- `Console` is an advanced alternative to Standard Streams. It provides all the features of Standard streams and adds some more
- It is particularly useful for retrieving passwords
- It is not a subclass of output/input stream

```java
Console c = System.console()
String username = c.readLine("Enter username: ");
char[] password = c.readPassword("Enter passwd: ");
c.format(STR."\{username} logged in!%n");
```

#### Data Streams

- All data streams implement `DataInput` or `DataOutput`. They are used for I/O of primitive data type values and strings
  - `DataInputStream` and `DataOutputStream` is what we use mostly
- It is up to programmer to read the data in same order as it was written
- There is no method to indicate eof instead EOFException is thrown when all data is read

#### Object streams

- All object streams implement `ObjectInput` or `ObjectOutput` which are subinterfaces of `DataInput` and `DataOutput`
- Data Streams are for I/O of primitives while as Object Streams are for objects. However ObjectStreams can be used for both as they implement DataInput and DataOutput as well.
- Marker interface `Serialization` is implemented for an object to be serializable

- If a single object is written by two different streams (like written to two different files) then the programming reading from both streams will get two distinct object i.e obj1 != obj2
- If a single object is written multiple times by same stream then the pogram that reads it multiple times will get a sinlge object i.e obj1 == obj2 will be true.
- Read objects in same order as you wrote them

```java
    public static class Book implements Serializable {
        @Serial
        private void readObject(ObjectInputStream ois) {
            System.out.println("READ");
        }

        @Serial
        private void writeObject(ObjectOutputStream ois) {
            System.out.println("WRITE");
        }


    }
```

## java.nio.file package

- Introduced in JDK 7. It is very intuitive
- **Symbolic link** or **symlink** or **soft link** are all same
- java won't put you in an infinite loop in case of circular reference
  - **a** contains **b** which contains **c** which contains symlink of **a**

### Path (Heart of nio)

- Path class is one of the primary entrypoints of the package
- Path methods are sometimes called `syntactic operations` as they operate on the path itself (except some methods like `toRealPath()`) and don't access the file system
- Highest element in the directory structure is at index 0 and the lowest at index n-1 (where n = getNameCount()). You can get any kth element using `path.getName(k)`

  - Root can be retrieved via `path.getRoot()`
  - `path.getParent()` is equivalent to `path.getRoot().resolve(path.subpath(0, path.getNameCount() - 1))`
  - `normalize()` function removes redundancies like remove '.' and resolves '..'
    - '/home/red/../foo' will be normalized to '/home/foo'. However if 'red' is a symlink then the original and final path don't represent same path. See next function.
  - `toRealPath()` resolves symbolic links and removes redundancies in path. In general it derives an absolute path that locates the same file as this path.
    - Throws exception if file DNE
  - `resolve(Path)` is used for joining paths. If you pass an absolute path then that path will be returned (see example)
  - `p.relativize(q)` will return a path in which 'q' is relative to 'p'. It inverse of resolve if 'p' has a root
    - e.g `'/home/a/b/c/d'.relativize('a/b')` will give '../..' which if you resolve in directory
  - `toUri()` if you want to open file in browser
  - `toAbsolutePath()`: (see example below)
  - `equals(Object)`, `startsWith(Path)` and `endsWith(Path)` are used for comparing paths

```java
/* Creation of Path */
Path p1 = Paths.get("C:\\home\\tmp"); // 0 is home and 1 is tmp. Here root is C:/
Path p2 = Paths.get("/home/tmp"); // 0 is home and 1 is tmp. Here root is /
Path p2 = Paths.get(System.getProperty("user.home"), "logs", "foo.log");

/* Recall 'syntactic operation' */
Path p = Paths.get("foo");
p.getRoot(); p.getParent(); // all return null
p = p.toAbsolutePath(); /* OR */ p = p.toRealPath();
p.getRoot(); p.getParent(); // all return expected values

/* Resolve example */
Paths.get("foo/bar").resolve("basket"); // result: foo/bar/basket
Paths.get("foo/bar").resolve("/home/usr"); // result: /home/usr

/* MISC */
// prints component of a path
for(Path name : path)
  print(name)

```
