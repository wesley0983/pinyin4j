package com.example.pinyin4j;

import java.util.*;

public class Test {
    /**
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<Integer> myList = new ArrayList<>();
        initList(myList);
        printList(myList);

        // *********************************************************************
        // 使用HashSet删除ArrayList中重复的元素
        // myList = new ArrayList<Integer>(new HashSet<Integer>(myList));
        // HashSet<Integer> mySet = new HashSet<>();
        // for (int i = 0; i < myList.size(); i++) {
        // mySet.add(myList.get(i));
        // }
        // myList = new ArrayList<>(mySet);

        // *********************************************************************
        // 使用LinkedHashSet删除ArrayList中重复的元素
        // LinkedHashSet不允许重复元素，同时保持元素的插入顺序。
        // LinkedHashSet的这两个属性可以确保在删除ArrayList中的重复元素之后，
        // 依然保持元素的插入顺序
        myList = new ArrayList<Integer>(new LinkedHashSet<Integer>(myList));
        // LinkedHashSet<Integer> myLHSet = new LinkedHashSet<>();
        // for (int i = 0; i < myList.size(); i++) {
        // myLHSet.add(myList.get(i));
        // }
        // myList = new ArrayList<>(myLHSet);
        // *********************************************************************
        System.out.println("删除重复元素后： ");
        printList(myList);

        // 删除小于5的元素
        Remove_elements4(myList);

        System.out.println("删除小于5的元素： ");
        printList(myList);
    }

    public static void Remove_elements1(ArrayList<Integer> arraylist) {
        // 顺序删除，但是对下标和索引进行了处理
        int size = arraylist.size();
        for (int i = 0; i < size; i++) {
            if (arraylist.get(i) < 5) {
                arraylist.remove(i);
                size--;
                i--;
            }
        }
        // for (int i = 0, len = arraylist.size(); i < len; i++) {
        // if (arraylist.get(i) < 5) {
        // arraylist.remove(i);
        // len--;
        // i--;
        // }
        // }
    }

    public static void Remove_elements2(ArrayList<Integer> arraylist) {
        // 倒序删除，以防因为删除中间项导致数据下标变更，使得数据出错
        Iterator<Integer> listIterator = arraylist.iterator();
        while (listIterator.hasNext()) {
            Integer integer = listIterator.next();
            if (integer < 5) {
                listIterator.remove();
            }
        }
    }

    public static void Remove_elements3(ArrayList<Integer> arraylist) {
        // 使用iterator，这个是java和Android源码中经常使用到的一种方法，所以最为推荐
        for (int i = arraylist.size() - 1; i >= 0; i--) {
            if (arraylist.get(i) < 5) {
                arraylist.remove(i);
            }
        }

    }

    public static void Remove_elements4(ArrayList<Integer> arraylist) {
        // 使用辅助数据结构
        ArrayList<Integer> temp = new ArrayList<>();
        for (Integer integer : arraylist) {
            if (integer < 5) {
                temp.add(integer);
            }
        }
        arraylist.removeAll(temp);

    }


    public static void initList(List<Integer> list) {
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(4);
        list.add(6);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
    }

    public static void printList(List<Integer> list) {
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

}
