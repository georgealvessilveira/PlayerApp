package com.georgeasilveira.playerapp.ui.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.georgeasilveira.playerapp.R;
import com.georgeasilveira.playerapp.filter.FileExtensionFinder;
import com.georgeasilveira.playerapp.model.Video;
import com.georgeasilveira.playerapp.ui.adapter.VideoAdapter;

import org.joda.time.LocalTime;

import java.io.File;
import java.util.List;

import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupRecycler();

        List<Video> videos = StreamSupport
                .stream(getFiles())
                .map(file -> new Video(file.getName(), LocalTime.MIDNIGHT, file.getAbsolutePath()))
                .collect(Collectors.toList());

        setVideoList(videos);
    }

    private List<File> getFiles() {
        FileExtensionFinder finderMp4 = new FileExtensionFinder(".mp4");
        FileExtensionFinder finderFlv = new FileExtensionFinder(".flv");
        FileExtensionFinder finderMkv = new FileExtensionFinder(".mkv");
        FileExtensionFinder finder3gp = new FileExtensionFinder(".3gp");

        File directory = Environment.getExternalStorageDirectory();
        List<File> files = finderMp4.findFiles(directory);
        files.addAll(finderFlv.findFiles(directory));
        files.addAll(finderMkv.findFiles(directory));
        files.addAll(finder3gp.findFiles(directory));
        return files;
    }

    private void setupRecycler() {
        recyclerView = findViewById(R.id.main_videos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void setVideoList(List<Video> videos) {
        VideoAdapter adapter = new VideoAdapter(videos);
        recyclerView.setAdapter(adapter);
    }
}
