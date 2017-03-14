/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testex.JFfolder;

import interfaces.IFetcherFactory;
import interfaces.IJokeFetcher;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Frederik
 */
public class FetcherFactory implements IFetcherFactory{

    private IJokeFetcher edujoke;
    private IJokeFetcher chucknorris;
    private IJokeFetcher moma;
    private IJokeFetcher tambal;
    
    private final List<String> availableTypes
            = Arrays.asList("EduJoke", "ChuckNorris", "Moma", "Tambal");

    public FetcherFactory(IJokeFetcher eduJoke, IJokeFetcher chuckJoke, IJokeFetcher momaJoke, IJokeFetcher tambalJoke){
        edujoke = eduJoke;
        chucknorris = chuckJoke;
        moma = momaJoke;
        tambal = tambalJoke;
    }
    
    @Override
    public List<String> getAvailableTypes() {
        return availableTypes;
    }

    @Override
    public List<IJokeFetcher> getJokeFetchers(String jokesToFetch) {
        List<IJokeFetcher> fetchers = new ArrayList();
        List<String> list = Arrays.asList(jokesToFetch.split(","));
        
        if(list.contains("eduprog")){
            fetchers.add(edujoke);
        }
        if(list.contains("chucknorris")){
            fetchers.add(chucknorris);
        }
        if(list.contains("moma")){
            fetchers.add(moma);
        }
        if(list.contains("tambal")){
            fetchers.add(tambal);
        }
        
        System.out.println(fetchers.size());
        return fetchers;
    }
}
