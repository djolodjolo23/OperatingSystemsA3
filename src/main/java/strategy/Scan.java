package strategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import model.Memory;
import model.RegistryReader;

public class Scan extends SuperScan{

  private final RegistryReader registryReader;

  private final char allocationType = 'S';

  public Scan(Memory memory, RegistryReader registryReader) throws IOException {
    this.registryReader = registryReader;
    run(memory, registryReader, allocationType);

  }

  private void runSchedule(Memory memory) throws IOException {
    int sum = 0;
    int startingPos = memory.getStartingCyllinder();
    int direction = memory.getDirectionCyllinder();
    StringBuilder sb = new StringBuilder();
    ArrayList<Integer> requestedCylinders = memory.getRequestedCyllinderVisits();
    if (direction == 0) {
      if (!requestedCylinders.contains(199)) {
        requestedCylinders.add(199);
      }
    }
    if (direction == 1) {
      if (!requestedCylinders.contains(0)) {
        requestedCylinders.add(0);
      }
    }
    ArrayList<Integer> numsLowerThanStartingPos = new ArrayList<>();
    ArrayList<Integer> numsBiggerThanStartingPos = new ArrayList<>();
    for (int i = 0; i < memory.getRequestedCyllinderVisits().size(); i++) {
      int nextNum = memory.getRequestedCyllinderVisits().get(i);
      sb.append(nextNum);
      sb.append(",");
      int result;
      if (nextNum > startingPos) {
        numsBiggerThanStartingPos.add(nextNum);
      } else {
        numsLowerThanStartingPos.add(nextNum);
      }
    }
    Collections.sort(numsBiggerThanStartingPos);
    Collections.sort(numsLowerThanStartingPos);
    ArrayList<Integer> finalSortedList = new ArrayList<>();
    if (direction == 0) {
      for (Integer i : numsBiggerThanStartingPos) {
        finalSortedList.add(i);
        int next = i;
        sum += next - startingPos;
        startingPos = next;
      }
      startingPos = memory.getStartingCyllinder();
      for (int i = numsLowerThanStartingPos.size()-1; i >= 0; i--) {
        finalSortedList.add(numsLowerThanStartingPos.get(i));
        int next = numsLowerThanStartingPos.get(i);
        sum += startingPos - next;
        startingPos = next;
      }
    }
    if (direction == 1) {
      for (int i = numsLowerThanStartingPos.size()-1; i >= 0; i--) {
        finalSortedList.add(numsLowerThanStartingPos.get(i));
        int next = numsLowerThanStartingPos.get(i);
        sum += startingPos - next;
        startingPos = next;
      }
      for (Integer i : numsBiggerThanStartingPos) {
        finalSortedList.add(i);
        int next = i;
        sum += next - startingPos;
        startingPos = next;
      }
    }
    String listString = finalSortedList.stream().map(Object::toString)
        .collect(Collectors.joining(","));
    registryReader.saveFinalFile(sum, listString, 'S');
  }

}
