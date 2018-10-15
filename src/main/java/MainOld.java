import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.SparkConf;
import scala.Tuple2;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MainOld {

    public static void main(String[] args) throws IOException {

//        List<Match> matches = MatchBuilder.getMatches();
        //      matches.forEach(System.out::println);

        SparkConf conf = new SparkConf().setMaster("local").setAppName("Word Cup Matches");
        JavaSparkContext context = new JavaSparkContext(conf);
/*
        JavaRDD<Match> matchRDD = context.parallelize(matches);
        JavaRDD<Match> yokohamaRDD = matchRDD.filter( m -> m.getCity().equals("Yokohama") );
        System.out.println(yokohamaRDD.count());
        JavaRDD<Match> fiftiesRDD = matchRDD.filter( match -> match.getYear()>1949 && match.getYear()<1960 );
        System.out.println(fiftiesRDD.count());

        JavaRDD<Integer> yearsRDD = matchRDD.map(Match::getYear);
        System.out.println(yearsRDD.count());
*/
//        JavaPairRDD<String,Integer> cityAttendance = matchRDD.map();

        /*        while (true) {
            try {
                Thread.sleep(1000);

            } catch (Exception e) {

            }
        }
*/
    }

}