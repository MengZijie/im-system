package com.mzj.im.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Created by ob on 17-3-30.
 */
@Component
public class IOUtil {
    /**
     * 取得文件类型后缀
     *
     * @param filename
     * @return
     */
    public String getFileSuffix(String filename) {
        if (StringUtils.isNotBlank(filename)) {
            String[] parts = filename.split("/");
            filename = parts[parts.length - 1];
            parts = filename.split("\\.");
            if(parts.length > 1){
                String suffix = parts[parts.length -1];
                return StringUtils.lowerCase(suffix);
            }
        }
        return StringUtils.EMPTY;
    }
}
