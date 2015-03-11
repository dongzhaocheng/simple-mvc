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
 * Created by dongzhaocheng on 2014/12/25.
 * model生成器
 */
public class ModelGenerator extends AbstractGenerator {


    @Override
    public void generate() throws IOException {
        if(template!=null){
            String path = config.getPackageValue().replace(".","\\");
            path = path + "\\model";
            File f = new File(path);
            if(!f.mkdirs()){
                return;
            }
            for (VTable table : config.getTables()){
                StringBuilder val = new StringBuilder();
                StringBuilder templateCopy = new StringBuilder(template);
                //package语句
                {
                    val.append(config.getPackageValue()).append(".model;").append(System.lineSeparator());
                    templateReplace(templateCopy, Constants.MOD_KEY_PACKAGE,val.toString());
                }
                //MOD_KEY_FIELDS = "#fields#"; private Integer col;
                {
                    val = new StringBuilder();
                    for(VColumn c : table.getColumns()){
                        val.append("\t private ");
                        if(c.isPk()){
                            val.append(c.getType()).append(" ").append(c.getColName()).append(" = -1").append(";").append(System.lineSeparator());
                        }else{
                            val.append(c.getType()).append(" ").append(c.getColName()).append(";").append(System.lineSeparator());
                        }

                    }
                    templateReplace(templateCopy,Constants.MOD_KEY_FIELDS,val.toString());
                }
                //MOD_KEY_INTF = "#modelInterface#";
                {
                    val = new StringBuilder();
                    val.append(upperCase(table.getName())).append("Model");
                    templateReplace(templateCopy,Constants.MOD_KEY_INTF,val.toString());
                }
                //MOD_KEY_PARA = "#paraFeilds#";
                {
                    val = new StringBuilder();
                    for(VColumn c : table.getColumns()){
                        if(!c.isPk())
                            val.append(c.getType()).append(" ").append(c.getColName()).append(",");
                    }
                    val.deleteCharAt(val.length()-1);
                    templateReplace(templateCopy,Constants.MOD_KEY_PARA,val.toString());
                }
                //D_KEY_FIELD_INIT = "#feildsInitValue#";
                {
                    val = new StringBuilder();
                    for (VColumn c : table.getColumns()){
                        if(!c.isPk()){
                            val.append("\t\tthis.").append(c.getColName()).append(" = ").append(c.getColName()).append(";").append(System.lineSeparator());
                        }
                    }
                    templateReplace(templateCopy,Constants.MOD_KEY_FIELD_INIT,val.toString());
                }
                //MOD_KEY_SETTER = "#setter#"; public void setCol1(Type col1){this.col1=col1;}
                {
                    val = new StringBuilder();
                    for (VColumn c : table.getColumns()){
                        val.append("\tpublic void set").append(upperCase(c.getColName())).append("(")
                                .append(c.getType()).append(" ").append(c.getColName()).append("){this.")
                                .append(c.getColName()).append("=").append(c.getColName()).append(";}")
                                .append(System.lineSeparator());
                    }
                    templateReplace(templateCopy,Constants.MOD_KEY_SETTER,val.toString());
                }
                //MOD_KEY_GETTER = "#getter#";
                //public type getCol1(){return this.col1;}
                {
                    val = new StringBuilder();
                    for (VColumn c : table.getColumns()){
                        val.append("\tpublic ").append(c.getType()).append(" get").append(upperCase(c.getColName()))
                                .append("(){return this.").append(c.getColName()).append(";}").append(System.lineSeparator());
                    }
                    templateReplace(templateCopy,Constants.MOD_KEY_GETTER,val.toString());
                }
                //MOD_KEY_MAP_INIT = "#mapInitValue#";
                //setCol1((col1Type) map.get("col1Name"));
                {
                    val = new StringBuilder();
                    for (VColumn c : table.getColumns()){
                        val.append("\t\tset").append(upperCase(c.getColName())).append("((").append(c.getType()).append(") map.get(\"").append(c.getColName()).append("\"));").append(System.lineSeparator());
                    }
                    templateReplace(templateCopy,Constants.MOD_KEY_MAP_INIT,val.toString());
                }

                String javaFile = path+"\\"+upperCase(table.getName())+"Model.java";
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
    public void setTemplate(StringBuilder template) {
        this.template = template;
    }

    @Override
    public void setVConfig(ViceConfig config) {
        this.config = config;
    }


}
