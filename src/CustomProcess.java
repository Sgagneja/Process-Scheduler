//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P10 SJF PROCESS SCHEDULER
// Files:           NA
// Course:          121148, 1st Semester, 2018
//
// Author:          Shaan Gagneja
// Email:           sgagneja@wisc.edu
// Lecturer's Name: Alexi Brooks
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Will Ryan
// Partner Email:   wrryan@wisc.edu
// Partner Lecturer's Gary Dahl
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   X Write-up states that pair programming is allowed for this assignment.
//   X We have both read and understand the course Pair Programming Policy.
//   X We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         NA
// Online Sources:  NA
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * A process that will be held in a queue
 * @author Shaan and Will
 */
public class CustomProcess implements Comparable<CustomProcess> {
  private static int nextProcessId = 1; // stores the id to be assigned to the next process 
  									   // to be created
  private final int PROCESS_ID; // unique identifier for this process
  private int burstTime; // time required by this process for CPU execution

  /**
   * Constructor for the CustomProcess class
   * @param burstTime the amount of time required for CPU execution
   */
  public CustomProcess(int burstTime) {
	this.burstTime = burstTime;
	this.PROCESS_ID = CustomProcess.nextProcessId;
	CustomProcess.nextProcessId++;
  }
  
  /**
   * Compares two different processes based on burst time first, then ID
   * 
   * @param other the other CustomProcess
   * @return 1 if this process is > other 
   * 		-1 if this process is < other
   */
  @Override
  public int compareTo(CustomProcess other) {
	if(this.burstTime == other.burstTime) {
      if(this.PROCESS_ID == other.PROCESS_ID) {
    	    return 0;
      }else if(this.PROCESS_ID < other.PROCESS_ID) {
    	    return -1;
      }else {
    	    return 1;
      }
	}else if(this.burstTime < other.burstTime) {
	  return -1;
	}else {
	  return 1;
	}
  }
  
  /**
   * getter for the PROCESS_ID
   * 
   * @return this process' PROCESS_ID
   */
  public int getProcessId() {
	return this.PROCESS_ID;
  }
  
  /**
   * getter for the burstTime
   * @return this process' burstTime
   */
  public int getBurstTime() {
	return this.burstTime;
  }
}
