package cn.janescott.common;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.AbstractFileResolvingResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

/**
 * Created by scott on 2017/6/9.
 * 配置文件加密
 */
public class PropertiesLoad extends PropertyPlaceholderConfigurer {
    private PropertiesProcessor propertiesProcessor = new PropertiesProcessor();

    private Resource[] locations;

    @Override
    public void setLocation(Resource location) {
        this.locations = new Resource[]{location};
    }

    @Override
    public void setLocations(Resource... locations) {
        this.locations = locations;
    }

    @Override
    @LoggerManage(description = "加载配置文件")
    protected void loadProperties(Properties props) throws IOException {
        if(this.locations != null){
            InputStream is;
            Reader reader;
            String filename;
            for(Resource location : locations){
                is = null;
                try {
                    is = location.getInputStream();
                    reader = new InputStreamReader(is);

                    filename = (location instanceof AbstractFileResolvingResource) ? location.getFilename() : null;

                    if((filename != null) && (filename.endsWith(".xml"))){
                        this.propertiesProcessor.loadFromXml(props, is);
                    } else {
                        this.propertiesProcessor.load(props, reader);
                    }
                } catch(IOException ex){
                    System.out.println("");
                } finally {
                    if(is != null){
                       is.close();
                    }
                }
            }
        }
    }
}
