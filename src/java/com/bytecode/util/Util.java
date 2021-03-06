/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.util;

/**
 *
 * @author Ahmed
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
 
 
public class Util {
 
      public static HttpSession getSession() {
        return (HttpSession)
          FacesContext.
          getCurrentInstance().
          getExternalContext().
          getSession(false);
      }
       
      public static HttpServletRequest getRequest() {
       return (HttpServletRequest) FacesContext.
          getCurrentInstance().
          getExternalContext().getRequest();
      }
 
      public static String getUserName()
      {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return  session.getAttribute("username").toString();
      }
       
      public static String getUserId()
      {
        HttpSession session = getSession();
        if ( session != null )
            return (String) session.getAttribute("userid");
        else
            return null;
      }
      
      
       public static String hash(String password) throws Exception {
        try {
            MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
            byte[] passwordBytes = password.getBytes();
            byte[] digest = sha512.digest(passwordBytes);
            StringBuilder stringBuilder = new StringBuilder();
            String s;
            for (byte b : digest) {
                s = Integer.toHexString((int) b & 0xff).toUpperCase();
                if (s.length() == 1) {
                    stringBuilder.append('0');
                }
                stringBuilder.append(s);
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException ex) {
            
            Log.l.errorLog.error(ex.getMessage());
            throw new Exception("NoSuchAlgorithmException");
        }
    }
       
       
       
   
       public static String employPasswordPolicy(String newPW) {
        Pattern sp = Pattern.compile(".*[~!@#$%\\^&*()\\-_=+\\|\\[{\\]};:'\",<.>/?].*");//, Pattern.CASE_INSENSITIVE);//Special character
        Pattern number = Pattern.compile(".*[0-9].*");//Number
        Pattern sch = Pattern.compile(".*[a-z].*");// lower case letters
        Pattern cch = Pattern.compile(".*[A-Z].*");// upper case letters
        Matcher m1 = sp.matcher(newPW);
        Matcher m2 = number.matcher(newPW);
        Matcher m3 = sch.matcher(newPW);
        Matcher m4 = cch.matcher(newPW);
        if (newPW != null && newPW.length() > 7) {//imposing password min lenght to 8
            if (m1.find()) {
                if (m2.find()) {
                    if (m3.find()) {
                        if (m4.find()) {
                            return "0";
                        } else {
                            return "Password must contain at least one upper case letter";
                        }
                    } else {
                        return "Password must contain at least one lower case letter";
                    }
                } else {
                    return "Password must contain at least one number between 0-9";
                }
            } else {
                return "Password must contain at least one special character";
            }
        } else {
            return "Password length must be minimum of eight(8)";
        }
    }
       
       
       
       public static void main(String argd[]){
          try {
              System.out.println(new Util().hash("lawale"));
          } catch (Exception ex) {
              Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
          }
       }
       
      
}
