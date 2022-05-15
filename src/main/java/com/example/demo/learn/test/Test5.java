package com.example.demo.learn.test;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import com.example.demo.entity.SysUser;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.poi.ss.formula.functions.T;
import org.junit.BeforeClass;
import org.springframework.util.StringUtils;
import sun.applet.AppletClassLoader;

import java.beans.Transient;
import java.io.*;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.*;

/**
 * Test5
 *
 * @author zouzhihao
 * @date 2021/1/26
 */
public interface Test5 {
    String a = "1212";
    String b = "121";
    void x();

    default String aaa(){
        return null;
    }

    static String bbb(){
        return null;
    }
}
class Test6 implements Test5 {

    public static void main(String[] args) {



    }

    @Override
    public void x() {

    }

    @Override
    public String aaa() {
        Test5.bbb();
        return null;
    }
}
@Data
class User implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    private String name = "123";

    private int age;

    private String t;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

@Data
class User2 implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    private String name;

    private int age;

    private String t;

    private User user;

    private Integer integer;

    private List<String> arr;

    private Map<String, Object> map;

//    @Override
//    public void writeExternal(ObjectOutput out) throws IOException {
//        out.writeObject(this.name);
//    }
//
//    @Override
//    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
//        this.name = (String) in.readObject();
////        this.age = (int) in.readObject();
//    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        User2 user2 = (User2) super.clone();
        user2.setUser((User) user2.getUser().clone());
        user2.setArr(user2.getArr());
        return user2;
    }
}

