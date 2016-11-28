/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.json.exception ;

/**
 *
 * @author fgyara
 */
public class JsonException extends RuntimeException {
    
    public JsonException(String mesg, Exception e) {
        super(mesg, e);
    }
    
}
