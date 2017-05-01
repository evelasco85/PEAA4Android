package com.codeflowcrafter.Sample.Project;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.codeflowcrafter.DatabaseAccess.AdapterHelper;
import com.codeflowcrafter.DatabaseAccess.MapperTemplate;
import com.codeflowcrafter.Sample.Project.Implementation.DB.ProjectModel;
import com.codeflowcrafter.Sample.Project.Implementation.Domain.Project;
import com.codeflowcrafter.Sample.R;

import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by aiko on 5/1/17.
 */

public class ProjectAdapter extends AdapterHelper<ProjectModel> {

    TextView _idView;
    TextView _nameView;
    Button _btnMenu;
    Button _btnAddAmount;

    public ProjectAdapter(MainActivity activity, int resource, List<ProjectModel> items, MapperTemplate<ProjectModel> projectMapper)
    {
        super(activity, resource, items, projectMapper);
    }

    public View getView(int position, final View convertView, final ViewGroup parent)
    {
        final ProjectModel item = getItem(position);
        final LinearLayout itemLayout = this.GetLayout(convertView, getContext(), this.GetResource());

        this.SetViewToLocalVarAssociation(itemLayout);
        this.SetItemViewHandler(itemLayout, item);

        return itemLayout;
    }

    public void SetViewToLocalVarAssociation(LinearLayout itemLayout)
    {
        _idView = (TextView) itemLayout.findViewById(R.id.projectId);
        _nameView = (TextView) itemLayout.findViewById(R.id.projectName);
        _btnMenu = (Button) itemLayout.findViewById(R.id.btnProjectMenu);
        _btnAddAmount = (Button) itemLayout.findViewById(R.id.btnAddAmount);

    }

    public void SetItemViewHandler(LinearLayout itemLayout, ProjectModel item)
    {
//        _idView.setText(String.valueOf(item.GetId()));
//        _nameView.setText(item.GetName());

        final MainActivity activity = (MainActivity)this.GetActivity();
        final PopupMenu popMenu = new PopupMenu(activity, _btnMenu);
        final ProjectModel projectItem = item;

        popMenu.inflate(R.menu.project_listitem);
        popMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem mnuItem) {
//                switch (mnuItem.getItemId()) {
//                    case (R.id.mnuEdit):
//                        activity.InvokeEditProjectEntry(projectItem.GetId());
//                        return true;
//                    case R.id.mnuAmountList:
//                        activity.InvokeShowAmountList(projectItem.GetId());
//                        return true;
//                    case (R.id.mnuDelete):
//                        activity.InvokeDeleteProjectEntry(projectItem);
//                        return true;
//                    default:
                        return false;
//                }
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
//                activity.InvokeAddAmountEntry(projectItem.GetId());
            }
        });

        itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                activity.InvokeShowProjectEntryDetail();
            }
        });
    }
}
