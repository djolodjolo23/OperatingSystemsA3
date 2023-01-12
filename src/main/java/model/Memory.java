package model;

import java.util.ArrayList;
import model.Command;

public class Memory {

  private ArrayList<Integer> allCyllinders;

  private ArrayList<Command> allCommands;

  private ArrayList<Integer> requestedCyllinderVisits;

  public Memory() {
    allCyllinders = new ArrayList<>();
    requestedCyllinderVisits = new ArrayList<>();
    allCommands = new ArrayList<>();
  }

  public void clearAllLists() {
    allCyllinders.clear();
    allCommands.clear();
    requestedCyllinderVisits.clear();
  }


  public void addToAllCommands(Command command) {
    allCommands.add(command);
  }

  public ArrayList<Command> getAllCommands() {
    return allCommands;
  }

  public ArrayList<Integer> getAllCyllinders() {
    return allCyllinders;
  }

  public ArrayList<Integer> getRequestedCyllinderVisits() {
    return requestedCyllinderVisits;
  }

  public void addListToRequestedVisits(ArrayList<Integer> list) {
    requestedCyllinderVisits.addAll(list);
  }

  public void addToAllCyllinders(int num) {
    allCyllinders.add(num);
  }

  public void addToRequestedCylinders(int num) {
    requestedCyllinderVisits.add(num);
  }

  public int getStartingCyllinder() {
    for (Command c : allCommands) {
      if (c.isStartingPointCommand()) {
        return c.getNum();
      }
    }
    return -1;
  }

  public int getDirectionCyllinder() {
    for (Command c : allCommands) {
      if (c.isMovingDirectionCommand()) {
        return c.getNum();
      }
    }
    return -1;
  }
}
