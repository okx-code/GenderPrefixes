package sh.okx.genderprefixes;

import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.LuckPermsApi;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class GenderPrefixes extends JavaPlugin {
  @Override
  public void onEnable() {
    saveDefaultConfig();

    Gender defaultGender = Gender.valueOf(getConfig().getString("default-gender").toUpperCase());

    LuckPermsApi api = LuckPerms.getApi();
    api.getContextManager().registerCalculator(new GenderCalculator(defaultGender));


    getCommand("male").setExecutor(new GenderCommand(getMessage("male"), Gender.MALE));
    getCommand("female").setExecutor(new GenderCommand(getMessage("female"), Gender.FEMALE));
  }

  private String getMessage(String message) {
    return ChatColor.translateAlternateColorCodes('&', getConfig().getString("messages." + message));
  }
}
