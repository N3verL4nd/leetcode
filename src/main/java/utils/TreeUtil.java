package utils;

import java.util.LinkedList;
import java.util.Queue;


public class TreeUtil {
    private static int pos;

    /**
     * 前序遍历构建二叉树(-1代表空结点)
     *
     * @param arr 结点数组
     * @return 根结点
     */
    public static TreeNode createTree(Integer... arr) {
        if (pos >= arr.length) {
            return null;
        }
        if (arr[pos] == null || arr[pos] == -1) {
            pos++;
            return null;
        }

        TreeNode root = new TreeNode(arr[pos]);
        pos++;
        root.left = createTree(arr);
        root.right = createTree(arr);
        return root;
    }

    public static TreeNode createTreeLevelOrder(Integer... arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        pos = 0;
        TreeNode root = new TreeNode(arr[pos]);
        pos++;
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();

            if (pos >= arr.length) {
                break;
            }

            if (arr[pos] != null) {
                poll.left = new TreeNode(arr[pos]);
                queue.offer(poll.left);
            } else {
                poll.left = null;
            }

            pos++;

            if (arr[pos] != null) {
                poll.right = new TreeNode(arr[pos]);
                queue.offer(poll.right);
            } else {
                poll.right = null;
            }

            pos++;
        }
        return root;
    }

    /**
     * 先序遍历
     *
     * @param root
     */
    public static void preOrder(TreeNode root) {
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
    public static void inOrder(TreeNode root) {
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
    public static void postOrder(TreeNode root) {
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
    public static void levOrder(TreeNode root) {
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
    public static int getSize(TreeNode root) {
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
    public static int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        return (leftHeight > rightHeight) ? (leftHeight + 1) : (rightHeight + 1);
    }

    /**
     * 重置 pos
     */
    public static void reset() {
        pos = 0;
    }

    public static void main(String[] args) {
        Integer[] arr = {5, 4, 11, 7, -1, -1, 2, -1, -1, -1, 8, 13, -1, -1, 4, 5, -1, -1, 1, -1, -1};
        TreeNode root = createTree(arr);
        System.out.println("先序遍历：");
        preOrder(root);

        System.out.println("\n中序遍历：");
        inOrder(root);

        System.out.println("\n后序遍历：");
        postOrder(root);

        System.out.println("\n层次遍历：");
        levOrder(root);

        System.out.println("\n二叉树叶子结点数：" + getSize(root));
        System.out.println("二叉树高度：" + getHeight(root));


        TreeNode treeLevelOrder = createTreeLevelOrder(10, 5, -3, 3, 2, null, 11, 3, -2, null, 1);
        levOrder(treeLevelOrder);
    }
}
