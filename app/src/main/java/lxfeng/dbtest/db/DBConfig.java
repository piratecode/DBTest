package lxfeng.dbtest.db;


public class DBConfig {

    public static final String DB_NAME = "test.db";
    public static final int DB_VERSION = 6;

    public static final String CREATE_TAB_MEMNER =
            "create table member(_id integer primary key autoincrement,memberId varchar(64),memberName varchar(64),memberAge integer);";
    public static final String DELETE_TAB_MEMBER = "drop table member;";
    public static final String TAB_MEMBER_NAME = "member";
    public static final String MEMBER_ID = "memberId";
    public static final String MEMBER_NAME = "memberName";
    public static final String MEMBER_AGE = "memberAge";

    public static final int _ID = 0;
    public static final int MEMBER_ID_INDEX = 1;
    public static final int MEMBER_NAME_INDEX = 2;
    public static final int MEMBER_AGE_INDEX = 3;

}
