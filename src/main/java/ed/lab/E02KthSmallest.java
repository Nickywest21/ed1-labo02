package ed.lab;
import java.util.Stack;

public class E02KthSmallest {

    public int kthSmallest(TreeNode<Integer> root, int k) {
        Stack<TreeNode> stack = new Stack<>();

        while(!stack.empty() || root != null)
        {
            while(root != null)
            {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            k--;

            if(k == 0)
            {
                return root.value;
            }
            root = root.right;
        }
        return 0;
    }

}