package utils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author liguanghui02
 * @date 2021/3/13
 */
public class BinaryTreeUtil {
    public static TreeNode create(Integer... arr) {
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int pos = 1;
        while (pos < arr.length) {
            TreeNode poll = queue.poll();

            assert poll != null;

            if (arr[pos] == null) {
                poll.left = null;
            } else {
                TreeNode leftNode = new TreeNode(arr[pos]);
                poll.left = leftNode;
                queue.offer(leftNode);
            }
            pos++;

            if (arr[pos] == null) {
                poll.right = null;
            } else {
                TreeNode rightNode = new TreeNode(arr[pos]);
                poll.right = rightNode;
                queue.offer(rightNode);
            }
            pos++;
        }
        return root;
    }

    /**
     * 层次遍历
     *
     * @param root
     */
    public static void levOrder(TreeNode root) {
        if (root != null) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    root = queue.poll();
                    assert root != null;
                    System.out.print(root.val + " ");
                    if (root.left != null) {
                        queue.offer(root.left);
                    }
                    if (root.right != null) {
                        queue.offer(root.right);
                    }
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = create(1, 2, 3, 4, 5, null, 6, 7, null, null, null, null, 8);
        levOrder(root);
    }
}
