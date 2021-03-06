package apple.example.com.newsreaderapp.Model;

public class Icon {

    private String Url;
    private int width, height, bytes;
    private String format, shalsum;
    private Object error;

    public Icon(String url, int width, int height, int bytes, String format, String shalsum, Object error) {
        Url = url;
        this.width = width;
        this.height = height;
        this.bytes = bytes;
        this.format = format;
        this.shalsum = shalsum;
        this.error = error;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBytes() {
        return bytes;
    }

    public void setBytes(int bytes) {
        this.bytes = bytes;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getShalsum() {
        return shalsum;
    }

    public void setShalsum(String shalsum) {
        this.shalsum = shalsum;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }
}
