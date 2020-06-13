package cn.bj.brook.interview;

/**
 * 多边形构成问题
 * 奇虎360校招面试题
 */
public class NShapeCompose {

    public int canComposeNShape(int[] edges) {
        // 多边形边数小于3，无法构成 或者  数组元素不够边数的
        if (edges.length < 3) {
            return -1;
        }
        // 多边形的和
        int sum = 0;
        // 一开始取最小值
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < edges.length; i++) {
            // 多边形的任意i条边之和
            sum += edges[i];
            // 只要大于下一个边
            max = max > edges[i] ? max : edges[i];
            // 当然i必须从第2个边开始，2条边不能构成多边形，至少3个
            if (i > 1 && sum > 2 * max) {
                return i + 1;
            }
        }
        return -1;
    }

    class Point {
        int x;
        int y;
    }

    public int minAreaOfSquare(Point[] points) {
        int maxX = 0;
        int maxY = 0;
        for (Point p : points) {
            if (maxX < p.x) {
                maxX = p.x;
            }
            if (maxY < p.y) {
                maxY = p.y;
            }
        }
        int mx = maxX > maxY ? maxX : maxY;
        return mx * mx;
    }

    public static void main(String[] args) {
        NShapeCompose nShapeCompose = new NShapeCompose();
        int t = nShapeCompose.canComposeNShape(new int[]{1, 2, 3, 4});
        System.out.println("t=" + t);

        Point p1 = nShapeCompose.new Point();
        p1.x = 2;
        p1.y = 2;

        Point p2 = nShapeCompose.new Point();
        p2.x = 1;
        p2.y = 3;

        t = nShapeCompose.minAreaOfSquare(new Point[]{p1, p2});
        System.out.println(t);
    }
}
