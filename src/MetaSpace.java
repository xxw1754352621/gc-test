import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

public class MetaSpace {
    /**
     * * Metaspace  启动/运行时加载元数据报错
     * * -XX:MaxMetaspaceSize=20M
     *
     * @param args
     */
    public static void main(String[] args) {
        int i = 0;
        while (true) {
            try {
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMObject.class);
                enhancer.setUseCache(false);
                enhancer.setCallback((MethodInterceptor) (obj, method, args1, proxy) -> proxy.invokeSuper(obj, args1));
                enhancer.create();
                i++;
            } catch (Throwable e) {
                e.printStackTrace();
                break;
            }

        }
        System.out.println("该类加载数量==========" + i);

    }

    static class OOMObject {
    }
}
