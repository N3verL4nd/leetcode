package study;

import utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;


public class BinaryTree {
    private int[] arr;
    private int count;

    public BinaryTree(int[] arr) {
        this.arr = arr;
    }


    /**
     * 构建二叉树(,代表空结点)
     *
     * @return 根结点
     */
    public TreeNode Create() {
        if (count >= arr.length || arr[count] == -1) {
            count++;// 跳过空结点
            return null;
        }
        TreeNode root = new TreeNode(arr[count++]);
        root.left = Create();
        root.right = Create();
        return root;
    }

    /**
     * 先序遍历
     *
     * @param root
     */
    public void preOrder(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    /**
     * 中序遍历
     *
     * @param root
     */
    public void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.val + " ");
            inOrder(root.right);
        }
    }

    /**
     * 后序遍历
     *
     * @param root
     */
    public void postOrder(TreeNode root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.val + " ");
        }
    }

    /**
     * 层次遍历
     *
     * @param root
     */
    public void levOrder(TreeNode root) {
        if (root != null) {
            TreeNode p = root;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(p);
            while (!queue.isEmpty()) {
                p = queue.poll();
                System.out.print(p.val + " ");
                if (p.left != null) {
                    queue.add(p.left);
                }
                if (p.right != null) {
                    queue.add(p.right);
                }
            }
        }
    }

    /**
     * 计算叶子节点的个数
     *
     * @param root
     * @return
     */
    public int getSize(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        } else {
            return getSize(root.left) + getSize(root.right);
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
        return (leftHeight > rightHeight) ? (leftHeight + 1) : (rightHeight + 1);
    }

    public static void main(String[] args) {
        int[] str = {'A', 'B', 'C', ',', ',', 'D', 'E', ',', 'G', ',', ',', 'F', ',', ',', ','};
        BinaryTree binaryTree = new BinaryTree(str);
        TreeNode root = binaryTree.Create();
        System.out.println("先序遍历：");
        binaryTree.preOrder(root);

        System.out.println("\n中序遍历：");
        binaryTree.inOrder(root);

        System.out.println("\n后序遍历：");
        binaryTree.postOrder(root);

        System.out.println("\n层次遍历：");
        binaryTree.levOrder(root);

        System.out.println("\n二叉树叶子结点数：" + binaryTree.getSize(root));
        System.out.println("二叉树高度：" + binaryTree.getHeight(root));
    }
}
