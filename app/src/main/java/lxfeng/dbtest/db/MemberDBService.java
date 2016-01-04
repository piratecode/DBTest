package lxfeng.dbtest.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import lxfeng.dbtest.model.MemberModel;


/**
 * 项目名称：My Application
 * 类描述：
 * 创建人：liuxiufeng
 * 创建时间：2016/1/2 10:50
 */
public class MemberDBService extends BaseDB {

    public MemberDBService(Context contex) {
        super(contex);
    }

    public long insertMemberData(MemberModel memberModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConfig.MEMBER_ID, memberModel.getMemberId());
        contentValues.put(DBConfig.MEMBER_NAME, memberModel.getName());
        contentValues.put(DBConfig.MEMBER_AGE, memberModel.getAge());
        return db.insert(DBConfig.TAB_MEMBER_NAME, null, contentValues);
    }

    public void insertMemberListWithTransaction(List<MemberModel> memberModelList) {
        db.beginTransaction();
        try {
            int size = memberModelList.size();
            for (int i = 0; i < size; i++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DBConfig.MEMBER_ID, memberModelList.get(i).getMemberId());
                contentValues.put(DBConfig.MEMBER_NAME, memberModelList.get(i).getName());
                db.insert(DBConfig.TAB_MEMBER_NAME, null, contentValues);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    public void insertMemberListNormal(List<MemberModel> memberModelList) {
        int size = memberModelList.size();
        for (int i = 0; i < size; i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DBConfig.MEMBER_ID, memberModelList.get(i).getMemberId());
            contentValues.put(DBConfig.MEMBER_NAME, memberModelList.get(i).getName());
            db.insert(DBConfig.TAB_MEMBER_NAME, null, contentValues);
        }
    }

    public List<MemberModel> getMemberListNormal() {
        Cursor cursor = db.query(DBConfig.TAB_MEMBER_NAME, null, null, null, null, null, null);
        ArrayList<MemberModel> memberModelList = new ArrayList<>();
        while (cursor.moveToNext()) {
            MemberModel memberModel = new MemberModel();
            memberModel.setMemberId(cursor.getString(cursor.getColumnIndex(DBConfig.MEMBER_ID)));
            memberModel.setName(cursor.getString(cursor.getColumnIndex(DBConfig.MEMBER_NAME)));
            memberModel.setAge(cursor.getInt(cursor.getColumnIndex(DBConfig.MEMBER_AGE)));
            memberModelList.add(memberModel);
        }
        cursor.close();
        return memberModelList;
    }

    public List<MemberModel> getMemberListOptimize() {
        Cursor cursor = db.query(DBConfig.TAB_MEMBER_NAME, null, null, null, null, null, null);
        ArrayList<MemberModel> memberModelList = new ArrayList<>();
        while (cursor.moveToNext()) {
            MemberModel memberModel = new MemberModel();
            memberModel.setMemberId(cursor.getString(DBConfig.MEMBER_ID_INDEX));
            memberModel.setName(cursor.getString(DBConfig.MEMBER_NAME_INDEX));
            memberModel.setAge(cursor.getInt(DBConfig.MEMBER_AGE_INDEX));
            memberModelList.add(memberModel);
        }
        cursor.close();
        return memberModelList;
    }
}
