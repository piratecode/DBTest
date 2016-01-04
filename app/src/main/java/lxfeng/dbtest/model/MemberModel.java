package lxfeng.dbtest.model;

/**
 * 项目名称：My Application
 * 类描述：
 * 创建人：liuxiufeng
 * 创建时间：2016/1/2 10:52
 */
public class MemberModel {
    private String memberId;
    private String name;
    private int age;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
