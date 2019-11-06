package com.example.pinyin4j;

import java.util.*;

public class Hello {
    public static void main(String[] args) {
        List<User> list=new ArrayList<User>();
        User u=new User();
        u.setName("H");
        u.setAge(21);
        list.add(u);

        u=new User();
        u.setName("電競牛");
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
//            System.out.println(user.getName());
            System.out.println(PinyinUtil.hanziToPinyin(user.getName()));
        }
        Collections.sort(list, new ToSort());//new ToSort() 根据需求定义排序
        System.out.println("排序后！！！！！！！！！");
        for(User user: list) {
//            System.out.println((user.getName()));
            System.out.println(PinyinUtil.hanziToPinyin(user.getName()));
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
//(int)c <= 96 || 123 <= (int)c || (int)c<= 126
    public static void main(String[] args) {
        int c = 125;
        if((int)c <= 96 || c >= 123 && c < 127 ){
            System.out.println("失敗");
        }
    }

}
