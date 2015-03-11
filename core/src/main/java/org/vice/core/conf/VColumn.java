package org.vice.core.conf;

/**
 * Created by dongzhaocheng on 2014/12/21.
 * 配置文件生成column
 */
public class VColumn {

    private Boolean isPk;
    private String colName;
    private String type;

    public void setPk(String pk){
        isPk = Boolean.valueOf(pk);
    }

    public Boolean isPk(){
        return isPk;
    }

    public void setColName(String name){
        colName = name;
    }

    public String getColName(){
        return colName;
    }

    public void setType(String t){
        type = t;
    }

    public String getType(){
        return type;
    }

}
