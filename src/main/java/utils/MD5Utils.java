package utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utils {
    public final static String md5key = "md5";
    private static boolean md5Verify;

    /**
     * 密碼加密
     * @param passwd
     * @param md5key
     * @return
     */
    public static String md5(String passwd,String md5key){

        String md5passwd = DigestUtils.md5Hex(passwd+md5key);

        return md5passwd;
    }

    /**
     * 驗證加密
     * @param passwd
     * @param md5key
     * @param md5passwd
     * @return
     */
    public static boolean md5_verify(String passwd,String md5key,String md5passwd){
        String md5Text = md5(passwd,md5key);

        if (md5Text.equalsIgnoreCase(md5key+md5passwd)){
            md5Verify = true;
            return md5Verify;
        }
        return false;
    }
}
