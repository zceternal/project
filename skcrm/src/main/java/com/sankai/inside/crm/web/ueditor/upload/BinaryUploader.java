package com.sankai.inside.crm.web.ueditor.upload;

import java.io.File;
import java.io.File;
import java.io.IOException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Arrays;
import java.util.List;
import java.util.List;
import java.util.Map;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sankai.inside.crm.web.ueditor.PathFormat;
import com.sankai.inside.crm.web.ueditor.define.AppInfo;
import com.sankai.inside.crm.web.ueditor.define.BaseState;
import com.sankai.inside.crm.web.ueditor.define.FileType;
import com.sankai.inside.crm.web.ueditor.define.State;

public class BinaryUploader {

	public static final State save(HttpServletRequest request, Map<String, Object> conf) {
		FileItemStream fileStream = null;
		boolean isAjaxUpload = request.getHeader("X_Requested_With") != null;

		if (!ServletFileUpload.isMultipartContent(request)) {
			return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
		}

		ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());

		upload.setHeaderEncoding("UTF-8");

		try {
			FileItemIterator iterator = upload.getItemIterator(request);

			while (iterator.hasNext()) {
				fileStream = iterator.next();

				if (!fileStream.isFormField())
					break;
				fileStream = null;
			}

			if (fileStream == null) {
				return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
			}

			String savePath = (String) conf.get("savePath");
			String originFileName = fileStream.getName();
			String suffix = FileType.getSuffixByFilename(originFileName);

			originFileName = originFileName.substring(0, originFileName.length() - suffix.length());

			savePath = savePath + suffix;

			long maxSize = ((Long) conf.get("maxSize")).longValue();

			if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
				return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
			}

			savePath = PathFormat.parse(savePath, originFileName);

			String physicalPath = (String) conf.get("rootPath") + savePath;

			InputStream is = fileStream.openStream();
			State storageState = StorageManager.saveFileByInputStream(is, physicalPath, maxSize);
			is.close();

			if (storageState.isSuccess()) {
				// 保存路径****************************************
				if (originFileName.contains("\\")) {
					String[] strArr = originFileName.split("\\\\");
					originFileName = strArr[strArr.length - 1];
				}
				// 判断request.getContextPath()是否为空 避免附件路径出现多余的“/”
				if (request.getContextPath() != null && !"".equals(request.getContextPath())
						&& !"/".equals(request.getContextPath())) {
					
					//StringBuffer url = request.getRequestURL();  
					//String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getContextPath()).append("/").toString(); 
					
					storageState.putInfo("url",
							request.getContextPath() + File.separator + PathFormat.format(savePath));
				} else {
					storageState.putInfo("url", PathFormat.format(savePath));
				}
				storageState.putInfo("type", suffix);
				storageState.putInfo("original", originFileName + suffix);
				storageState.putInfo("physicalPath", physicalPath);
				request.getSession().getServletContext().getRealPath("/");
			}

			return storageState;
		} catch (FileUploadException e) {
			return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR);
		} catch (IOException e) {
		}
		return new BaseState(false, AppInfo.IO_ERROR);
	}

	private static boolean validType(String type, String[] allowTypes) {
		List<String> list = Arrays.asList(allowTypes);

		return list.contains(type);
	}
}
