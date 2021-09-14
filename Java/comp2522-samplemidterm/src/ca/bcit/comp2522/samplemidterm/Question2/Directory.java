package ca.bcit.comp2522.samplemidterm.Question2;

import java.util.ArrayList;

public class Directory {
    static class FileNode {
        private final String fileName;
        private final Boolean isDirectory;

        public FileNode(String fileName, Boolean isDirectory) {
            this.fileName = fileName;
            this.isDirectory = isDirectory;
        }

        public String getFileName() {
            return fileName;
        }

        public Boolean getDirectory() {
            return isDirectory;
        }
    }

    private final ArrayList<FileNode> fileList = new ArrayList<>();

    public void addFile(FileNode file) {
        fileList.add(file);
    }

    public void printFiles() {
        for (FileNode node : fileList) {
            System.out.printf("%s : %s\n", node.getFileName(),
                    node.getDirectory() ? "Directory" : "File");
        }
    }
}
