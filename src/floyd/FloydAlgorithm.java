package floyd;

import java.util.Arrays;

//佛洛依德求最短路径
public class FloydAlgorithm {
    public static void main(String[] args) {
        //测试
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //创建邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};

        //创建图对象
        Graph graph = new Graph(vertex.length, matrix, vertex);
        graph.floyd();
        graph.show();


    }
}

//创建图
class Graph {
    private char[] vertex;//存放顶点数组
    private int[][] dis;//保存,从各个顶点到其他顶点的距离
    private int[][] pre;//保存到达目标顶点的前驱顶点,中间顶点

    //构造器,length长度,matrix邻接矩阵,vertex顶点数组
    public Graph(int length, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        //对pre数组初始化,存放前驱顶点的下标
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    //显示pre数组和dis数组
    public void show() {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        for (int k = 0; k < dis.length; k++) {
            //先输出pre数组的一行
            for (int i = 0; i < dis.length; i++) {
                System.out.print(vertex[pre[k][i]] + " ");
            }
            System.out.println();
            //输出dis数组的一行数据
            for (int i = 0; i < dis.length; i++) {
                System.out.print("(" + vertex[k] + "到" + vertex[i] + "的最短路径为" + dis[k][i] + ")");
            }
            System.out.println();
            System.out.println();
        }
    }

    //佛洛依德算法
    public void floyd() {
        int len = 0;//保存距离
        //对中间顶点遍历,k就是中间顶点下标[A,B,C,D,E,F,G],如i->k->j
        for (int k = 0; k < dis.length; k++) {//中间结点
            //从i顶点出发[A,B,C,D,E,F,G]
            for (int i = 0; i < dis.length; i++) {//遍历距离,如i到k
                //到达j顶点/[A,B,C,D,E,F,G]
                for (int j = 0; j < dis.length; j++) {//遍历距离,如k到j
                    len = dis[i][k] + dis[k][j];//AB+AC的距离
                    if (len < dis[i][j]) {//如果len小于dis[i][j]就更新距离
                        dis[i][j] = len;
                        pre[i][j] = pre[k][j];//更新前驱结点
                    }
                }
            }
        }
    }
}
