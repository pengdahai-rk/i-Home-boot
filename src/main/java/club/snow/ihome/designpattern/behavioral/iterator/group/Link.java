package club.snow.ihome.designpattern.behavioral.iterator.group;

/**
 * The type Link.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @since 2024/6/14
 */
public class Link {

    private String fromId;
    private String toId;

    public Link(String fromId, String toId) {
        this.fromId = fromId;
        this.toId = toId;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }
}
