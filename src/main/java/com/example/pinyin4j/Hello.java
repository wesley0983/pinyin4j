package com.example.pinyin4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Hello {
    public static void main(String[] args) {
        List<User> list=new ArrayList<User>();
        User u=new User();
        u.setName("张三");
        u.setAge(21);
        list.add(u);

        u=new User();
        u.setName("李四");
        u.setAge(18);
        list.add(u);

        u=new User();
        u.setName("王五");
        u.setAge(25);
        list.add(u);

        u=new User();
        u.setName("寒子");
        u.setAge(89);
        list.add(u);

        u=new User();
        u.setName("aaa");
        u.setAge(1);
        list.add(u);

        for(User user: list) {
            System.out.println(user.getName());
        }
        Collections.sort(list, new ToSort());//new ToSort() 根据需求定义排序
        System.out.println("排序后！！！！！！！！！");
        for(User user: list) {
            System.out.println((user.getName()));
        }
    }
}
//排序
class ToSort implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        // TODO Auto-generated method stub
        String s1=PinyinUtil.hanziToPinyin(o1.getName());
        String s2=PinyinUtil.hanziToPinyin(o2.getName());
        if(s1.compareTo(s2)>0) {
            return 1;
        } else {
            return -1;
        }
    }
}
