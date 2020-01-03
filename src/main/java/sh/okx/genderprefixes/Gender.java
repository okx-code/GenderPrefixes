package sh.okx.genderprefixes;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.model.user.UserManager;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.PermissionNode;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.stream.Collectors;

public enum Gender {
  MALE("gender.male"),
  FEMALE("gender.female");

  private final String permission;

  Gender(String permission) {
    this.permission = permission;
  }

  private Node getPermission() {
    return PermissionNode.builder(permission).build();
  }

  public void set(Player player) {
    UserManager userManager = LuckPermsProvider.get().getUserManager();
    User user = userManager.getUser(player.getUniqueId());
    for (Gender gender : values()) {
      if (gender != this) {
        user.data().remove(gender.getPermission());
      }
    }
    user.data().add(getPermission());
    userManager.saveUser(user);
  }

  public static Gender getGender(Player player, Gender defaultGender) {
    UserManager userManager = LuckPermsProvider.get().getUserManager();
    User user = userManager.getUser(player.getUniqueId());

    Set<PermissionNode> nodes = user.getNodes().stream()
            .filter(NodeType.PERMISSION::matches)
            .map(NodeType.PERMISSION::cast)
            .collect(Collectors.toSet());

    for (Gender gender : values()) {
      for (PermissionNode node : nodes) {
        if (node.getPermission().equalsIgnoreCase(gender.permission)) {
          return gender;
        }
      }
    }
    return defaultGender;
  }
}
