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

import java.util.Scanner;

/**
 * Scheduler for the CustomProcessQueue
 * @author Shaan and Will
 */
public class ProcessScheduler {
  private int currentTime; // stores the current time after the last run
  private int numProcessesRun; // stores the number of processes run so far
  private CustomProcessQueue queue; // this processing unit's custom process queue
  
  /**
   * Constructor for the ProcessScheduler class
   */
  public ProcessScheduler() {
	this.currentTime = 0;
	this.numProcessesRun = 0;
	this.queue = new CustomProcessQueue();
  }
  
  /**
   * Enqueues process
   * @param process the new process to be enqueued
   */
  public void scheduleProcess(CustomProcess process) {
	this.queue.enqueue(process);
  }
  
  /**
   * Runs the queue
   * @return the string for the run of the queue
   */
  public String run() {
	String ret = "";
	int initialQueueSize = queue.size();
	if(queue.size() == 1) {
	  ret += "Starting " + (queue.size()) + " process\n\n";
	}else if(queue.size() > 1) {
	  ret += "Starting " + (queue.size()) + " processes\n\n";
	}for(int i = 0; i < initialQueueSize; i++) {
	  this.numProcessesRun++;
	  int processID = queue.peek().getProcessId();
	  ret += "Time " + this.currentTime + " : Process ID " + processID + " Starting.\n";
	  this.currentTime += queue.peek().getBurstTime();
	  queue.dequeue();
	  ret += "Time " + this.currentTime + ": Process ID " + processID + " Completed.\n";
	}ret += "\nTime " + this.currentTime + ": All scheduled processes completed.\n";
	return ret;
  }
  
  /**
   * getter for the queue
   * @return the queue
   */
  public CustomProcessQueue getQueue() {
	return this.queue;
  }
  
  /**
   * getter for numProcessesRun
   * @return numProcessesRun
   */
  public int getNumProcessesRun() {
    return this.numProcessesRun;
  }
  
  /**
   * getter for the currentTime
   * @return the currentTime
   */
  public int getTime() {
    return this.currentTime;
  }
  
  /**
   * The main method for the ProcessSchedulerClass
   * @param args the list of arguments
   */
  public static void main(String[] args) {
	System.out.println("==========   Welcome to the SJF Process Scheduler App   ========\n");
	ProcessScheduler process = new ProcessScheduler();
	Scanner in = new Scanner(System.in);
	while(true){
      System.out.println("Enter command:\n" + 
      		"[schedule <burstTime>] or [s <burstTime>]\n" + 
      		"[run] or [r]\n" + 
      		"[quit] or [q]\n");
	  String inp = in.nextLine();
	  if(inp.equals("quit") || inp.equals("q")) {
		System.out.println(process.numProcessesRun + " processes run in " + process.currentTime + " units of time!");
		System.out.println("Thank you for using our scheduler!");
		System.out.println("Goodbye!");
		break;
	  }
	  String[] pin = inp.split(" ");
	  if(!(pin[0].toLowerCase().equals("s") || pin[0].toLowerCase().equals("schedule") || (pin[0].toLowerCase().equals("r") && pin.length == 1))) {
		System.out.println("WARNING: Please enter a valid command!\n");
		continue;
	  }
	  if(pin[0].toLowerCase().equals("s") || pin[0].toLowerCase().equals("schedule")) {
		try {
		  Integer.parseInt(pin[1]);
	    }catch(NumberFormatException e) {
		  System.out.println("WARNING: burst time MUST be an integer!\n");
		  continue;
	    }
		if(Integer.parseInt(pin[1]) <= 0) {
		  System.out.println("WARNING: burst time MUST be greater than 0!\n");
		  continue;
		}
		CustomProcess c = new CustomProcess(Integer.parseInt(pin[1]));
		int id = c.getProcessId();
		int burst = c.getBurstTime();
		process.scheduleProcess(c);
		System.out.println("Process ID " + id + " scheduled. Burst Time = " + burst + "\n");
		continue;
	  }if(pin[0].toLowerCase().equals("r")) {
		if(process.queue.isEmpty()) {
		  System.out.println("You cannot run when there are no processes to run.");
		}else {
		  System.out.println(process.run());
		}
	  }
	}
  }
}
