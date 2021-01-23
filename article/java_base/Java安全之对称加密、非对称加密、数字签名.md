## 两种加密方式

- Java中加密分为两种方式
  + 一个是对称加密
  + 另一个是非对称加密
- 对称加密是加密和解密的钥匙相同
- 非对称加密是加密和解密的钥匙不同

## 对称加密与非对称加密的区别
- 对称加密称为密钥加密
  + 速度快
  + 加密和解密的钥匙必须相同
  + 只有通信双方才能知道密钥。
- 非对称加密称为公钥加密
  + 算法更加复杂，速度慢，
  + 加密和解密钥匙不相同
  + 任何人都可以知道公钥，只有一个人持有私钥可以解密。

## 对称加密解密
	/* 
	 * 对称加密 
	 */  
	private static void secretEncrypt() throws Exception {  
	    //使用Cipher的实例  
	    Cipher cipher =Cipher.getInstance("AES");  
	      
	    //得到加密的钥匙  
	    SecretKey key =KeyGenerator.getInstance("AES").generateKey();  
	      
	    //初始化加密操作,传递加密的钥匙  
	    cipher.init(Cipher.ENCRYPT_MODE,key);  
	      
	    //将加密的钥匙写入secretKey.key文件中  
	    FileOutputStream fosKey=new FileOutputStream("secretKey.key");  
	    ObjectOutputStream oosSecretKey =new ObjectOutputStream(fosKey);  
	    oosSecretKey.writeObject(key);  
	    oosSecretKey.close();  
	    fosKey.close();  
	       
	     //将加密的内容传递进去，返回加密后的二进制数据  
	    byte [] results =cipher.doFinal("liuyong666".getBytes());  
	      
	    //将加密后的二进制数据写入到secretContent.dat文件中  
	    FileOutputStream fosData=new FileOutputStream("secretContent.dat");  
	    fosData.write(results);  
	    fosData.close();  
	}  
	  
	/* 
	 * 对称解密 
	 */  
	private static void secretDecrypt() throws Exception{  
	    Cipher cipher =Cipher.getInstance("AES");  
	      
	    //获取文件中的key进行解密  
	    FileInputStream fisKey=new FileInputStream("secretKey.key");  
	    ObjectInputStream oisKey =new ObjectInputStream(fisKey);  
	    Key key =(Key)oisKey.readObject();  
	    oisKey.close();  
	    fisKey.close();  
	      
	    //初始化解密操作,传递加密的钥匙  
	    cipher.init(Cipher.DECRYPT_MODE,key);  
	      
	    //获取文件中的二进制数据  
	    FileInputStream fisDat=new FileInputStream("secretContent.dat");  
	    //获取数据第一种方式  
	    byte [] src=new byte [fisDat.available()];  
	    int len =fisDat.read(src);  
	    int total =0;  
	    while(total<src.length){  
	        total +=len;  
	        len=fisDat.read(src,total,src.length-total);  
	    }  
	    //执行解密  
	    byte [] result=cipher.doFinal(src);  
	    fisDat.close();  
	    System.out.println(new String(result));  
	     /*
		//读文件中的数据第二种方式  
		ByteArrayOutputStream baos =new ByteArrayOutputStream();  
		copyStream(fisDat, baos);  
	 	byte [] result=cipher.doFinal(baos.toByteArray());  
		fisDat.close();  
		baos.close();  */
	}
