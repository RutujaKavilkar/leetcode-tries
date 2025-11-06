// File: TrieNotInSheet.java
public class TrieNotInSheet {

    private static class Node {
        Node[] next = new Node[26];
        boolean isWord = false;
        int passCount = 0; // how many words pass through this node (helps deletion)
    } 

    private final Node root;

    public TrieNotInSheet() {
        root = new Node();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.isEmpty()) return;
        Node cur = root;
        cur.passCount++;
        for (char ch : word.toCharArray()) {
            if (ch < 'a' || ch > 'z')
                throw new IllegalArgumentException("Only lowercase a-z supported");
            int idx = ch - 'a';
            if (cur.next[idx] == null) cur.next[idx] = new Node();
            cur = cur.next[idx];
            cur.passCount++;
        }
        cur.isWord = true;
    }

    /** Returns true if the word is in the trie. */
    public boolean search(String word) {
        if (word == null || word.isEmpty()) return false;
        Node cur = root;
        for (char ch : word.toCharArray()) {
            if (ch < 'a' || ch > 'z') return false;
            cur = cur.next[ch - 'a'];
            if (cur == null) return false;
        }
        return cur.isWord;
    }

    /** Returns true if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.isEmpty()) return false;
        Node cur = root;
        for (char ch : prefix.toCharArray()) {
            if (ch < 'a' || ch > 'z') return false;
            cur = cur.next[ch - 'a'];
            if (cur == null) return false;
        }
        return true;
    }

    /** Deletes a word from the trie. If word doesn't exist, nothing happens. */
    public void delete(String word) {
        if (word == null || word.isEmpty()) return;
        if (!search(word)) return; // nothing to delete
        deleteHelper(root, word, 0);
    }

    // returns true if the caller should remove the child reference
    private boolean deleteHelper(Node node, String word, int i) {
        if (i == word.length()) {
            node.isWord = false;
            node.passCount--;
            // if no children and not a word, signal to remove reference
            return node.passCount == 0 && !node.isWord;
        }
        char ch = word.charAt(i);
        int idx = ch - 'a';
        Node child = node.next[idx];
        if (child == null) return false; // shouldn't happen if we checked existence
        boolean shouldRemoveChild = deleteHelper(child, word, i + 1);
        // decrement passCount because one word that passed here is removed
        node.passCount--;
        if (shouldRemoveChild) {
            node.next[idx] = null;
        }
        // remove this node if it's not a word and has no children
        return node.passCount == 0 && !node.isWord;
    }

    // Simple demonstration
    public static void main(String[] args) {
        TrieNotInSheet trie = new TrieNotInSheet();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("apply");
        System.out.println(trie.search("app"));     // true
        System.out.println(trie.search("apple"));   // true
        System.out.println(trie.search("apples"));  // false
        System.out.println(trie.startsWith("ap"));  // true
        System.out.println(trie.startsWith("apl")); // false

        trie.delete("app");
        System.out.println(trie.search("app"));     // false
        System.out.println(trie.search("apple"));   // true (still present)
    }
}
