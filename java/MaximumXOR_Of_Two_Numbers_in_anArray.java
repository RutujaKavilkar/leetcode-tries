public class MaximumXOR_Of_Two_Numbers_in_anArray {
    // Each node of our binary Trie
    static class TrieNode {
        TrieNode[] children = new TrieNode[2];   // children[0] → path for bit 0, children[1] → path for bit 1
        TrieNode() {
            children[0] = null;
            children[1] = null;
        }
    }

    // Insert a number into the Trie by looking at each of its 32 bits
    private void insert(TrieNode root, int num) {
        TrieNode curr = root;                     // start from root
        for (int i = 31; i >= 0; i--) {            // check bits from MSB(31) to LSB(0)
            int bit = (num >> i) & 1;             // extract the i-th bit (0 or 1)
            if (curr.children[bit] == null) {     // if no path for this bit, create it
                curr.children[bit] = new TrieNode();
            }
            curr = curr.children[bit];            // move down to next bit
        }
    }

    // For a given number, find the number in the Trie giving maximum XOR
    private int findMaxXor(TrieNode root, int num) {
        TrieNode curr = root;
        int maxXor = 0;                            // store XOR result bit by bit
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;             // current bit of num
            int opp = bit ^ 1;                    // opposite bit (0->1 or 1->0)
            if (curr.children[opp] != null) {
                // If opposite bit exists, choose it to maximize XOR
                maxXor |= (1 << i);               // set i-th bit to 1 in answer
                curr = curr.children[opp];        // move to opposite path
            } else {
                // Otherwise follow same bit path
                curr = curr.children[bit];
            }
        }
        return maxXor;
    }

    public int findMaximumXOR(int[] nums) {
        TrieNode root = new TrieNode();

        // Step 1: insert all numbers into Trie
        for (int num : nums) {
            insert(root, num);
        }

        // Step 2: for each number, compute best XOR using the Trie
        int ans = 0;
        for (int num : nums) {
            ans = Math.max(ans, findMaxXor(root, num));
        }
        return ans;   // final maximum XOR
    }
}


