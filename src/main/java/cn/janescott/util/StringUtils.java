package cn.janescott.util;

/**
 * Created by scott on 2017/6/9.
 * String工具类
 */
public class StringUtils extends org.springframework.util.StringUtils{

    public static boolean endsWithContinuationMarker(String line) {
        boolean evenSlashCount = true;
        int index = line.length() - 1;
        while (index >= 0 && line.charAt(index) == '\\') {
            evenSlashCount = !evenSlashCount;
            index--;
        }
        return !evenSlashCount;
    }

    public static String unescape(String str) {
        StringBuffer outBuffer = new StringBuffer(str.length());
        for (int index = 0; index < str.length(); ) {
            char c = str.charAt(index++);
            if (c == '\\') {
                c = str.charAt(index++);
                if (c == 't') {
                    c = '\t';
                } else if (c == 'r') {
                    c = '\r';
                } else if (c == 'n') {
                    c = '\n';
                } else if (c == 'f') {
                    c = '\f';
                }
            }
            outBuffer.append(c);
        }
        return outBuffer.toString();
    }
}
