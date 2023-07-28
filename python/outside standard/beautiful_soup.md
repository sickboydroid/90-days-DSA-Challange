# Beautiful Soup

## Misc

- [Beautiful Soup's Beautiful documentation](https://www.crummy.com/software/BeautifulSoup/bs4/doc/#a-function)
- Four objects that you will deal with:
  1. **bs4.Tag:** Corresponds to the HTML/XML tag in original document
     - `name` attribute contains name of tag
     - `attrs` attribute of dict that contains attributes of tag. These can be accessed by treating the tag as dictionary like `anchor['href']` or simply `anchor.attrs['href']`
  2. **bs4.NavigableString:** It is used to contain the text within a tag
  3. **bs4.Comment:** Special type of NavigableString
  4. **bs4.BeautifulSoup:** Represents the parsed document
     - For most part, you can treat it as a `Tag` object
- At the top of hierarchy there is BeautifulSoup with name `[document]`. Parent of BeautifulSoup is defined to be None
- `str(soup)`: html with no fancy formatting
- `soup.prettify()`: html with fancy formatting
- Any two `NavigableString` or `Tag` objects are equal when their HTML/XML representation is same. It does not matter if they represent the same portion of object tree or different portion
  - Use `is` operator is you want to check if two variables refer to same object
  - See Example 6 for more info
- `diagnose(html_doc)` will show what Beautiful Soup is doing to the document
-

## Navigating the tree

- Tags may contain strings and other tags. These elements are tag's children. Strings don't have children

1. **Going Down:**

   - `tag.contents` (list) or `tag.children` (list_iterator) contain **direct children** of a tag. Don't modify `.contents` list directly if you want to modify tag's children. Use upcoming methods.
   - `tag.descendants` (generator): Recursively iterate over all of a tag's children
   - `tag.string`: If tag has only one child as string then returns that string. If tag has only one child which is another tag then that tags string is returned. Otherwise None is returned
   - `tag.strings` and `tag.stripped_strings` returns all strings that a tag or any of its children contains

2. **Going Up:**

   - `tag.parent` and `tag.parents` can be used to go up in hierarchy

3. **Going Sideways:**

   - `tag.next_sibling` and `tag.previous_sibling` return the siblings of that tag (see example 2: Going Sideways)
   - `tag.next_siblings` and `tag.previous_siblings` will return all siblings next/previous to the tag

4. **Going Back and Forth:**

   - `tag/str.next_element` and `tag/str.previous_element` return whatever was parsed immediately after/before the tag/str
   - `tag/str.next_elements` and `tag/str.previous_elements` return generators of all elements

## Searching the tree

- **Filter** can be a **str**, **pattern** (generated vai re.compile(regex)), **list**, **True** (matches everything). It can be a **function** as well which takes the item being filtered as input and returns bool value
- Most of the upcoming functions accept arguments same as that of `find_all`

1. **find_all(name, attrs, recursive, string, limit, \*\*kwargs)**: It is so useful that you can use `soup(...)` and `tag(...)` instead of `soup.find_all(...)` and `tag.find_all(...)`. It searches in the tag's descendants
   - `name` any filter. Filter will be applied to tags. Thus `find_all('p')` will find all paragraph tags
     - Function will given element as an input.
   - `attrs` should be a dictionary (see examples)
   - `recursive` if it is False then Beautiful soup will search only in direct children and will not go down in hierarchy
   - `string` can be any filter. Filter will be applied to strings
   - `limit` only find first limit matches
   - `kwargs`, `attribute=filter` will find all elements with **attribute** and value **filter**. Filter can be any of previous values. If filter is a function then that function should take attribute's value as argument and return bool based on that.
     - e.g `find_all(href=not_google)` where `not_google = lambda val: val and not re.search('google', val)` will return all tags with href attribute which does not contain word **google** in it
2. **find(...)**: Returns first match or None if no match was found
3. **find_parents(...)** and **find_parent(...)**: Searches in the tag's/string/s parents
4. **find_next_siblings(...)** and **find_next_sibling(...)**: Searches in the tag's siblings which are after it
5. **find_previous_siblings(...)** and **find_previous_sibling(...)**: Searches in the tag's siblings which are before it
6. **find_all_next(...)** and **find_next(...)** searches in the elements that were parsed after the tag/string
7. **CSS Selectors**: `tag.css.select(selector)` or for convenience `tag.select(selector)` can be used to select tags via css selectors (see example 4)
   - `tag.css.iselect(selector)` returns a generator rather than a list
   - `tag.css.closest(selector)` returns the closest parent of selected tags
   - `tag.css.match(selector)` returns True if the 'tag' matches the 'selector' otherwise returns False
   - `tag.css.filter(selector)` returns tags **direct children** which match the selector

## Modifying the tree

- You can rename a tag, change values of attributes, add attributes and delete attributes. Use `tag` as dict to perform these operations
- You can also change tag's contents via `tag.string = 'new content'`
- `tag.append(string)`: appends the contents of a tag. You can use it to add new tags inside a tag. (see example 5)
- `tag.extend(list)`: appends all elements of a list to the tag
- `tag.insert(...)` same as 'append' except you specify the index where contents will inserted. **tag.append(tag)** is equivalent to **tag.insert(len(tag.contents), tag)**
- `tag.clear()` removes all contents of tag
- `tag.smooth()` consolidates adjacent strings. For example if `.contents` is **['a very', ' beautiful cat']** it will become **['a very beautiful cat']**

## EXAMPLES

```python
soup = BeautifulSoup(html_doc, 'html.parser')
head = soup.head # will return the first tag by head name
anchors = soup.find_all('a') # return all anchor tags
all_strings = soup.strings # returns all strings present in html doc
# strings consisting entirely of whitespace are ignored, and the rest of strings are stripped
all_stripped_strings = soup.stripped_strings

##################################

# Later you will see that
soup.head.title.string # is equivalent to
soup.find('head').find('title').string
```

```python
# Example 2: Going sideways
# <a><b>text1</b><c>text2</c></a>
# direct children of tag are called siblings
soup.a.b.next_sibling # returns tag c
soup.a.b.previous_sibling # returns None
soup.a.c.next_sibling # returns None
soup.a.c.previous_sibling # returns tab b
```

```python
# Example 3: Searching
# returns all p and a tags
soup.find_all(['p','a'])
# returns tags with class sister
soup.find_all(attrs={'class':'sister'})
# same as above
soup.find_all(class_='sister')
# returns tags with class sister or brother
soup.find_all(attrs={'class':['sister', 'brother']})
# returns tags with class sister and brother
soup.find_all(attrs={'class':['sister brother']})
# returns tags with href containing google
soup.find_all(href=re.compile('google'))
# returns tags with class sister and href containing google
soup.find_all(attrs={'class':'sister', href=re.compile('google')})
# returns tags with string containing sisters
soup.find_all(strings=re.compile('sister'))
# returns all tags with id attribute regardless of its value
soup.find_all(id=True)
# returns p tag with class='story'
# (idk the logic behind this one)
soup.find('p', 'story')
```

```python
# Example 4: Searching/Selecting using css
# returns 'title' tags whose direct or indirect parent is 'head' tag
soup.select('head title')
# returns 'title' tags which are direct children of 'head' tag
soup.select('head > title')
# returns tag(s) whose id is 'google_link' and are direct/indirect children of body
soup.select('body #google_link')
# returns tags which has class 'sister' and is a (next) sibling of tag with id 'google_link'
soup.select('#google_link ~ .sister)
# returns 'anchor' tags with id 'google_link'
soup.select('a#google_link')
# returns 'anchor' tags with id 'google_link' or 'yahoo_link'
soup.select('#google_link,#yahoo_link')
# returns 'anchor' tags with attribute 'href' or with "class='sister'"
soup.select('a[href],a[class="sister"]')
```

```python
# Example 5: Modifying tree
# appends <a>link</a> to the tag
tag.append('<alink</a>')
# appends 'Hello There!' to the tag
tag.append('Hello there!')
# appends 'Hello There!' to the tag
tag.append(NavigableString('Hello There!'))
# appends a comment to the tag
tag.append(Comment('Comment that will be automatically surrounded by <!-- --')
# appends <a href='google.com'>link</a> tot the tag
new_tag = soup.new_tag('a', href='google.com')
new_tag.string = 'link'
tag.append(new_tag)
```

```python
# Example 6: Checking equality
soup = BeautifulSoup('<p id="para1">Hello</p><p id="para1">Hello</p>')
tag1 = soup.select('#para1')[0]
tag2 = soup.find_all('p', id='para1')[0]
tag3 = soup.find_all('p', id='para1')[1]
print(tag1 == tag3) # true: because both elements have same html
print(tag1 == tag2) # true: because both elements have same html
print(tag1 is tag3) # false: because tags represent different objects
print(tag1 is tag2) # true: because both tags represent same objects
# Notice: Even though we retrieved tag1 and tag2 via different methods, they
# still represent same object
```
