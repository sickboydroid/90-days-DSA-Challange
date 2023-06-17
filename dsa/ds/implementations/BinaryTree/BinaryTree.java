
import java.util.*;

public class BinaryTree {
    private final int[] tree1 = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
    private final int[] tree2 = {1, 2, 3, 4, 5, 6, -1, -1, -1, -1, 7, 8, 9, 10, 11, 12, -1, -1, -1, -1, -1, -1, -1, 13, -1, -1, 14};

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        Node root = bt.createBinaryTree(bt.tree1);
        System.out.println(bt.diameter(root));
    }

    /**************************************
     * HELPERS
     **************************************/
    int idx = -1;

    private Node createBinaryTree(int[] preOrder) {
        idx++;
        if (idx >= preOrder.length || preOrder[idx] == -1) return null;
        Node root = new Node(preOrder[idx]);
        root.left = createBinaryTree(preOrder);
        root.right = createBinaryTree(preOrder);
        return root;
    }

    /***************************************
     * Algorithms
     **************************************/
    public int countNodes(Node root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    public int sumNodes(Node root) {
        if (root == null) return 0;
        return root.val + sumNodes(root.left) + sumNodes(root.right);
    }

    ////////////////////////////////
    HashMap<Integer, Integer> memo = new HashMap<>();

    public int height(Node root) {
        if (root == null) return 0;
        int heightLeft = height(root.left);
        int heightRight = height(root.right);
        return Math.max(heightLeft, heightRight) + 1;
    }

    public int diameter(Node root) {
        if (root == null) return 0;
        if (memo.containsKey(root.val)) return memo.get(root.val);
        int diam1 = height(root.left) + height(root.right) + 1; // diameter passing through root
        int max = Math.max(diam1, Math.max(diameter(root.left), diameter(root.right)));
        memo.put(root.val, max);
        return max;
    }
    ////////////////////////////////

    public boolean isSubtree(Node tree, Node subtree) {
        if (tree == null || subtree == null) return tree == subtree;
        boolean areSame = tree.val == subtree.val && areSame(tree, subtree);
        if (areSame)
            return true;
        return isSubtree(tree.left, subtree) || isSubtree(tree.right, subtree);
    }

    public boolean areSame(Node tree1, Node tree2) {
        if (tree1 == null || tree2 == null) return tree1 == tree2;
        return tree1.val == tree2.val && areSame(tree1.left, tree2.left) && areSame(tree1.right, tree2.right);
    }

    //////////////////////////////

    /****************************************
     * TRAVERSAL: 4 methods (3 dfs and 1 bfs)
     ***************************************/
    public void preOrder(Node root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public void inOrder(Node root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    public void postOrder(Node root) {
        if (root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.val);
    }

    /**
     * BFS: Instead of going in depth we go in breadth.
     * BFS for binary tree is simply level order traversal
     * DFS for binary tree is preorder, postorder, inorder
     **/
    /* Distinguish b/w levels using null */
    public void levelOrder1(Node root) {
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        nodes.add(null);
        while (!nodes.isEmpty()) {
            Node cur = nodes.remove();
            if (cur == null) {
                System.out.println();
                if (!nodes.isEmpty()) nodes.remove(null);
                continue;
            }
            if (cur.left != null) nodes.remove(cur.left);
            if (cur.right != null) nodes.remove(cur.right);
            System.out.print(cur.val);
        }
    }

    /* Distinguish b/w levels using nested loop */
    public void levelOrder2(Node root) {
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        nodes.add(null);
        int level = 0;
        while (!nodes.isEmpty()) {
            level++;
            Node node = nodes.remove();
            if (node == null) {
                if (!nodes.isEmpty()) nodes.add(null);
                continue;
            }
            if (node.left != null) nodes.add(node.left);
            if (node.right != null) nodes.add(node.right);
            System.out.println("LEVEL: " + level + " NODE: " + node.val);
        }
    }

    /* Don't use queue */
    public void levelOrder3(Node root, int level) {
        if (root == null) return;
        levelOrder3(root.left, level + 1);
        System.out.println("LEVEL: " + level + " NODE: " + root.val);
        levelOrder3(root.right, level + 1);
    }

    public static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}