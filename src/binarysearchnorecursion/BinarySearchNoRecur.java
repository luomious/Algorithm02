package binarysearchnorecursion;

//二分查找,非递归
public class BinarySearchNoRecur {
    public static void main(String[] args) {
        //测试
        int arr[] = {1, 3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, 1);
        System.out.println("index=" + index);

    }

    //二分查找的非递归实现,默认是升序排列
    public static int binarySearch(int arr[], int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {//说明继续查找
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                //需要向左边查找
                right = mid - 1;
            } else {
                //需要向右边查找
                left = mid + 1;
            }

        }
        return -1;
    }

}
