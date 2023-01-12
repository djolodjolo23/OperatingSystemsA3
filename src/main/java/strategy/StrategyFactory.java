package strategy;

import java.io.IOException;
import model.Memory;
import model.RegistryReader;

public class StrategyFactory implements AbstractStrategyFactory{

  @Override
  public void getFcfsAllocationRule(RegistryReader registryReader, Memory memory) throws IOException {
    new Fcfs(memory, registryReader);
  }

  @Override
  public void getScanAllocationRule(RegistryReader registryReader, Memory memory) throws IOException {

  }

  @Override
  public void getCScanAllocationRule(RegistryReader registryReader, Memory memory) throws IOException {

  }
}
