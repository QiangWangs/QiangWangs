package com.qiangws.demo.stream;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * @author : QiangWs
 * @version : V1.0.0
 * @className : Stream
 * @packageName : com.qiangws.demo.stream
 * @createTime : 2024/6/17
 * @description :  TODO Java8的 使用的是函数式编程模式，可以被用来对集合或数组进行链状流式的操作。有别于IO流，这里是针对集合操作数据的流
 */
public class Streams {

    public static void main(String[] args) {
//        //单列集合： 集合对象.stream()
//        List<String> list=new ArrayList<>();
//        Stream<String> listStream = list.stream();
//
//        //数组：`Arrays.stream(数组) `或者使用`Stream.of`来创建
//        Integer[] arr={1,2,3,4,5};
//        //创建流 法一
//        Stream<Integer> arrayStream = Arrays.stream(arr);
//        //创建流 法二
//        Stream<Integer> stream= Stream.of(arr);
//
//       //双列集合：转换成单列集合后再创建
//        Map<String,Integer> map = new HashMap<>();
//        map.put("蜡笔小新",19);
//        map.put("黑子",17);
//        map.put("日向翔阳",16);
//        Stream<Map.Entry<String, Integer>> mapStream = map.entrySet().stream();
        //filter();
        //distinct();
        //sorted();
        //limit();
        //skip();
        //flasMap();
        //count();
        //max_min();
        //collect();
        //anyMatch();
        //findAny();
        reduce();
    }

