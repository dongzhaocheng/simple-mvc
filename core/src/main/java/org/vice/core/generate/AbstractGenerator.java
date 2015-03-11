package org.vice.core.generate;

import org.vice.core.conf.ViceConfig;

/**
 * Created by dongzhaocheng on 2014/12/25.
 * 抽象Generator
 */
public abstract class AbstractGenerator implements Generator {

    protected StringBuilder template;
    protected ViceConfig config;

    protected void templateReplace(StringBuilder t, String key, String val){
        while (true){
            int begin = t.indexOf(key);
            if(begin==-1) break;
            int end = begin + key.length();
            t.replace(begin,end,val);
        }
    }

    protected String upperCase(String s){
        char first = s.charAt(0);
        return String.valueOf(first).toUpperCase()+s.substring(1);
    }

}
