/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
 import java.util.*;

class Solution {
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> nodeMap = new HashMap<>();
        Set<Integer> children = new HashSet<>();
        
        for (int[] d : descriptions) {
            int parentVal = d[0];
            int childVal = d[1];
            boolean isLeft = d[2] == 1;
            
            nodeMap.putIfAbsent(parentVal, new TreeNode(parentVal));
            TreeNode parentNode = nodeMap.get(parentVal);
            
            nodeMap.putIfAbsent(childVal, new TreeNode(childVal));
            TreeNode childNode = nodeMap.get(childVal);
            
            if (isLeft) {
                parentNode.left = childNode;
            } else {
                parentNode.right = childNode;
            }
            
            children.add(childVal);
        }
        
        for (int[] d : descriptions) {
            int parentVal = d[0];
            if (!children.contains(parentVal)) {
                return nodeMap.get(parentVal);
            }
        }
        
        return null;
    }
}