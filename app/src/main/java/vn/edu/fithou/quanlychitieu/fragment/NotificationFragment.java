package vn.edu.fithou.quanlychitieu.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import vn.edu.fithou.quanlychitieu.R;
import vn.edu.fithou.quanlychitieu.activity.NotificationDetail;
import vn.edu.fithou.quanlychitieu.activity.SelectTransactionGroup;
import vn.edu.fithou.quanlychitieu.adapter.NotificationAdapter;
import vn.edu.fithou.quanlychitieu.adapter.SelectTransactionGroupListViewAdapter;
import vn.edu.fithou.quanlychitieu.model.Notification;
import vn.edu.fithou.quanlychitieu.model.TransactionGroup;
import vn.edu.fithou.quanlychitieu.util.SQLiteUtil;

public class NotificationFragment extends Fragment implements NotificationAdapter.OnItemClickedListener {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView rvNotification;
    List<Notification> data;
    SQLiteUtil sqLiteUtil;
    NotificationAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notification, container, false);
        swipeRefreshLayout = rootView.findViewById(R.id.srlNotification);
        rvNotification = rootView.findViewById(R.id.rvNotification);

        sqLiteUtil = new SQLiteUtil(getActivity());
        data = sqLiteUtil.getAllNotification();
        adapter = new NotificationAdapter(getActivity(), data);
        rvNotification.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvNotification.setAdapter(adapter);

        adapter.setOnItemClickedListener(this);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchAndFillData();
            }
        });
        return rootView;
    }

    private void fetchAndFillData() {
        data.clear();
        data.addAll(sqLiteUtil.getAllNotification());

        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onItemClick(int notificationId) {
        Intent i = new Intent(getActivity(), NotificationDetail.class);
        i.putExtra("id", notificationId);
        startActivity(i);
    }
}
