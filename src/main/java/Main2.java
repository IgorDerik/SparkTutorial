import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class Main2 {

    public static void main(String[] args) {

        SparkConf conf = new SparkConf().setMaster("local").setAppName("Calc dupes");
        JavaSparkContext context = new JavaSparkContext(conf);

        JavaRDD<String> stringRDD = context.textFile("src/main/resources/Book1.csv");

//        stringRDD.collect().forEach(System.out::println);

        JavaRDD<Book1> book1JavaRDD = stringRDD.map(Book1::createBook);

        JavaPairRDD<String, Integer> pairRDD = book1JavaRDD
                .mapToPair(book1 -> new Tuple2<>(book1.getDate(), book1.getDupes()))
                .reduceByKey((a, b) -> a + b);

        for(Tuple2<String, Integer> element : pairRDD.collect()){
            System.out.println("("+element._1+", "+element._2+")");
        }

    }

}