![对称加密后生成的文件](http://i.imgur.com/jDLHrBr.png)
## 基于口令的对称加密与解密
- 系统自动生成的Key不容易记忆，我们可以使用我们容易记忆的口令通过java自带的一个工具将它转换成Key，在解密的时候我们就可以通过口令进行解密。

		private static void secretEncrypt() throws Exception {  
		    //实例化工具  
		    Cipher cipher2=Cipher.getInstance("PBEWithMD5AndDES");  
		      
		    //使用该工具将基于密码的形式生成Key  
		    SecretKey key2=SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(new PBEKeySpec("123".toCharArray()));  
		    PBEParameterSpec parameterspec=new PBEParameterSpec(new byte[]{1,2,3,4,5,6,7,8},1000);  
		      
		    //初始化加密操作，同时传递加密的算法  
		    cipher2.init(Cipher.ENCRYPT_MODE,key2,parameterspec);  
		      
		     //将要加密的数据传递进去，返回加密后的数据  
		    byte [] results =cipher2.doFinal("liuyong666.com".getBytes());  
		      
		    //将加密后的数据写入到文件中  
		    FileOutputStream fosData=new FileOutputStream("liuyong666.com.dat");  
		    fosData.write(results);  
		    fosData.close();  
		}  
		  
		/* 
		 * 基于口令的对称解密 
		 */  
		private static void secretDecrypt() throws Exception{  
		    Cipher cipher2=Cipher.getInstance("PBEWithMD5AndDES");  
		    SecretKey key2=SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(new PBEKeySpec("123".toCharArray()));  
		    PBEParameterSpec parameterspec=new PBEParameterSpec(new byte[]{1,2,3,4,5,6,7,8},1000);  
		    cipher2.init(Cipher.DECRYPT_MODE,key2,parameterspec);  
		    FileInputStream fisDat=new FileInputStream("liuyong666.com.dat");  
		    byte [] src=new byte [fisDat.available()];  
		    int len =fisDat.read(src);  
		    int total =0;  
		    while(total<src.length){  
		        total +=len;  
		        len=fisDat.read(src,total,src.length-total);  
		    }  
		    byte [] result=cipher2.doFinal(src);  
		    fisDat.close();  
		    System.out.println(new String(result));  
		}  		
	
## 非对称加密解密
- 非对称加密是公钥加密，私钥来解密
- 这个个人做用的少一点，主要针对于大型的网站大型的企业

		private static void PublicEnrypt()throws Exception {  
		    Cipher cipher =Cipher.getInstance("RSA");  
		    //实例化Key  
		    KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");  
		    //获取一对钥匙  
		    KeyPair keyPair=keyPairGenerator.generateKeyPair();  
		    //获得公钥  
		    Key publicKey=keyPair.getPublic();  
		    //获得私钥   
		    Key privateKey=keyPair.getPrivate();  
		    //用公钥加密  
		    cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
		    byte [] result=cipher.doFinal("liuyong666".getBytes("UTF-8"));  
		    //将Key写入到文件  
		    saveKey(privateKey,"liuyong_private.key");  
		    //加密后的数据写入到文件  
		    saveData(result,"public_encryt.dat");  
		}  
		  
		/* 
		 * 私钥解密 
		 */  
		private static void privateDecrypt() throws Exception {  
		    Cipher cipher=Cipher.getInstance("RSA");  
		    //得到Key  
		    Key privateKey=readKey("liuyong_private.key");  
		    //用私钥去解密  
		    cipher.init(Cipher.DECRYPT_MODE, privateKey);  
		    //读数据源  
		    byte [] src =readData("public_encryt.dat");  
		    //得到解密后的结果  
		    byte[] result=cipher.doFinal(src);  
		    //二进制数据要变成字符串需解码  
		    System.out.println(new String(result,"UTF-8"));  
		}  
		  
		private static void saveData(byte[] result, String fileName) throws Exception {  
		    FileOutputStream fosData=new FileOutputStream(fileName);  
		    fosData.write(result);  
		    fosData.close();  
		}  
		public static void saveKey(Key key,String fileName)throws Exception{  
		    FileOutputStream fosKey=new FileOutputStream(fileName);  
		    ObjectOutputStream oosSecretKey =new ObjectOutputStream(fosKey);  
		    oosSecretKey.writeObject(key);  
		    oosSecretKey.close();  
		    fosKey.close();  
		}  
		private static Key readKey(String fileName) throws Exception {  
		    FileInputStream fisKey=new FileInputStream(fileName);  
		    ObjectInputStream oisKey =new ObjectInputStream(fisKey);  
		    Key key=(Key)oisKey.readObject();  
		    oisKey.close();  
		    fisKey.close();  
		    return key;  
		}  
		private static byte[] readData(String filename) throws Exception {  
		    FileInputStream fisDat=new FileInputStream(filename);  
		    byte [] src=new byte [fisDat.available()];  
		    int len =fisDat.read(src);  
		    int total =0;  
		    while(total<src.length){  
		        total +=len;  
		        len=fisDat.read(src,total,src.length-total);  
		    }  
		    fisDat.close();  
		    return src;  
		}  
![非对称加密后根目录下的文件](http://i.imgur.com/hUf4lyL.png)

## 数字签名
- 数字签名的基础是公钥和私钥的非对称加密
- 发送者使用私钥加密的消息摘要(签名)
- 接收者使用公钥解密消息摘要以验证签名是否是某个人
- 数字签名是个加密的过程，数字签名验证是个解密的过程
- 要证明这段数据是你发过来的，并且没有被别人改过，这就需要用到数字签名，首先我们对整个文档进行md5加密得到16个字节，然后把消息摘要和文档发过去，解密者首先对发过来的文档进行解密，解密后得到一个摘要(md5)，对接收的文档进行md5加密,得到的md5结果匹配解密后的摘要，如果匹配成功的话证明没有修改过，我们使用Signature进行签名
	
		/*  
		 * 使用私钥签名  
		 */    
		private static void sign()throws Exception {    
		    //实例化Key     
		    KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");    
		    //获取一对钥匙     
		    KeyPair keyPair=keyPairGenerator.generateKeyPair();    
		    //获得公钥     
		    PublicKey publicKey=keyPair.getPublic();    
		    //获得私钥      
		    PrivateKey privateKey=keyPair.getPrivate();    
		      
		    //数字签名  
		    Signature signature =Signature.getInstance("SHA1withRSA");  
		    signature.initSign(privateKey);//用私钥签名  
		    signature.update("这里签名".getBytes());//对怎样的数据进行签名  
		    byte [] sign=signature.sign();  //获取签名的结果  
		      
		    //保存公钥并写入文件中   
		    saveKey(publicKey,"liuyong_private.key");    
		    //将签名后的数据写入到文件     
		    saveData(sign,"public_encryt.dat");    
		}  
		    
		/*  
		 * 公钥解密  
		 */    
		private static void verify() throws Exception {    
		    Signature signture =Signature.getInstance("SHA1withRSA");  
		    //获取到公钥  
		    PublicKey publicKey=(PublicKey)readKey("liuyong_private.key");  
		    //初始化校验  
		    signture.initVerify(publicKey);  
		    //初始化签名对象  
		    signture.update("这里签名".getBytes());  
		    //读数据源     
		    byte [] sign =readData("public_encryt.dat");    
		    //返回匹配结果  
		    boolean isYouSigned=signture.verify(sign);  
		    //如果返回数据为true则数据没有发生修改，否则发生修改  
		    System.out.println(isYouSigned);  
		}   
![数字签名后生成的文件](http://i.imgur.com/7uTR6a8.png)



