package com.koubeisi;

import com.koubeisi.dao.UserDOMapper;
import com.koubeisi.dataobject.UserDO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication(scanBasePackages = "com.koubeisi")
@RestController
@MapperScan("com.koubeisi.dao")
public class App {

    public static void main( String[] args ) {

        SpringApplication.run(App.class,args);

    }
}
