package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.HangManPuzzle;
import model.WordModel;

/**
 * Servlet implementation class HangManController
 */
/*@WebServlet("/hangManControllerPath")*/
public class HangManController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String correctLetter="";
		HangManPuzzle puzzle = new HangManPuzzle();
		WordModel wordModel = new WordModel();
		WordGenerator wordGenerator = new WordGenerator();
		HttpSession session = request.getSession();
		
		wordModel.readFile();
		String words[] = wordModel.getWords();
		
		wordGenerator.generate(words,wordModel.getCount());
		String word = wordGenerator.getWord();
		String gameWord = wordGenerator.getGameWord();
		
		request.setAttribute("GameWord", gameWord);
		request.setAttribute("Puzzle", puzzle);
		
		session.setAttribute("UnknownWord", gameWord);
		session.setAttribute("Puzzle", puzzle);
		session.setAttribute("Word", word);
		session.setAttribute("GameWord", gameWord);
		session.setAttribute("CorrectLetters", correctLetter);
	    System.out.println(word);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("View.jsp");  
		if (dispatcher != null){  
			dispatcher.forward(request, response);  
			}	
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HangManPuzzle puzzle = new HangManPuzzle();
		HttpSession session = request.getSession();
		String guess = request.getParameter("guess");
  	    String OriginalWord =(String) session.getAttribute("Word");
  	    String UnknownWord = (String) session.getAttribute("UnknownWord");
  	    String totalCorrectWords=(String) session.getAttribute("CorrectLetters");
  	    totalCorrectWords=totalCorrectWords+guess;
  	    session.setAttribute("CorrectLetters", totalCorrectWords);
  	    puzzle = (HangManPuzzle) session.getAttribute("Puzzle");
  	    
  	   if(OriginalWord.indexOf(guess.charAt(0))!=-1)
  	   {
  		    OriginalWord=OriginalWord.replaceAll("[^"+totalCorrectWords+" ]","-");
  		    session.setAttribute("GameWord", OriginalWord);
  		    RequestDispatcher dispatcher = request.getRequestDispatcher("View.jsp");  
 		    if (dispatcher != null){  
 			dispatcher.forward(request, response);  
 			}	
  		     		  
  	   }
  	   
  	   else
  	   {
  		   
  	   }
	}

}
