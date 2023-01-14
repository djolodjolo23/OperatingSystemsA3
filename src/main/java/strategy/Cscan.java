package strategy;

import java.io.IOException;
import model.Memory;
import model.RegistryReader;

public class Cscan extends SuperScan {

  private final RegistryReader registryReader;

  private final char allocationType = 'C';

  public Cscan(Memory memory, RegistryReader registryReader) throws IOException {
    this.registryReader = registryReader;
    run(memory, registryReader, allocationType);
  }

}
