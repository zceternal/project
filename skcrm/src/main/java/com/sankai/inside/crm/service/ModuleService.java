package com.sankai.inside.crm.service;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.sankai.inside.crm.entity.Module;

@Service
public class ModuleService {

	@Cacheable("sys-resourceCache")
	public Module[] GetModulesForCache() throws IOException {
		return GetModules();
	}

	public Module[] GetModules() throws IOException {
		System.out.println("加载模块信息");

		InputStream stream = this.getClass().getResourceAsStream("/modules.json");
		DataInputStream dis = new DataInputStream(stream);
		byte[] buf = new byte[4096];
		int len = dis.read(buf);
		String data = new String(buf, 0, len,Charset.forName("utf-8"));

		Module[] result = JSON.parseObject(data, Module[].class);
		System.out.println("加载模块完成，数量：" + result.length);
		return result;
	}

}
