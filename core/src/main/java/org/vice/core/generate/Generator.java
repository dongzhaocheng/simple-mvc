package org.vice.core.generate;

import org.vice.core.conf.ViceConfig;

import java.io.IOException;

/**
 * Created by dongzhaocheng on 2014/12/21.
 * 生成器
 */
public interface Generator {

    public void generate() throws IOException;

    public void setTemplate(StringBuilder template);

    public void setVConfig(ViceConfig config);

}