    /**
     * 中间操作  可以对流中的元素进行条件过滤，符合过滤条件的才能继续留在流中。
     * Predicate 接口的泛型参数和集合元素类型是一样的，并且所实现的方法参数也是一样的
     * 集合会把每个元素传给test方法，如果test方法返回true，该元素就会被保留，返回false则过滤掉
     */
    public static void filter(){
        List<String> strings = new ArrayList<>();
        strings.add("abcde");
        strings.add("ac");
        strings.stream().filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length()>2;
            }
        });
        List<String> collect = strings.stream().filter(s -> s.length() > 2).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    static class Author implements Comparator<Author> {
        //id
        private Long id;
        //姓名
        private String name;
        //年龄
        private Integer age;
        //简介
        private String intro;
        //作品
        private List<Book> books;

        @Override
        public int compare(Author o1, Author o2) {
            return o1.getAge()-o2.getAge();
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @EqualsAndHashCode
        public static class Book {
            //id
            private Long id;
            //书名
            private String name;
            //分类
            private String category;
            //评分
            private Integer score;
            //简介
            private String intro;
        }
    }

    public static List<Author> getAuthors() {
        //数据初始化
        Author author = new Author(1L,"蒙多",33,"一个从菜刀中明悟哲理的祖安人",null);
        Author author2 = new Author(2L,"亚拉索",15,"狂风也追逐不上他的思考速度",null);
        Author author3 = new Author(3L,"易",14,"是这个世界在限制他的思维",null);
        Author author4 = new Author(3L,"易",14,"是这个世界在限制他的思维",null);

        //书籍列表
        List<Author.Book> books1 = new ArrayList<>();
        List<Author.Book> books2 = new ArrayList<>();
        List<Author.Book> books3 = new ArrayList<>();

        books1.add(new Author.Book(1L,"刀的两侧是光明与黑暗","哲学,爱情",88,"用一把刀划分了爱恨"));
        books1.add(new Author.Book(2L,"一个人不能死在同一把刀下","个人成长,爱情",99,"讲述如何从失败中明悟真理"));

        books2.add(new Author.Book(3L,"那风吹不到的地方","哲学",85,"带你用思维去领略世界的尽头"));
        books2.add(new Author.Book(3L,"那风吹不到的地方","哲学",85,"带你用思维去领略世界的尽头"));
        books2.add(new Author.Book(4L,"吹或不吹","爱情,个人传记",56,"一个哲学家的恋爱观注定很难把他所在的时代理解"));

        books3.add(new Author.Book(5L,"你的剑就是我的剑","爱情",56,"无法想象一个武者能对他的伴侣这么的宽容"));
        books3.add(new Author.Book(6L,"风与剑","个人传记",100,"两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));
        books3.add(new Author.Book(6L,"风与剑","个人传记",100,"两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));

        author.setBooks(books1);
        author2.setBooks(books2);
        author3.setBooks(books3);
        author4.setBooks(books3);

        List<Author> authorList = new ArrayList<>(Arrays.asList(author,author2,author3,author4));
        return authorList;
    }

    /**
     * 对流中的元素进行计算或转换。
     */
    public void map(){
        List<Author> authors = getAuthors();
        authors.stream().map(new Function<Author, String>() {
            //匿名内部类的方式实现
            @Override
            public String apply(Author author) {
                return author.getName();
            }
        });
        //第一个泛型参数和集合元素类型是一样的，第二个是要转换成的目标类型
        authors.stream().map(data->{
            System.out.println(data.getName());
            return data.getName();
        });
        authors.stream().map(data->data.getName());
    }

    /**
     * 去除流中的重复元素。
     * distinct方法是依赖Object的equals方法来判断是否是相同对象的。所以需要注意重写equals方法。
     */
    public static void distinct(){
        List<Author.Book> authors = new ArrayList();
        authors.add(new Author.Book(6L,"风与剑","个人传记",100,"两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));
        authors.add(new Author.Book(6L,"风与剑","个人传记",100,"两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));
        List<Author.Book> distinct = authors.stream().distinct().collect(Collectors.toList());
        System.out.println(distinct);
//        List<String> list= new ArrayList<>();
//        list.add("a");
//        list.add("b");
//        list.add("c");
//        list.add("d");
//        list.add("e");
//        list.add("f");
//        list.add("a");
//        list.add("b");
//        list.add("c");
//        list.add("d");
//        list.add("e");
//        list.add("f");
//        List<String> distinct = list.stream().distinct().collect(Collectors.toList());
//        System.out.println(distinct);
    }

    /**
     * 可以对流中的元素进行排序。
     *  Comparable 接口
     * 调用空参的sorted()方法，需要流中的元素是实现了Comparable接口，否则会抛出java.lang.ClassCastException异
     */
    public static void sorted(){
        List<Author> authors = getAuthors();
        //对流中的元素按照年龄进行升序排序，并且要求不能有重复的元素。
        authors.stream().distinct().sorted();
        List<Author> sorted = authors.stream().distinct().sorted(new Comparator<Author>(){
            @Override
            public int compare(Author o1, Author o2) {
                return o1.getAge()-o2.getAge();
            }
        }).collect(Collectors.toList());
        authors.stream().distinct().sorted((o1,o2)->{return o1.getAge()-o2.getAge();}).collect(Collectors.toList());
        System.out.println(sorted);
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
//    public static void main(String[] args) {
//        List<Book> list = new ArrayList<>();
//        {
//            list.add(new Book("Core Java", 200));
//            list.add(new Book("Core Java", 300));
//            list.add(new Book("Learning Freemarker", 150));
//            list.add(new Book("Spring MVC", 200));
//            list.add(new Book("Hibernate", 300));
//        }
//        list.stream().filter(distinctByKey(b -> b.getName()))
//                .forEach(b -> System.out.println(b.getName()+ "," + b.getPrice()));
//    }

    /**
     *   可以设置流的最大长度，超出的部分将被抛弃。
     */
    public static void limit(){
        List<Author> authors = getAuthors();
        List<Author> collect = authors.stream().distinct().sorted((o1, o2) -> o2.getAge() - o1.getAge()).limit(1).collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * 跳过流中的前n个元素，返回剩下的元素
     */
    public static void skip(){
        List<Author> authors = getAuthors();
        authors.stream().distinct().sorted(((o1, o2) -> {return o1.getAge()-o2.getAge();})).skip(2).forEach(data->{
            System.out.println(data);
        });
    }

    /**
     * map只能把一个对象转换成另一个对象来作为流中的元素。而flatMap可以把一个对象转换成多个对象作为流中的元素。
     */
    public static void flasMap(){

        List<Author> authors = getAuthors();
//        authors.stream().map(author->author.getBooks()).distinct().forEach(data->{
//            System.out.println(data);
//        });
//        System.out.println("-------------------------------");
        // 要求对重复的元素进行去重。
        authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .forEach(book -> System.out.println(book));
    }

    /**
     * 对流中的元素进行遍历操作，我们通过传入的参数去指定对遍历到的元素进行什么具体操作。
     */
    public static void forEach(){
        List<Author> authors = getAuthors();
        authors.stream()
                .map(author -> author.getName())
                .distinct()
                .forEach(name-> System.out.println(name));
    }

    /**
     * 可以用来获取当前流中元素的个数。
     */
    public static void count(){
        List<Author> authors = getAuthors();
        long count = authors.stream().flatMap(data -> data.getBooks().stream()).count();
        System.out.println(count);
        //去重
        long counts = authors.stream().flatMap(data -> data.getBooks().stream()).distinct().count();
        System.out.println(counts);
    }

    /**
     * 可以用来获取流中的最值，返回Optional
     */
    public static void max_min(){
        List<Author> authors = getAuthors();
        Optional<Integer> max = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book -> book.getScore())
                .max(new Comparator<Integer>() {
                    //比较规则
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1-o2;
                    }
                });

        Optional<Integer> min = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book -> book.getScore())
                .max(new Comparator<Integer>() {
                    //比较规则
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2-o1;
                    }
                });
        System.out.println(max.get());
        System.out.println(min.get()); 
    }

    /**
     * 把当前流转换成一个集合。
     */
    public static void collect(){
        List<Author> authors = getAuthors();
        List<String> list = authors.stream().map(data->data.getName()).collect(Collectors.toList());
        System.out.println(list);
        Set<Author> set = authors.stream().collect(Collectors.toSet());
        System.out.println(set);
        Map<String,List<Author.Book>> map = authors.stream().collect(Collectors.toMap(data->data.getName(), data->data.getBooks()));
        System.out.println(map);
    }

    /**
     * 查找与匹配
     * anyMatch 可以用来判断是否有任意符合匹配条件的元素，结果为boolean类型。
     * allMatch 可以用来判断是否都符合匹配条件，结果为boolean类型。如果都符合结果为true，否则结果为false。
     * noneMatch 可以判断流中的元素是否都不符合匹配条件。如果都不符合结果为true，否则结果为false
     */
    public static void anyMatch(){
        List<Author> authors = getAuthors();
        boolean flag = authors.stream().anyMatch(data -> data.getAge() > 10);
        System.out.println("anyMatch:"+flag);
        flag = authors.stream()
                .allMatch(author -> author.getAge() >= 18);
        System.out.println("allMatch:"+flag);
        flag = authors.stream()
                .noneMatch(author -> author.getAge() > 100);
        System.out.println("noneMatch:"+flag);
    }

    /**
     * findAny 获取流中的任意一个元素。该方法没有办法保证获取的一定是流中的第一个元素。用的较少
     * findFirst 获取流中的第一个元素。
     */
    public static void findAny(){
        List<Author> authors = getAuthors();
        Optional<Author> optionalAuthor = authors.stream()
                .filter(author -> author.getAge()>18)
                .findAny();
        //如果存在
        optionalAuthor.ifPresent(author -> System.out.println(author.getName()));

        Optional<Author> first = authors.stream()
                .sorted((o1, o2) -> o1.getAge() - o2.getAge())
                .findFirst();
        //如果存在
        first.ifPresent(author -> System.out.println(author.getName()));
    }

    /**
     * reduce 归并:  对流中的数据按照你指定的计算方式计算出一个结果。（缩减操作）
     * reduce的作用是把stream中的元素给组合起来，我们可以传入一个初始值，它会按照我们的计算方式依次拿流中的元素和初始化值进行计算，计算结果再和后面的元素计算。
     * 惰性求值（如果没有终结操作，没有中间操作是不会得到执行的）
     * 流是一次性的（一旦一个流对象经过一个终结操作后。这个流就不能再被使用）
     * 不会影响原数据（我们在流中可以多数据做很多处理。但是正常情况下是不会影响原来集合中的元素的。这往往也是我们期望的
     */
    public static void reduce(){
        List<Author> authors = getAuthors();
        Integer sum = authors.stream()
                .distinct()
                .map(author -> author.getAge())
                .reduce(0, new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer result, Integer element) {
                        return result+element;
                    }
                });//.reduce(0, (result, element) -> result + element);
        System.out.println(sum);

        Optional<Integer> minOptional = authors.stream()
                .map(author -> author.getAge())
                .reduce((result, element) -> result > element ? element : result);
        minOptional.ifPresent(age-> System.out.println(age));
    }


//    public static void distinctByProperty() throws JsonProcessingException {
//        // 这里第一种方法我们通过新创建一个只有不同元素列表来实现根据对象某个属性去重
//        ObjectMapper objectMapper = new ObjectMapper();
//        List<Author> authors = getAuthors();
//
//        System.out.print("去重前        :");
//        System.out.println(objectMapper.writeValueAsString(authors));
//        authors = authors.stream().distinct().collect(Collectors.toList());
//        System.out.print("distinct去重后:");
//        System.out.println(objectMapper.writeValueAsString(authors));
//        // 这里我们引入了两个静态方法，以及通过 TreeSet<> 来达到获取不同元素的效果
//        // 1. import static java.util.stream.Collectors.collectingAndThen;
//        // 2. import static java.util.stream.Collectors.toCollection;
//        authors = authors.stream().collect(
//                collectingAndThen(
//                        toCollection(() -> new TreeSet<>(Comparator.comparing(Author::getName))), ArrayList::new)
//        );
//        System.out.print("根据名字去重后 :");
//        System.out.println(objectMapper.writeValueAsString(authors));
//    }

    /**
     * TreeSet部分数据无法去重原因分析
     * @param l1
     * @param l2
     * @return
     */
//    @Override
//    public int compare(List<Integer> l1, List<Integer> l2) {
//        Collections.sort(l1);
//        Collections.sort(l2);
//        if (l1.equals(l2)) {
//            return 0;
//        } else {
//            if (l1.isEmpty())
//                return 1;
//            if (l2.isEmpty())
//                return -1;
//            int i = 0;
//            while (l1.get(i).equals(l2.get(i))) i++;
//            if (l1.get(i) > l2.get(i))
//                return 1;
//            else
//                return -1;
//        }
//    }


}
