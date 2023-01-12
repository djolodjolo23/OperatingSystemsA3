public enum AllocationType {

  FCFS('F'), SCAN('S'), CSCAN('C');

  private final char value;

  public char getValue() {
    return value;
  }

  AllocationType(char value) {
    this.value = value;
  }


}
