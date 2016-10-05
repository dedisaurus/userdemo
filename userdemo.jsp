<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Spring MVC</title>
    </head>
    <body>
        <div class="contain-to-grid sticky">
            <nav class="top-bar" data-topbar role="navigation">
                <ul class="title-area">
                    <li class="name"><h1>
                            <a href="<c:url value="/userdemo"/>">User Demo</a>
                        </h1></li>
                    <li class="toggle-topbar menu-icon"><a href="#"><span>Menu</span></a></li>
                </ul>
                <section class="top-bar-section">
                    <ul class="right">
                        <li class="has-dropdown"><a href="<c:url value="#"/>">User List</a>
                            <ul class="dropdown">
                                <li><a href="<c:url value="/userdemo/findAll"/>">Find All User</a></li>
                                <li><a href="<c:url value="/userdemo/insert"/>">Input User</a></li>
                            </ul>
                        </li>                          
                    </ul>
                </section>
            </nav>
        </div>

        <div class="row">
            <div class="large-12 column">
                <h3>developed by Shivraj Patil</h3>
            </div>
        </div>
        <script>
            $(document).foundation();
        </script>
    </body>
</html>