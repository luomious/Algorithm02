package dynamic;

//动态规划
public class KnapsackProblem {
    public static void main(String[] args) {
        int w[] = {1, 4, 3};//物品的重量
        int val[] = {1500, 3000, 2000};//物品的价值
        int m = 4;//背包的容量
        int n = val.length;//物品的个数

        //为了记录放入商品的情况,定一个二维数组
        int[][] path = new int[n + 1][m + 1];


        //创建二维数组,v[i][j] ,表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];
        //初始化
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i < v.length; i++) {
            v[0][i] = 0;
        }

        //根据公式动态规划代处理
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[i].length; j++) {
                //公式
                if (w[i - 1] > j) {//因为程序i是从1开始的,因此公式中的w[i]改为w[i-1]
                    v[i][j] = v[i - 1][j];
                } else {
//                    v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    //为了记录商品存放的情况
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }
        //输出
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j]+" ");
            }
            System.out.println();
        }
        //输出最后商品

        int i = path.length - 1;//行的最大下标
        int j = path[0].length - 1;//列的最大下标
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= w[i - 1];//w[i-1]

            }
            i--;
        }
    }
}
