package study;

/**
* @file UnionFindSet.java
* @CopyRight (C) http://blog.csdn.net/x_iya
* @Description 并查集
* @author N3verL4nd
* @email lgh1992314@qq.com
* @date 2017/9/13
*/
public class UnionFindSet {
    // father[x] 表示 x 的父节点
    private int father[];
    // rank[x] 表示 x 的秩
    private int rank[];

    public void MakeSet(int n) {
        for (int i = 1; i <= n; i++) {
            father[i] = i;
            rank[i] = 0;
        }
    }

    public int FindSet(int x) {
        if (x != father[x]) {
            father[x] = FindSet(father[x]);
        }
        return father[x];
    }

    public void UnionSet(int x, int y) {
        x = FindSet(x);
        y = FindSet(y);
        if (x == y) {
            return;
        }
        if (rank[x] > rank[y]) {
            father[y] = x;
        } else if (rank[x] < rank[y]) {
            father[x] = y;
        } else {
            rank[y]++;
            father[x] = y;
        }
    }

    public static void main(String[] args) {

    }
}
