package com.fnst.face;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.fnst.face.mapper")
@ServletComponentScan("com.fnst.face.common.filter")
public class FaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FaceApplication.class, args);
	}

}
