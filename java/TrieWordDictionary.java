
class TrieWordDictionary {

    private static class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;

        TrieNode() {
            children = new TrieNode[26];
            isEndOfWord = false;
        }
    }

    private TrieNode root;

    public TrieWordDictionary() {
        root = new TrieNode();
    }

    // Add a word to the Trie
    public void addWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null)
                node.children[index] = new TrieNode();
            node = node.children[index];
        }
        node.isEndOfWord = true;
    }

    // Search a word (supports '.' wildcard)
    public boolean search(String word) {
        return dfsSearch(word, 0, root);
    }

    private boolean dfsSearch(String word, int index, TrieNode node) {
        if (node == null) return false;
        if (index == word.length()) return node.isEndOfWord;

        char c = word.charAt(index);
        if (c == '.') {
            for (TrieNode child : node.children) {
                if (child != null && dfsSearch(word, index + 1, child))
                    return true;
            }
            return false;
        } else {
            return dfsSearch(word, index + 1, node.children[c - 'a']);
        }
    }

    // For testing
    public static void main(String[] args) {
        TrieWordDictionary dict = new TrieWordDictionary();
        dict.addWord("bad");
        dict.addWord("dad");
        dict.addWord("mad");

        System.out.println(dict.search("pad")); // false
        System.out.println(dict.search("bad")); // true
        System.out.println(dict.search(".ad")); // true
        System.out.println(dict.search("b..")); // true
    }
}