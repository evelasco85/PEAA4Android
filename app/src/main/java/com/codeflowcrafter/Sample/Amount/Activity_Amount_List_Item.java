package com.codeflowcrafter.Sample.Amount;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.view.MenuItem;
import android.widget.TextView;

import com.codeflowcrafter.Sample.Amount.Implementation.Domain.Amount;
import com.codeflowcrafter.Sample.Amount.Implementation.MVP.IRequests_Amount;
import com.codeflowcrafter.Sample.R;

import java.util.List;

/**
 * Created by aiko on 5/12/17.
 */

public class Activity_Amount_List_Item extends ArrayAdapter<Amount> {

    Button _btnAmountMenu;
    TextView _txtAmount;

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
        final Amount item = getItem(position);
        final LinearLayout itemLayout = this.GetLayout(convertView, getContext(), _resource);

        SetViewToLocalVarAssociation(itemLayout);
        SetItemViewHandler(itemLayout, item);

        return itemLayout;
    }

    public void SetViewToLocalVarAssociation(LinearLayout itemLayout)
    {
        _txtAmount = (TextView) itemLayout.findViewById(R.id.txtAmount);
        _btnAmountMenu = (Button) itemLayout.findViewById(R.id.btnAmountMenu);
    }

    public void SetItemViewHandler(LinearLayout itemLayout, Amount item) {
        String amount = (item.GetIsExpense() ? "-" : "") + String.valueOf(item.GetAmount());

        _txtAmount.setText(amount);

        final Activity_Main activity = _activity;
        final PopupMenu popMenu = new PopupMenu(activity, _btnAmountMenu);
        final Amount amountItem = item;

        final IRequests_Amount viewrequest = activity.GetViewRequest();

        popMenu.inflate(R.menu.amount_listitem);
        popMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem mnuItem) {
                switch (mnuItem.getItemId()) {
                    case (R.id.mnuEdit):
                        viewrequest.Prompt_EditAmountEntry(amountItem);
                        return true;
                    case (R.id.mnuDelete):
                        viewrequest.Prompt_DeleteAmountEntry(amountItem);
                        return true;
                    default:
                        return false;
                }
            }
        });

        _btnAmountMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popMenu.show();
            }
        });


        itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewrequest.Prompt_AmountDetail(amountItem);
            }
        });
    }
}
