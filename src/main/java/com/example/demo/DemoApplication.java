package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//TODO GTB-完成度: * 整体功能完成度还可以。
//TODO GTB-完成度: * 某些边界情况没有考虑完善。

//TODO GTB-完成度: - user 不存在时，查询其 educations，应该返回 404，而不是空数组。
//TODO GTB-完成度: - user 不存在时，不能为其创建 educations，但现在可以创建。
//TODO GTB-完成度: - 创建 user 时，如果 name 字段没提供、age 不合法等校验失败时，返回了 404，应该是 400。
//TODO GTB-完成度: - 创建 education 时，如果字段校验失败，应该返回 400，而不是 404。

//TODO GTB-测试: * 没写测试。

//TODO GTB-知识点: * 对 Optional API 的掌握还不够熟练。
//TODO GTB-知识点: * Spring MVC 的一些 annotations 掌握不够熟练。

//TODO GTB-工程实践: * clean code 方面欠缺明显。

//TODO GTB-工程实践: - 很多提交的 log 都不够表意。
//TODO GTB-工程实践: - 很多提交的提交内容不够内聚，不是迭代开发的。

//TODO GTB-综合: * 功能方面完成度还可以，但是知识点运用和工程实践方面欠缺较多。

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
