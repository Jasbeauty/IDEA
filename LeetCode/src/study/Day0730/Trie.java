package study.Day0730;

public class Trie {

    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     * <p>
     * 1、从头到尾遍历字符串的每一个字符
     * 2、从根节点开始插入，若该字符存在，那就不用插入新节点；要是不存在，则插入新节点
     * 3、然后顺着插入的节点一直按照上述方法插入剩余的节点
     * <p>
     * 时间复杂度：O(m)，其中 m 为键长。在算法的每次迭代中，我们要么检查要么创建一个节点，直至到达键尾。只需要 m 次操作
     * 空间复杂度：O(m)。最坏的情况下，新插入的键和 Trie 树中已有的键没有公共前缀。此时需要添加 m 个结点，使用 O(m) 空间
     */
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!node.containsKey(currentChar)) {
                node.put(currentChar, new TrieNode());
            }
            node = node.get(currentChar);
        }
        node.setEnd();
    }

    /**
     * Returns if the word is in the trie.
     * <p>
     * 1、查询字符串是从第一个字符开始的
     * 2、当查询的位置已经超过了字符串的长度，比如要查的是“adc”,但是我们查到树的深度已经超过了c，那么肯定是不存在的
     * 3、如果查询的位置刚好为字符串的长度，这时就可以判断当前节点子节点是否符合要求，若存在，则字符串存在，否则不存在
     * 4、其余情况则需要继续深入查询，若符合要求的子节点存在，则继续查询，否则不存在
     * <p>
     * 时间复杂度 : O(m)。算法的每一步均搜索下一个键字符。最坏的情况下需要 m 次操作
     * 空间复杂度 : O(1)
     */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    // search a prefix or whole key in trie and
    // returns the node where search ends
    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char curLetter = word.charAt(i);
            if (node.containsKey(curLetter)) {
                node = node.get(curLetter);
            } else {
                return null;
            }
        }
        return node;
    }


    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     * <p>
     * 与上述的查询方法非常相似：
     * 从根遍历 Trie 树，直到键前缀中没有字符，或者无法用当前的键字符继续 Trie 中的路径
     * 唯一的区别是，到达键前缀的末尾时，总是返回 true
     * 我们不需要考虑当前 Trie 节点是否用 “isend” 标记，因为我们搜索的是键的前缀，而不是整个键
     * <p>
     * 时间复杂度 : O(m)
     * 空间复杂度 : O(1)
     */
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // 返回 true
        System.out.println(trie.search("app"));     // 返回 false
        System.out.println(trie.startsWith("app"));       // 返回 true
        trie.insert("app");
        System.out.println(trie.search("app"));     // 返回 true
    }
}

class TrieNode {
    private TrieNode[] links;
    // R links to node children
    private final int R = 26;

    private boolean isEnd;

    public TrieNode() {
        links = new TrieNode[R];
    }

    public boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }

    public TrieNode get(char ch) {
        return links[ch - 'a'];
    }

    public void put(char ch, TrieNode node) {
        links[ch - 'a'] = node;
    }

    public void setEnd() {
        isEnd = true;
    }

    public boolean isEnd() {
        return isEnd;
    }
}