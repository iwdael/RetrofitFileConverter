# RetrofitFileConverter
![](https://img.shields.io/badge/platform-android-orange.svg)
![](https://img.shields.io/badge/language-java-yellow.svg)
![](https://jitpack.io/v/com.iwdael/retrofitfileconverter.svg)
![](https://img.shields.io/badge/build-passing-brightgreen.svg)
![](https://img.shields.io/badge/license-apache--2.0-green.svg)
![](https://img.shields.io/badge/api-19+-green.svg)

Retrofit转换器，将Response自动转为File，简单快速。

## 示例
添加转换器
```
    Retrofit.Builder().client(builder.build()) 
            .addConverterFactory(FileConverterFactory.create(context.cacheDir)) 
            .baseUrl(BASE_URL)
            .build()
```
未使用`@FileName`将随机生成文件名
```
interface Api {
    @FileName("file_name")
    @GET("/download_url")
    fun downFile(): Observable<File>
}
```

## 如何配置
将本仓库引入你的项目:
### Step 1. 添加JitPack仓库到Build文件
合并以下代码到项目根目录下的build.gradle文件的repositories尾。

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

### Step 2. 添加依赖
合并以下代码到需要使用的application Module的dependencies尾。
```Java
	dependencies {
	  ...
          compile 'com.iwdael:retrofitfileconverer:$version'
	}
```
