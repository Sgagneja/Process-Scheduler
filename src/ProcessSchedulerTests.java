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
 * Tests for the multiple classes in this project
 * @author Shaan
 *
 */
public class ProcessSchedulerTests {

  /**
   * tests the enqueue method of the CustomProcessQueue class
   * @return true if it works correctly, false otherwise
   */
  public static boolean testEnqueueCustomProcessQueue(){  
    CustomProcessQueue q = new CustomProcessQueue();     
    boolean passed = true;
    q.enqueue(new CustomProcess(5));
    q.enqueue(new CustomProcess(4));
    q.enqueue(new CustomProcess(11));
    CustomProcess[] heap = q.getHeap();
    if(heap[0] != null) {
    	  System.out.println("The queue does not start at index 1");
    	  passed = false;
    }if(heap[1].getBurstTime() != 4) {
    	  System.out.println("The heap ordering property is not working properly");
    	  passed = false;
    }if(heap[3].getBurstTime() != 11) {
    	  System.out.println("The heap ordering property is not working properly");
    	  passed = false;
    }return passed;
  }
  
  /**
   * tests the dequeue method of the CustomProcessQueue class
   * @return true if it works correctly, false otherwise
   */
  public static boolean testDequeueCustomProcessQueue() { 
	CustomProcessQueue q = new CustomProcessQueue();     
	boolean passed = true;
	if(q.dequeue() != null) {
	  System.out.println("Dequeue is not returning null when it is empty");
	  passed = false;
	}if(q.peek() != null) {
	  System.out.println("Peek is not returning null when it is empty");
	  passed = false;
	}q.enqueue(new CustomProcess(7));
	if(q.peek().getBurstTime() != 7) {
	  System.out.println("Peek is not working properly");
	  passed = false;
	}if(q.dequeue().getBurstTime() != 7) {
	  System.out.println("Dequeue is not working properly");
	}if(!q.isEmpty()) {
	  System.out.println("Dequeue is not removing the element");
	  passed = false;
	}q.enqueue(new CustomProcess(4));
	q.enqueue(new CustomProcess(1));
	if(q.dequeue().getBurstTime() != 1) {
      System.out.println("Dequeue is not accessing the right element");
      passed = false;
	}return passed;
  }
  
  /**
   * tests the peek method of the CustomProcessQueue class
   * @return true if it works correctly, false otherwise
   */
  public static boolean testPeekCustomProcessQueue() {
	CustomProcessQueue q = new CustomProcessQueue();     
	boolean passed = true;
	if(q.peek() != null) {
	  passed = false;
	}
	q.enqueue(new CustomProcess(5));
	if(q.peek() == null) {
	  passed = false;
	}
	if(q.peek().getBurstTime() != 5) {
	  passed = false;
	}
	return passed;
  }
  
  /**
   * tests the schedule method of the ProcessScheduler class
   * @return true if it works correctly, false otherwise
   */
  public static boolean testScheduleProcessScheduler() {
	ProcessScheduler p = new ProcessScheduler();
	boolean passed = true;
	p.run();
	if(p.getNumProcessesRun() != 0) {
	  passed = false;
	}
	p.scheduleProcess(new CustomProcess(5));
	if(p.getQueue().peek().getBurstTime() != 5) {
	  passed = false;
	}
	p.run();
	if(p.getNumProcessesRun() != 1) {
	  passed = false;
	}
	if(p.getTime() != 5) {
	  passed = false;
	}
	return passed;
  }
  
  /**
   * Main method for the ProcessSchedulerTests class
   * @param args list of arguments
   */
  public static void main(String[] args) {
	if(testEnqueueCustomProcessQueue() && testDequeueCustomProcessQueue()
			&& testPeekCustomProcessQueue() && testScheduleProcessScheduler()) {
	  System.out.println("All tests passed.");
	}
  }
}
