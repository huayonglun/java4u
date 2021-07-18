## 一、下载 PicGo



PicGo 是啥？顾名思义，它是一个快速上传图片并获取 图片 URL 链接的工具。



目前支持七牛、腾讯云、阿里云和 GitHub 等图床。该工具代码已在 GitHub 开源，读者可以自行去下载。



考虑到网络问题，mac 用户后台回复 picgo_dmg 获取高速下载链接。



[📎PicGo-2.2.2.dmg](https://www.yuque.com/attachments/yuque/0/2021/dmg/292263/1610785259608-1aebc9d6-bf82-468e-ad31-064d165d3ade.dmg)





下载完成后，应用列表会有这么一个图标，点击就启动了。



![](https://cdn.jsdelivr.net/gh/huayonglun/cdn_image001/img/1610785381453-176f47e3-f259-4e30-ae59-d98072baaaef-20210116211040479.png)



PicGo 默认只出现在顶部菜单栏，点击软件图标会显示已上传的图片列表，点击图片会复制链接。



![](https://cdn.jsdelivr.net/gh/huayonglun/cdn_image001/img/1610785560440-bee9dd34-45d0-4081-83ce-7150f0b2d2be-20210116211111752.png)

右击图标，打开详细窗口，我们可以对图床做下配置。



![](https://cdn.jsdelivr.net/gh/huayonglun/cdn_image001/img/1610786569046-1e987e9a-98f2-4d8f-b4e7-dd1b3bc6d85b-20210116211140992.png)

## 二、图床配置



点击图床设置-GitHub 图床，可以看到需要配置以下信息。



![](https://cdn.jsdelivr.net/gh/huayonglun/cdn_image001/img/1610786849004-9b255dad-7ad2-4535-bb7a-52a2106632a8-20210116211226093.png)

必选项：

- 仓库名：用户名/图床仓库名
- 分支名：主干分支即可，**GitHub 主干分支近期从 master 改为了 main**，所以这里要添 main，否则上传图片时会异常。
- Token：PicGo 访问你 GitHub 仓库的令牌，需要你在 GitHub 个人设置里生成，下文会提到。
- 存储路径：仓库下的具体路径。如果名称不存在，PicGo 会在图片上传时自动创建文件夹。
- 自定义域名：默认访问 GItHub 资源，但由于 GitHub 访问特别慢，影响图片加载速度。因此接入免费开源的 CDN 加速服务 **jsdelivr。**

- - 配置格式：https://cdn.jsdelivr.net/gh/用户名/仓库名
  - 举例：https://cdn.jsdelivr.net/gh/huayonglun/cdn_image001



## 三、GitHub 接入



### 3.1 创建仓库



![](https://cdn.jsdelivr.net/gh/huayonglun/cdn_image001/img/1610797667093-64c89cf3-bbd1-411b-987d-e4ae6d13ffd6-20210116211308597.png)

### 3.2 获取 Token



获取路径：S**ettings** --> **Developer settings** --> **Personal access tokens** --> **New personal access token** --> **copy token**

![](https://cdn.jsdelivr.net/gh/huayonglun/cdn_image001/img/1610797352744-d515fbd7-3a71-455d-ada3-91490fdf9555-20210116211341369.png)



![](https://cdn.jsdelivr.net/gh/huayonglun/cdn_image001/img/1610797621225-6e3202a3-1c0d-4ba6-b0c4-bf4026b95f98-20210116211421765.png)

将生成的 token 填写到第二节里的配置里，图床即可投入使用。



## 四、图床使用



将图片拖入详细窗口或者顶部缩略图标里，即可完成图片上传，链接会复制到剪贴板，使用的时候直接粘贴就好。



![](https://cdn.jsdelivr.net/gh/huayonglun/cdn_image001/img/1610797896196-31cf65db-d4f1-447a-a5f6-9bd1fea19c5e-20210116211450510.png)

这种组装的图床工具简单又好用吧，如果对你也有用的话，可以给我**点个赞**，**点个在看**，也可以分享给身边朋友，非常感谢读者朋友。



我是蜗牛，正在互联网上疯狂爬行，下期见。



---



[我是蜗牛，Java 后端开发，正在互联网上疯狂爬行，欢迎一起来爬。我们下期再见！微信搜“蜗牛互联网”回复“1024”领取我整理的 Java 程序员必备的学习资料。](https://www.yuque.com/woniu666/tech_doc/about_woniu)