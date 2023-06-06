package il.ac.telhai.ds.stack;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class EvaluatePostfix {

	private static StreamTokenizer tokenizer = new StreamTokenizer(new InputStreamReader(System.in));
	private static Stack<Double> myStack = new DLinkedListStack<Double>();
	
	public static void main(String[] args) throws IOException {
		tokenizer.slashSlashComments(false);
		tokenizer.ordinaryChar('/');

		while(tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
			if(tokenizer.ttype == StreamTokenizer.TT_WORD) {
				if(tokenizer.sval.equals("quit")) {
					break;
				}
				else {
					System.err.println(tokenizer);
					System.err.println(myStack);
					System.exit(1);
				}
			}
			else if(tokenizer.ttype == StreamTokenizer.TT_NUMBER) {
				myStack.push(tokenizer.nval);
			}
			else {
				char op = (char) tokenizer.ttype;
				Double y = myStack.pop();
				Double x = myStack.pop();
				if(x == null || y == null) {
					System.err.println(tokenizer);
					System.err.println(myStack);
					System.exit(1);
				}
				switch (op) {
					case '+' -> myStack.push(x + y);
					case '-' -> myStack.push(x - y);
					case '*' -> myStack.push(x * y);
					case '/' -> myStack.push(x / y);
				}
			}
		}
		if(myStack.isEmpty()) {
			System.err.println(tokenizer);
			System.err.println(myStack);
			System.exit(1);
		}
		else {
			double x = myStack.pop();
			if(!myStack.isEmpty()) {
				System.err.println(tokenizer);
				System.err.println(myStack);
				System.exit(1);
			}
			System.out.println(x);
		}
	}

}
