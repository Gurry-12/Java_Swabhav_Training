package com.database.test;

import java.util.Scanner;

import com.database.connect.DeleteAllRecordWithMinimumMarks;
import com.database.connect.DeleteRecordWithId;
import com.database.connect.DisplayAllRecord;
import com.database.connect.DisplayRecordAgeBetweenRange;
import com.database.connect.DisplayRecordGreaterThanMarks;
import com.database.connect.DisplayRecordWithBranch;
import com.database.connect.DisplayRecordWithId;
import com.database.connect.InsertNewStudentRecord;
import com.database.connect.InsertRecordWithBatchProcessing;
import com.database.connect.UpdateRecordBranchAndMarksWithId;
import com.database.connect.UpdateRecordMarksByBranch;
import com.database.connect.UpdateRecordNameWithId;
import com.database.helper.Helpers;

public class TestDatabase {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Enter your choice : ");

            int choice = Helpers.validateIntRange(scanner, 0, 12);

            switch (choice) {

                case 1 -> {
                    InsertNewStudentRecord record = new InsertNewStudentRecord();
                    record.insertNewStudentInDatabase();
                }

                case 2 -> {
                    InsertRecordWithBatchProcessing batch = new InsertRecordWithBatchProcessing();
                    batch.insertRecordWithBatch();
                }

                case 3 -> {
                    DisplayAllRecord displayAll = new DisplayAllRecord();
                    displayAll.getAllRecords();
                }

                case 4 -> {
                    DisplayRecordWithId displayRecord = new DisplayRecordWithId();
                    displayRecord.recordWithId();
                }

                case 5 -> {
                    DisplayRecordWithBranch displayRecordBranch = new DisplayRecordWithBranch();
                    displayRecordBranch.recordWithBranch();
                }

                case 6 -> {
                    DisplayRecordGreaterThanMarks greaterMarks = new DisplayRecordGreaterThanMarks();
                    greaterMarks.recordGreaterThanSpecificMarks();
                }

                case 7 -> {
                    DisplayRecordAgeBetweenRange recordWithAge = new DisplayRecordAgeBetweenRange();
                    recordWithAge.recordAgeInBetweenRange();
                }

                case 8 -> {
                    UpdateRecordNameWithId updateRecordWithId = new UpdateRecordNameWithId();
                    updateRecordWithId.updateStudnetNameWithId();
                }

                case 9 -> {
                    UpdateRecordBranchAndMarksWithId updateBranchMarks = new UpdateRecordBranchAndMarksWithId();
                    updateBranchMarks.updateStudnetBranchAndMarksWithId();
                }

                case 10 -> {
                    UpdateRecordMarksByBranch updateMarks = new UpdateRecordMarksByBranch();
                    updateMarks.updateMarksByBranch();
                }

                case 11 -> {
                    DeleteRecordWithId deleteRecord = new DeleteRecordWithId();
                    deleteRecord.deleteStudentRecordWithId();
                }

                case 12 -> {
                    DeleteAllRecordWithMinimumMarks deleteRecordWithMarks = new DeleteAllRecordWithMinimumMarks();
                    deleteRecordWithMarks.deleteStudentRecordWithMarks();
                }

                case 0 -> {
                    System.out.println("Exiting... Goodbye!");
                    running = false;
                }

                default -> System.out.println("Invalid choice. Please select between 0 and 12.\n");
            }

            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("""
       
         STUDENT DATABASE MANAGER     
 ----------------------------------------------
 INSERT                                      
        1.  Insert a new student record            
        2.  Insert 5 records (batch processing)    
 -----------------------------------------------
 DISPLAY                                     
        3.  Display all records                    
        4.  Display record by ID                   
        5.  Display records by branch              
        6.  Display records with marks > value     
        7.  Display records by age range           
 -----------------------------------------------
 UPDATE                                      
        8.  Update student name by ID              
        9.  Update branch and marks by ID          
        10.  Increase marks by branch               
  -----------------------------------------------
 DELETE                                      
        11.  Delete student by ID                   
        12.  Delete students below minimum marks    
  -----------------------------------------------
        0.  Exit                                   
 """);
    }
}