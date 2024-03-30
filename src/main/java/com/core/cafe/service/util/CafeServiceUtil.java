package com.core.cafe.service.util;

import com.core.cafe.service.beans.CafeUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Set;

public class CafeServiceUtil {


    public static String getUserNameFromSecurityContext(){
        String userName = null;
        CafeUserDetails userDetails =  getUserDetailsFromSecurityContext();
        if(userDetails != null){
           userName =  userDetails.getUsername();
        }
        return userName;
    }


    public static String getUserRoleFromSecurityContext(){
        String userRole = null;
        CafeUserDetails userDetails = getUserDetailsFromSecurityContext();
        if(userDetails != null){

            Set<SimpleGrantedAuthority> authorities = (Set<SimpleGrantedAuthority>) userDetails.getAuthorities();
            if(authorities != null){
                SimpleGrantedAuthority authority = authorities.stream().findFirst().orElse(null);
                if(authority != null){
                   userRole =  authority.getAuthority().split("_")[1];
                }
            }
        }

        return userRole;
    }

    public static CafeUserDetails getUserDetailsFromSecurityContext(){
        CafeUserDetails userDetails = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
           userDetails =  (CafeUserDetails) authentication.getPrincipal();
        }

        return userDetails;
    }
}
