package com.nana.personalblogsystem.config.init;

import com.nana.personalblogsystem.mapper.InfoMapper;
import com.nana.personalblogsystem.model.entity.InfoDO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class PrepareAlgorithm {
    private final InfoMapper infoMapper;

    /**
     * 检查信息表字段
     * <p>
     * 该方法用于检查信息表字段，当字段不存在时，创建字段；若字段存在，忽略数据库的检查。
     *
     * @param ikey   键
     * @param ivalue 值
     */
    public void checkInfoTableFields(String ikey, String ivalue) {
        InfoDO infoDO = infoMapper.selectInfoByKey(ikey);
        if (infoDO == null) {
            InfoDO newInfo = new InfoDO()
                    .setIkey(ikey)
                    .setIvalue(ivalue);
            infoMapper.insertInfo(newInfo);
            log.debug("[INIT] 创建信息表字段 | <{}>{}", ikey, ivalue);
        }
    }


    /**
     * 获取全局变量
     * <p>
     * 该方法用于获取全局变量；当全局变量不存在时，初始化全局变量。
     */
    public String initGetGlobalVariable(String ikey){
            InfoDO infoDO = infoMapper.selectInfoByKey(ikey);
            if(infoDO == null){
                return null;
            }
            return infoDO.getIvalue();
    }
}