class Test7 {
    public static void main(String[] args) {
//        output();
//        input();
//        outputExternallizable();
        inputExternallizable();

        User user = new User();
        user.getName();
    }
    public static void output(){
        User user = new User();
        user.setAge(1);
        user.setName("序列化一");
        user.setT("ttttt");

        try (ObjectOutputStream bufferedOutputStream = new ObjectOutputStream(new FileOutputStream(new File("D://aaa.txt")))) {
            bufferedOutputStream.writeObject(user);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void input(){
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("D://aaa.txt"))){
            User user2 = (User) objectInputStream.readObject();
            System.out.println(user2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void outputExternallizable(){
        User2 user = new User2();
        user.setAge(1);
        user.setName("序列化一");
        user.setT("ttttt");

        try (ObjectOutputStream bufferedOutputStream = new ObjectOutputStream(new FileOutputStream(new File("D://user2.txt")))) {
            bufferedOutputStream.writeObject(user);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void inputExternallizable(){
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("D://user2.txt"))){
            User2 user2 = (User2) objectInputStream.readObject();
            System.out.println(user2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}


class Test8{
    public static void main(String[] args) throws CloneNotSupportedException {
        User2 user = new User2();
        user.setAge(1);
        user.setName("序列化一");
        user.setT("ttttt");
        user.setInteger(new Integer(1111111));
        List<String> objects = new ArrayList<>();
        objects.add("1");objects.add("2");
        user.setArr(objects);
        Map<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("1", "1");
        user.setMap(stringObjectHashMap);
        User innerUser = new User();
        innerUser.setName("内部");
        user.setUser(innerUser);

        User2 user2 = (User2) user.clone();
//        User2 user2 = CloneUtils.clone(user);
        user.setName("改了");
//        user.setInteger(new Integer(1111));
        user.setAge(111);
        user.getUser().setName("又改了");
        user.getArr().add("11");
        user.getMap().put("2","2");
        System.out.println(user);
        System.out.println(user2);
        System.out.println(user.getT()==user2.getT());
    }
}

class CloneUtils {
    public static <T extends Serializable> T clone(T obj) {
        T cloneObj = null;
        try (ByteArrayOutputStream bo = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bo);){
            //写入字节流
            oos.writeObject(obj);
            //分配内存,写入原始对象,生成新对象
            ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
            ObjectInputStream oi = new ObjectInputStream(bi);
            //返回生成的新对象
            cloneObj = (T) oi.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}

class Test9{

    public static void a(){
        throw new RuntimeException("");
    }

    public static String b() {
        try{
            System.out.println("开始");
            return "a";
        }catch (Exception e){
            return "b";
        }finally {
            System.out.println("结束");
            return "c";
        }
    }
    public static void main(String[] args) throws Exception {
//        a();
        System.out.println(b());
    }
}
@Data
class Test10 <T> {
    public T name;

}
@Data
class Test12 extends Test10<Integer> {
//    public String name;

}

class Test11 {
    public static void main(String[] args) {
        Test11.<Integer>a(1);
        Test10<String>[] test10s = new Test10[10];
        test10s[0]=new Test10<String>();
//        Test10<String> test10 = new Test10<>();
//        test10.setName("123456");
//        System.out.println(test10.getName());
//        System.out.println(test10.getName().getClass());
//
//        final Test12 test12 = new Test12();
//        test12.setName(1);


    }

    public static <T extends Number> T a(T a){
        return a;
    }

}
class Test13 {

    public static void main(String[] args) {
        String a = "5";


        Optional.ofNullable(a)
                .map(o -> Integer.valueOf(o))
                .map(Integer::new)
                .filter(o -> o > 1)
                .orElseGet(() -> 0);

//        Long aLong = Optional.ofNullable(a)
//                .filter(o -> o.length() > 0)
//                .flatMap(b ->
//                        Optional.ofNullable(b)
//                                .map(Integer::parseInt)
//                                .filter(x -> x > 3)
//                                .flatMap(obj -> Optional.ofNullable(obj)
//                                        .map(l -> l.longValue())
//                                        .filter(y -> y > 5))
//
//                )
//                .orElse(100L);
//        System.out.println(aLong);

        Collectors.toList();
        Collectors.toSet();

        //结果 100

//
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);

        List<User> userList = new ArrayList<>();
        IntStream.range(0, 10).forEach(obj -> {
            User user = new User();
            user.setName(String.valueOf(obj));
            user.setAge(obj);
            userList.add(user);
        });





        List<String> collect = integers.stream().map(String::valueOf).collect(Collectors.toList());

        Set<String> collect2 = integers.stream().map(String::valueOf).collect(Collectors.toSet());

        String s = integers.stream().map(String::valueOf).collect(Collectors.joining(",", "[", "]"));
        //结果：[1,2,3,4,5]
        String s2 = integers.parallelStream().map(String::valueOf).collect(Collectors.joining(",", "[", "]"));

        List<Integer> collect14 = integers.parallelStream().peek(System.out::println).collect(Collectors.toList());


//        Long collect1 =
//        integers.stream().map(TTT::new).collect(Collectors.counting());
        //结果：5

        User user = userList.stream().collect(Collectors.maxBy(Comparator.comparing(User::getAge))).orElse(null);

        //summingInt
        Integer collect3 = integers.stream().collect(Collectors.summingInt(o -> o));

        Integer collect4 = userList.stream().collect(Collectors.summingInt(User::getAge));

        Integer collect5 = userList.stream().collect(Collectors.summingInt(o -> o.getAge() + o.getAge()));

        //averagingInt
        Double collect6 = integers.stream().collect(Collectors.averagingInt(o -> o));
        Double collect7 = userList.stream().collect(Collectors.averagingInt(User::getAge));
        Double collect8 = userList.stream().collect(Collectors.averagingInt(o -> o.getAge() + o.getAge()));

        //groupingBy
        Map<String, List<User>> collect9 = userList.stream().collect(Collectors.groupingBy(User::getName));
        Map<String, Integer> collect10 = userList.stream().collect(Collectors.groupingBy(User::getName, Collectors.summingInt(User::getAge)));
        TreeMap<String, Integer> collect11 = userList.stream().collect(Collectors.groupingBy(User::getName, TreeMap::new,
                                                                                                    Collectors.summingInt(User::getAge)));

        //toMap
        Map<Integer, String> collect12 = userList.stream().collect(Collectors.toMap(User::getAge, User::getName));
        Map<Integer, String> collect13 = userList.stream().collect(Collectors.toMap(User::getAge, o -> o.getName()));


//        Double collect8 = userList.stream().collect(Collectors.averagingInt(o -> o.getAge() + o.getAge()));

        //reducing
//        integers.stream().collect(Collectors.reducing());

//        Map<String, Integer> stringIntegerMap = userList.stream().collect(Collectors.toMap(User::getName,  a -> a,(k1,k2)->k1) ));


//
//        List<Integer> integers2 = new ArrayList<>();
//        integers2.add(1);
//        integers2.add(1);
//        integers2.add(1);
//        integers2.add(1);
//        integers2.add(1);
//
//        List<String> string3 = Arrays.asList("1", "2", "3", "4", "5");
//
//        IntStream intStream = string3.stream().mapToInt(Integer::parseInt);
//
//
//        //合并LongStream
//        LongStream concat = LongStream.concat(LongStream.range(100, 106), LongStream.range(106, 109));
//        concat.forEach(System.out::println);
//        //结果：100，101，102，103，104，105，106，107，108
//
//        string3.stream().map(Long::parseLong).sorted().skip(2).peek(System.out::println).collect(Collectors.toList());
//

//        integers.stream()
//                .flatMapToLong(o -> integers2.stream().mapToLong(ob -> ob)).map( o->o).collect(Collectors.counting()));


//                .mapToDouble(obj -> obj).peek(System.out::println).collect(Collectors.toList());


    }
}

class Test14<T, R>{
    T t;

    public static void main(String[] args) {
        TTT ttt = new TTT();
        i1<Object> i1 = TTT::b;
        Object b2 = i1.b();
//        i1.b(1);

        Comparator<Integer> comparator = (a ,b) -> a - b;

        Test14<String, String> stringStringTest14 = new Test14<>();
        stringStringTest14.t = "ttttt";
        String a = stringStringTest14.a(obj -> stringStringTest14.t + "111111");
        System.out.println(a);
    }

    public R a(Function<T , R> function){
        return function.apply(t);
    }

    @FunctionalInterface
    interface i1<T> {
        T b();
    }
}
class Test15{
    static void arrayList(){
        ArrayList<String> arrayList = new ArrayList<>();
//        IntStream.range(0, 15)
//                .forEach(o -> {
//                    arrayList.add(o);
//                });
        arrayList.add("1");
        arrayList.addAll(Arrays.asList("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"));
        arrayList.get(0);
        arrayList.clear();
//        Iterator<Object> iterator = arrayList.iterator();
//        iterator.remove();
//        arrayList.set()

        Vector vector = new Vector();
        vector.add(1);vector.add(1);
        vector.add(1);
        vector.add(1);vector.add(1);vector.add(1);vector.add(1);vector.add(1);vector.add(1);vector.add(1);vector.add(1);vector.add(1);


        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        IntStream.range(0, 15)
                .forEach(o -> {
                    copyOnWriteArrayList.add(o);
                });
        copyOnWriteArrayList.get(0);
        copyOnWriteArrayList.set(4, 666);
    }

    static void linkedList(){
        LinkedList<Object> linkedList = new LinkedList<>();
        IntStream.range(1, 15)
                .forEach(o -> linkedList.add(o));
        linkedList.getFirst();
        linkedList.getLast();

        linkedList.get(1);

        linkedList.add(1, 1);

        Object peek = linkedList.peek();
        Object poll = linkedList.poll();
//        linkedList.pollLast()
        linkedList.pop();
        linkedList.remove(1);
    }

    static void queue(){
        Queue<Object> queue = new LinkedList<>();
        Deque deque = new LinkedList<>();
//        Stack
    }

    static void hashSet(){
        Set<Integer> set = new HashSet<>();
        set.add(8);set.add(2);set.add(1);
        set.add(3);set.add(6);
        set.forEach(obj -> {
            System.out.println(obj);
        });

        List<Integer> collect = set.stream().sorted((a, b) -> a.compareTo(b)).collect(Collectors.toList());

        HashMap<Integer, Integer> integerIntegerHashMap = new HashMap<>();
        integerIntegerHashMap.put(1, 1);
        integerIntegerHashMap.put(4, 1);
        integerIntegerHashMap.put(2, 1);
        integerIntegerHashMap.put(5, 1);
        integerIntegerHashMap.put(3, 1);

        integerIntegerHashMap.forEach((k, v) -> {
            System.out.println(k + "===" + v);
        });

    }

    static void hashMap(){
        Map<Object, Object> map = new HashMap<>();
        ArrayList<String> strings = new ArrayList<>();
//        for (int i = 0; i< 1000000; i++){
//            String s = RandomUtil.randomString(4);
//            strings.add(s);
//            map.put(s, new String(i + ""));
//        }
        strings.forEach(obj -> {
            Object o = map.get(obj);
        });
        map.remove("1");

        System.out.println(map);

        map.put(new Integer(1), "");
        Object o = map.get(new Integer(1));
//        Node<String, Object> stringObjectNode = new Node<>(49, "1", "", null);
//        User user = new User();
//        user.setName("1");
//        map.put(user, "1");
//        User user2 = new User();
//        Object o1 = map.get(user2);
//        int i = user.hashCode();
//        int i1 = user2.hashCode();
//        boolean equals = user2.equals(user);
        Hashtable<Object, Object> hashtable = new Hashtable<>();
//        for (int i = 0; i< 1000000; i++){
//            hashtable.put(i, i);
//            hashtable.get();
//            hashtable.remove()
//        }
        TreeMap<Integer, Object> objectTreeMap = new TreeMap<>();
        objectTreeMap.put(new Integer(5), "1");
        objectTreeMap.put(new Integer(9), "1");
        objectTreeMap.put(new Integer(6), "1");
        objectTreeMap.put(new Integer(11), null);

        Set<Integer> objects = objectTreeMap.keySet();
        for (Object obj : objects){
//            System.out.println(obj);
        }


        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("1", "111");
        linkedHashMap.put("7", "111");
        linkedHashMap.put("3", "111");
        linkedHashMap.put("8", "111");
        linkedHashMap.put("6", "111");
        linkedHashMap.remove("3");
        linkedHashMap.get("1");
        linkedHashMap.forEach((k, v)->{
            System.out.println(k);
        });
//        linkedHashMap.get()

    }


    static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Test15.Node<K,V> next;

        Node(int hash, K key, V value, Test15.Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }


        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            return this.value;
        }
    }

    public static void main(String[] args) {
//        arrayList();
//        linkedList();
//        queue();
        hashSet();
//        hashMap();
//        int a = 33;
//        System.out.println(a >> 1);
//        System.out.println(a + (a >> 1));

        VaccinationStatisticsVo build = VaccinationStatisticsVo.builder()
                .deptId(1L)
                .yjs(1)
                .build();
        VaccinationStatisticsVo build2 = VaccinationStatisticsVo.builder()
                .deptId(1L)
                .yjs(2)
                .build();
        VaccinationStatisticsVo build3 = VaccinationStatisticsVo.builder()
                .deptId(2L)
                .yjs(1)
                .build();
        List<VaccinationStatisticsVo> vaccinationStatisticsVos = Arrays.asList(build, build2, build3);

        Map<Long, Integer> yjs = vaccinationStatisticsVos.parallelStream().collect(Collectors.groupingBy(VaccinationStatisticsVo::getDeptId,
                Collectors.summingInt(VaccinationStatisticsVo::getYjs)));


        ArrayList<VaccinationStatisticsVo> collect = vaccinationStatisticsVos.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<VaccinationStatisticsVo>(Comparator.comparing(o -> o.getDeptId()))), ArrayList::new));
        collect.stream().map(obj -> {
            obj.setYjs(
                    vaccinationStatisticsVos.stream()
                            .filter(o -> o.getDeptId().longValue() == obj.getDeptId().longValue())
                            .collect(Collectors.summingInt(VaccinationStatisticsVo::getYjs))
            );

            return obj;
        }).collect(Collectors.toList());


//
//        Integer collect1 = vaccinationStatisticsVos.stream()
//                .filter(obj -> obj.getDeptId().longValue() == 100L)
//                .collect(Collectors.summingInt(VaccinationStatisticsVo::getYjs));
////        Map<Long, List<Integer>> collect1 = vaccinationStatisticsVos.stream().collect(Collectors.groupingBy(VaccinationStatisticsVo::getDeptId, Collectors.mapping(Collectors(VaccinationStatisticsVo::getYjs))));
//
//
//        Collectors.summarizingInt((o)->{return 1;});
//        vaccinationStatisticsVos.parallelStream().collect(Collectors.groupingBy(VaccinationStatisticsVo::getDeptId,
//                ()->{return null;}, Collectors.toList()));
//        Map<Long, Integer> collect = vaccinationStatisticsVos.parallelStream().collect(Collectors.groupingBy(VaccinationStatisticsVo::getDeptId,
//                Collectors.toMap(VaccinationStatisticsVo::getYjs)));

//        Map<String, Product> prodMap = prodList.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Product::getNum)), Optional::get)));

    }

    @Data
    @Builder
    static class VaccinationStatisticsVo{
        private String datetime;
        private Integer zpb;
        private Integer dqr;
        private Integer yqr;
        private Integer yjs;
        private Integer yqx;
        private Long deptId;
        private String deptName;
        private Long orgId;
        private String bookingId;
        private Long schedulingId;
        private Long doctorId;
        private String doctorName;
    }
}
class Test16 {
    static class test16{

    }

