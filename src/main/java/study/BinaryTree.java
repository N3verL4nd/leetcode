package study;

import utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;


public class BinaryTree {
    private final char[] arr;
    private int pos;

    public BinaryTree(char[] arr) {
        this.arr = arr;
    }

    private void printValue(TreeNode node) {
        if (node != null) {
            System.out.print((char) node.val.intValue() + " ");
        }
    }


    /**
     * 构建二叉树(,代表空结点)
     *
     * @return 根结点
     */
    public TreeNode Create() {
        if (pos >= arr.length) {
            return null;
        }
        if (arr[pos] == ',') {
            // 跳过空结点
            pos++;
            return null;
        }
        TreeNode root = new TreeNode(arr[pos]);
        pos++;
        root.left = Create();
        root.right = Create();
        return root;
    }

    /**
     * 先序遍历-递归
     *
     * @param root
     */
    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        printValue(root);
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * 先序遍历-非递归
     *
     * @param root
     */
    public void preorderTraversal(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                printValue(root);
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                root = root.right;
            }
        }
    }

    /**
     * 中序遍历-递归
     *
     * @param root
     */
    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        printValue(root);
        inOrder(root.right);
    }

    /**
     * 中序遍历-非递归
     *
     * @param root
     */
    public void inorderTraversal(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();

        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                printValue(root);
                root = root.right;
            }
        }
    }

    /**
     * 后序遍历
     *
     * @param root
     */
    public void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        printValue(root);
    }

    public void postorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> leftStack = new LinkedList<>();
        LinkedList<TreeNode> rightStack = new LinkedList<>();
        leftStack.push(root);
        while (!leftStack.isEmpty()) {
            root = leftStack.pop();
            rightStack.push(root);
            if (root.left != null) {
                leftStack.push(root.left);
            }
            if (root.right != null) {
                leftStack.push(root.right);
            }
        }
        while (!rightStack.isEmpty()) {
            printValue(rightStack.pop());
        }
    }

    /**
     * 层次遍历
     *
     * @param root
     */
    public void levelOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode p = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(p);
        while (!queue.isEmpty()) {
            p = queue.poll();
            printValue(p);
            if (p.left != null) {
                queue.add(p.left);
            }
            if (p.right != null) {
                queue.add(p.right);
            }
        }
    }

    /**
     * 计算叶子节点的个数
     *
     * @param root
     * @return
     */
    public int getLeafCount(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        } else {
            return getLeafCount(root.left) + getLeafCount(root.right);
        }
    }

    /**
     * 计算二叉树的高度
     *
     * @param root
     * @return
     */
    public int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        return Math.max(leftHeight + 1, rightHeight + 1);
    }

    public static void main(String[] args) {
        char[] str = {'A', 'B', 'C', ',', ',', 'D', 'E', ',', 'G', ',', ',', 'F', ',', ',', ','};
        BinaryTree binaryTree = new BinaryTree(str);
        TreeNode root = binaryTree.Create();
        System.out.println("先序遍历：");
        binaryTree.preOrder(root);
        System.out.println();
        binaryTree.preorderTraversal(root);
        System.out.println("\n中序遍历：");
        binaryTree.inOrder(root);
        System.out.println();
        binaryTree.inorderTraversal(root);

        System.out.println("\n后序遍历：");
        binaryTree.postOrder(root);
        System.out.println();
        binaryTree.postorderTraversal(root);

        System.out.println("\n层次遍历：");
        binaryTree.levelOrder(root);

        System.out.println("\n二叉树叶子结点数：" + binaryTree.getLeafCount(root));
        System.out.println("二叉树高度：" + binaryTree.getHeight(root));
    }
}
