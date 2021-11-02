package com.jpa.develop;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JasyptEncoding {

    final static Logger logger = LoggerFactory.getLogger(JasyptEncoding.class);
    static StandardPBEStringEncryptor encryptor;

    // 1. 모든 테스트 실행 하기 전 딱 한번만 수행 한다
    // 2. static 선언 필요
    @BeforeAll
    static void setUp() {
        encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword("grayson");
    }

    @Test
    @DisplayName("Jasypt 암호화 테스트")
    void jasyptEncoding() {
        logger.info("암호 전 : " + "jdbc:mysql://localhost:3306/db_spring?serverTimezone=UTC&useSLL=false");
        logger.info("암호 후 : " +  encryptor.encrypt("jdbc:mysql://localhost:3306/db_spring?serverTimezone=UTC&useSLL=false"));

    }


}
