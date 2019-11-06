package com.example.pinyin4j;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

public class DataTest {

//    @GetMapping(value = "/hello")
//    public String hello() throws BadHanyuPinyinOutputFormatCombination {
//        return DataTest.run();
//    }
    public static void main(String[] args)throws BadHanyuPinyinOutputFormatCombination {
        PinyinTool pinyinTool = new PinyinTool();
        PinyinUtil pinyinUtil = new PinyinUtil();
        List<Manager> list = new ArrayList<>();
        list.add(new Manager("2","Hesat"));
        list.add(new Manager("2","Hesat"));
        list.add(new Manager("2","Hesat"));
        list.add(new Manager("3","電競牛"));
        list.add(new Manager("3","電競牛"));
        list.add(new Manager("4","李四"));
        list.add(new Manager("5","王五"));
        list.add(new Manager("5","王五"));
        list.add(new Manager("5","王五"));
        list.add(new Manager("6","寒子"));
        for(Manager manager : list){
            manager.setApiNameOrder(pinyinTool.toPinYin(manager.getApiName(),"", PinyinTool.Type.UPPERCASE));
        }
        Collections.sort(list, new ToSortManager());
        Map<String, List<Manager>> map = new LinkedHashMap<>();
        Map<String,Map<String, List<Manager>>> mapOrder = new LinkedHashMap<>();
        Map<String, Map<String, List<Manager>>> collect = list.stream().collect(groupingBy(Manager::test, groupingBy(Manager::getApiId)));
//        for (Manager manager : list){
//            String apiId = manager.getApiId();
////            char c = manager.getApiNameOrder().charAt(0);
////            if (mapOrder.containsKey(c)){
////                mapOrder.get(c).get(apiId).add()
////            }
//            if (map.containsKey(apiId)){
//                map.get(apiId).add(manager);
//            } else {
//                map.put(apiId,new ArrayList<Manager>());
//                map.get(apiId).add(manager);
//            }
//        }
//        for (Manager manager : list){
//            for (Manager it : list){
//                if (it.getApiId() == manager.getApiId()){
//                    manager.setManagerList(new ArrayList<Manager>());
//                }
//                manager.getManagerList().add(it);
//            }
//        }
        System.out.println();
    }

}
//排序
class ToSortManager implements Comparator<Manager> {
    @Override
    public int compare(Manager o1, Manager o2) {
        // TODO Auto-generated method stub
        String s1 = PinyinUtil.hanziToPinyin(o1.getApiNameOrder());
        String s2 = PinyinUtil.hanziToPinyin(o2.getApiNameOrder());
        if (s1.compareTo(s2) > 0) {
            return 1;
        } else {
            return -1;
        }
    }
}
