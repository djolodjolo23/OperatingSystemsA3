package strategy;

import java.util.ArrayList;
import java.util.Arrays;
import model.Command;
import model.Memory;
import model.RegistryReader;

public abstract class SuperScan {

  public void run(Memory memory, RegistryReader registryReader, char allocationType) {
    for (Command c : memory.getAllCommands()) {
      if (c.isInitiationCommand()) {
        for (int i = 0; i < c.getNum(); i++) {
          memory.addToAllCyllinders(i);
        }
      }
      if (c.isListOfCylindersCommand()) {
        String value = c.getRequestedVisits();
        ArrayList<Integer> nums = new ArrayList<>(Integer.parseInt(Arrays.toString(value.split(";"))));
        memory.addListToRequestedVisits(nums);
      }
    }
    switch (allocationType) {
      case ('F') -> {
        int sum = 0;
        int startingPos = memory.getStartingCyllinder();
        for (int i = 0; i < memory.getRequestedCyllinderVisits().size(); i++) {
          int nextNum = memory.getRequestedCyllinderVisits().get(i);
          int result;
          if (nextNum > startingPos) {
            result = nextNum - startingPos;
          } else {
            result = startingPos - nextNum;
          }
          sum += result;
          startingPos = nextNum;
        }
      }
    }

  }

}
