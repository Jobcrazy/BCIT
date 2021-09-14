package ca.bcit.winter2021.comp2522.midterm.main;

import ca.bcit.winter2021.comp2522.midterm.question1.*;
import ca.bcit.winter2021.comp2522.midterm.question2.*;
import ca.bcit.winter2021.comp2522.midterm.question3.*;
import ca.bcit.winter2021.comp2522.midterm.question4.*;
import ca.bcit.winter2021.comp2522.midterm.question5.*;

//********************************************************************************//
//Please write your information here:
//********************************************************************************//
//first name: Hang
//last name: Liu
//student ID: A01173804
//Your github ID: Jobcrazy (xianip@163.com)
//********************************************************************************//

public class MidtermApplicationDriver {

    public static void run(){
        //************************************************************************************************//
        System.out.println("I" + " Hang Liu " + "with student id "+ "A01173804" +
                "certify that I have read the exam policy and agree with them all.");
        //***********************************************************************************************//

        TestQuestion1.runAllTestScenariosForQuestion1();
        TestQuestion2.runAllTestScenariosForQuestion2();
        TestQuestion3.runAllTestScenariosForQuestion3();
        TestQuestion4.runAllTestScenariosForQuestion4();
        TestQuestion5.runAllTestScenariosForQuestion5();
        TestQuestion6.runAllTestScenariosForQuestion6();
    }
}
