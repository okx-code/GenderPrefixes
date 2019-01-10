package sh.okx.genderprefixes;

import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.Node;
import me.lucko.luckperms.api.User;
import org.bukkit.entity.Player;

public enum Gender {
  MALE("gender.male"),
  FEMALE("gender.female");

  private final String permission;

  Gender(String permission) {
    this.permission = permission;
  }

  private Node getPermission() {
    return LuckPerms.getApi().getNodeFactory().newBuilder(permission).build();
  }

  public void set(Player player) {
    User user = LuckPerms.getApi().getUser(player.getUniqueId());
    for (Gender gender : values()) {
      if (gender != this) {
        user.unsetPermission(gender.getPermission());
      }
    }
    user.setPermission(getPermission());
    LuckPerms.getApi().getUserManager().saveUser(user);
  }

  public static Gender getGender(Player player, Gender defaultGender) {
    User user = LuckPerms.getApi().getUser(player.getUniqueId());
    for (Gender gender : values()) {
      if (user.hasPermission(gender.getPermission()).asBoolean()) {
        return gender;
      }
    }
    return defaultGender;
  }
}
