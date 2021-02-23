package com.mypro.system.common;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.UUID;

public class MD5Utils {
/**
 *@ClassName MD5Utils
 *@Description TODO
 *@Auther Silin
 *@Date 22.02.21 15:55
 **/

    /**
     * create UUID
     * @return
     */
    public static String createUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }


    /**
     * jiama hashing
      * @param source
     * @param slat
     * @param hashIterations
     * @return
     */

    public static String md5(String source, String salt, Integer hashIterations){
        Md5Hash md5Hash = new Md5Hash(source, salt, hashIterations);
        return  md5Hash.toString();
    }

}