    static void a1(){
        int l = 629000000;
        System.out.println(l);
        a1();
    }

    public static void main(String[] args) {
//        List<test16> test16s = new ArrayList<>();
//        while (true) {
//            test16s.add(new test16());
//        }
        a1();
    }
}
class Test17 {

    public static int par_a = 1;

    static {
        par_a = 100;
        System.out.println("父类static" + par_a);
    }

}
class Test18 extends Test17 {

    static int b = 0;

    static {
        b = 1;
        Test17.par_a = 101;
        System.out.println("子类static" + Test17.par_a);
    }

    public Test18() {
        System.out.println("初始化");
    }
    public static void main(String[] args) {
        new ArrayList<>();
        new Test17();
        Test18 test18 = new Test18();
        Test18 test182 = new Test18();




        String a = "abca";
        ReferenceQueue<String> queue = new ReferenceQueue<>();//创建引用队列保存引用
        SoftReference<String> softReference = new SoftReference<>(a, queue);
        a = null;//去掉强引用
        String s = softReference.get();//再次创建强引用

        String b = "abca";
        WeakReference<String> weakProduct = new WeakReference<>(b);
        b = null;
        String s1 = weakProduct.get();

        String c = "ccc";
        ReferenceQueue<String> referenceQueue = new ReferenceQueue<>();
        PhantomReference<String> phantomReference = new PhantomReference<>(c, referenceQueue);
        c = null;
        String s2 = phantomReference.get();

//        softReference.get()



//        String a = new String("a" + "b");

    }
}
class Test19 extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }
}
class Test20{
    String a ="";
    public void test() throws InterruptedException {
        Runnable runnable = () -> {
            String msg = "开始咯";
            String msg2 = "结束咯";
            System.out.println(msg);
            try {
                wait();
            } catch (InterruptedException e) {
            }
            System.out.println(msg2);
        };
        new Thread(runnable).start();
    }

