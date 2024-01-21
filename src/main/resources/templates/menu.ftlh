<header class="header-section">
    <style>
        .admin-btn {
            background-color: #d9534f;
            color: white;
            transition: background-color 0.2s;
            margin-left: 10px;

        }
    </style>
    <div class="container">
        <div class="logo">
            <a href="./home">
                <img src="img/logo.png" alt="">
            </a>
        </div>
        <div class="nav-menu">
            <nav class="mainmenu mobile-menu">
                <ul>
                    <li class="active"><a href="./home">Home</a></li>
                    <li><a href="./ClassesOverview">Classes</a></li>
                    <#if isConnected>
                        <!-- Lien Logout -->
                        <li><a href="/logout" class="highlighted-link">Logout</a></li>
                    </#if>
                </ul>
            </nav>
            <#if isConnected>
                <a href="./myaccount" class="primary-btn signup-btn">Account</a>
                <a href="./myaccountadmin" class="primary-btn admin-btn">Admin panel</a>
            <#else>
                <a href="./register" class="primary-btn signup-btn">Registration</a>
            </#if>
        </div>
        <div id="mobile-menu-wrap"></div>
    </div>
</header>