package model;

public class Command {

  private int num;


  private String requestedVisits;

  private boolean initiationCommand = false;

  private boolean startingPointCommand = false;

  private boolean movingDirectionCommand = false;

  private boolean listOfCylindersCommand = false;

  public Command(int num, Memory memory) {
    this.num = num;
    if (memory.getAllCommands().isEmpty()) {
      this.initiationCommand = true;
    } else if (num == 0 || num == 1) {
      this.movingDirectionCommand = true;
    } else {
      this.startingPointCommand = true;
    }
  }

  public Command(String requestedVisits) {
    this.requestedVisits = requestedVisits;
    this.listOfCylindersCommand = true;
  }

  public int getNum() {
    return num;
  }

  public String getRequestedVisits() {
    return requestedVisits;
  }

  public boolean isInitiationCommand() {
    return initiationCommand;
  }

  public boolean isListOfCylindersCommand() {
    return listOfCylindersCommand;
  }

  public boolean isStartingPointCommand() {
    return startingPointCommand;
  }

  public boolean isMovingDirectionCommand() {
    return movingDirectionCommand;
  }
}
