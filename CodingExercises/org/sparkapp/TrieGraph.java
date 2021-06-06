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

class TrieGraph {

    class Trie {

        /**
         * Initialize your data structure here.
         */
        class TrieNode {
            TrieNode[] children;
            Boolean endOfWord = false;
            public TrieNode() {
                children = new TrieNode[26];
            }
        }

        TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            int length = word.length();

            TrieNode nodePointer = this.root;
            for (int i = 0; i < length; i++) {
                // getting the character value at index i of the word.
                int index = word.charAt(i) - 'a';
                // check if the node pointer
                if (nodePointer.children[index] == null) {
                    nodePointer.children[index] = new TrieNode();
                }
                nodePointer = nodePointer.children[index];
            }
            nodePointer.endOfWord = true;           
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            int length = word.length();

            TrieNode nodePointer = this.root;
            for (int i = 0; i < length; i++) {
                // getting the character value at index i of the word.
                int index = word.charAt(i) - 'a';
                // check if the node pointer
                if (nodePointer.children[index] == null) {
                    return false;
                }
                nodePointer = nodePointer.children[index];
            }
            return (nodePointer != null && nodePointer.endOfWord);
        }

        /**
         * Returns if there is any word in the trie that starts with the given
         * prefix.
         */
        public boolean startsWith(String word) {
            int length = word.length();

            TrieNode nodePointer = this.root;
            for (int i = 0; i < length; i++) {
                // getting the character value at index i of the word.
                int index = word.charAt(i) - 'a';
                // check if the node pointer
                if (nodePointer.children[index] == null) {
                    return false;
                }
                nodePointer = nodePointer.children[index];
            }
            return true;
        }
    }
    
    public void run() {
    }
    
    public static void main(String[] args) {
        new TrieGraph().run();
    }
}
