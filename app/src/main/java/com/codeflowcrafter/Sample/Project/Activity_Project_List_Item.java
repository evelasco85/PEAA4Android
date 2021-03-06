package com.codeflowcrafter.Sample.Project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.codeflowcrafter.Sample.Project.Implementation.Domain.Project;
import com.codeflowcrafter.Sample.Project.Implementation.MVP.IRequests_Project;
import com.codeflowcrafter.Sample.R;

import java.util.List;

/**
 * Created by aiko on 5/1/17.
 */

public class Activity_Project_List_Item extends ArrayAdapter<Project> {

    private TextView _idView, _nameView, _txtTotal;
    private Button _btnMenu;
    private Button _btnAddAmount;

    private int _resource;
    private Activity_Main _activity;

    public Activity_Project_List_Item(Activity_Main activity, List<Project> items)
    {
        super(activity, R.layout.activity_project_listitem, items);

        _resource = R.layout.activity_project_listitem;
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

    public View getView(int position, final View convertView, final ViewGroup parent)
    {
        final Project item = getItem(position);
        final LinearLayout itemLayout = this.GetLayout(convertView, getContext(), _resource);

        SetViewToLocalVarAssociation(itemLayout);
        SetItemViewHandler(itemLayout, item);

        return itemLayout;
    }

    public void SetViewToLocalVarAssociation(LinearLayout itemLayout)
    {
        _idView = (TextView) itemLayout.findViewById(R.id.projectId);
        _nameView = (TextView) itemLayout.findViewById(R.id.projectName);
        _txtTotal = (TextView) itemLayout.findViewById(R.id.txtTotal);
        _btnMenu = (Button) itemLayout.findViewById(R.id.btnProjectMenu);
        _btnAddAmount = (Button) itemLayout.findViewById(R.id.btnAddAmount);
    }

    public void SetItemViewHandler(LinearLayout itemLayout, Project item)
    {
        _idView.setText(String.valueOf(item.GetId()));
        _nameView.setText(item.GetName());
        _txtTotal.setText(String.valueOf(item.GetTotal()));

        final Activity_Main activity = _activity;
        final PopupMenu popMenu = new PopupMenu(activity, _btnMenu);
        final Project projectItem = item;

        final IRequests_Project viewrequest = activity.GetViewRequest();

        popMenu.inflate(R.menu.project_listitem);
        popMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem mnuItem) {
                switch (mnuItem.getItemId()) {
                    case (R.id.mnuEdit):
                        viewrequest.Prompt_EditProjectEntry(projectItem);
                        return true;
                    case R.id.mnuAmountList:
                        viewrequest.Prompt_AmountList(projectItem);
                        return true;
                    case (R.id.mnuDelete):
                        viewrequest.Prompt_DeleteProjectEntry(projectItem);
                        return true;
                    default:
                        return false;
                }
            }
        });

        _btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popMenu.show();
            }
        });
        _btnAddAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewrequest.Prompt_AddAmountEntry(projectItem);
            }
        });

        itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewrequest.Prompt_ProjectDetail(projectItem);
            }
        });
    }
}
