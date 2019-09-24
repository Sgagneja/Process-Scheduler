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
 * interface for the queue to be utilized in the CustomProcessQueue class
 * @author Shaan and Will
 *
 * @param <T> T
 */
public interface WaitingQueueADT<T extends Comparable<T>> {
 
  /**
   * inserts a newObject in the priority queue
   * @param newObject the object being inserted
   */
  public void enqueue(T newObject);  
 
  /**
   * removes and returns the item with the highest priority
   * @return the item with the highest priority
   */
  public T dequeue(); 
 
  /**
   * returns the item with the highest priority
   * @return the item with the highest priority
   */
  public T peek();
 
  /**
   * returns size of the waiting queue
   * @return size of the waiting queue
   */
  public int size(); 
 
  /**
   * checks if the waiting queue is empty
   * @return true if it is, false otherwise
   */
  public boolean isEmpty(); 
}