package sh.okx.genderprefixes;

import me.lucko.luckperms.api.context.ContextCalculator;
import me.lucko.luckperms.api.context.MutableContextSet;
import org.bukkit.entity.Player;

public class GenderCalculator implements ContextCalculator<Player> {
  private final Gender defaultGender;

  public GenderCalculator(Gender defaultGender) {
    this.defaultGender = defaultGender;
  }

  @Override
  public MutableContextSet giveApplicableContext(Player subject, MutableContextSet accumulator) {
    accumulator.add("gender", Gender.getGender(subject, defaultGender).name());
    return accumulator;
  }
}
