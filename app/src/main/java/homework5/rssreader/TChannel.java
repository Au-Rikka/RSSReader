package homework5.rssreader;

/**
 * Created by Anstanasia on 26.12.2015.
 */
public class TChannel {
    private String title;
    private String url;

    public TChannel() {
        title = "null";
        url = "null";
    }

    public TChannel(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {this.url = url;}

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
