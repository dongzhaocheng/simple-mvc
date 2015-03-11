package org.vice.test;

import org.vice.core.conf.D4JXmlLoader;
import org.vice.core.conf.ViceConfig;
import org.vice.core.generate.DaoGenerator;
import org.vice.core.generate.Generator;
import org.vice.core.generate.ModelGenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by dongzhaocheng on 2014/12/17.
 * 测试
 */
public class DocTest {

    public static void main(String[] args) throws Exception{
        D4JXmlLoader d = new D4JXmlLoader();
        ViceConfig config = d.read();
        Generator g = new DaoGenerator();
        File file = new File("dao.template");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while(true){
            line = reader.readLine();
            if(line!=null){
                stringBuilder.append(line).append(System.lineSeparator());
            }else {
                break;
            }
        }
        reader.close();
        g.setTemplate(stringBuilder);
        g.setVConfig(config);
        g.generate();

        g = new ModelGenerator();
        file = new File("model.template");
        reader = new BufferedReader(new FileReader(file));
        stringBuilder = new StringBuilder();
        while(true){
            line = reader.readLine();
            if(line!=null){
                stringBuilder.append(line).append(System.lineSeparator());
            }else {
                break;
            }
        }
        reader.close();
        g.setTemplate(stringBuilder);
        g.setVConfig(config);
        g.generate();

    }
}
