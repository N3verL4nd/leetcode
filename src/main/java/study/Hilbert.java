package study;

class Point {
    public int x;   // X坐标
    public int y;   // X坐标

    public Point() {
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Hilbert {

    private void rot(int n, Point pt, int rx, int ry) {
        if (ry == 0) {
            if (rx == 1) {
                pt.x = n - 1 - pt.x;
                pt.y = n - 1 - pt.y;
            }

            //Swap x and y
            int temp = pt.x;
            pt.x = pt.y;
            pt.y = temp;
        }
    }

    //Hilbert代码到XY坐标
    public void d2xy(int n, int d, Point pt) {
        int rx, ry, s, t = d;
        pt.x = pt.y = 0;
        for (s = 1; s < n; s *= 2) {
            rx = 1 & (t / 2);
            ry = 1 & (t ^ rx);
            rot(s, pt, rx, ry);
            pt.x += s * rx;
            pt.y += s * ry;
            t /= 4;
        }
    }

    //XY坐标到Hilbert代码转换
    public int xy2d(int n, Point pt) {
        int rx, ry, s, d = 0;
        for (s = n / 2; s > 0; s /= 2) {
            rx = ((pt.x & s) > 0) ? 1 : 0;
            ry = ((pt.y & s) > 0) ? 1 : 0;
            d += s * s * ((3 * rx) ^ ry);
            rot(s, pt, rx, ry);
        }
        return d;
    }

    public static void main(String[] args) {
        Hilbert hilbert = new Hilbert();
        int n = 8;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%2d ", hilbert.xy2d(n, new Point(j, i)));
            }
            System.out.println();
        }
    }
}
