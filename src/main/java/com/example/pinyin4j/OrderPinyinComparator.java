package com.example.pinyin4j;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;
import org.springframework.stereotype.Component;


public class OrderPinyinComparator implements Comparator<Object> {
    public int compare(Object o1, Object o2) {
        char c1 = ((String) o1).charAt(0);
        char c2 = ((String) o2).charAt(0);
        return concatPinyinStringArray(
                PinyinHelper.toHanyuPinyinStringArray(c1)).compareTo(
                concatPinyinStringArray(PinyinHelper
                        .toHanyuPinyinStringArray(c2)));
    }
    private String concatPinyinStringArray(String[] pinyinArray) {
        StringBuffer pinyinSbf = new StringBuffer();
        if ((pinyinArray != null) && (pinyinArray.length > 0)) {
            for (int i = 0; i < pinyinArray.length; i++) {
                pinyinSbf.append(pinyinArray[i]);
            }
        }
        return pinyinSbf.toString();
    }

    public static void main(String[] args) {
        String[] arr = { "张三", "李四", "王五", "赵六", "JAVA", "123", "$%$#", "哈哈A",
                "1哈哈A", "1哈哈b", "1哈哈a", "哈哈", "哈", "怡情" };
        List<String> list = Arrays.asList(arr);
        Arrays.sort(arr, new OrderPinyinComparator());
        System.out.println(list);
    }
}
