package UI;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import dbTest.QuizDAO;
import dbTest.QuizVO;

public class test {
	private static String str;
	
	private static QuizDAO qdao;
    private static List<QuizVO> list;
    private static int i=0;
public static void main(String[] args) {
	

	  qdao = new QuizDAO();
      list = new ArrayList<QuizVO>();
      list = qdao.selectQuiz();
	
      
    	  str = list.get(0).getQuizNum()+"&"+list.get(0).getQuizQuestion()+"&"+ 
	              list.get(0).getQuizCho()+"&"+list.get(0).getAnswer();
	         list.remove(0);
	         System.out.println(str);
	

		}
	}


