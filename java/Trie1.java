import java.util.*;

public class Trie1 {

    // --- TrieNode class ---
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd;
        int childCount; // helps to track divergence
    }

    // --- Trie class ---
    static class Trie {
        TrieNode root;

        Trie() {
            root = new TrieNode();
        }

        // Insert word into Trie
        void insert(String word) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new TrieNode();
                    node.childCount++;
                }
                node = node.children[idx];
            }
            node.isEnd = true;
        }

        // Find Longest Common Prefix using the first word as reference
        String getLCP(String firstWord) {
            StringBuilder prefix = new StringBuilder();
            TrieNode node = root;

            for (char ch : firstWord.toCharArray()) {
                int idx = ch - 'a';
                // if only one child and not end of word, continue prefix
                if (node.childCount == 1 && !node.isEnd) {
                    prefix.append(ch);
                    node = node.children[idx];
                } else {
                    break;
                }
            }
            return prefix.toString();
        }
    }

    // --- Main logic ---
    public static String longestCommonPrefix(String[] strs) {
        Trie trie = new Trie();

        for (String word : strs) {
            trie.insert(word);
        }

        return trie.getLCP(strs[0]);
    }

    // --- Main method ---
    public static void main(String[] args) {
        String[] words = {"flower", "flow", "flight"};
        System.out.println("Longest Common Prefix: " + longestCommonPrefix(words));
    }
}