    public void tryNotify(){
        notify();
    }

    public static void main(String[] args) throws InterruptedException {
        Test20 test20 = new Test20();
        test20.test();
        Thread.sleep(5000);
        test20.tryNotify();
    }
}
class InstructionReorder {

    private static int num = 0;
    private static boolean ready = false;

    public static class ReadThread extends Thread {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()){
                if(ready){ //(1)
                    System.out.println(num+num); //(2)
                }
                System.out.println("read thread...");
            }
        }
    }

    public static class WriteThread extends Thread {
        @Override
        public void run() {
            num = 2; //(3)
            ready = true; //(4)
            System.out.println("write thread set over");
        }
    }

    public static void main(String[] args) throws InterruptedException{
        ReadThread readThread = new ReadThread();
        readThread.start();

        WriteThread writeThread = new WriteThread();
        writeThread.start();

        Thread.sleep(1);
        readThread.interrupt();
        System.out.println("main exit");
    }


    public void test(){
        String a = "";
        synchronized (InstructionReorder.class) {
            System.out.println("");
        }
    }
}
class Test21{
    final static CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
    static {
        copyOnWriteArrayList.add(1);
        copyOnWriteArrayList.add(2);
        copyOnWriteArrayList.add(3);
        copyOnWriteArrayList.add(4);
        copyOnWriteArrayList.add(5);
        copyOnWriteArrayList.add(6);
        copyOnWriteArrayList.add(7);
        copyOnWriteArrayList.add(8);
        copyOnWriteArrayList.add(9);
        copyOnWriteArrayList.add(10);
    }
    public void get(){
        List<Object> objects = Collections.synchronizedList(new ArrayList<>());
        List<Object> linkedList = Collections.synchronizedList(new LinkedList<>());
        ConcurrentHashMap<String, Object> hashMap = new ConcurrentHashMap<>();
        hashMap.put("", "");
    }

