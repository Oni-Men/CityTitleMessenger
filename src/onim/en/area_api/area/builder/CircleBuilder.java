package onim.en.area_api.area.builder;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import onim.en.area_api.area.AreaModel;
import onim.en.area_api.area.model.CircleArea;

public class CircleBuilder extends AreaBuilder<CircleArea> {

  private Location origin, radius;

  private static final List<String> INSTRUCTIONS = Lists.newArrayList();
  static {
    INSTRUCTIONS.add("Left click to change origin.");
    INSTRUCTIONS.add("Right click to change radius.");
    INSTRUCTIONS.add("Right click same block again to create area!");
  }

  public CircleBuilder(Player player) {
    super(player);
  }

  public CircleBuilder(Player player, AreaModel area) {
    super(player, area);
  }

  @Override
  public void onLeftClick(Block block) {
    this.origin = block.getLocation();
  }

  @Override
  public void onRightClick(Block block) {
    if (this.origin != null && this.radius != null && this.radius.equals(block.getLocation())) {
      this.createAndRegister();
    } else {
      this.radius = block.getLocation();
    }
  }

  @Override
  public List<String> getInstructions() {
    return INSTRUCTIONS;
  }

  @Override
  public CircleArea create() {
    double r = Math.hypot(origin.getX() - radius.getX(), origin.getZ() - radius.getZ());
    CircleArea area = new CircleArea(areaId, areaName, origin, (int) r);
    area.setAreaMessage(areaMessage);
    return area;
  }

}
