package week7_abstract_interfaces.category_b;

/**
 * Problem 3 – Backyard Toolshed Routine
 * Runner: demonstrates multilevel super.use() chain.
 */
public class ToolshedApp {

    public static void main(String[] args) {
        System.out.println("--- Problem 3: Backyard Toolshed Routine ---");

        CuttingTool c = new CuttingTool();
        System.out.println(c.use());

        Pruner p = new Pruner();
        System.out.println(p.use());
    }
}
