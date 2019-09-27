import sun.misc.VM;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // stackOverFlowError();
        // headSpaceError();
        //overheadLimit();
        //directBufferMemory();
        //metaSpace();
        nativeThread();
    }

    public static void headSpaceError() {
        //不断创建新对象
        byte[] bytes = new byte[1024];
    }

    public static void overheadLimit() {
        //回收时间长和频繁，效果不好
        List<String> list = new ArrayList<>();
        int i = 0;
        try {
            while (true) {
                list.add(String.valueOf(i++).intern());
            }
        } catch (Throwable e) {
            System.out.println("直接内存大小==========================="+VM.maxDirectMemory() / 1024 / 1024);
            System.out.println("数组大小========================" + i);
            e.printStackTrace();
            throw e;
        }

    }


    public static void stackOverFlowError() {
        stackOverFlowError();
    }

    public static void directBufferMemory(){
        ByteBuffer buffer=ByteBuffer.allocateDirect(2*1024*1024);
    }

    public static void metaSpace(){

    }

    public static void nativeThread(){
        while(true){
            new Thread(() -> {
                try {
                    Thread.sleep(10000000);
                } catch(InterruptedException e) { }
            }).start();
        }
    }


    /************************
     * 1.Java.lang.OutOfMemoryError:Java head space
     * -Xms2M -Xmx2M
     *
     * GC overhead limit exceeded    垃圾回收上的时间太多了，往往伴随着cpu升高。
     * -Xmx2M -Xms2M -Xss200k -XX:MaxDirectMemorySize=1M -XX:+PrintGCDetails -verbose -verbose:gc
     *
     * Metaspace  启动/运行时加载元数据报错
     * -XX:MaxMetaspaceSize=1M
     *
     * Direct buffer memory 直接内存溢出
     * -XX:MaxDirectMemorySize=1M
     *
     * Unable to create new native thread  线程过多而造成的OOM
     *
     * 2.java.lang.StackOverflowError:stackOverFlowError
     *
     * -Xss200k
     *
     *
     **/
}
