package exam;

public class Code897 {

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

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(5);
        TreeNode treeNode2 = new TreeNode(3);
        TreeNode treeNode3 = new TreeNode(6);
        TreeNode treeNode4 = new TreeNode(2);
        TreeNode treeNode5 = new TreeNode(4);
        TreeNode treeNode7 = new TreeNode(8);
        TreeNode treeNode8 = new TreeNode(1);
        TreeNode treeNode9 = new TreeNode(7);
        TreeNode treeNode10 = new TreeNode(9);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.right = treeNode7;
        treeNode4.left = treeNode8;
        treeNode7.left = treeNode9;
        treeNode7.right = treeNode10;
        TreeNode treeNode = new Code897().increasingBST(treeNode1);
        System.out.println(treeNode);
    }

    TreeNode treeNode = null;
    TreeNode currentTreeNode = null;
    TreeNode resNode = new TreeNode(-1);

    public TreeNode increasingBST(TreeNode root) {
        if (root != null) {
            if (root.left != null) {
                increasingBST(root.left);
            }
            if (treeNode == null) {
                treeNode = new TreeNode(root.val);
                currentTreeNode = treeNode;
            } else {
                currentTreeNode.right = new TreeNode(root.val);
                currentTreeNode = currentTreeNode.right;
            }
            if (root.right != null) {
                increasingBST(root.right);
            }
        }
        return treeNode;
    }
}
