package ppgee.ufes.com.somatosoft.integration.payload;

public class VersionPayload {
    private Integer version;

    public VersionPayload() {

    }

    public VersionPayload(Integer version, String link) {
        this.version = version;
        this.link = link;
    }

    private String link;

    public Integer getVersion() {
        return version;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}