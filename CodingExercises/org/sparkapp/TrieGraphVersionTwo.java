/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sparkapp;

/**
 *
 * @author Ronnie
 */
import java.util.*;

class TrieGraphVersionTwo {

    class Trie {

        /**
         * Initialize your data structure here.
         */
        class TrieNode {

            // R links to node children
            private TrieNode[] links;
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

        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // Inserts a word into the trie.
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

        // Returns if the word is in the trie.
        public boolean search(String word) {
            TrieNode node = searchPrefix(word);
            return node != null && node.isEnd();
        }

        public boolean startsWith(String word) {
            TrieNode node = searchPrefix(word);
            return node != null;
        }
    }

    private void show(Object s) {
        System.out.println(s.toString());
    }

    public void run() {
        /*
        Input
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
Output
[null, null, true, false, true, null, true]
         */

        Trie trie = new Trie();
        trie.insert("apple");
        show(trie.search("apple"));   // return True
        show(trie.search("app"));     // return False
        show(trie.startsWith("app")); // return True
        trie.insert("app");
        show(trie.search("app"));     // return True
    }

    public static void main(String[] args) {
        new TrieGraphVersionTwo().run();
    }
}
