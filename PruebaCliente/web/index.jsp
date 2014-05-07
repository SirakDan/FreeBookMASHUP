<%-- 
    Document   : index
    Created on : 04-may-2014, 19:23:32
    Author     : Daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP Page</title>
    </head>
    <body>
        <%-- start web service invocation --%><hr/>
    <%
    try {
	main.BookInfo_Service service = new main.BookInfo_Service();
	main.BookInfo port = service.getBookInfoPort();
	 // TODO initialize WS operation arguments here
	int id = 2;
	// TODO process result here
	main.Book result = port.getBook(id);
	out.println("Result = "+result.getTitle().toString());
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%><hr/>

    <div> <% Boolean variable = (Boolean)session.getAttribute("Resultado"); %></div>
   
    <hr/> <div><%= variable %> </div>
    
    </body>
</html>
