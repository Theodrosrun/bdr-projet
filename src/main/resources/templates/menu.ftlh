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
                    <li class="active"><a href="./home">Home</a></li>
                    <li><a href="./about-us.html">About</a></li>
                    <li><a href="./classes.html">Classes</a></li>
                    <li><a href="./blog.html">Blog</a></li>
                    <li><a href="./gallery.html">Gallery</a></li>
                    <li><a href="./contact.html">Contacts</a></li>
                    <#if isConnected>
                        <!-- Lien Logout -->
                        <li><a href="/logout" class="highlighted-link">Logout</a></li>
                    </#if>
                </ul>
            </nav>
            <#if isConnected>
                <a href="./myaccount" class="primary-btn signup-btn">MyAccount</a>
            <#else>
                <a href="./register" class="primary-btn signup-btn">Registration</a>
            </#if>
        </div>
        <div id="mobile-menu-wrap"></div>
    </div>
</header>