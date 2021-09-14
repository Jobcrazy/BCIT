package ca.bcit.comp2522.samplemidterm.Question2;

public class Question2 {
    public void test() {
        Ellipse ellipse = new Ellipse(5, 5, 10);
        ellipse.printEllipse();

        URI uri = new URI("https", "google.com", "download_chrome");
        uri.printURI();

        Directory directory= new Directory();
        Directory.FileNode fileNode1 = new Directory.FileNode("1.txt", false);
        Directory.FileNode fileNode2 = new Directory.FileNode("test", true);
        directory.addFile(fileNode1);
        directory.addFile(fileNode2);
        directory.printFiles();
    }
}
