package com.codeflowcrafter.Sample.Amount;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.codeflowcrafter.Sample.Amount.Implementation.Domain.Amount;
import com.codeflowcrafter.Sample.R;

import java.util.List;

/**
 * Created by aiko on 5/12/17.
 */

public class Activity_Amount_List_Item extends ArrayAdapter<Amount> {

    private int _resource;
    private Activity_Main _activity;

    public Activity_Amount_List_Item(Activity_Main activity, List<Amount> items)
    {
        super(activity, R.layout.activity_amount_listitem, items);

        _resource = R.layout.activity_amount_listitem;
        _activity = activity;
    }

    private LinearLayout GetLayout(View view, Context context, int resource)
    {
        LinearLayout layout;

        if(view == null)
        {
            LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            layout = new LinearLayout(context);

            li.inflate(resource, layout, true);
        }
        else
            layout = (LinearLayout) view;

        return layout;
    }

        @Override
    public View getView(int position, final View convertView, final ViewGroup parent)
    {
//        final AmountModel item = getItem(position);
        final LinearLayout itemLayout = this.GetLayout(convertView, getContext(), _resource);

//        this.SetViewToLocalVarAssociation(itemLayout);
//        SetItemViewHandler(itemLayout, item);

        return itemLayout;
    }
}
