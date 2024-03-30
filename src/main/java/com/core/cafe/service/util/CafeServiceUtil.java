package com.core.cafe.service.util;

import com.core.cafe.service.beans.CafeUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CafeServiceUtil {


    public static String getUserNameFromSecurityContext(){
        String userName = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
           CafeUserDetails principal = (CafeUserDetails) authentication.getPrincipal();
           if(principal != null){
               userName = principal.getUsername();
           }
        }
        return userName;
    }
}
