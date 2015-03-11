package org.vice.core.conf;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultEntity;
import org.vice.core.Constants;
import org.vice.core.ViceConfiguration;
import org.vice.core.XMLLoader;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongzhaocheng on 2014/12/17.
 * Dom4j实现
 */
public class D4JXmlLoader implements XMLLoader {

    private static final String TRUE = "true";
    private static final String FALSE = "false";

    @Override
    public ViceConfig read() throws Exception{
        File xmlFile = new File(Constants.CFILE);
        SAXReader saxReader = new SAXReader();
        Class<?> viceConfigInstance = Class.forName(Constants.CPATH);
        Object o = viceConfigInstance.newInstance();

        Document document = saxReader.read(xmlFile);
        Element root = document.getRootElement();
        Element base = root.element(Constants.NODE_BASE);
        Method m;
        for (Object e : base.elements()){//遍历一层
            String name = ((Element)e).getName();
            for (int i = 0; i < ((Element) e).attributeCount(); i++) {
                if(((Element) e).attribute(i).getValue().equals(TRUE) ||
                        ((Element) e).attribute(i).getValue().equals(FALSE)){
                    m = viceConfigInstance.getDeclaredMethod("set"+upperCase(name)+upperCase(((Element) e).attribute(i).getName()),Boolean.class);
                    m.invoke(o,Boolean.valueOf(((Element) e).attribute(i).getValue()));
                }else{
                    m = viceConfigInstance.getDeclaredMethod("set"+upperCase(name)+upperCase(((Element) e).attribute(i).getName()),String.class);
                    m.invoke(o,((Element) e).attribute(i).getValue());
                }
            }
            if(((Element)e).hasMixedContent()){//本层有children
                Element elm1 = (Element)e;
                for (Object e2 : elm1.elements()){//遍历二层
                    Element elm2 = (Element)e2;
                    String elm2Name = elm2.getName();
                    //先赋值属性
                    for (int i = 0; i < elm2.attributeCount(); i++) {
                        if(elm2.attribute(i).getValue().equals(TRUE) || elm2.attribute(i).getValue().equals(FALSE)){
                            m = viceConfigInstance.getDeclaredMethod("set"+upperCase(name)+upperCase(elm2Name)+upperCase(elm2.attribute(i).getName()),Boolean.class);
                            m.invoke(o,Boolean.valueOf(elm2.attribute(i).getValue()));
                        }else{
                            m = viceConfigInstance.getDeclaredMethod("set"+upperCase(name)+upperCase(elm2Name)+upperCase(elm2.attribute(i).getName()),String.class);
                            m.invoke(o,elm2.attribute(i).getValue());
                        }
                    }
                    if(elm2.hasMixedContent()){//本层有children
                        for (Object e3 : elm2.elements()){//遍历第三层
                            //赋值属性
                            Element elm3 = (Element)e3;
                            String elm3Name = elm3.getName();
                            for(int i = 0;i<elm3.attributeCount();i++){
                                if(elm3.attribute(i).getValue().equals("true") || elm3.attribute(i).getValue().equals("false")){
                                    m = viceConfigInstance.getDeclaredMethod("set"+upperCase(name)+upperCase(elm2Name)+upperCase(elm3Name)+upperCase(elm3.attribute(i).getName()),Boolean.class);
                                    m.invoke(o,Boolean.valueOf(elm3.attribute(i).getValue()));
                                }else{
                                    m = viceConfigInstance.getDeclaredMethod("set"+upperCase(name)+upperCase(elm2Name)+upperCase(elm3Name)+upperCase(elm3.attribute(i).getName()),String.class);
                                    m.invoke(o,elm3.attribute(i).getValue());
                                }
                            }
                        }
                    }
                }

            }
        }
        ViceConfig finalConf = (ViceConfig)o;

        List<VTable> tables = new ArrayList<VTable>();
        //表结构
        Element db = root.element(Constants.DB_L);
        String prefix = db.attributeValue(Constants.DB_TAB_PREFIX);//表名前缀
        //遍历database标签
        for(Object to : db.elements()){
            Element tableElement = (Element)to;
            VTable table = new VTable();
            table.setName(prefix+upperCase(tableElement.getName()));//表名
            List<VColumn> columns = new ArrayList<VColumn>();
            for (Object co : tableElement.elements()){//列遍历
                Element columnElement = (Element)co;
                VColumn column = new VColumn();
                column.setColName(columnElement.getName());
                if(columnElement.attributeValue(Constants.DB_TAB_PK).equals(TRUE)){//主键字段
                    column.setPk(TRUE);
                    column.setType(finalConf.getGarbageTableIdType());
                }else{
                    column.setPk(FALSE);
                    column.setType(columnElement.attributeValue(Constants.DB_TAB_TYPE));
                }
                columns.add(column);
            }
            table.setColumns(columns);
            tables.add(table);
        }
        finalConf.setTables(tables);
        return finalConf;
    }

    /**
     * 将s单词首字母大写
     * @param s 待处理
     * @return 处理完成
     */
    private String upperCase(String s){
        char first = s.charAt(0);
        return String.valueOf(first).toUpperCase()+s.substring(1);
    }


}
