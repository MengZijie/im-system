package com.mzj.im.util;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by OB on 2017/2/9.
 */
public final class ObjectUtil {
    public static boolean isNull(Object o) {
        return o == null;
    }

    public static boolean isNotNull(Object o) {
        return !isNull(o);
    }

    public static boolean removeElementFromMap(Map map, Object key) {
        if (isNull(map)) {
            return false;
        }
        Iterator<Map.Entry> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = it.next();
            if (entry.getKey().equals(key)) {
                return isNotNull(map.remove(key));
            }
        }
        return false;
    }
}
