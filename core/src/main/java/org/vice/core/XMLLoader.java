package org.vice.core;

/**
 * Created by dongzhaocheng on 2014/12/17.
 * 配置文件加载
 */
public interface XMLLoader {

    ViceConfiguration read() throws Exception;

}
