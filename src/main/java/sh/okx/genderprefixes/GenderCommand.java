package sh.okx.genderprefixes;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GenderCommand implements CommandExecutor {
  private final String message;
  private final Gender gender;

  public GenderCommand(String message, Gender gender) {
    this.message = message;
    this.gender = gender;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      return false;
    }

    Player player = (Player) sender;
    gender.set(player);
    player.sendMessage(message);
    return true;
  }
}
