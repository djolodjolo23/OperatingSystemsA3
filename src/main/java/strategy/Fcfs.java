package strategy;

import java.io.IOException;
import java.util.ArrayList;
import model.Memory;
import model.RegistryReader;

public class Fcfs extends SuperScan {

  private final RegistryReader registryReader;

  private final char allocationType = 'F';

  public Fcfs(Memory memory, RegistryReader registryReader) throws IOException {
    this.registryReader = registryReader;
    run(memory, registryReader, allocationType);
  }

  private void runSchedule(Memory memory) throws IOException {
    int sum = 0;
    int startingPos = memory.getStartingCyllinder();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < memory.getRequestedCyllinderVisits().size(); i++) {
      int nextNum = memory.getRequestedCyllinderVisits().get(i);
      sb.append(nextNum);
      sb.append(",");
      int result;
      if (nextNum > startingPos) {
        result = nextNum - startingPos;
      } else {
        result = startingPos - nextNum;
      }
      sum += result;
      startingPos = nextNum;
    }
    sb.delete(sb.length()-1, sb.length());
    registryReader.saveFinalFile(sum, String.valueOf(sb), 'F');
  }

}
