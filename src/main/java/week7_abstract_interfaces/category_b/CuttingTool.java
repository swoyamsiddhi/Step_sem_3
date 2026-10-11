package week7_abstract_interfaces.category_b;

/**
 * Problem 3 – Backyard Toolshed Routine
 * Second level: overrides use() and calls super.use() first.
 */
public class CuttingTool extends GardenTool {

    public CuttingTool() {
        super();
    }

    @Override
    public String use() {
        return "Using the tool in the garden, blade sharpened first";
    }
}
