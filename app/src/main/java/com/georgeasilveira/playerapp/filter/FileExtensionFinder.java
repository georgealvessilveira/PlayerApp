package com.georgeasilveira.playerapp.filter;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * A {@link FileFilter} implementation that checks recursively files of a
 * specified fileName or extension string
 * https://stackoverflow.com/questions/10589298/file-search-recursively
 */
public class FileExtensionFinder implements FileFilter {
    private final String fileName;
    private final List<File> foundFiles;

    /**
     * Constructor for FileExtensionFinder
     * @param fileName string of the filename or extension being searched for
     */
    public FileExtensionFinder(String fileName) {
        this.fileName = fileName;
        this.foundFiles = new ArrayList<>();
    }

    @Override
    public boolean accept(File pathname) {
        // accept anything that is a folder or matches the fileName string
        return pathname.isDirectory() || pathname.getName().toLowerCase().endsWith(fileName);
    }

    /**
     * Searches recursively for all files with the provided filename/extension string
     * @param filesArray an array of files (including folders) to iterate over
     */
    public List<File> findFiles(File... filesArray) {
        if (filesArray == null) return new ArrayList<>();
        for (File file : filesArray) {
            if (file.isDirectory()) {
                findFiles(file.listFiles(this));
            } else if (file.getName().toLowerCase().endsWith(fileName)) {
                foundFiles.add(file);
            }
        }
        return foundFiles;
    }
}