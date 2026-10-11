package week7_abstract_interfaces.category_b;

/**
 * Problem 3 – Backyard Toolshed Routine
 * Third level: overrides use(), calls super.use() (CuttingTool's version), then appends its own detail.
 */
public class Pruner extends CuttingTool {

    public Pruner() {
        super();
    }

    @Override
    public String use() {
        return super.use() + ", then trimming branches precisely";
    }
}
