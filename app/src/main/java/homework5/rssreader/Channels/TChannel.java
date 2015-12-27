package homework5.rssreader.Channels;

/**
 * Created by Anstanasia on 26.12.2015.
 */
public class TChannel {
    private String title;
    private String url;
    private long id;

    public TChannel() {
        title = "null";
        url = "null";
        id = -1;
    }

    public TChannel(String title, String url, long id) {
        this.title = title;
        this.url = url;
        this.id = id;
    }

    public String getTitle() {return title;}

    public String getUrl() {return url;}

    public long getId() {
        return id;
    }
}
