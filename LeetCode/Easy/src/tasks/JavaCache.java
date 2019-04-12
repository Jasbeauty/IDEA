package tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * java 中缓存的基本实现
 */
public class JavaCache {
    // 一个本地的缓存变量
    private Map<String,Object> map = new HashMap<>();

    public void localCache(){
        List<Object> infoList = this.getInfoList();

        for (Object item :
                infoList) {
            if (map.containsKey(item)){     // 缓存命中，使用缓存数据
                // todo
            }else {     // 缓存未命中，IO获取数据，结果存入缓存
                Object valueObject = this.getInfoFromDB();
                map.put(valueObject.toString(),valueObject);
            }
        }
    }

    private List<Object> getInfoList(){
        return new ArrayList<>();
    }

    // 数据库IO获取
    private Object getInfoFromDB(){
        return new Object();
    }
}
