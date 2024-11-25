package com.nana.personalblogsystem.service;

import com.nana.personalblogsystem.model.dto.InfoDTO;
import com.nana.personalblogsystem.model.entity.InfoDO;

public interface InfoService {
    InfoDTO selectInfoByKey(String ikey);

    String getSuperAdminUuid();
}
