package cn.janescott.common;

import cn.janescott.util.CodeUtils;
import cn.janescott.util.StringUtils;
import org.springframework.util.DefaultPropertiesPersister;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

/**
 * Created by scott on 2017/6/9.
 * 解析配置文件
 */
@Deprecated
public class PropertiesProcessor extends DefaultPropertiesPersister {

    @Override
    public void load(Properties props, Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;
        String nextLine;
        char firstChar;

        while (true) {
            line = in.readLine();
            if (line == null) {
                return;
            }
            line = StringUtils.trimLeadingWhitespace(line);
            if (line.length() > 0) {
                firstChar = line.charAt(0);
                if (firstChar != '#' && firstChar != '!') {
                    while (StringUtils.endsWithContinuationMarker(line)) {
                        nextLine = in.readLine();
                        line = line.substring(0, line.length() - 1);
                        if (nextLine != null) {
                            line = line + StringUtils.trimLeadingWhitespace(nextLine);
                        }
                    }
                    int separatorIndex = line.indexOf("=");
                    if (separatorIndex == -1) {
                        separatorIndex = line.indexOf(":");
                    }
                    String key = separatorIndex != -1 ? line.substring(0, separatorIndex) : line;
                    String value = separatorIndex != -1 ? line.substring(separatorIndex + 1) : "";
                    key = StringUtils.trimTrailingWhitespace(key);
                    value = StringUtils.trimTrailingWhitespace(value);

                    if (Constants.ENCODED_CONFIGURATION.contains(key)) {
                        value = CodeUtils.standardPBEStringEncryptor().decrypt(value);
                    }

                    props.put(StringUtils.unescape(key), StringUtils.unescape(value));
                }
            }
        }
    }
}
