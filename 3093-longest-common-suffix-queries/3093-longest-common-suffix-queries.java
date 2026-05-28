import java.util.Arrays;

class Solution {
    // Define the Trie Node structure
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        // Stores the index of the best matching word from wordsContainer for this suffix path
        int bestIndex = -1; 
    }

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        TrieNode root = new TrieNode();
        
        // Track the global default best index (for words with no common suffix / empty string match)
        int defaultBestIndex = 0;
        for (int i = 1; i < wordsContainer.length; i++) {
            if (shouldUpdate(wordsContainer, i, defaultBestIndex)) {
                defaultBestIndex = i;
            }
        }
        root.bestIndex = defaultBestIndex;

        // Step 1: Build the Trie by inserting words in reverse
        for (int i = 0; i < wordsContainer.length; i++) {
            String word = wordsContainer[i];
            TrieNode curr = root;
            
            // Traverse the word backwards
            for (int j = word.length() - 1; j >= 0; j--) {
                int charIdx = word.charAt(j) - 'a';
                if (curr.children[charIdx] == null) {
                    curr.children[charIdx] = new TrieNode();
                }
                curr = curr.children[charIdx];
                
                // Update the node's best index based on tie-breaking rules
                if (curr.bestIndex == -1 || shouldUpdate(wordsContainer, i, curr.bestIndex)) {
                    curr.bestIndex = i;
                }
            }
        }

        // Step 2: Answer each query using the Trie
        int[] ans = new int[wordsQuery.length];
        for (int i = 0; i < wordsQuery.length; i++) {
            String query = wordsQuery[i];
            TrieNode curr = root;
            int lastValidBestIndex = root.bestIndex;
            
            // Search backwards
            for (int j = query.length() - 1; j >= 0; j--) {
                int charIdx = query.charAt(j) - 'a';
                if (curr.children[charIdx] == null) {
                    break; // No deeper common suffix found
                }
                curr = curr.children[charIdx];
                lastValidBestIndex = curr.bestIndex; // Update to the deeper, longer match
            }
            ans[i] = lastValidBestIndex;
        }

        return ans;
    }

    /**
     * Helper method to determine if the 'newIdx' is a better candidate than 'currentBestIdx'
     * based on the problem's criteria.
     */
    private boolean shouldUpdate(String[] words, int newIdx, int currentBestIdx) {
        if (words[newIdx].length() < words[currentBestIdx].length()) {
            return true;
        }
        if (words[newIdx].length() == words[currentBestIdx].length()) {
            return newIdx < currentBestIdx; // Earliest occurrence tie-breaker
        }
        return false;
    }
}