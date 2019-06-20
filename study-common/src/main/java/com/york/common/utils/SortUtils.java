package com.york.common.utils;

import java.util.*;

/**
 * @author York.yuan
 * @version <1.0>
 * @description 排序工具
 * @createdate 2019/6/19 15:24
 **/
public class SortUtils {

    /**
     * Map按照键排序(默认顺序)，
     * key必须实现Comparable接口
     * @param map
     * @return
     */
    public static Map sortedMapByKey(Map<? extends Comparable,?> map){
        return sortedMapByKey(map,Boolean.TRUE);
    }

    /**
     * Map根据键排序(顺序或者倒序)，
     * key必须实现Comparable接口
     * @param map
     * @param aes
     * @return
     */
    public static Map sortedMapByKey(Map<? extends Comparable,?> map,Boolean aes){
//        Map res = new TreeMap<? extends Comparable,?>(new Comparator<Map.Entry<? extends Comparable,?>>() {
//            @Override
//            public int compare(Map.Entry<? extends Comparable,?> o1, Map.Entry<? extends Comparable,?> o2) {
//                return aes ? o1.getKey().compareTo(o2.getKey()) : o2.getKey().compareTo(o1.getKey());
//            }
//        });
//        Map res1 = new TreeMap<? extends Comparable,?>();
//        Comparator.comparing();
//        res1.putAll(map);
        return null;
    }

    /**
     * Map按照value排序(默认顺序)，
     * value必须实现Comparable接口
     * @param map
     * @return
     */
    public static Map sortedMapByValue(Map<?,? extends Comparable> map){
        return sortedMapByValue(map,Boolean.TRUE);
    }

    /**
     * Map按照value排序(顺序或者倒序)，
     * value必须实现Comparable接口
     * @param map
     * @param asc
     * @return
     */
    public static Map sortedMapByValue(Map<?,? extends Comparable> map,Boolean asc){
        if(map == null || map.keySet().isEmpty()){
            return null;
        }
        List<Map.Entry<?,? extends Comparable>> list = new ArrayList(map.entrySet());
//        Collections.sort(list, new Comparator<Map.Entry<?,? extends Comparable>>() {
//            @Override
//            public int compare(Map.Entry<?, ? extends Comparable> o1, Map.Entry<?, ? extends Comparable> o2) {
//                return asc ? o1.getValue().compareTo(o2.getValue()):o2.getValue().compareTo(o1.getValue());
//            }
//        });

        Map res = new LinkedHashMap();
//        for (Map.Entry<?, ? extends Comparable> entry : list) {
//            res.put(entry.getKey(),entry.getValue());
//        }
        list.stream().sorted(Comparator.comparing(e->e.getValue())).forEach(e->res.put(((Map.Entry) e).getKey(),((Map.Entry) e).getValue()));
        return res;
    }

    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put("张三",20);
        map.put("李四",19);
        map.put("王五",25);
        map.put("赵一",18);
        Map map1 = SortUtils.sortedMapByValue(map);
        for (Object o : map1.keySet()) {
            String key = (String)o;
            System.out.printf("key=%s,value=%d\n",key,map1.get(key));
        }
    }
}
