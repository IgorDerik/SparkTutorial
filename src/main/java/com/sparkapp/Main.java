package com.sparkapp;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.*;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.sources.In;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;
import scala.Tuple2;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        SparkConf conf = new SparkConf().setMaster("yarn").setAppName("Word Cup Matches");
//        SparkConf conf = new SparkConf().setMaster("local").setAppName("Word Cup Matches");
        JavaSparkContext context = new JavaSparkContext(conf);

//        JavaRDD<String> stringRDD = context.textFile("src/main/resources/WorldCupMatches.csv");
        JavaRDD<String> stringRDD = context.textFile("gs://jarstorage1/WorldCupMatches.csv");
        JavaRDD<Match> matchRDD = stringRDD.map(Match::createMatch);

        //Counting percentage of matches with more than 4 goals...

        long allMatchesCount = matchRDD.count();
        //matchRDD.collect().forEach( match -> System.out.println(match.getCity()) );
        //JavaRDD<Match> fiftiesRDD = matchRDD.filter( match -> match.getYear()>1949 && match.getYear()<1960 );
        //System.out.println(fiftiesRDD.count());
        JavaRDD<Match> moreThan4goalsMatchesRDD = matchRDD.filter(match -> match.getHomeTeamGoals() > 4);
        long moreThan4goalsMatchesCount = moreThan4goalsMatchesRDD.count();
        String message = "Percentage of matches with more than 4 goals: ";
        System.out.println(message + ((double) moreThan4goalsMatchesCount / allMatchesCount) * 100 + "%");

        //Calculate sum of matches by city
        System.out.println("Calculating sum of matches by city...");
        JavaPairRDD<String, Integer> sumOfMatchesByCitiesRDD = matchRDD
                .mapToPair(match -> new Tuple2<>(match.getCity(), 1))
                .reduceByKey((a, b) -> a + b);

        for (Tuple2<String, Integer> element : sumOfMatchesByCitiesRDD.collect()) {
            System.out.println("(" + element._1 + ", " + element._2 + ")");
        }

        //Calculate average goals by city
        System.out.println("Calculating average goals by city...");
        JavaPairRDD<String, Integer> cityGoalsRDD = matchRDD
                .mapToPair(m -> new Tuple2<>(m.getCity(), m.getHomeTeamGoals()));

        JavaPairRDD<String, Tuple2<Integer, Integer>> valueCount = cityGoalsRDD
                .mapValues(value -> new Tuple2<>(value,1));

        JavaPairRDD<String, Tuple2<Integer, Integer>> reducedCount = valueCount
                .reduceByKey((tuple1,tuple2) ->  new Tuple2<>(tuple1._1 + tuple2._1, tuple1._2 + tuple2._2));

        JavaPairRDD<String, Integer> averageGoals = reducedCount.mapValues( v -> v._1/v._2 );

        averageGoals.foreach(data -> System.out.println("City: "+data._1 + "| Average Goals: " + data._2));

        //writing results to disc
        SparkSession spark = SparkSession.builder().getOrCreate();

        JavaRDD<Row> averageGoalsRDD = averageGoals.map(tuple -> RowFactory.create(tuple._1(),tuple._2()));

        StructType structType = new StructType()
                .add("City", DataTypes.StringType)
                .add("Goals", DataTypes.IntegerType);

        Dataset<Row> averGoalsDF = spark.createDataFrame(averageGoalsRDD, structType);

        averGoalsDF.write().json("gs://jarstorage1/averageGoals");

//        averGoalsDF.show(1000);

        //Testing spark sql
        /*
        SparkSession spark = SparkSession.builder().getOrCreate();
        Dataset<Row> matchDF = spark.createDataFrame(matchRDD, Match.class);
        matchDF.createOrReplaceTempView("matches");
        Dataset<Row> sqlDF = spark.sql("SELECT homeTeamGoals FROM matches where homeTeamGoals>5");
        sqlDF.show(100);
        */

        /*
        while (true) {
            Thread.sleep(100000000);
        }
        */

    }

}