package strategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import model.Command;
import model.Comparator;
import model.Memory;
import model.RegistryReader;

public abstract class SuperScan {

  public void run(Memory memory, RegistryReader registryReader, char allocationType) throws IOException {
    for (Command c : memory.getAllCommands()) {
      if (c.isInitiationCommand()) {
        for (int i = 0; i < c.getNum(); i++) {
          memory.addToAllCyllinders(i);
        }
      }
      if (c.isListOfCylindersCommand()) {
        String value = c.getRequestedVisits();
        String[] array = value.split(",");
        for (int i = 0; i < array.length; i++) {
          memory.addToRequestedCylinders(Integer.parseInt(array[i]));
        }
      }
    }
    switch (allocationType) {
      case ('F') -> {
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
      case ('S') -> {
        int sum = 0;
        int startingPos = memory.getStartingCyllinder();
        int direction = memory.getDirectionCyllinder();
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> requestedCylinders = memory.getRequestedCyllinderVisits();
        if (direction == 0) {
          if (!requestedCylinders.contains(0)) {
            requestedCylinders.add(0);
          }
        }
        if (direction == 1) {
          if (!requestedCylinders.contains(199)) {
            requestedCylinders.add(199);
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
      }
    }
    memory.clearAllLists();

  }

}
