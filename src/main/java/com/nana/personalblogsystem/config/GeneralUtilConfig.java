/*
 * ***************************************************************************************
 * author: XiaoLFeng(https://www.x-lf.com)
 * about:
 *   The project contains the source code of com.xlf.schedule.
 *   All source code for this project is licensed under the MIT open source license.
 * licenseStatement:
 *   Copyright (c) 2016-2024 XiaoLFeng. All rights reserved.
 *   For more information about the MIT license, please view the LICENSE file
 *     in the project root directory or visit:
 *   https://opensource.org/license/MIT
 * disclaimer:
 *   Since this project is in the model design stage, we are not responsible for any losses
 *     caused by using this project for commercial purposes.
 *   If you modify the code and redistribute it, you need to clearly indicate what changes
 *     you made in the corresponding file.
 *   If you want to modify it for commercial use, please contact me.
 * ***************************************************************************************
 */

package com.nana.personalblogsystem.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.xlf.utility.UtilProperties;
import com.xlf.utility.config.UtilConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 通用工具配置类
 * <p>
 * 该类用于配置通用工具相关配置;
 * 该类使用 {@link Configuration} 注解标记;
 *
 * @version v1.0.0
 * @since v1.0.0
 * @author xiao_lfeng
 */
@Configuration
@RequiredArgsConstructor
public class GeneralUtilConfig {

    /**
     * 配置通用工具配置
     *
     * @return {@link UtilConfiguration} 对象
     */
    @Bean
    public UtilConfiguration utilConfiguration() {
        UtilProperties utilProperties = new UtilProperties();

        return new UtilConfiguration(utilProperties);
    }
}