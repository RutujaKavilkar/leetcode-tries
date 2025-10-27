import java.util.*;


class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd = false;
}

class Trie {
    private TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    // Insert root word into the Trie
    void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (node.children[idx] == null)
                node.children[idx] = new TrieNode();
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    // Search the shortest prefix that matches
    String getShortestRoot(String word) {
        TrieNode node = root;
        StringBuilder prefix = new StringBuilder();

        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (node.children[idx] == null)
                return word; // No prefix found

            prefix.append(ch);
            node = node.children[idx];

            if (node.isEnd)
                return prefix.toString(); // Found shortest root
        }

        return word; // No prefix found
    }
}

public class Tries {
    public String replaceWords(List<String> dictionary, String sentence) {
        Trie trie = new Trie();

        // Step 1: Insert all roots
        for (String root : dictionary)
            trie.insert(root);

        // Step 2: Replace each word in sentence
        StringBuilder result = new StringBuilder();
        for (String word : sentence.split(" ")) {
            if (result.length() > 0) result.append(" ");
            result.append(trie.getShortestRoot(word));
        }

        return result.toString();
    }

    // Test
    public static void main(String[] args) {
        Tries sol = new Tries();
        List<String> dict = Arrays.asList("cat", "bat", "rat");
        String sentence = "the cattle was rattled by the battery";
        System.out.println(sol.replaceWords(dict, sentence));
    }
}



