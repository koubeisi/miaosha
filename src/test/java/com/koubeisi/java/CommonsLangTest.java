package com.koubeisi.java;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description
 * @Author koubeisi
 * @Date 2019/1/15 19:13
 * @Version 1.0
 **/
public class CommonsLangTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonsLangTest.class);

    @Test
    public void testSpringUtils(){

        String str1 = null;

        String str2 = null;

        String str3 = "abcd";

        String str4 = "abcd";

        LOGGER.info("null & null = {}",StringUtils.equals(str1, str2));
        LOGGER.info("null & abcd = {}",StringUtils.equals(str3, str2));
        LOGGER.info("abcd & abcd = {}",StringUtils.equals(str3, str4));

    }

}