    public void edit(){
        copyOnWriteArrayList.set(5, 100);
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            //能读取到实时的数据
            for (int i = 0; i < copyOnWriteArrayList.size(); i++) {
                System.out.println(copyOnWriteArrayList.get(i));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //不能读取到实时的数据
            for (Integer i : copyOnWriteArrayList) {
                System.out.println(i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            copyOnWriteArrayList.set(5, 100);
        }).start();
        Thread.sleep(1000);
        for (Integer i : copyOnWriteArrayList) {
            System.out.println(i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Test22 {
    public static void main(String[] args) {
        CopyOnWriteArraySet<Integer> copyOnWriteArraySet = new CopyOnWriteArraySet<>();
        copyOnWriteArraySet.add(1);
        copyOnWriteArraySet.add(1);
    }
}

class Test23 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        ThreadUtil.execute();
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());

//        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 3,
//                10L, TimeUnit.MINUTES, new LinkedBlockingQueue(100),
//                new DefaultThreadFactory("test"));

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 3,
                10L, TimeUnit.MINUTES, new SynchronousQueue(true),
                new DefaultThreadFactory("test"));

        String result = "";
        Future<?> submit = poolExecutor.submit(() -> {
            try{
                System.out.println("0");
                int a = 0/0;
            }catch (Exception e){

            }

        });
        Object o = submit.get();
        System.out.println(submit);
//        for (int i = 0; i < 5; i++) {
//            int finalI = i;
//            poolExecutor.execute(() -> {
//                try {
//                    Thread.sleep(1000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName() + "正在执行");
//            });
//        }
//
//        new Thread(() -> {
//            try {
//                Thread.sleep(5000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            for (int i = 0; i < 5; i++) {
//                int finalI = i;
//                poolExecutor.execute(() -> {
//                    try {
//                        Thread.sleep(100L);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(Thread.currentThread().getName() + "正在执行");
//                    System.out.println();
//                });
//                try {
//                    Thread.sleep(1000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }).start();
//
//        new Thread(() -> {
//            try {
//                Thread.sleep(30000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            poolExecutor.execute(() -> {
//                try {
//                    Thread.sleep(1000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName() + "正在执行=======");
//                System.out.println();
//            });
//        }).start();
//
//
//
//
//














//
//        for (;;){
//            Thread.sleep(1000L);//scheduleAtFixedRate
//            System.out.println("getCorePoolSize===" + poolExecutor.getCorePoolSize());
//            System.out.println("getUnCorePoolSize===" + (poolExecutor.getPoolSize() - poolExecutor.getCorePoolSize()));
//            System.out.println("getPoolSize===" + poolExecutor.getPoolSize());
//            System.out.println("getLargestPoolSize===" + poolExecutor.getLargestPoolSize());
//            System.out.println("getMaximumPoolSize===" + poolExecutor.getMaximumPoolSize());
//            System.out.println("getActiveCount===" + poolExecutor.getActiveCount());
//            System.out.println("------------------------");
//        }


    }
}

class Test24 {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingDeque<Object> objects = new LinkedBlockingDeque<>();
        objects.poll(10000000000L, TimeUnit.NANOSECONDS);
        System.out.println("111");


        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());

    }
}
class Test25 {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
//        CountDownLatch countDownLatch = new CountDownLatch(3);
//        ArrayList<Integer> integers = new ArrayList<>(3);
//        new Thread(() -> {
//            integers.add(1);
//            countDownLatch.countDown();
//        }).start();
//
//        new Thread(() -> {
//            integers.add(3);
//            countDownLatch.countDown();
//        }).start();
//
//        new Thread(() -> {
//            integers.add(2);
//            countDownLatch.countDown();
//        }).start();
//        countDownLatch.await();
//        System.out.println(integers.toString());

        ArrayList<Integer> integers = new ArrayList<>(3);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
        new Thread(() -> {
            integers.add(1);
            try {
                System.out.println("开始加载1");
                cyclicBarrier.await();
                System.out.println("加载完成1" + integers.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            integers.add(2);
            try {
                System.out.println("开始加载2");
                cyclicBarrier.await();
                System.out.println("加载完成2" + integers.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            integers.add(3);
            try {
                System.out.println("开始加载3");
                cyclicBarrier.await();
                System.out.println("加载完成3" + integers.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();


    }
}