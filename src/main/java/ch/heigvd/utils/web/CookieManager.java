package ch.heigvd.utils.web;

import ch.heigvd.utils.structure.Account;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieManager {

    public static boolean mustLogin(HttpServletRequest req) {
        Cookie usernameCookie =  CookieManager.getCookie(req, "username");
        Cookie passwordCookie = CookieManager.getCookie(req, "password");
        return usernameCookie == null || passwordCookie == null;
    }

    public static boolean isLogged(HttpServletRequest req) {
        Cookie usernameCookie =  CookieManager.getCookie(req, "username");
        Cookie passwordCookie = CookieManager.getCookie(req, "password");
        return usernameCookie != null && passwordCookie != null;
    }

    public static void setCookie(HttpServletResponse resp, HttpServletRequest req, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setDomain(req.getServerName());
        cookie.setMaxAge(600);
        cookie.setHttpOnly(true);
        resp.addCookie(cookie);
    }

    public static void setCookie(HttpServletResponse resp, HttpServletRequest req, String name, String value) {
        setCookie(resp, req, name, value, 600);
    }

    public static void deleteCookie(HttpServletResponse resp, HttpServletRequest req, String name) {
        Cookie cookie = new Cookie(name, "");
        cookie.setPath("/");
        cookie.setDomain(req.getServerName());
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        resp.addCookie(cookie);
    }

    public static Cookie getCookie(HttpServletRequest req, String name) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie;
            }
        }
        return null;
    }


}
