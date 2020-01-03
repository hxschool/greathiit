package  com.thinkgem.jeesite.modules.storage.service;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.xmlpull.v1.XmlPullParserException;

import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.Result;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidArgumentException;
import io.minio.errors.InvalidBucketNameException;
import io.minio.errors.NoResponseException;
import io.minio.messages.Bucket;
import io.minio.messages.Item;


//会生成一个包含常量，和标识了NotNull的变量 的构造方法。生成的构造方法是private，如何想要对外提供使用可以使用staticName选项生成一个static方法。

@Service
public class MinioTemplate implements InitializingBean {

	@Value("${minio.endpoint}")
	private String endpoint;
	@Value("${minio.accessKey}")
	private String accessKey;
	@Value("${minio.secretKey}")
	private String secretKey;
	private MinioClient client;

	/**
	 * 创建bucket
	 *
	 * @param bucketName bucket名称
	 */
	
	public void createBucket(String bucketName) throws Exception{
		if (!client.bucketExists(bucketName)) {
			client.makeBucket(bucketName);
		}
	}

	/**
	 * 获取全部bucket
	 * <p>
	 * https://docs.minio.io/cn/java-client-api-reference.html#listBuckets
	 */
	
	public List<Bucket> getAllBuckets() throws Exception{
		return client.listBuckets();
	}

	/**
	 * 根据bucketName获取信息
	 * 
	 * @param bucketName bucket名称
	 */
	
	public Optional<Bucket> getBucket(String bucketName) throws Exception{
		return client.listBuckets().stream().filter(b -> b.name().equals(bucketName)).findFirst();
	}

	/**
	 * 根据bucketName删除信息
	 * 
	 * @param bucketName bucket名称
	 */
	
	public void removeBucket(String bucketName) throws Exception{
		client.removeBucket(bucketName);
	}

	/**
	 * 根据文件前置查询文件
	 *
	 * @param bucketName bucket名称
	 * @param prefix     前缀
	 * @param recursive  是否递归查询
	 * @return MinioItem 列表
	 */
	
	public List<Item> getAllObjectsByPrefix(String bucketName, String prefix, boolean recursive) throws Exception{
		List<Item> objectList = new ArrayList<>();
		Iterable<Result<Item>> objectsIterator = client.listObjects(bucketName, prefix, recursive);
		
		while (objectsIterator.iterator().hasNext()) {
			objectList.add(objectsIterator.iterator().next().get());
		}
		return objectList;
	}

	/**
	 * 获取文件外链
	 *
	 * @param bucketName bucket名称
	 * @param objectName 文件名称
	 * @param expires    过期时间 <=7
	 * @return url
	 */
	
	public String getObjectURL(String bucketName, String objectName, Integer expires) throws Exception{
		return client.presignedGetObject(bucketName, objectName, expires);
	}

	/**
	 * 获取文件
	 *
	 * @param bucketName bucket名称
	 * @param objectName 文件名称
	 * @return 二进制流
	 * @throws XmlPullParserException 
	 * @throws IOException 
	 * @throws InvalidArgumentException 
	 * @throws InternalException 
	 * @throws ErrorResponseException 
	 * @throws NoResponseException 
	 * @throws InsufficientDataException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidBucketNameException 
	 * @throws InvalidKeyException 
	 */
	
	public InputStream getObject(String bucketName, String objectName) throws Exception {
		return client.getObject(bucketName, objectName);
	}

	/**
	 * 上传文件
	 *
	 * @param bucketName bucket名称
	 * @param objectName 文件名称
	 * @param stream     文件流
	 * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
	 */
	public void putObject(String bucketName, String objectName, InputStream stream) throws Exception {
		client.putObject(bucketName, objectName, stream, stream.available(), "application/octet-stream");
	}

	/**
	 * 上传文件
	 *
	 * @param bucketName  bucket名称
	 * @param objectName  文件名称
	 * @param stream      文件流
	 * @param size        大小
	 * @param contextType 类型
	 * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
	 */
	public void putObject(String bucketName, String objectName, InputStream stream, long size, String contextType)
			throws Exception {
		client.putObject(bucketName, objectName, stream, size, contextType);
	}

	/**
	 * 获取文件信息
	 *
	 * @param bucketName bucket名称
	 * @param objectName 文件名称
	 * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#statObject
	 */
	public ObjectStat getObjectInfo(String bucketName, String objectName) throws Exception {
		return client.statObject(bucketName, objectName);
	}

	/**
	 * 删除文件
	 *
	 * @param bucketName bucket名称
	 * @param objectName 文件名称
	 * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#removeObject
	 */
	public void removeObject(String bucketName, String objectName) throws Exception {
		client.removeObject(bucketName, objectName);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.hasText(endpoint, "Minio url 为空");
		Assert.hasText(accessKey, "Minio accessKey为空");
		Assert.hasText(secretKey, "Minio secretKey为空");
		this.client = new MinioClient(endpoint, accessKey, secretKey);
	}

	
}