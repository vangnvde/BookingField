/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dao.ServerFieldDao;
import dao.UserDao;
import java.sql.SQLException;

/**
 *
 * @author Vang Nguyen
 */
public class ValidationInput {

    public static boolean checkInputPhone(String phone, String id, int type) throws SQLException {
        try {
            int number = Integer.parseInt(phone.trim());
            if (phone.length() > 11 || phone.length() < 9) {
                throw new NumberFormatException();
            }
            if (type == 1) {
                if (new UserDao().checkPhoneNumberExit(phone, id)) {
                    throw new NumberFormatException();
                }
            } else {
                if (new ServerFieldDao().checkPhoneNumberExit(phone, id)) {
                    throw new NumberFormatException();
                }
            }

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean checkInputEmail(String email, String id, int type) throws SQLException {

        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if (type == 1) {
            if (new UserDao().checkEmailExit(email, id)) {
                return false;
            }
        } else{
            if (new ServerFieldDao().checkEmailExit(email, id)) {
                return false;
            }
        }

        return email.matches(regex);

    }
    
    public static boolean checkIsEmpty(String input){
        if(input.equals("") && input.trim().equals("")){
            return false;
        }else{
            return true;
        }
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(checkInputEmail("vang@gmail.com", "1", 1));
    }
}
