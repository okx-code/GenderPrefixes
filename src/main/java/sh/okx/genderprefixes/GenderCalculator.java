package sh.okx.genderprefixes;

import net.luckperms.api.context.ContextCalculator;
import net.luckperms.api.context.ContextConsumer;
import net.luckperms.api.context.ContextSet;
import net.luckperms.api.context.ImmutableContextSet;
import org.bukkit.entity.Player;

public class GenderCalculator implements ContextCalculator<Player> {
  private final Gender defaultGender;

  public GenderCalculator(Gender defaultGender) {
    this.defaultGender = defaultGender;
  }

  @Override
  public void calculate(Player target, ContextConsumer consumer) {
    consumer.accept("gender", Gender.getGender(target, defaultGender).toString());
  }

  @Override
  public ContextSet estimatePotentialContexts() {
    ImmutableContextSet.Builder builder = ImmutableContextSet.builder();
    for (Gender gender : Gender.values()) {
      builder.add("gender", gender.toString());
    }
    return builder.build();
  }
}
