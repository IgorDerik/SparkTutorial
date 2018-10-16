import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.SparkConf;
import scala.Tuple2;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.spark.sql.SparkSession;

public class Main {

    public static void main(String[] args) throws IOException {

        SparkConf conf = new SparkConf().setMaster("local").setAppName("Word Cup Matches");
        JavaSparkContext context = new JavaSparkContext(conf);

        JavaRDD<String> stringRDD = context.textFile("src/main/resources/WorldCupMatches.csv");
        JavaRDD<Match> matchRDD = stringRDD.map(Match::createMatch);
        long allMatchesCount = matchRDD.count();
        //matchRDD.collect().forEach( match -> System.out.println(match.getCity()) );
        //JavaRDD<Match> fiftiesRDD = matchRDD.filter( match -> match.getYear()>1949 && match.getYear()<1960 );
        //System.out.println(fiftiesRDD.count());
        JavaRDD<Match> moreThan4goalsMatchesRDD = matchRDD.filter( match -> match.getHomeTeamGoals()>4 );
        long moreThan4goalsMatchesCount = moreThan4goalsMatchesRDD.count();
        String message = "Percentage of matches with more than 4 goals: ";
        System.out.println( message + ((double) moreThan4goalsMatchesCount/allMatchesCount)*100 + "%");
        //System.out.println(moreThan4goalsMatchesCount);
        //System.out.println(allMatchesCount);
        //moreThan4goalsMatches.collect().forEach(System.out::println);

        //SparkSession spark = SparkSession.builder().getOrCreate();
        //Dataset<Row> matchDF = spark.createDataFrame(matchRDD,Match.class);
        //matchDF.show(1000);
        //matchDF.createOrReplaceTempView("matches");
        //Dataset<Row> sqlDF = spark.sql("SELECT homeTeamGoals FROM matches where homeTeamGoals>5");
        //Dataset<Row> sqlDF = spark.sql("");
        //sqlDF.show(100);

    }
}