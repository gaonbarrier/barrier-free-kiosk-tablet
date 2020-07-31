package io.github.gaonbarrier.easykiosk.tablet.image;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

public class image {
    public static String encode(String image) throws UnsupportedEncodingException{
        byte[] data = image.getBytes("UTF-8");
        return Base64.encodeToString(data,Base64.DEFAULT);
    }
    public static String decode(String image) throws UnsupportedEncodingException{
        return new String(Base64.decode(image,Base64.DEFAULT),"UTF-8");
    }
    //Base64 관련 메서드들 -> DB가 있기에 굳이 이미지들을 저장해둘 필요가 없단 판단으로 다른 패키지로 옮김
}
