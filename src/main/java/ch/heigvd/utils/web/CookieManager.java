package ch.heigvd.utils.web;

import ch.heigvd.utils.structure.Account;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/***
 * Pour l'instant, on vérifie juste que le nom d'utilisateur et le mot de passe est différent de null
 */
public class CookieManager {

    /***
     * Fonction qui sert à savoir si l'utilisateur possède un cookie ou non (la différence est || avec isLogged)
     *
     * @param req l'objet HttpServletRequest représentant la requête HTTP actuelle
     * @return si l'utilisateur possède un cookie ou non
     */
    public static boolean mustLogin(HttpServletRequest req) {
        Cookie usernameCookie =  CookieManager.getCookie(req, "username");
        Cookie passwordCookie = CookieManager.getCookie(req, "password");
        return usernameCookie == null || passwordCookie == null;
    }

    /***
     * Fonction qui sert à savoir si l'utilisateur possède un cookie ou non
     *
     * @param req l'objet HttpServletRequest représentant la requête HTTP actuelle
     * @return si l'utilisateur possède un cookie ou non
     */
    public static boolean isLogged(HttpServletRequest req) {
        Cookie usernameCookie =  CookieManager.getCookie(req, "username");
        Cookie passwordCookie = CookieManager.getCookie(req, "password");
        return usernameCookie != null && passwordCookie != null;
    }

    /***
     * Définit un cookie avec le nom, la valeur et la durée de vie spécifiés, puis l'ajoute à la réponse HTTP.
     *
     * @param resp    l'objet HttpServletResponse représentant la réponse HTTP actuelle
     * @param req     l'objet HttpServletRequest représentant la requête HTTP actuelle
     * @param name    le nom du cookie à définir
     * @param value   la valeur du cookie à définir
     * @param maxAge  la durée de vie maximale du cookie en secondes
     */
    public static void setCookie(HttpServletResponse resp, HttpServletRequest req, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setDomain(req.getServerName());
        cookie.setMaxAge(600);
        cookie.setHttpOnly(true);
        resp.addCookie(cookie);
    }

    /***
     * Réutilisation de la fonction ci-dessus
     *
     * @param resp    l'objet HttpServletResponse représentant la réponse HTTP actuelle
     * @param req     l'objet HttpServletRequest représentant la requête HTTP actuelle
     * @param name    le nom du cookie à définir
     * @param value   la valeur du cookie à définir
     */
    public static void setCookie(HttpServletResponse resp, HttpServletRequest req, String name, String value) {
        setCookie(resp, req, name, value, 600);
    }

    /***
     * Supprime un cookie en définissant un cookie avec le nom spécifié et une valeur vide puis ajuste la durée de vie à 0
     *
     * @param resp  l'objet HttpServletResponse représentant la réponse HTTP actuelle
     * @param req   l'objet HttpServletRequest représentant la requête HTTP actuelle
     * @param name  le nom du cookie à supprimer
     */
    public static void deleteCookie(HttpServletResponse resp, HttpServletRequest req, String name) {
        Cookie cookie = new Cookie(name, "");
        cookie.setPath("/");
        cookie.setDomain(req.getServerName());
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        resp.addCookie(cookie);
    }

    /**
     * Fonction retournant le cookie
     *
     * @param req l'objet HttpServletRequest représentant la requête HTTP actuelle
     * @param name le nom du cookie à rechercher
     * @return le cookie correspondant au nom spécifié ou null s'il n'est pas trouvé
     */
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
