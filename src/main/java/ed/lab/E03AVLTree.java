package ed.lab;

import java.util.Comparator;

public class E03AVLTree<T> {

    private final Comparator<T> comparator;
    private TreeNode<T> root;
    private int size = 0;

    public E03AVLTree(Comparator<T> comparator) {
        this.comparator = comparator;
        this.root = null;
        this.size = 0;
    }

    private int balanceFactor(TreeNode<T> root) {
        if (root == null) {
            return 0;
        }
        return height(root.left) - height(root.right);
    }

    private int height(TreeNode<T> root) {
        if(root!=null)
        {
            int LEFT = height(root.left);
            int RIGHT = height(root.right);
            if(LEFT>RIGHT)
                {
                    return 1+LEFT;
                }
            else
                {
                    return RIGHT+1;
                }
        }
        else
            return 0;
    }

    private TreeNode<T> rotateRight(TreeNode<T> root) {
        if (root == null || root.left == null) return root;

        TreeNode<T> newRoot = root.left;
        TreeNode<T> Aux = newRoot.right;

        newRoot.right = root;
        root.left = Aux;

        return newRoot;
    }

    private TreeNode<T> rotateLeft(TreeNode<T> root) {
        if (root == null || root.right == null) return root;

        TreeNode<T> newRoot = root.right;
        TreeNode<T> Aux = newRoot.left;

        newRoot.left = root;
        root.right = Aux;

        return newRoot;
    }

    private TreeNode<T> balance(TreeNode<T> root) {
        if (root == null) {
            return root;
        }
        int balance = balanceFactor(root);
        if (balance > 1) {
            if (balanceFactor(root.left) < 0) {
                root.left = rotateLeft(root.left);
            }
            return rotateRight(root);
        }
        if (balance < -1) {
            if (balanceFactor(root.right) > 0) {
                root.right = rotateRight(root.right);
            }
            return rotateLeft(root);
        }
        return root;
    }

    public void insert(T value) {
        root = insert(root, value);
    }

    private TreeNode<T> insert(TreeNode<T> root, T value)
    {
        if(root == null)
        {
            size++;
            return new TreeNode<>(value);
        }

        if(comparator.compare(value, root.value) == 0)
        {
            return root;
        }

        if(comparator.compare(value, root.value) < 0)
        {
            root.left = insert(root.left, value);
        }
        else
        {
            root.right = insert(root.right, value);
        }
        root = balance(root);
        return root;
    }


    public void delete(T value) {
        if(root == null) return;
        root = delete(root, value);
    }
    public TreeNode<T> delete(TreeNode<T> root, T key) {
        if (root == null) {
            return null;
        }

        int valor = comparator.compare(key, root.value);

        if (valor > 0) {
            root.right = delete(root.right, key);
        } else if (valor < 0) {
            root.left = delete(root.left, key);
        } else {
            if (root.left == null || root.right == null) {
                size--;
                return (root.left != null) ? root.left : root.right;
            }

            TreeNode<T> aux = findInOrderSuccessor(root.right);
            root.value = aux.value;
            root.right = delete(root.right, aux.value);
        }

        return balance(root);
    }
    private TreeNode<T> findInOrderSuccessor(TreeNode<T> root){
        if(root == null) return null;
        TreeNode<T> temp = root;
        while (temp.left != null) {
            temp = temp.left;
        }
        return temp;
    }

    public T search(T value) {
        TreeNode<T> node = root;
        while (node != null) {
            int cmp = comparator.compare(value, node.value);
            if (cmp < 0) node = node.left;
            else if (cmp > 0) node = node.right;
            else return node.value;
        }
        return null;
    }

    public int height() {
        return height(root);
    }

    public int size() {
        return size;
    }
}
