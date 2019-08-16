package study.Day0625;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestTree {
    public static List<Menu> initData() {
        List<Menu> treeData = new ArrayList<>();
        treeData.add(new Menu("1", "1", "0"));
        treeData.add(new Menu("2", "1-1", "1"));
        treeData.add(new Menu("3", "1-1-1", "2"));
        treeData.add(new Menu("4", "1-2", "1"));
        treeData.add(new Menu("5", "1-2-2", "4"));
        treeData.add(new Menu("6", "1-1-1-1", "3"));
        treeData.add(new Menu("7", "2", "0"));
        return treeData;
    }

    /**
     * 子菜单递归
     */
    private static List<Menu> getchild(String id, List<Menu> rootMenu) {
        // 子菜单
        List<Menu> childList = new ArrayList<>();
        // 遍历所有节点，将父菜单id与传过来的id比较
        for (Menu menu : rootMenu
                ) {
            if (menu.getParentId().equals(id)) {
                childList.add(menu);
            }
        }

        // 将子菜单的子菜单再循环一遍
        for (Menu menu : childList) {
            // 递归
            menu.setChildMenus(getchild(menu.getId(), rootMenu));
        }

        // 判断递归结束
        if (childList.size() == 0) {
            return null;
        }

        return childList;
    }


    public static void main(String[] args) {
        List<Menu> treeData = initData();

        // 查看转换前的内容
        for (Menu menu : treeData) {
            System.out.println(menu.toString());
            System.out.println("***************************************");
        }

        List<Menu> menuList = new ArrayList<>();

        // 先找到所有的一级菜单
        for (int i = 0; i < treeData.size(); i++) {
            // 一级菜单父菜单为0
            if (treeData.get(i).getParentId().equals("0")) {
                menuList.add(treeData.get(i));
            }
        }

        // 为一级菜单设置子菜单，getChild()是递归调用的
        for (Menu menu : menuList) {
            menu.setChildMenus(getchild(menu.getId(), treeData));
        }

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("菜单：", menuList);
        System.out.println(jsonMap.toString());
    }
}
