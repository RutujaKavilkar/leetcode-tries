import java.util.*;

class Trie2 {
    static class Node {
        Node[] links = new Node[26];
        boolean flag = false;

        boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        Node get(char ch) {
            return links[ch - 'a'];
        }

        void put(char ch, Node node) {
            links[ch - 'a'] = node;
        }

        void setEnd() {
            flag = true;
        }

        boolean isEnd() {
            return flag;
        }
    }

    static class Trie {
        private static Node root;

        Trie() {
            root = new Node();
        }

        void insert(String word) {
            Node node = root;
            for (char ch : word.toCharArray()) {
                if (!node.containsKey(ch)) {
                    node.put(ch, new Node());
                }
                node = node.get(ch);
            }
            node.setEnd();
        }

        // Check if all prefixes of the word exist
        boolean allPrefixesExist(String word) {
            Node node = root;
            for (char ch : word.toCharArray()) {
                if (node.containsKey(ch)) {
                    node = node.get(ch);
                    if (!node.isEnd()) return false;
                } else {
                    return false;
                }
            }
            return true;
        }
    }

    public static String longestWord(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        String longest = "";
        for (String word : words) {
            if (trie.allPrefixesExist(word)) {
                if (word.length() > longest.length() ||
                    (word.length() == longest.length() && word.compareTo(longest) < 0)) {
                    longest = word;
                }
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        String[] words = {"n", "ni", "nin", "ninj", "ninja", "ninga"};
        System.out.println("Longest word: " + longestWord(words));
    }
}
