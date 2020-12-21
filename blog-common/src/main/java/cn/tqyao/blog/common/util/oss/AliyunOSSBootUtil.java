/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.common.util.oss;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * .<br>
 * 阿里云 oss 上传工具类
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/12/21 16:47 <br>
 */

public class AliyunOSSBootUtil {

    public static String FILE_DIR = "img";

    private volatile static OSSClient  ossClient = null;

    /**
     * 初始化 oss 客户端
     * @param properties
     * @return
     */
    private static void initOSS(AliyunOSSProperties properties) {
        if (ossClient == null ) {
            synchronized (AliyunOSSBootUtil.class) {
                if (ossClient == null) {
                    ossClient = new OSSClient(properties.getEndpoint(),
                            new DefaultCredentialProvider(properties.getAccessKeyId(), properties.getAccessKeySecret()),
                            new ClientConfiguration());
                }
            }
        }
    }

    /**
     * 上传文件至阿里云 OSS
     * 文件上传成功,返回文件完整访问路径
     * 文件上传失败,返回 null
     *
     * @param properties oss 配置信息
     * @param file 待上传文件
     * @param fileDir 文件保存目录
     * @return oss 中的相对文件路径
     */
    public static String upload(AliyunOSSProperties properties, MultipartFile file, String fileDir) {
        initOSS(properties);
        StringBuilder fileUrl = new StringBuilder();
        try {
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
            String fileName = System.currentTimeMillis() + "-" + UUID.randomUUID().toString().substring(0,18) + suffix;
            if (!fileDir.endsWith("/")) {
                fileDir = fileDir.concat("/");
            }
            fileUrl.append(fileDir).append(fileName);
            ossClient.putObject(properties.getBucketName(), fileUrl.toString(), file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        fileUrl.insert(0, properties.getUrl()).append("/");
        return fileUrl.toString();
    }

    /**
     * 上传文件至阿里云 OSS
     * 文件上传成功,返回文件完整访问路径
     * 文件上传失败,返回 null
     *
     * @param properties oss 配置信息
     * @param file 待上传文件
     * @return oss 中的相对文件路径
     */
    public static String upload(AliyunOSSProperties properties, MultipartFile file) {
        return upload(properties, file,FILE_DIR);
    }


    /**
     * 删除阿里云 OSS Bucket 指定文件
     * @param objectName 对象路径
     * @return
     */
    public static Boolean remove(AliyunOSSProperties properties, String objectName){
        initOSS(properties);
        try {
            ossClient.deleteObject(properties.getBucketName(), objectName);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
