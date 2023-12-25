package ch.heigvd.components;

public class Menu {
    public static String doGet(){
        return """
                    <header class="header-section">
                        <div class="container">
                            <div class="logo">
                                <a href="./index.html">
                                    <img src="img/logo.png" alt="">
                                </a>
                            </div>
                            <div class="nav-menu">
                                <nav class="mainmenu mobile-menu">
                                    <ul>
                                        <li class="active"><a href="./index.html">Home</a></li>
                                        <li><a href="./about-us.html">About</a></li>
                                        <li><a href="./classes.html">Classes</a></li>
                                        <li><a href="./blog.html">Blog</a></li>
                                        <li><a href="./gallery.html">Gallery</a></li>
                                        <li><a href="./contact.html">Contacts</a></li>
                                    </ul>
                                </nav>
                                <a href="#" class="primary-btn signup-btn">Sign Up Today</a>
                            </div>
                            <div id="mobile-menu-wrap"></div>
                        </div>
                    </header>
                """;
    }
}
