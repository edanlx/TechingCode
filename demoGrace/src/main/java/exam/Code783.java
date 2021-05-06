package exam;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * 二叉搜索树节点最小距离->最小值产生在某个节点左侧最大值，右侧最小值
 *
 * @author 876651109@qq.com
 * @date 2021/4/13 3:19 下午
 */
public class Code783 {
    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.builder().val(90)
                .left(TreeNode.builder().val(69)
                        .left(TreeNode.builder().val(49)
                                .right(TreeNode.builder().val(52).build())
                                .build())
                        .right(TreeNode.builder().val(89).build())
                        .build())
                .build();
        System.out.println(new Code783().minDiffInBST(treeNode));
    }

    @Data
    @ToString(callSuper = true)
    @Builder
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class TreeNode {
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
    int pre;
    int ans;
    public int minDiffInBST(TreeNode root) {
        ans = Integer.MAX_VALUE;
        pre = -1;
        dfs(root);
        return ans;
    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (pre == -1) {
            pre = root.val;
        } else {
            ans = Math.min(ans, root.val - pre);
            pre = root.val;
        }
        dfs(root.right);
    }
}
