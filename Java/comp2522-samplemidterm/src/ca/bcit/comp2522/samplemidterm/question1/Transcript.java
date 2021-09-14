package ca.bcit.comp2522.samplemidterm.question1;

import java.util.ArrayList;

public class Transcript {
    private final ArrayList<Float> grades = new ArrayList<Float>();

    public Transcript() {
        super();
    }

    public void initializeGrades() {
        grades.clear();

        float[] tmpGrade = {45.6f, 89.0f, 79.1f, 60.6f,
                55f, 84f, 71f, 69f, 80f};

        for (Float grade : tmpGrade) {
            grades.add(grade);
        }
    }

    public String convertToLetterGrade(Float grade) {
        if (grade > 90) {
            return "A";
        } else if (grade > 80) {
            return "B";
        } else if (grade > 70) {
            return "C";
        } else if (grade > 60) {
            return "D";
        } else {
            return "F";
        }
    }

    public void printTranscript() {
        for (Float grade : grades) {
            System.out.printf("%.2f: %s\n", grade,
                    convertToLetterGrade(grade));
        }
    }

    public String printLetterGPA() {
        Float total = 0f;
        for (Float grade : grades) {
            total += grade;
        }
        return convertToLetterGrade(total / grades.size());
    }
}
