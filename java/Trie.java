import java.util.*;

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd = false;
}

class Trie {
    TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    // Insert word into Trie
    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (node.children[idx] == null)
                node.children[idx] = new TrieNode();
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    // Search prefix node
    private TrieNode searchPrefix(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            int idx = ch - 'a';
            if (node.children[idx] == null)
                return null;
            node = node.children[idx];
        }
        return node;
    }

    // DFS to collect all words
    private void dfs(TrieNode node, StringBuilder path, List<String> res) {
        if (node == null) return;
        if (node.isEnd) res.add(path.toString());

        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                path.append((char) (i + 'a'));
                dfs(node.children[i], path, res);
                path.deleteCharAt(path.length() - 1); // backtrack
            }
        }
    }

    // Get all words with given prefix
    public List<String> getWordsWithPrefix(String prefix) {
        List<String> res = new ArrayList<>();
        TrieNode node = searchPrefix(prefix);
        if (node == null) return res;

        dfs(node, new StringBuilder(prefix), res);
        return res;
    }
}

public class AutocompleteSystem {
    public static void main(String[] args) {
        Trie trie = new Trie();
        String[] words = {"apple", "app", "apricot", "bat", "ball"};
        for (String word : words) trie.insert(word);

        String prefix = "ap";
        List<String> results = trie.getWordsWithPrefix(prefix);

        System.out.println("Autocomplete results for \"" + prefix + "\": " + results);
    }
}

