# JSON Module

- json module contains only 4 methods. Two for serialization, two for deserialization and one for detecting encoding
  1. **Serializing** or **Encoding** (converting *dict* to *json*):
     - `json.dumps(dict)`: dict => json string
     - `json.dump(dict, file)`: dict => json file
  2. **Deserializing** or **Decoding** (converting *json* to *dict*):
     - `json.loads(str)`: json string => dict (see [conversion table](#conversion-table))
     - `json.load(file)`: json file => dict
- Even though I used `dict` above, it is not necessary. If you json something like `[..something...]` it will be converted to python `list`

## Conversion table

<pre>
________________________
<b>Python</b>           <b>JSON</b>
________________________
dict             object
list, tuple      array
str              string
int, float       number
True             true
False            false
None             null
</pre>