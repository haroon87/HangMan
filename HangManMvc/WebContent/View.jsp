<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.HangManPuzzle"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
 HangManPuzzle puzzle = (HangManPuzzle)session.getAttribute("Puzzle");
%>
<pre>
<%
for(int i =0;i<puzzle.getPuzzleBlocks().length;i++)
out.println(puzzle.getPuzzleBlocks()[i]);
%></pre><br><br>
<%=session.getAttribute("GameWord") %>
<br>
Enter a letter to guess
<form method = post action= "hangManControllerPath">
<input type="text" name="guess"><br>
<input type="submit" value="Submit">
</form>

</body>
</html>