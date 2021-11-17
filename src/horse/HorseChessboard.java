package horse;

import javax.xml.transform.Source;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

//骑士周游回溯算法,马踏棋盘
public class HorseChessboard {
    private static int X;//棋盘的列数
    private static int Y;//棋盘的行数
    //创建一个数组,标记棋盘各个位置是否被访问过
    private static boolean visited[];
    //使用一个属性,标记是否所有棋盘都被访问过
    private static boolean finished;//true表示成功

    public static void main(String[] args) {
        System.out.println("骑士周游回溯算法");
        //测试
        X = 8;
        Y = 8;
        int row = 1, column = 1;//马初始位置
        int[][] chessboard = new int[Y][X];
        visited = new boolean[X * Y];//默认false
        long start = System.currentTimeMillis();
        traversalChessboard(chessboard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("耗时" + (end - start) + "ms");

        //输出棋盘情况
        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }

    }

    //完成骑士周游回溯算法,chessboard棋盘,row表示行,column表示列,step表示第几步,初始为1
    public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {
        chessboard[row][column] = step;
        visited[row * X + column] = true;///标记该位置已经访问
        //获取当前位置可以走的位置集合
        ArrayList<Point> ps = next(new Point(column, row));
        //对ps进行排序,对ps所有元素的下一步位置个数进行非递减排序,减小回溯次数,先选择较小的
        sort(ps);
        //遍历ps
        while (!ps.isEmpty()) {
            Point p = ps.remove(0);//取出下一个可以走的位置
            //判断该点是否被访问过
            if (!visited[p.y * X + p.x]) {
                traversalChessboard(chessboard, p.y, p.x, step + 1);
            }
        }
        //判断马是否完成任务
        if (step < X * Y && !finished) {//没有完成
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }
    }

    //根据当前位置(Point对象),计算马还能走那些位置(Point)，并放入到ArrayList中，最多有88个位置
    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();//坐标
        //表示马可以走5
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //表示马可以走6
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //表示马可以走7
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //表示马可以走0
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //表示马可以走1
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        //表示马可以走2
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //表示马可以走3
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //表示马可以走4
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;

    }

    //根据当前这一步的所有的下一步的选择位置,进行非递减排序
    public static void sort(ArrayList<Point> ps) {
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point point, Point t1) {
                //获取O1的下一步的所有位置个数
                int size1 = next(point).size();
                int size2 = next(t1).size();
                if (size1 < size2) {
                    return -1;
                } else if (size1 == size2) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }
}
