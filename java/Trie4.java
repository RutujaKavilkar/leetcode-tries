class Trie4 {

    // Trie Node structure
    static class Node {
        Node[] children = new Node[26];
        boolean isEndOfWord;

        Node() {
            isEndOfWord = false;
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
        }
    }

    private Node root;

    // Constructor
    public Trie4() {
        root = new Node();
    }

    // Insert a word into the trie
    public void insert(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Node();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
    }

    // Search for a complete word in the trie
    public boolean search(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }
        return node.isEndOfWord;
    }

    // Check if any word starts with the given prefix
    public boolean startsWith(String prefix) {
        Node node = root;
        for (char ch : prefix.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }
        return true;
    }

    // Delete a word from the trie (optional, extra feature)
    public boolean delete(String word) {
        return deleteHelper(root, word, 0);
    }

    private boolean deleteHelper(Node node, String word, int depth) {
        if (node == null) return false;

        if (depth == word.length()) {
            if (!node.isEndOfWord) return false;
            node.isEndOfWord = false;

            // If no children, we can delete this node
            for (Node child : node.children) {
                if (child != null) return false;
            }
            return true;
        }

        int index = word.charAt(depth) - 'a';
        if (deleteHelper(node.children[index], word, depth + 1)) {
            node.children[index] = null;

            // If current node is not end of another word and has no children
            if (!node.isEndOfWord) {
                for (Node child : node.children) {
                    if (child != null) return false;
                }
                return true;
            }
        }
        return false;
    }

    // Main for quick testing
    public static void main(String[] args) {
        Trie4 trie = new Trie4();

        trie.insert("apple");
        trie.insert("app");
        trie.insert("bat");

        System.out.println(trie.search("app"));      // true
        System.out.println(trie.search("apple"));    // true
        System.out.println(trie.search("appl"));     // false
        System.out.println(trie.startsWith("ap"));   // true

        trie.delete("app");
        System.out.println(trie.search("app"));      // false
        System.out.println(trie.search("apple"));    // true
    }
}
