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

}
