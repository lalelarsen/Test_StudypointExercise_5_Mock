/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.Date;
import testex.JokeException;

/**
 *
 * @author Frederik
 */
public interface IDateFormatter {
    public String getFormattedDate(String timeZone, Date time)throws JokeException;
    
    
}
