package fordream.binary.builder.database.struts;

/**
 * Created by forDream on 2016-08-09.
 */
public class Record {
    private byte[] key;
    private String fileUrl;

    public byte[] getKey() {
        return key;
    }

    public void setKey(byte[] key) {
        this.key = key;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
