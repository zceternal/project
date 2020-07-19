package com.sankai.inside.crm.web.core;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.sankai.inside.crm.entity.UploadResultDTO;

@Controller
public class UploadToolkits {
	private final static int IMAGE_SIZE=120;
	
	/**
	 * 
	 * @param fileType 文件类型 只允许 "img"和"file"两类
	 * @param file 控制层传过来的上传文件实例，参见DemoController中的upload方法
	 * @return 如果上传成功 返回保存的路径，   例:[isSuccess=true, path=upload\136cb134-a999-4598-8c42-7340ae4c698b.docx, resultCode=SUCCESS, size=277.0, msg=上传成功，文件大小：277.0KB]
	 */
	public static UploadResultDTO upload(String fileType,MultipartFile file){
		String ext=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1,file.getOriginalFilename().length());
		
		UploadResultDTO result=new UploadResultDTO();
		if(ext.equalsIgnoreCase("exe")||ext.equalsIgnoreCase("iso")){
			result.setMsg("不允许的文件类型");
			result.setSuccess(false);
			return result;
		}
		
		//double size=Double.parseDouble(new DecimalFormat("#.000").format(file.getSize()/1024.0/1024.0));//单位M
		double size=file.getSize()/1024;//单位KB 
		if((size/1024)>10){
			result.setMsg("文件大小超出限制，最大只允许上传10M文件");
			result.setResultCode("FAIL");
			result.setSize(size);
			result.setSuccess(false);
			return result;
		}
		if(!(fileType.equalsIgnoreCase("img")||fileType.equalsIgnoreCase("file"))){
			result.setSuccess(false);
			result.setResultCode("FAIL");
			result.setMsg("传入参数错误：fileType，文件大小："+size+"KB");
			result.setSize(size);
			return result;
		}
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		HttpSession session=request.getSession();
		
		
		String filePath=session.getServletContext().getRealPath("/"+"upload"+File.separator+fileType);
		
		String fileName=UUID.randomUUID().toString()+"."+ext;
		File targetFile=new File(filePath+File.separator+fileName);
		if(!targetFile.getParentFile().exists()){//创建目录
			targetFile.getParentFile().mkdirs();
		}
		try {
			file.transferTo(targetFile);
			if(targetFile.exists()){
				result.setSuccess(true);
				result.setResultCode("SUCCESS");
				result.setMsg("上传成功，文件大小："+size+"KB");
				result.setPath("upload"+File.separator+fileName);
				result.setSize(size);
				
			}else{
				result.setSuccess(false);
				result.setResultCode("FAIL");
				result.setMsg("上传失败");
			}
			
		} catch (IllegalStateException | IOException e) {
			result.setResultCode("FAILURE");
			result.setMsg("上传失败，文件大小："+size+"KB");
			e.printStackTrace();
			return result;
		}
		return result;
	}
	
	
}
