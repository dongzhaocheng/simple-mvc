package org.vice.core.conf;

import java.util.List;

/**
 * Created by dongzhaocheng on 2014/12/21.
 * 配置文件生成table
 */
public class VTable {

    private String name;
    private List<VColumn> columns;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<VColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<VColumn> columns) {
        this.columns = columns;
    }
}
