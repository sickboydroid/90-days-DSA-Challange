public static class BST<Key extends Comparable<Key>, Value> {

  private class Node {

    private Node left, right;
    private Key key;
    private Value val;
    private int count = 1; // number of nodes in tree whose root is this node

    public Node(Key key, Value val) {
      this.key = key;
      this.val = val;
    }
  }

  private Node root;

  /* put operation */
  /**
   * Most of the time the link we get back will be the same link that we
   * send in but for only one case, that is when the link we sent is null, we
   * are going to get different link. Thus, we update our left/right subtree
   * everytime we call put function
   * To see what i am talking about see @putEfficient(...) which does not do
   * this and updates left and right subtree only when it sends null link
   */
  public void put(Key key, Value val) {
    root = put(root, key, val);
  }

  private Node put(Node cur, Key key, Value val) {
    if (cur == null) return new Node(key, val);
    int cmp = key.compareTo(cur.key);

    if (cmp < 0) cur.left = put(cur.left, key, val); else if (
      cmp > 0
    ) cur.right = put(cur.right, key, val); else cur.val = val;
    cur.count = 1 + size(cur.left) + size(cur.right);
    return cur;
  }

  /* get operation (simple binary search) */
  public Value get(Key key) {
    Node cur = this.root;
    while (cur != null) {
      int cmp = key.compareTo(cur.key);
      if (cmp < 0) cur = cur.left; else if (cmp > 0) cur =
        cur.right; else return cur.val;
    }
    return null;
  }

  /* size operation */
  public int size() {
    return size(root);
  }

  private int size(Node node) {
    if (node == null) return 0;
    return node.count;
  }

  /* Deletion: Using Hibbard Method (don't use too much head, just remember the method) */
  public void delete(Key key) {
    root = delete(root, key);
  }

  private Node delete(Node node, Key key) {
    if (node == null) return null;
    int cmp = key.compareTo(node.key);
    if (cmp < 0) { // search in left
      node.left = delete(node.left, key);
    } else if (cmp > 0) { // search in right
      node.right = delete(node.right, key);
    } else { // found node, now let's delete it
      if (
        node.left == null && node.right == null
      ) return null; // case 1: no child
      if (
        node.left == null
      ) return node.right; // case 2: one child
      if (
        node.right == null
      ) return node.left; // case 2: one child
      // case 3: two child: replace cur node with its successor
      Node successor = node.right;
      Node successorParent = node;
      while (successor.left != null) {
        successorParent = successor;
        successor = successor.left;
      }
      successorParent.left = null; // remove the successor from its parent
      successorParent.count -= 1; // because we remove its left child
      node.key = successor.key;
      node.val = successor.val;
      /* return node; we have update node's (to be deleted) key and value so return this to parent so that he can update */
    }
    node.count = 1 + size(node.left) + size(node.right);
    return node;
  }

  /** Ordered operations */
  /* RANK: returns num of keys whose key < given key */
  public int rank(Key key) {
    return rank(root, key);
  }

  private int rank(Node node, Key target) {
    if (node == null) return 0;
    int cmp = target.compareTo(node.key);
    if (cmp == 0) return size(node.left); // number of nodes in subtree
    else if (cmp < 0) return rank(node.left, target); // (target < key) get smaller
    else/* (target > key) */ return (
      1 + size(node.left) + rank(node.right, target)
    ); // this + nodes on left + some nodes on right
  }

  /* ceil function returns the smallest key >= given key*/
  public Key ceil(Key key) {
    Node ceil = ceil(root, key);
    if (ceil != null) return ceil.key;
    return null;
  }

  private Node ceil(Node node, Key target) {
    if (node == null) return null;
    int cmp = target.compareTo(node.key);
    if (cmp == 0) return node; else if (cmp > 0) return ceil(
      node.right,
      target
    ); // (target > node) ceil has to be greater than target
    else { // (target < node) -> this node can be ceil(target) only if there is no other value in left subtree holding this same condition
      Node lt = ceil(node.left, target);
      if (lt != null) return lt; // found value in left subtree (which by definition would be smaller than this)
      return node; // no node found in left subtree where (target <= node)
    }
  }

  /* floor function returns the largest key <= given key */
  public Key floor(Key key) {
    Node floor = floor(root, key);
    if (floor != null) return floor.key;
    return null;
  }

  private Node floor(Node node, Key target) {
    if (node == null) return null;
    int cmp = target.compareTo(node.key);
    if (cmp == 0) return node; else if (cmp < 0) return floor(
      node.left,
      target
    ); // (target < node) floor has to be <= than target
    else { // (target > node) -> node can be floor(target) only if there is no other value in right subtree holding this same condition
      Node rt = floor(node.right, target);
      if (rt != null) return rt; // found value in right subtree (which by definition would be greater than this)
      return node; // no node found in right subtree where (target >= node)
    }
  }

  /* for iterating */
  public Iterable<Key> keys() {
    Queue<Key> queue = new LinkedList<>();
    inorder(root, queue);
    return queue;
  }

  private void inorder(Node node, Queue<Key> traversal) {
    if (node == null) return;
    inorder(node.left, traversal);
    traversal.add(node.key);
    inorder(node.right, traversal);
  }

  /* put (efficient) */
  public void putEfficient(Key key, Value val) {
    if (root == null) root = new Node(key, val); else putEfficient(
      root,
      key,
      val
    );
  }

  private void putEfficient(Node cur, Key key, Value val) {
    int cmp = key.compareTo(cur.key);
    if (cmp < 0) {
      if (cur.left == null) {
        cur.left = new Node(key, val);
      } else putEfficient(cur.left, key, val);
    } else if (cmp > 0) {
      if (cur.right == null) {
        cur.right = new Node(key, val);
      } else putEfficient(cur.right, key, val);
    } else cur.val = val;
  }
}
