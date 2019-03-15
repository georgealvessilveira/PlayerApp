package com.georgeasilveira.playerapp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.solver.widgets.ConstraintTableLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.georgeasilveira.playerapp.R;
import com.georgeasilveira.playerapp.model.Models;
import com.georgeasilveira.playerapp.model.Video;
import com.georgeasilveira.playerapp.ui.activity.FullscreenVideoActivity;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private final List<Video> videos;

    public VideoAdapter(List<Video> videos) {
        this.videos = videos;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_video, parent, false);

        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        Video video = videos.get(position);
        holder.name.setText(video.getName());
        holder.time.setText(video.getTime().toString());
        holder.selection.setOnClickListener(v -> {
            Context context = holder.itemView.getContext();
            Intent intent = new Intent(context, FullscreenVideoActivity.class);
            intent.putExtra(Models.VIDEO, video);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder {
        private final View itemView;
        private final TextView name;
        private final TextView time;
        private final ConstraintLayout selection;

        VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.name = itemView.findViewById(R.id.item_video_name);
            this.time = itemView.findViewById(R.id.item_video_time);
            this.selection = itemView.findViewById(R.id.item_video_constraint);
        }
    }
}
