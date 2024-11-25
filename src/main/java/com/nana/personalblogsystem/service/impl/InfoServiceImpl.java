package com.nana.personalblogsystem.service.impl;

import com.nana.personalblogsystem.mapper.InfoMapper;
import com.nana.personalblogsystem.model.dto.InfoDTO;
import com.nana.personalblogsystem.model.dto.TokenDTO;
import com.nana.personalblogsystem.model.entity.InfoDO;
import com.nana.personalblogsystem.model.entity.TokenDO;
import com.nana.personalblogsystem.service.InfoService;
import com.xlf.utility.ErrorCode;
import com.xlf.utility.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class InfoServiceImpl implements InfoService {
    private final InfoMapper infoMapper;

    @Override
    public InfoDTO selectInfoByKey(String ikey) {

        //检查info是否存在
        InfoDO infoDO = infoMapper.selectInfoByKey(ikey);
        if (infoDO == null) {
            throw new BusinessException("info不存在", ErrorCode.NOT_EXIST);
        }
        InfoDTO infoDTO = new InfoDTO();
        BeanUtils.copyProperties(infoDO, infoDTO);
        return infoDTO;
    }

    @Override
    public String getSuperAdminUuid() {
        // 查询超级管理员的 UUID
        InfoDO infoDO = infoMapper.selectInfoByKey("system_super_admin_uuid");
        if (infoDO == null) {
            throw new BusinessException("超级管理员 UUID 未配置", ErrorCode.NOT_EXIST);
        }
        return infoDO.getIvalue(); // 返回超级管理员 UUID
    }

}
