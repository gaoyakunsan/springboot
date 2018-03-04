package com.springboot.core.util;

import java.util.UUID;
import org.springframework.util.StringUtils;

public class StringUtil extends StringUtils {

    /**
     * 将多个字符串连接成一个， 避免使用加号造成的性能问题
     */
    public static String concat(Object ...strings) {
        return org.springframework.util.StringUtils.arrayToDelimitedString(strings, "");
    }

    public static String concatByDelimit(String delimit, Object... strings) {
        return org.springframework.util.StringUtils.arrayToDelimitedString(strings, delimit);
    }
    
	public static boolean isEmpty(String value) {
		if (null == value) {
			return true;
		}
		for (int length = value.length(), i = 0; i < length; i++) {
			if (' ' != value.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotEmpty(String value){
        return !isEmpty(value);
    }

	public static String getString(Object o){
        if(o != null){
            return String.valueOf(o);
        }
        else {
            return "";
        }
    }
	
	public static String getRandomGuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
  public static String notNullTrim(String strTemp) {
        if(strTemp == null) {
            return "";
        }else if(strTemp.trim().equalsIgnoreCase("null")){
            return "";
        }
        else {
            return strTemp.trim();
        }
    }
	
	public static boolean isBlank(String str) {
        return (notNullTrim(str).length() == 0);
	}


    /**
     * 连接成完整路径， 保证每段路径只有一个分隔符 "/"
     */
    public static String concatPath(String... paths) {
        String result = String.join("/", paths);
        result = result.replaceAll("///|//", "/");
        return result;
    }


    public static boolean isNumeric(CharSequence cs) {
        if (cs == null || cs.length() == 0) {
            return false;
        }
        int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isDigit(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
