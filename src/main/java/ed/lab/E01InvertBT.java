package ed.lab;

public class E01InvertBT {
    public TreeNode<Integer> invertTree(TreeNode<Integer> root) {
        if(root == null)
        {
            return root;
        }

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root. right);

        root.right = left;
        root.left = right;

        return root;
    }
}
