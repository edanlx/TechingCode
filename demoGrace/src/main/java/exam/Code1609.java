package exam;

import lombok.Builder;

import java.util.HashMap;
import java.util.Map;

public class Code1609 {

    @Builder
    static public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Code1609().isEvenOddTree(TreeNode.builder().val(1)
                .left(TreeNode.builder().val(2)
                        .left(TreeNode.builder().val(3).build())
                        .build())
                .right(TreeNode.builder().val(4).build()).build()));

    }

    boolean result = true;

    public boolean isEvenOddTree(TreeNode root) {
        execute(root, 0, new HashMap<>());
        return result;
    }

    public void execute(TreeNode node, int floor, Map<Integer, Integer> map) {
        if (node == null) {
            return;
        }
        if (!result) {
            return;
        }
        if ((floor & 1) == 0) {
            // 偶数
            if ((node.val & 1) == 0) {
                // 偶数
                result = false;
            }
        } else {
            // 奇数
            if ((node.val & 1) == 1) {
                // 奇数
                result = false;
            }
        }
        Integer integer = map.get(floor);
        if (integer == null) {
            map.put(floor, node.val);
        } else {
            if ((floor & 1) == 0) {
                // 偶数
                if (integer >= node.val) {
                    result = false;
                }
            } else {
                if (integer <= node.val) {
                    result = false;
                }
            }
        }
        execute(node.left, floor + 1, map);
        execute(node.right, floor + 1, map);
    }
}
