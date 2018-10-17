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
    public void testNoQuote() throws Exception {

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
    public void testDoubleQuotes() throws Exception {

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
    public void testDoubleQuotesButDoubleQuotesInColumn() throws Exception {

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
    public void testDoubleQuotesButCommaInColumn() throws Exception {

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
    public void testNoQuoteButDoubleQuotesInColumn() throws Exception {

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
