package sh.okx.genderprefixes;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class GenderPrefixes extends JavaPlugin {
  @Override
  public void onEnable() {
    saveDefaultConfig();

    Gender defaultGender = Gender.valueOf(getConfig().getString("default-gender").toUpperCase());

    LuckPerms api = LuckPermsProvider.get();
    api.getContextManager().registerCalculator(new GenderCalculator(defaultGender));

    getCommand("male").setExecutor(new GenderCommand(getMessage("male"), Gender.MALE));
    getCommand("female").setExecutor(new GenderCommand(getMessage("female"), Gender.FEMALE));
  }

  private String getMessage(String message) {
    return ChatColor.translateAlternateColorCodes('&', getConfig().getString("messages." + message));
  }
}
