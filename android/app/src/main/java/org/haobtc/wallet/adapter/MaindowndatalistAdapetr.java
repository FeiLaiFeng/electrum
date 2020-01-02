package org.haobtc.wallet.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.haobtc.wallet.R;
import org.haobtc.wallet.bean.MaintrsactionlistEvent;

import java.util.List;

public class MaindowndatalistAdapetr extends BaseQuickAdapter<MaintrsactionlistEvent, BaseViewHolder> {

    public MaindowndatalistAdapetr(@Nullable List<MaintrsactionlistEvent> data) {
        super(R.layout.fragment_item_trans, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MaintrsactionlistEvent item) {
        boolean is_mine = item.isIs_mine();
        if (is_mine) {
            helper.setText(R.id.tet_name, R.string.send);
        } else {
            helper.setText(R.id.tet_name, R.string.receive);
        }
        helper.setText(R.id.tet_address, item.getTx_hash())
                .setText(R.id.tet_BTC, item.getAmount());

        String date = item.getDate();
        if (date.equals("unknown")|| TextUtils.isEmpty(date)){
            helper.setText(R.id.tet_Time, "");
        }else{
            helper.setText(R.id.tet_Time, item.getDate());
        }

        //judge type
        String type = item.getType();
        if (type.equals("history")) {
            //history
            String confirmations = item.getConfirmations();
            int anInt = Integer.parseInt(confirmations);
            if (anInt > 0) {
                helper.setText(R.id.tet_zt, R.string.alreadychoose);
                TextView tetview = helper.getView(R.id.tet_zt);
                tetview.setTextColor(Color.parseColor("#FF6182F5"));
                tetview.setBackground(mContext.getResources().getDrawable(R.drawable.gray_tuocircle));
            } else {
                helper.setText(R.id.tet_zt, R.string.waitchoose);
            }

        } else {
            String tx_status = item.getTx_status();
            if (tx_status.equals("Signed")) {
                //new creat trsaction
                helper.setText(R.id.tet_zt, R.string.wait_broadcast);
                TextView tetview = helper.getView(R.id.tet_zt);
                tetview.setTextColor(Color.parseColor("#FF838383"));
            } else if (tx_status.equals("Unsigned")) {
                //new creat trsaction
                helper.setText(R.id.tet_zt, R.string.transaction_waitting);
                TextView tetview = helper.getView(R.id.tet_zt);
                tetview.setTextColor(Color.parseColor("#FFF26A3A"));
                tetview.setBackground(mContext.getResources().getDrawable(R.drawable.orange_circle));
            }else if (tx_status.contains("Partially signed")){
                //new creat trsaction
                helper.setText(R.id.tet_zt, R.string.partsigned);
                TextView tetview = helper.getView(R.id.tet_zt);
                tetview.setTextColor(Color.parseColor("#FFF26A3A"));
                tetview.setBackground(mContext.getResources().getDrawable(R.drawable.orange_circle));
            }

        }

    }
}
