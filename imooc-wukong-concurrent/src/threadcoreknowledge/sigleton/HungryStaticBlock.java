package threadcoreknowledge.sigleton;

/**
 * 描述：饿汉式单例_静态代码块（可用）
 * @author mfh
 * @date 2020/7/18 15:30
 */
public class HungryStaticBlock {
    private static final HungryStaticBlock INSTANCE;
    static {
        INSTANCE = new HungryStaticBlock();
    }
    private HungryStaticBlock(){}

    public HungryStaticBlock getInstance() {
        return INSTANCE;
    }
}
