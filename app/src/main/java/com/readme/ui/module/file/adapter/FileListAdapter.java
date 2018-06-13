package com.readme.ui.module.file.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.utils.FileUtil;
import com.readme.R;
import com.readme.config.Extra;
import com.readme.ui.module.ReadActivity;
import com.readme.ui.module.file.bean.FileInfo;
import com.readme.ui.module.file.FileListActivity;


import java.util.List;

public class FileListAdapter extends BaseAdapter {
    private List<FileInfo> apps;
    private Context context;

    public FileListAdapter(Context context, List<FileInfo> app){
        this.context = context;
        this.apps = app;
    }

    @Override

    public int getCount() {
        return apps.size();
    }

    @Override
    public FileInfo getItem(int position) {
        return apps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_file_list, null);
            holder.tvLayout = (LinearLayout) convertView.findViewById(R.id.hm_root);

            holder.tvType = (TextView) convertView.findViewById(R.id.hm_type);
            holder.tvName = (TextView) convertView.findViewById(R.id.hm_name);

            holder.tvPath = (TextView) convertView.findViewById(R.id.hm_path);
            holder.tvExtra = (TextView) convertView.findViewById(R.id.hm_extra);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final FileInfo appInfo = getItem(position);
        String type = appInfo.isDir ? "Dir": "File";
        Resources res = context.getResources();
        if(appInfo.isDir){
            holder.tvLayout.setBackgroundColor(res.getColor(R.color.colorPrimary));
            holder.tvLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, FileListActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(Extra.FILE_PATH, appInfo.appPath);
                    intent.putExtras(bundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }else{
            holder.tvLayout.setBackgroundColor(res.getColor(R.color.color_light_white));
            holder.tvLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse(appInfo.appPath);

                    Intent intent = new Intent(context, ReadActivity.class);
                    intent.setDataAndType(uri, "text/plain");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }

        if(appInfo.isHidden){
            holder.tvLayout.setBackgroundColor(res.getColor(R.color.color_hidden_six));
        }
        holder.tvType.setText(type);
        holder.tvName.setText(appInfo.appName);

        holder.tvPath.setText(appInfo.appPath);
        holder.tvExtra.setText(FileUtil.formetFileSize(appInfo.size));
        return convertView;
    }

    class ViewHolder {
        LinearLayout tvLayout;

        TextView tvType;
        TextView tvName;

        TextView tvPath;
        TextView tvExtra;
    }
}
