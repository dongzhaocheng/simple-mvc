package org.vice.core.conf;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.vice.core.Constants;
import org.vice.core.ViceConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dongzhaocheng on 2014/12/18.
 * 配置实体类
 */
public class ViceConfig implements ViceConfiguration {

    private Map<String,Object> entry;

    public ViceConfig(){
        entry = new HashMap<String, Object>();
    }

    private Object get(String key){
        return entry.get(key);
    }

    private void set(String key,Object val){
        entry.put(key,val);
    }

    public Boolean getPackageDefaultStructure(){
        if(entry.containsKey(Constants.KEY_PACKAGE_DEFAULT)){
            return (Boolean)get(Constants.KEY_PACKAGE_DEFAULT);
        }else{
            return null;
        }
    }

    public void setPackageDefaultStructure(Boolean isDefault){
        set(Constants.KEY_PACKAGE_DEFAULT,isDefault);
    }

    public String getPackageValue(){
        if(entry.containsKey(Constants.KEY_PACKAGE_VAL))
            return (String)get(Constants.KEY_PACKAGE_VAL);
        else
            return null;
    }

    public void setPackageValue(String val){
        set(Constants.KEY_PACKAGE_VAL,val);
    }

    public String getLoggerType(){
        if (entry.containsKey(Constants.KEY_LOGGER_TYPE))
            return (String)get(Constants.KEY_LOGGER_TYPE);
        else
            return null;
    }

    public void setLoggerType(String type){
        set(Constants.KEY_LOGGER_TYPE,type);
    }

    public Boolean getLoggerPrinterSqlValue(){
        if (entry.containsKey(Constants.KEY_LOGGER_SQL_VAL))
            return (Boolean)get(Constants.KEY_LOGGER_SQL_VAL);
        else
            return null;
    }

    public void setLoggerPrinterSqlValue(Boolean value){
        set(Constants.KEY_LOGGER_SQL_VAL,value);
    }

    public String getLoggerPrinterSqlLevel(){
        if (entry.containsKey(Constants.KEY_LOGGER_SQL_LVL))
            return (String)get(Constants.KEY_LOGGER_SQL_LVL);
        else
            return null;
    }

    public void setLoggerPrinterSqlLevel(String lvl){
        set(Constants.KEY_LOGGER_SQL_LVL,lvl);
    }

    public Boolean getLoggerPrinterUserNameValue(){
        if (entry.containsKey(Constants.KEY_LOGGER_USER_VAL))
            return (Boolean)get(Constants.KEY_LOGGER_USER_VAL);
        else
            return null;
    }

    public void setLoggerPrinterUserNameValue(Boolean val){
        set(Constants.KEY_LOGGER_USER_VAL,val);
    }

    public String getLoggerPrinterUserNameLevel(){
        if (entry.containsKey(Constants.KEY_LOGGER_USER_LVL))
            return (String)get(Constants.KEY_LOGGER_USER_LVL);
        else
            return null;
    }

    public void setLoggerPrinterUserNameLevel(String lvl){
        set(Constants.KEY_LOGGER_USER_LVL,lvl);
    }

    public Boolean getLoggerPrinterRoleValue(){
        if (entry.containsKey(Constants.KEY_LOGGER_ROLE_VAL))
            return (Boolean)get(Constants.KEY_LOGGER_ROLE_VAL);
        else
            return null;
    }

    public void setLoggerPrinterRoleValue(Boolean val){
        set(Constants.KEY_LOGGER_ROLE_VAL,val);
    }

    public String getLoggerPrinterRoleLevel(){
        if (entry.containsKey(Constants.KEY_LOGGER_ROLE_LVL))
            return (String)get(Constants.KEY_LOGGER_ROLE_LVL);
        else
            return null;
    }

    public void setLoggerPrinterRoleLevel(String lvl){
        set(Constants.KEY_LOGGER_ROLE_LVL,lvl);
    }

    public Boolean getSpringAutowiredValue(){
        if (entry.containsKey(Constants.KEY_SPRING_AUTOWIRED))
            return (Boolean)get(Constants.KEY_SPRING_AUTOWIRED);
        else
            return null;
    }

    public void setSpringAutowiredValue(Boolean val){
        set(Constants.KEY_SPRING_AUTOWIRED,val);
    }

    public String getGarbageTableIdType(){
        if (entry.containsKey(Constants.KEY_GARB_ID_TYPE))
            return (String)get(Constants.KEY_GARB_ID_TYPE);
        else
            return null;
    }

    public void setGarbageTableIdType(String type){
        set(Constants.KEY_GARB_ID_TYPE,type);
    }

    public Boolean getGarbageTableIdAutoInc(){
        if (entry.containsKey(Constants.KEY_GARB_ID_AI))
            return (Boolean)get(Constants.KEY_GARB_ID_AI);
        return null;
    }

    public void setGarbageTableIdAutoInc(Boolean isInc){
        set(Constants.KEY_GARB_ID_AI,isInc);
    }

    public String getGarbageRecordCreateTimeValue(){
        if (entry.containsKey(Constants.KEY_GARB_TIME_VAL))
            return (String)get(Constants.KEY_GARB_TIME_VAL);
        return null;
    }

    public void setGarbageRecordCreateTimeValue(String val){
        set(Constants.KEY_GARB_TIME_VAL,val);
    }


    public String getDatabaseTablePrefix(){
        if (entry.containsKey(Constants.KEY_DB_PREFIX))
            return (String)get(Constants.KEY_DB_PREFIX);
        return null;
    }

    public void setDatabaseTablePrefix(String prefix){
        set(Constants.KEY_DB_PREFIX,prefix);
    }

    public List<VTable> getTables(){
        if (entry.containsKey(Constants.KEY_TABLE))
            return (List<VTable>)get(Constants.KEY_TABLE);
        return null;
    }

    public void setTables(List<VTable> t){
        set(Constants.KEY_TABLE,t);
    }
}
