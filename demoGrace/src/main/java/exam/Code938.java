package exam;

public class Code938 {
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

    /**
     * 中序遍历，如果不在范围内则直接return
     */
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(10);
        TreeNode treeNode2 = new TreeNode(5);
        TreeNode treeNode3 = new TreeNode(15);
        TreeNode treeNode4 = new TreeNode(3);
        TreeNode treeNode5 = new TreeNode(7);
        TreeNode treeNode6 = new TreeNode(18);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.right = treeNode6;
        System.out.println(new Code938().rangeSumBST(treeNode1, 7, 15));
    }
    int result = 0;
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        // 如果小于low则左边不用走，如果大于high则右边不用走
        // ->如果走左边那么大于等于low，如果走右边那么小于等于high
        if (root.val >= low && root.left != null) {
            rangeSumBST(root.left, low, high);
        }
        if (root.val >= low && root.val <= high) {
            result += root.val;
        }
        if (root.val <= high && root.right != null) {
            rangeSumBST(root.right, low, high);
        }
        return result;
    }
}
