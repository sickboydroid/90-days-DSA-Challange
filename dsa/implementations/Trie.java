public class Trie {

  class Node {

    Node[] children = new Node[26];
    boolean eow;
  }

  // insertion
  public static void insert(Node root, String word) {
    for (int i = 0; i < word.length(); i++) {
      int idx = word.charAt(i) - 'a';
      if (root.children[idx] == null) root.children[idx] = new Node();
      root = root.children[idx];
    }
    root.endOfWord = true;
  }

  // prefix searching (only last line differs from normal search)
  public static boolean startsWith(Node root, String prefix) {
    for (int i = 0; i < prefix.length(); i++) {
      int idx = prefix.charAt(i) - 'a';
      if (root.children[idx] == null) return false;
      root = root.children[idx];
    }
    return true;
  }

  // traditional search
  public static boolean search(Node root, String word) {
    for (int i = 0; i < word.length(); i++) {
      int idx = word.charAt(i) - 'a';
      if (root.children[idx] == null) return false;
      root = root.children[idx];
    }
    return root.endOfWord;
  }
}
