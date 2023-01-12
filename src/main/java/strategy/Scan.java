package strategy;

import java.io.IOException;
import model.Memory;
import model.RegistryReader;

public class Scan extends SuperScan{

  private final RegistryReader registryReader;

  private final char allocationType = 'S';

  public Scan(Memory memory, RegistryReader registryReader) throws IOException {
    this.registryReader = registryReader;
    run(memory, registryReader, allocationType);
  }

}
