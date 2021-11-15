package greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

//p166
//贪心算法
public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建广播电台
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        //加到map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);
        //存放地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("大连");
        allAreas.add("深圳");
        allAreas.add("杭州");
        allAreas.add("成都");
        allAreas.add("广州");

        //创建ArrayList，存放电台
        ArrayList<String> selects = new ArrayList<>();
        //定义临时集合,与未覆盖地区的交集
        HashSet<String> tempSet = new HashSet<>();
        //定义maxKey，能够覆盖的最大地区的电台的key
        String maxKey = null;
        while (allAreas.size() != 0) {
            maxKey = null;
            //遍历
            for (String key : broadcasts.keySet()) {
                //清空
                tempSet.clear();
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                //求交集
                tempSet.retainAll(allAreas);
                //如果当前集合包含的未覆盖地区比maxKey指向的集合未覆盖的地区多,就重置maxKey
                //tempSet.size() > broadcasts.get(maxKey).size()体现贪心算法的地方
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            if (maxKey != null) {
                selects.add(maxKey);
                //将maxKey指向的电台覆盖的地区从allAreas中去掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println("得到的结果为" + selects);
    }
}
