
    // CountDistinctSubstrings.java
public class CountDistinctSubstrings {
    static class TrieNode {
        TrieNode[] next = new TrieNode[26];
        // no need to store end flags for this problem
    }

    private TrieNode root;
    private int nodeCount; // counts total nodes in trie (including root)

    public CountDistinctSubstrings() {
        root = new TrieNode();
        nodeCount = 1; // root exists
    }

    // insert a string into trie; create nodes when needed
    private void insert(String s) {
        TrieNode cur = root;
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            if (cur.next[c] == null) {
                cur.next[c] = new TrieNode();
                nodeCount++;
            }
            cur = cur.next[c];
        }
    }

    // main method to count distinct substrings using suffix-trie idea
    public int countDistinctSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            // insert suffix s[i..n-1]
            insert(s.substring(i));
        }
        // number of distinct substrings = total nodes - 1 (exclude root)
        return nodeCount - 1;
    }

    // quick test
    public static void main(String[] args) {
        CountDistinctSubstrings sol = new CountDistinctSubstrings();
        String s = "ababa";
        System.out.println("Distinct substrings of \"" + s + "\": " + sol.countDistinctSubstrings(s));
        // expected 9
    }
}


