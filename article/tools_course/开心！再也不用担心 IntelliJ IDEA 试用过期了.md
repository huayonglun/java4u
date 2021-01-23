## 背景



前段时间 Review 团队小伙伴代码，发现当他把鼠标挪到一个方法上时，就自动显示了该方法的所有注释信息，像下图这样，他和我用的 IDE 都是 IntelliJ IDEA。



![](https://cdn.jsdelivr.net/gh/huayonglun/cdn_image001/img/1610768801684-a6b1bd3b-0fc8-4883-8ca0-d51888b16034-20210116201303370-20210116201418997.png)



而我还按古老的方式，每次要点进方法内部去看相关的方法声明，瞬间感觉落伍。问了下小伙伴怎么做到的，他说 IDE 升级到 2020 版本就好了。



我一看自己的版本，2017 年的版本...看来落伍好多年，于是手抖加上心痒，立马更新到了 2020 版本的旗舰版。更新完我就有点后悔了，因为之前版本的激活方式已经不适用新版本了。网上找了几种激活方式，试了下，但并没有奏效，无奈先试用版用着吧...



时间过得好快，今天想起这个事，打开 IDE 看了下 Expiration date，看来试用版下周就要过期了啊。想起下周我搞 IDE 手忙脚乱的样子，我就决定今天把试用过期的问题搞定！



![](https://cdn.jsdelivr.net/gh/huayonglun/cdn_image001/img/1610766263118-e9f5ea38-fc9a-4e24-adf8-9285505c5c6c-20210116201338721.png)

## 方案

网上找了一圈，热心网友们给的方案都是 **jetbrains-agent 插件** 的方式，它是通过激活码或 License Server 达到长期使用的目的。但我实操了下，没有效果，把 IDE 卸载干净又实操了下，还是没有效果。

![](https://cdn.jsdelivr.net/gh/huayonglun/cdn_image001/img/1610775250912-00d141e2-b64f-4662-acdb-79e0a681eef7-20210116201457066.gif)

正当我一筹莫展时，我发现 **jetbrains-agent 插件** 的作者 [知了](https://zhile.io/) 最近发布了一篇文章，文章提到 **jetbrains-agent** 项目已经停止，他有了新的思路来延长产品使用时间，那就是重置试用时间。



因为 jetbrains 产品试用一个月的信息是写在代码里的，因此也让这个思路有了可行性。知了按照这个思路，很快做出了新的插件，它的名字叫 **IDE Eval Reset**。大神就是厉害，想到很快就能做到，执行力要给他点个赞👍。



![](https://cdn.jsdelivr.net/gh/huayonglun/cdn_image001/img/1610775351792-5a676e81-9ed3-4dff-93a5-33a3d77b092f-20210116201519819.png)

接下来讲讲这个新款插件如何使用。



## 安装



安装非常简单，将以下 zip 文件 (后台私信我 ide_eval_retter 可获取文件下载链接) 拖到 IDE 里即可安装，安装成功会有提示。



[📎ide-eval-resetter-2.1.6.zip](https://www.yuque.com/attachments/yuque/0/2021/zip/292263/1610774559743-91758786-d735-4bff-88d6-d47360c75e2d.zip)

![](https://cdn.jsdelivr.net/gh/huayonglun/cdn_image001/img/1610764563446-8791b249-0806-48a5-a887-98075ac00d27-20210116201555516.png)



## 设置



点击菜单栏 **Help** --> **Eval Reset**，会看到两个按钮一个选项。



![](https://cdn.jsdelivr.net/gh/huayonglun/cdn_image001/img/1610764701313-3793186f-bb29-484b-96f7-acf6b9ac6316-20210116201637631.png)



![](https://cdn.jsdelivr.net/gh/huayonglun/cdn_image001/img/1610766334636-8c1d2d48-c8ab-4733-bac6-2db2fc295ed6-20210116201711255.png)

- 按钮 **Reload** 是用来刷新界面上显示的试用信息。**UNTIL** 对于的值就是试用期结束的时间。
- 按钮 **Reset** 点击后会询问**是否重置**试用信息并**重启IDE**。选择 **Yes** 则执行重置操作并**重启IDE生效**，选择 No则什么也不做。这是**手动重置的方式。**
- 选项 **Auto reset before per restart** 如果勾选了，那么勾选后**每次重启/退出 IDE 时会自动重置试用信息**，你无需做额外的事情。这种操作是静默无感知的，是**自动重置**的方式。





我设置了自动重置，这样未来无需再管试用过期的问题，一劳永逸。



![](https://cdn.jsdelivr.net/gh/huayonglun/cdn_image001/img/1610764916397-c949b567-821e-4da2-aa71-adbe8e5ba8a7-20210116201742898.png)

重启 IDE 重新加载了下试用信息，**UNTIL** 信息告诉我可以继续试用到下一个月了~



![](https://cdn.jsdelivr.net/gh/huayonglun/cdn_image001/img/1610766468860-eda2c546-b1cf-4b81-ac4f-49ffa1c4a33c-20210116201829384.png)

## 其他



- 如之前有配置过 **javaagent**，为避免影响某些付费插件，需要**移除相关信息**。操作路径：**Help** -> **Edit Custom VM Options...** -> **移除 -javaagent: 开头的行**。
- 重置需要**重启IDE生效**！
- 如果长达 **25** 天不曾有任何重置动作，**IDE** 会有通知询问你是否进行重置。





>  IDE Eval Reset 项目只做个人学习研究之用，不得用于商业用途！
>
> 若资金允许，请点击[链接](https://www.jetbrains.com/idea/buy/)购买正版，谢谢合作！
>
> 学生凭学生证可[免费申请](https://sales.jetbrains.com/hc/zh-cn/articles/207154369-学生授权申请方式)正版授权！
>
> 创业公司可[5折购买](https://www.jetbrains.com/shop/eform/startup)正版授权！



这款 IDE 插件简单又好用吧，如果对你也有用的话，可以给我点个赞，点个在看，也可以分享给身边朋友，非常感谢读者朋友。



我是蜗牛，正在互联网上疯狂爬行，下期见。



---

![](https://gitee.com/java4u/resources/raw/master/global/gh_woniu.png)

