package dac;

//分治算法,汉诺塔
public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(3, 'A', 'B', 'C');
    }

    //汉诺塔移动方法
    //使用分治算法
    public static void hanoiTower(int num, char a, char b, char c) {
        //如果只有一个盘
        if (num == 1) {
            System.out.println("第1个盘从" + a + "->" + c);
        } else {
            //n>=2时,可以看做两个盘1，最下边的盘，和上面所有盘
            //1.先把最上面的所有盘A->B
            hanoiTower(num - 1, a, c, b);
            //2.把最下边的盘A->C
            System.out.println("第" + num + "个盘从" + a + "->" + c);
            //3.把B塔的所有盘从B->C，移动过程使用A塔
            hanoiTower(num - 1, b, a, c);
        }
    }
}
