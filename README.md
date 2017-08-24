# android-utils
Utils codes for faster app development

**Note:** The library is in development and might be unstable.

[ ![Download](https://api.bintray.com/packages/sagar2093/androidlibrary/android-utils/images/download.svg) ](https://bintray.com/sagar2093/androidlibrary/android-utils/_latestVersion)

# How to use

**Gradle**

1. It uses **jcenter()**, you might not need this step since android uses jcentre() as default repo

   Add it in your root build.gradle at the end of repositories:
```gradle
	allprojects {
		repositories {
			...
			jcentre()
		}
	}
```

2. Inside app level **build.gradle** include the following dependency:
```gradle
	dependencies {
	        compile 'com.github.sagar2093:android-utils:0.0.1'
	}
```

You are all set.

## Included Util Classes
The library contains following classes. Click on class to see the code 
1. [Util.java](https://github.com/sagar2093/android-utils/blob/master/android-utils/src/main/java/com/github/sagar2093/androidutils/Util.java)
2. [ViewPagerAdapter.java](https://github.com/sagar2093/android-utils/blob/master/android-utils/src/main/java/com/github/sagar2093/androidutils/adapter/ViewPagerAdapter.java)
3. [MultipartUtility.java](https://github.com/sagar2093/android-utils/blob/master/android-utils/src/main/java/com/github/sagar2093/androidutils/network/MultipartUtility.java)
