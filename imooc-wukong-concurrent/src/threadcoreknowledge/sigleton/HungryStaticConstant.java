package threadcoreknowledge.sigleton;

/**
 * 描述：饿汉式单例_静态成员变量（可用）
 * @author mfh
 * @date 2020/7/18 15:30
 */
public class HungryStaticConstant {
    private static final HungryStaticConstant INSTANCE = new HungryStaticConstant();
    private HungryStaticConstant(){}

    public HungryStaticConstant getInstance() {
        return INSTANCE;
    }
}
