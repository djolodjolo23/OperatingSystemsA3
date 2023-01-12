import controller.Controller;
import java.io.IOException;
import model.Memory;
import model.RegistryReader;
import strategy.AbstractStrategyFactory;
import strategy.StrategyFactory;

public class Main {

  public static void main(String[] args) throws IOException {
    Memory memory = new Memory();
    RegistryReader regReader = new RegistryReader(memory);
    AbstractStrategyFactory strategyFactory = new StrategyFactory();

    Controller controller = new Controller(regReader);
    controller.run(strategyFactory, memory);
  }

}
