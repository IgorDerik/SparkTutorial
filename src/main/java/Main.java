import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.SparkConf;
import scala.Tuple2;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        //List<Match> matches = MatchBuilder.getMatches();
        //      matches.forEach(System.out::println);

        SparkConf conf = new SparkConf().setMaster("local").setAppName("Word Cup Matches");
        JavaSparkContext context = new JavaSparkContext(conf);

        JavaRDD<String> matchJavaRDD = context.textFile("src/main/resources/WorldCupMatches.csv");

        //List<String> matchStrings = matchJavaRDD.collect();
        //matchStrings.forEach( s -> System.out.println( Match.createMatch(s).getMatchID() ) );

        //JavaRDD<Integer> sLenRDD = matchJavaRDD.map(String::length);
        //sLenRDD.collect().forEach(System.out::println);

    }
}