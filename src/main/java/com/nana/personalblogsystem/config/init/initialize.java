package com.nana.personalblogsystem.config.init;


import com.google.gson.Gson;
import com.nana.personalblogsystem.mapper.InfoMapper;
import com.nana.personalblogsystem.mapper.UserMapper;
import com.nana.personalblogsystem.model.entity.UserDO;
import com.xlf.utility.util.PasswordUtil;
import com.xlf.utility.util.UuidUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.nana.personalblogsystem.constant.SystemConstant;

import java.util.ArrayList;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class initialize {
    private final InfoMapper infoMapper;
    private final UserMapper userMapper;
    private PrepareAlgorithm prepare;

    /**
     * 初始化项目
     */
    @PostConstruct
    public void init() {
        log.info("[INIT] 系统初始化开始");
        log.info("========== Start of Initialization ==========");
        // 初始化准备算法
        prepare = new PrepareAlgorithm(infoMapper);

        // 初始化数据库完整性检查
        this.initGlobalVariableAssignment();
        this.initInfoCheck();
    }


    /**
     * 初始化结束
     *
     * @return {@link CommandLineRunner} 命令行运行器
     */
    @Bean
    public CommandLineRunner initFinal() {
        return args -> {
            log.info("=========== End of Initialization ===========");
            System.out.print("""
                    
                       ___  __            ____         __         \s
                      / _ )/ /__  ___ _  / __/_ _____ / /____ __ _\s
                     / _  / / _ \\/ _ `/ _\\ \\/ // (_-</ __/ -_)  ' \\
                    /____/_/\\___/\\_, / /___/\\_, /___/\\__/\\__/_/_/_/
                                /___/      /___/                  \s
                    
                
                    """);
            System.out.println("\t\t\t\u001B[33m::: " + SystemConstant.SYSTEM_AUTHOR + " :::\t\t\t\t\t\t\t ::: " + SystemConstant.SYSTEM_VERSION + " :::\u001B[0m");
        };
    }


    /**
     * 初始化全局变量赋值
     */
    private void initGlobalVariableAssignment() {
        log.info("[INIT] 全局变量初始化...");

        // 系统配置
        SystemConstant.superAdminUUID = prepare.initGetGlobalVariable("system_super_admin_uuid");
    }



    /**
     * 初始化信息表类型检查
     */
    private void initInfoCheck() {
        log.info("[INIT] 检查默认信息表信息...");
        if (prepare.initGetGlobalVariable("system_super_admin_uuid") == null) {
            String infoValueUuid = UuidUtil.generateUuidNoDash();
            UserDO userDO = new UserDO();
            userDO.setUuid(infoValueUuid);
            userDO.setEmail("admin@admin.com");
            userDO.setPassword(PasswordUtil.encrypt("123456"));
            userDO.setUsername("admin");
            userMapper.createUser(userDO);
            prepare.checkInfoTableFields("system_super_admin_uuid", infoValueUuid);
            SystemConstant.superAdminUUID = infoValueUuid;
        } else {
            SystemConstant.superAdminUUID = prepare.initGetGlobalVariable("system_super_admin_uuid");
        }
    }
}
