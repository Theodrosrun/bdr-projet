package ch.heigvd.components;

public class Head {
    private final String pageName;
    private static final String TITLE = "My Amazing Fitness";
    private static final String DESCRIPTION = "Become the best version of yourself";
    private static final String CHARSET = "UTF-8";

    public Head(String pageName) {
        this.pageName = pageName;
    }
    public String doGet() {
        return """
                <head>
                    <meta charset="%s">
                    <meta name="description" content="%s">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <meta http-equiv="X-UA-Compatible" content="ie=edge">
                    <title>%s | %s</title>
                                
                    <!-- Google Font -->
                    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900&display=swap"
                        rel="stylesheet">
                                
                    <!-- Css Styles -->
                    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
                    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
                    <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
                    <link rel="stylesheet" href="css/magnific-popup.css" type="text/css">
                    <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
                    <link rel="stylesheet" href="css/style.css" type="text/css">
                </head>
                """.formatted(CHARSET, DESCRIPTION, TITLE, pageName);
    }
}
