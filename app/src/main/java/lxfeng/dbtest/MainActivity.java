package lxfeng.dbtest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lxfeng.dbtest.db.MemberDBService;
import lxfeng.dbtest.model.MemberModel;


public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ListView mMemberListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        MemberModel memberModel = new MemberModel();
//        memberModel.setMemberId("1");
//        memberModel.setName("lxfeng");
//        memberModel.setAge(1);

        MemberDBService memberDBService = new MemberDBService(this);
        ArrayList<MemberModel> memberModelList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            MemberModel memberModel1 = new MemberModel();
            memberModel1.setName("lxfeng" + i);
            memberModel1.setMemberId(i + "");
            memberModel1.setAge(24);
            memberModelList.add(memberModel1);
        }

        long startTimeTransaction = System.currentTimeMillis();
        memberDBService.insertMemberListWithTransaction(memberModelList);
        long endTimeTransaction = System.currentTimeMillis();
        Log.e(TAG, "transaction insert time:" + (endTimeTransaction - startTimeTransaction));

        long startTimeNomal = System.currentTimeMillis();
        memberDBService.insertMemberListNormal(memberModelList);
        long endTimeNormal = System.currentTimeMillis();
        Log.e(TAG, "normal insert time:" + (endTimeNormal - startTimeNomal));


        long startGetNormal = System.currentTimeMillis();
        final List<MemberModel> normalList = memberDBService.getMemberListNormal();
        long endGetNormal = System.currentTimeMillis();
        Log.e(TAG, "normal get time:" + (endGetNormal - startGetNormal));

        long startGetOptimize = System.currentTimeMillis();
        List<MemberModel> optimizeList = memberDBService.getMemberListOptimize();
        long endGetOptimize = System.currentTimeMillis();
        Log.e(TAG, "optimize get time:" + (endGetOptimize - startGetOptimize));

        Log.e(TAG, "normallist size:" + normalList.size());
        Log.e(TAG, "optimizelist size:" + optimizeList.size());

        memberDBService.closeDB();


        mMemberListView = (ListView) findViewById(R.id.list_member);
        BaseAdapter memberAdapter = new BaseAdapter() {
            @Override public int getCount() {
                return normalList.size();
            }

            @Override public Object getItem(int position) {
                return normalList.get(position);
            }

            @Override public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (null == convertView) {
                    convertView =
                            getLayoutInflater().inflate(R.layout.item_list_member, parent, false);
                }
                ((TextView) convertView).setText(normalList.get(position).getName());
                return convertView;
            }
        };
        mMemberListView.setAdapter(memberAdapter);

    }


}
