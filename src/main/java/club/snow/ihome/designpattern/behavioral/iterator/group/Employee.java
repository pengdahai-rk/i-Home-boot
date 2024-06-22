package club.snow.ihome.designpattern.behavioral.iterator.group;

/**
 * The type Employee.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.6.14
 */
public class Employee {

    private String userId;
    private String name;
    private String desc;

    public Employee(String userId, String name, String desc) {
        this.userId = userId;
        this.name = name;
        this.desc = desc;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
