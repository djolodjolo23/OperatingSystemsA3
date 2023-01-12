

public class Command {

  private int num;


  private String requestedVisits;

  private boolean start;

  public Command(int num, RegistryReader registryReader) {
    this.num = num;
    if (registryReader.getAllCommands().isEmpty()) {
      this.start = true;
    } else {
      this.start = false;
    }
  }

  public Command(String requestedVisits) {
    this.requestedVisits = requestedVisits;
  }

  public int getNum() {
    return num;
  }

  public String getRequestedVisits() {
    return requestedVisits;
  }
}
