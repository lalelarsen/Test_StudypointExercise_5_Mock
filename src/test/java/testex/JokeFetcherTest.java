/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testex;

import interfaces.IDateFormatter;
import interfaces.IFetcherFactory;
import interfaces.IJokeFetcher;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import org.junit.Before;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import testex.JFfolder.ChuckNorris;
import testex.JFfolder.EduJoke;
import testex.JFfolder.Moma;
import testex.JFfolder.Tambal;

/**
 *
 * @author Frederik
 */
@RunWith(MockitoJUnitRunner.class)
public class JokeFetcherTest {
    
    private JokeFetcher jokeFetcher;
    
    @Mock
    IDateFormatter DF;
    @Mock
    IFetcherFactory FF;

    @Mock
    EduJoke edujoke;
    @Mock
    ChuckNorris chucknorris;
    @Mock
    Moma moma;
    @Mock
    Tambal tambal;

    @Before
    public void setup() throws JokeException {
        
        String typesToFetchString = "eduprog,chucknorris,moma,tambal";
        List<IJokeFetcher> result = Arrays.asList(edujoke, chucknorris, moma, tambal);
        List<String> availableTypes = Arrays.asList("eduprog","chucknorris","moma","tambal");

        
        when(FF.getAvailableTypes()).thenReturn(availableTypes);
        when(FF.getJokeFetchers(eq(typesToFetchString))).thenReturn(result);
        
        jokeFetcher = new JokeFetcher(DF, FF);
    }

    public JokeFetcherTest() {
    }

    //jeg kan ikke få den her til at fungere.....
    @Test
    public void testGetAvailableTypes() {
        JokeFetcher JF = new JokeFetcher(DF, FF);
        List<String> result = JF.getAvailableTypes();
        System.out.println(result.size());
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
        assertThat(result, hasItems("eduprog", "chucknorris", "moma", "tambal"));
    }

    @Test
    public void testIsStringValid() {
        JokeFetcher JF = new JokeFetcher(DF, FF);

        //true test
        boolean result = JF.isStringValid("moma");
        assertThat(result, is(true));

        //false test
        result = JF.isStringValid("momma");
        assertThat(result, is(false));
    }

    @Test
    public void testGetJokes() throws Exception {
        JokeFetcher JF = new JokeFetcher(DF, FF);

        String DateString = "1 Jan 2017 01:01 PM";
        String timeZoneString = "Europe/Copenhagen";

        given(DF.getFormattedDate(eq(timeZoneString), anyObject())).willReturn(DateString);
        Jokes jokes = JF.getJokes("moma", timeZoneString);
        assertThat(jokes.getTimeZoneString(), is(DateString));

        verify(DF, times(1)).getFormattedDate(eq(timeZoneString), anyObject());
    }
    
    @Test
    public void eduTest() throws JokeException {
        String jokeString = "Joke1";
        String referenceString = "Reference1";
        
        given(edujoke.getJoke()).willReturn(new Joke(jokeString, referenceString));
        Jokes Result = jokeFetcher.getJokes("eduprog,chucknorris,moma,tambal","Europe/Copenhagen");

        assertThat(Result.getJokes().get(0).getJoke(), is(jokeString));
        assertThat(Result.getJokes().get(0).getReference(), is(referenceString));
    }
    
    @Test
    public void chuckNorrisTest() throws JokeException {
        String jokeString = "Joke2";
        String referenceString = "Reference2";
        
        given(chucknorris.getJoke()).willReturn(new Joke(jokeString, referenceString));
        Jokes Result = jokeFetcher.getJokes("eduprog,chucknorris,moma,tambal","Europe/Copenhagen");

        assertThat(Result.getJokes().get(1).getJoke(), is(jokeString));
        assertThat(Result.getJokes().get(1).getReference(), is(referenceString));
    }

    @Test
    public void momaTest() throws JokeException {
        String jokeString = "Joke3";
        String referenceString = "Reference3";
        
        given(moma.getJoke()).willReturn(new Joke(jokeString, referenceString));
        Jokes Result = jokeFetcher.getJokes("eduprog,chucknorris,moma,tambal","Europe/Copenhagen");

        assertThat(Result.getJokes().get(2).getJoke(), is(jokeString));
        assertThat(Result.getJokes().get(2).getReference(), is(referenceString));
    }
    
    @Test
    public void tambalTest() throws JokeException {
        String jokeString = "Joke4";
        String referenceString = "Reference4";
        
        given(tambal.getJoke()).willReturn(new Joke(jokeString, referenceString));
        Jokes Result = jokeFetcher.getJokes("eduprog,chucknorris,moma,tambal","Europe/Copenhagen");

        assertThat(Result.getJokes().get(3).getJoke(), is(jokeString));
        assertThat(Result.getJokes().get(3).getReference(), is(referenceString));
    }
    
    /**
     * Test of main method, of class JokeFetcher.
     */
}
