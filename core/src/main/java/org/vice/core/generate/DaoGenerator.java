package org.vice.core.generate;

import org.vice.core.Constants;
import org.vice.core.conf.VColumn;
import org.vice.core.conf.VTable;
import org.vice.core.conf.ViceConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by dongzhaocheng on 2014/12/21.
 * dao层代码生成
 */
public class DaoGenerator extends AbstractGenerator {


    @Override
    public void generate() throws IOException{
        if(template!=null){
            String path = config.getPackageValue().replace(".","\\");
            path = path + "\\dao";
            File f = new File(path);
            if(!f.mkdirs()){
                return;
            }
            for (VTable table : config.getTables()){
                StringBuilder val = new StringBuilder();
                StringBuilder templateCopy = new StringBuilder(template);
                //package语句
                {
                    val.append(config.getPackageValue()).append(".dao;").append(System.lineSeparator());
                    templateReplace(templateCopy,Constants.DAO_KEY_PACK,val.toString());
                }
                //import语句
                {
                    val = new StringBuilder();
                    val.append("import ").append(config.getPackageValue())
                            .append(".model.").append(upperCase(table.getName()))
                            .append("Model;").append(System.lineSeparator());//import User
                    if(config.getLoggerType().equals("slf4j")){
                        val.append("import org.slf4j.Logger;").append(System.lineSeparator());
                        val.append("import org.slf4j.LoggerFactory;").append(System.lineSeparator());
                    }
                    templateReplace(templateCopy,Constants.DAO_KEY_IMPORT,val.toString());
                }
                //daoClass/Interface Name
                {
                    val = new StringBuilder();
                    val.append(upperCase(table.getName())).append("Dao");
                    templateReplace(templateCopy,Constants.DAO_KEY_INTERFACE,val.toString());

                    val = new StringBuilder();
                    val.append(upperCase(table.getName())).append("DaoImpl");
                    templateReplace(templateCopy,Constants.DAO_KEY_CLASS,val.toString());
                }
                //DAO_KEY_DOMAIN_CLASS = "#domainClass#";
                {
                    val = new StringBuilder();
                    val.append(upperCase(table.getName())).append("Model");
                    templateReplace(templateCopy,Constants.DAO_KEY_DOMAIN_CLASS,val.toString());
                }
                // DAO_KEY_DOMAIN_CLASS = "#domainClass#";
                {

                }
                //Autowired
                {
                    val = new StringBuilder();
                    val.append("@Autowired");
                    templateReplace(templateCopy,Constants.DAO_KEY_AUTO,val.toString());
                }
                //DAO_KEY_LOGGER = "#logger.code#"
                {
                    val = new StringBuilder();
                    if(config.getLoggerType().equals("slf4j")){
                        val.append("logger.info(sql.toString())");
                    }
                    templateReplace(templateCopy, Constants.DAO_KEY_LOGGER,val.toString());
                }
                //DAO_KEY_ID_TYPE = "#garbage.tableId.type#";
                {
                    val = new StringBuilder();
                    if(config.getGarbageTableIdType().equals("Integer")){
                        val.append("Integer");
                    }else if(config.getGarbageTableIdType().equals("String")){
                        val.append("String");
                    }
                    templateReplace(templateCopy,Constants.DAO_KEY_ID_TYPE,val.toString());
                }
                //DAO_KEY_ID_NAME = "#id#";
                {
                    val = new StringBuilder();
                    for (VColumn c : table.getColumns()){
                        if(c.isPk()){
                            val.append("\"").append(c.getColName()).append("\"");
                        }
                    }
                    templateReplace(templateCopy,Constants.DAO_KEY_ID_NAME,val.toString());
                }
                //DAO_KEY_PARA_COLS = "#para.columns#";获得列名的参数
                {
                    val = new StringBuilder();
                    for (VColumn c : table.getColumns()){
                        val.append(c.getType()).append(" ").append(c.getColName()).append(",");
                    }
                    val.deleteCharAt(val.length()-1);
                    templateReplace(templateCopy,Constants.DAO_KEY_PARA_COLS,val.toString());
                }
                //DAO_KEY_APP_INSERT = "#append.insert#";
                {
                    val = new StringBuilder();
                    val.append("append( \"(\")");
                    for (VColumn c : table.getColumns()){
                        if(!c.isPk())
                            if(c.getType().equals("String")){
                                val.append(".append(\"'\").append(r.get").append(upperCase(c.getColName())).append("()).append(\"'\").append(\",\")");
                            }else{
                                val.append(".append(r.get").append(upperCase(c.getColName())).append("()).append(\",\")");
                            }
                    }
                    val.delete(val.length()-".append(\",\")".length(),val.length());
                    val.append(".append(\"),\")");
                    templateReplace(templateCopy,Constants.DAO_KEY_APP_INSERT,val.toString());
                }
                //DAO_KEY_APP_UPDATE = "#append.update#";
                {
                    val = new StringBuilder();
                    for(VColumn c : table.getColumns()){
                        if(c.getType().equals("String")){
                            val.append(".append(\"").append(c.getColName())
                                    .append("='\").append(domain.get")
                                    .append(upperCase(c.getColName())).append("()).append(\"'\").append(\",\")");
                        }else{
                            val.append(".append(\"").append(c.getColName())
                                    .append("=\").append(domain.get")
                                    .append(upperCase(c.getColName())).append("()).append(\",\")");
                        }
                    }
                    //删除逗号
                    val.delete(val.length()-".append(\",\")".length(),val.length());
                    templateReplace(templateCopy,Constants.DAO_KEY_APP_UPDATE,val.toString());
                }
                //DAO_KEY_TABLE_NAME = "#table.name#";
                {
                    val = new StringBuilder();
                    val.append("\"").append(table.getName()).append("\"");
                    templateReplace(templateCopy,Constants.DAO_KEY_TABLE_NAME,val.toString());
                }
                //DAO_KEY_ADD_WHERE = "#addWhere#";
                {
                    val = new StringBuilder();
                    for(VColumn c : table.getColumns()){
                        if(c.getType().equals("Integer")){
                            val.append("\t\tif(").append(c.getColName()).append("!=null && !").append(c.getColName()).append(".equals(-1)){sql.append(\"")
                                    .append(c.getColName()).append("=\").append(").append(c.getColName())
                                    .append(").append(HYPHEN);}").append(System.lineSeparator());
                        }else if(c.getType().equals("String")){
                            val.append("\t\tif(").append(c.getColName()).append("!=null){sql.append(\"")
                                    .append(c.getColName()).append("=\'\").append(").append(c.getColName())
                                    .append(").append(\"\'\").append(HYPHEN);}").append(System.lineSeparator());
                        }
                    }
                    templateReplace(templateCopy,Constants.DAO_KEY_ADD_WHERE,val.toString());
                }
                //DAO_KEY_COLS = "#append.columns#";
                {
                    val = new StringBuilder();
                    val.append(".append(\" ");
                    for (VColumn c : table.getColumns()){
                        if(!c.isPk())
                            val.append(c.getColName()).append(",");
                    }
                    val.deleteCharAt(val.length()-1);
                    val.append(" \")");
                    templateReplace(templateCopy,Constants.DAO_KEY_COLS,val.toString());
                }
                //DAO_KEY_ID_MET = "#id.method#";
                {
                    val = new StringBuilder();
                    for (VColumn c : table.getColumns()){
                        if(c.isPk()){
                            val.append(upperCase(c.getColName()));
                        }
                    }
                    templateReplace(templateCopy,Constants.DAO_KEY_ID_MET,val.toString());
                }
                //DAO_KEY_COLS_USED = "#para.columns.used#";
                //col1,col2,col3
                {
                    val = new StringBuilder();
                    for (VColumn c : table.getColumns()){
                        val.append(c.getColName()).append(",");
                    }
                    val.deleteCharAt(val.length()-1);
                    templateReplace(templateCopy,Constants.DAO_KEY_COLS_USED,val.toString());
                }

                //生成文件
                //包名创建目录


                String javaFile = path+"\\"+upperCase(table.getName())+"Dao.java";
                f = new File(javaFile);
                if(f.createNewFile()){
                    BufferedWriter writer  = new BufferedWriter(new FileWriter(f));
                    writer.write(templateCopy.toString());
                    writer.flush();
                    writer.close();
                }


            }

        }
    }

    @Override
    public void setTemplate(StringBuilder t) {
        template = t;
    }

    @Override
    public void setVConfig(ViceConfig c) {
        config = c;
    }

}
