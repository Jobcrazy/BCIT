package ca.bcit.comp2522.samplemidterm.question1;

public class Question1 {
    public void test(){
        Transcript transcript = new Transcript();
        transcript.initializeGrades();
        transcript.printTranscript();
        System.out.println(transcript.printLetterGPA());
    }
}
