package cn.janescott.domain.dto;

/**
 * Created by scott on 2017/7/11.
 */
public class MenuDTO implements java.io.Serializable{
    private String name;

    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
