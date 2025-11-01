import java.util.*;

class Trie3 {

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    static class Trie {
        TrieNode root;

        Trie() {
            root = new TrieNode();
        }

        void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null)
                    node.children[index] = new TrieNode();
                node = node.children[index];
            }
            node.isEnd = true;
        }

        boolean allPrefixesExist(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                node = node.children[index];
                if (node == null || !node.isEnd)
                    return false;
            }
            return true;
        }
    }

    public static String longestWord(String[] words) {
        Trie trie = new Trie();
        for (String word : words) trie.insert(word);

        String result = "";
        for (String word : words) {
            if (trie.allPrefixesExist(word)) {
                if (word.length() > result.length() ||
                    (word.length() == result.length() && word.compareTo(result) < 0)) {
                    result = word;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] words = {"a", "banana", "app", "appl", "ap", "apply", "apple"};
        System.out.println("Longest Word with all prefixes: " + longestWord(words));
    }
}
