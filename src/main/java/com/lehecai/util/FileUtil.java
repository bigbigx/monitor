package com.lehecai.util;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public abstract class FileUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FileUtil.class.getName());

	public static void uploadFile(File file, String toFilePath)
	throws FileNotFoundException, IOException {
		InputStream is = null;
		BufferedOutputStream os = null;
		try {
			is = new FileInputStream(file);
			os = new BufferedOutputStream(new FileOutputStream(toFilePath));
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
		} catch (FileNotFoundException fnfe) {
			throw fnfe;
		} catch (IOException ioe) {
			throw ioe;
		} finally {
			if (os != null)
				os.close();
			if (is != null)
				is.close();
		}
	}
	public static boolean mkdir(String pathname) {
		File path = new File(pathname);
		if (!path.exists()) {
			return path.mkdirs();
		}
		return true;
	}
	
	public static void rm(String pathname) {
		File file = new File(pathname);
		if(file.exists()) {
			if(file.isFile())
				file.delete();
		}
	}
	
	public static boolean write(String pathname,String content) {
		File file = new File(pathname);
		boolean b = true;
		try {
			if(!file.exists()) {
				mkdir(file.getParentFile().getPath());
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(content.getBytes("utf-8"));
			fos.flush();
			fos.close();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			b = false;
		}
		return b;
	}
	public static boolean write(String pathname,String content,String charset) {
		File file = new File(pathname);
		if(charset==null ||charset.equals("")){
			charset="utf-8";
		}
		boolean b = true;
		try {
			if(!file.exists()) {
				mkdir(file.getParentFile().getPath());
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(content.getBytes(charset));
			fos.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
			b = false;
		}
		return b;
	}
	public static String read(String pathname) {
		File file = new File(pathname);
		String s = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			byte[] bytes = new byte[fis.available()];
			fis.read(bytes);
			s = new String(bytes);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}

    /**
     * 缓冲流读取文件，按行返回
     */
    public static List<String> readLine(String pathname){
        List<String> list = new ArrayList<String>();
        File file = new File(pathname);
        Reader reader ;
        BufferedReader br = null;
        if (!file.exists()) {
            return list ;
        }
        try {
            reader = new FileReader(pathname);
            br  = new BufferedReader(reader);
            String line ;
                while((line = br.readLine()) != null){
                    list.add(line);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            logger.error("读取文件" + pathname + "失败！");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                    logger.error("关闭IO流失败");
                }
            }
        }
        return list;
    }
	
	public static String toURIPath(String pathname,boolean isDirectory) {
		String p = pathname;
		if (File.separatorChar != '/')
		    p = p.replace(File.separatorChar, '/');
		if (!p.startsWith("/") && p.length() > 0)
		    p = "/" + p;
		if (!p.endsWith("/") && isDirectory)
			p = p + "/";
		if (p.startsWith("//"))
			p = "//" + p;
		URI uri = null;
		try {
			uri = new URI(null, null, p, null);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return uri == null ? null : uri.toString();
	}

    /**
     * 先创建目录与文件
     * */
    public static boolean createFile(String destFileName) {
        File file = new File(destFileName);
        if (file.exists()) {
            return false;
        }
        if (destFileName.endsWith(File.separator)) {
            return false;
        }
        //判断目标文件所在的目录是否存在
        if (!file.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            logger.info("目标文件所在目录不存在，准备创建它！");
            if (!file.getParentFile().mkdirs()) {
                logger.info("创建目标文件所在目录失败！");
                return false;
            }
        }
        //创建目标文件
        try {
            if (file.createNewFile()) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            logger.error("创建文件" + destFileName + "失败！");
            return false;
        }
    }
}
