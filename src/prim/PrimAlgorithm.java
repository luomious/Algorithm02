package prim;

import java.util.Arrays;

//普里姆算法
public class PrimAlgorithm {
    public static void main(String[] args) {

        //测试
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},};

        //创建MGraph对象
        MGraph graph = new MGraph(verxs);
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, verxs, data, weight);
        minTree.showGraph(graph);
        minTree.prim(graph, 0);
    }
}

//创建最小生成树->村庄的图
class MinTree {
    //创建图的邻接矩阵
    //graph图对象,verxs图对应的顶点个数,data[]图的各个顶点的值,weight图的邻接矩阵
    public void createGraph(MGraph graph, int verxs, char data[], int[][] weight) {
        int i, j;
        for (i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));

        }
    }

    //编写一个prim算法,生成最小生成树
    public void prim(MGraph graph, int v) {//v表示从第几个顶点生成
        //visited表示顶点是否被访问过
        int visited[] = new int[graph.verxs];//默认为0
        //把当前节点标记为已访问
        visited[v] = 1;
        //用h1,h2记录下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;//初始化
        for (int k = 1; k < graph.verxs; k++) {
            //确定每一次生成的子图,和哪个节点距离最近
            for (int i = 0; i < graph.verxs; i++) {//i表示遍历访问过的节点
                for (int j = 0; j < graph.verxs; j++) {//j表示遍历未访问过的节点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        //替换minWeight，寻找权值最小的边
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //找到最小的那条边
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + ">权值：" + minWeight);
            //将当前节点标记为已经访问
            visited[h2] = 1;
            //minWeight重新设置为10000
            minWeight = 10000;
        }


    }
}
class MGraph {
    int verxs;//表示图的节点个数
    char[] data;//存放节点数据
    int[][] weight;//存放边,邻接矩阵

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}
