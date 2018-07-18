package com.project.util;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.project.controller.IndexController;

@Component

public class FileUtil {

private  HttpSession httpSession;

	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

	/*private static final String imageDirectory ="ShoppingCartImages";

	private static String rootPath = System.getProperty("catalina.home");

	public static boolean copyFileNIO(MultipartFile file, String fileName){

		File dest = new File(rootPath +File.separator+imageDirectory+File.separator+fileName);{

			if (!dest.exists()) {

				dest.mkdir();*/

	private static String rootPath = IndexController.imageDirectory;//System.getProperty("user.dir");

	public static  boolean copyFileNIO(MultipartFile file,String fileName){ 

		System.out.println(rootPath);

		File dest = new File(rootPath+File.separator  + fileName);

		System.out.println("where it is uploading ??"+ dest.getAbsolutePath());

			try {

				file.transferTo(dest);

				return true;

			} catch (Exception e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}

			return false;

		}



}
