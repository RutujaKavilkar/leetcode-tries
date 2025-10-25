public class  ImplementTrie{
class Trie {
    static class Trie{
        Trie[] children;
        boolean eow;
        
    }

    public Trie() {
        children=new Trie[26];
        for(int i=0;i<26;i++)
        {
            children[i]=null;
        }eow=false;
    }
    
    public void insert(String word) {
        Node curr=root;
        for(int i=0;i<word.length();i++)
        {
            int idx=word.charAt(i)-'a';
            if(curr.children[idx]==null)
            {
                curr.children[idx]=new node;
            }
            if(i==word.length()-1)
            {
                curr.children[idx].eow=true;
            }
            curr=curr.children[idx];
        }
    }
    
    public boolean search(String word) {
        Node curr=root;
        for(int i=0;i<key.length();i++)
        {
            int idx=key.charAt(i)-'a';
            Node node=curr.children[idx];
            if(node==null)
            {
                return false;
            }
            if(node==null)
            {
                return false;
            }
            if(i==key.length()-1&&node.eow==false)
            {
                return false;
            }
            curr=curr.children[idx];
        }return true;
    }
    
    public boolean startsWith(String prefix) {
        Node curr=root;
        for(int i=0;i<prefix.length;i++)
        {
            int idx=prefix.charAt(i)-'a';
            if(curr.children[idx]==null)
            {
                return false;
            }curr=curr.children[idx];
        }return true;
    }
}
}