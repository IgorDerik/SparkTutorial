package com.sparkapp;

import org.hamcrest.core.IsNull;
import org.junit.Test;
//import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.StringReader;
import java.util.List;

public class CSVHelperTest {

    @Test
    public void test_no_quote() throws Exception {

        String line = "10,AU,Australia";
        StringReader readRow = new StringReader(line);
        List<String> result = CSVHelper.parseLine(readRow);

        assertThat(result, IsNull.notNullValue());
        assertThat(result.size(), is(3));
        assertThat(result.get(0), is("10"));
        assertThat(result.get(1), is("AU"));
        assertThat(result.get(2), is("Australia"));

    }

    @Test
    public void test_double_quotes() throws Exception {

        String line = "\"10\",\"AU\",\"Australia\"";
        StringReader readRow = new StringReader(line);
        List<String> result = CSVHelper.parseLine(readRow);

        assertThat(result, IsNull.notNullValue());
        assertThat(result.size(), is(3));
        assertThat(result.get(0), is("10"));
        assertThat(result.get(1), is("AU"));
        assertThat(result.get(2), is("Australia"));

    }

    @Test
    public void test_double_quotes_but_double_quotes_in_column() throws Exception {

        String line = "\"10\",\"AU\",\"Aus\"\"tralia\"";
        StringReader readRow = new StringReader(line);
        List<String> result = CSVHelper.parseLine(readRow);

        assertThat(result, IsNull.notNullValue());
        assertThat(result.size(), is(3));
        assertThat(result.get(0), is("10"));
        assertThat(result.get(1), is("AU"));
        assertThat(result.get(2), is("Aus\"tralia"));

    }

    @Test
    public void test_double_quotes_but_comma_in_column() throws Exception {

        String line = "\"10\",\"AU\",\"Aus,tralia\"";
        StringReader readRow = new StringReader(line);
        List<String> result = CSVHelper.parseLine(readRow);

        assertThat(result, IsNull.notNullValue());
        assertThat(result.size(), is(3));
        assertThat(result.get(0), is("10"));
        assertThat(result.get(1), is("AU"));
        assertThat(result.get(2), is("Aus,tralia"));

    }

    @Test
    public void test_no_quote_but_double_quotes_in_column() throws Exception {

        String line = "10,AU,Aus\"\"tralia";
        StringReader readRow = new StringReader(line);
        List<String> result = CSVHelper.parseLine(readRow);

        assertThat(result, IsNull.notNullValue());
        assertThat(result.size(), is(3));
        assertThat(result.get(0), is("10"));
        assertThat(result.get(1), is("AU"));
        assertThat(result.get(2), is("Australia"));

    }

}
