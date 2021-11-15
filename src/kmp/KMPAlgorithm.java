package kmp;

import javax.swing.*;
import javax.xml.transform.Source;
import java.util.Arrays;

//kmp算法
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next = kmpNext(str2);
        System.out.println(Arrays.toString(next));

        int index = kmpSearch(str1, str2, next);
        System.out.println("index=" + index);

    }

    //kmp算法
    //str1是原字符串,str2是子字符串,next是匹配值表
    public static int kmpSearch(String str1, String str2, int[] next) {
        //遍历
        for (int i = 0, j = 0; i < str1.length(); i++) {
            //处理str1.charAt(i) ！= str2.charAt(j)，调整j的大小
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                //找到了
                return i - j + 1;
            }
        }
        return -1;
    }

    //获取一个字符串(子串)部分匹配值表
    public static int[] kmpNext(String dest) {
        //创建一个next数组保存部分匹配值
        int next[] = new int[dest.length()];
        next[0] = 0;//如果字符串长度为1部分匹配值为0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //当dest.charAt(i) != dest.charAt(j)时，需要从next[j-1]获取新的j
            //直到有dest.charAt(i) == dest.charAt(j)满足时才退出
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            //当dest.charAt(i) == dest.charAt(j)满足时,部分匹配值就要+1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;

    }
}
