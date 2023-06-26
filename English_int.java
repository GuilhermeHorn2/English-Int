package misc;

import java.util.ArrayList;
import java.util.HashMap;

public class English_int {
	
	//english int from 0 to (10e+12)-1,if you want more just apply the same idea.
	
	
	private HashMap<Integer,String> unit;//0 to 9
	private HashMap<Integer,String> ten;//10-19,20,30,40,...,90
	
	
	public English_int(){
		unit = new HashMap<>();
		ten = new HashMap<>();
		unit.put(0, "zero");
		unit.put(1, "one");
		unit.put(2, "two");
		unit.put(3, "three");
		unit.put(4, "four");
		unit.put(5, "five");
		unit.put(6, "six");
		unit.put(7, "seven");
		unit.put(8, "eight");
		unit.put(9, "nine");
		
		ten.put(10, "ten");
		ten.put(11, "eleven");
		ten.put(12, "twelve");
		ten.put(13, "thirteen");
		ten.put(14, "fourteen");
		ten.put(15, "fifteen");
		ten.put(16, "sixteen");
		ten.put(17, "seventeen");
		ten.put(18, "eighteen");
		ten.put(19, "nineteen");
		
		ten.put(20, "twenty");
		ten.put(30, "thrirty");
		ten.put(40, "fourty");
		ten.put(50, "fifty");
		ten.put(60, "sixty");
		ten.put(70, "seventy");
		ten.put(80, "eighty");
		ten.put(90, "ninety");
			
	}
	
	//hundred:2 ;idx = 0
	//thousand:3-5 ;idx = 1
	//million:6-9 ;idx = 2
	//billion:10-14 ;idx = 3
	
	public String get_word_hundreds(int n){
		StringBuilder build = new StringBuilder();
		int c = 0;
		int except = 0;
		while(true){
			
			int x = n % 10;
			if(c == 0) {
				except += x;
				build.insert(0, unit.get(x));
			}
			if(c == 1) {
				except += x*10;
				if(except < 20) {
					build = new StringBuilder();
					build.insert(0, ten.get(except));
				}
				else {
					build.insert(0, ten.get(x*10));
				}
			}
			if(c == 2) {
				build.insert(0, "hundred");
				build.insert(0," ");
				build.insert(0,unit.get(x));
			}
			n /= 10;
			c++;
			if(n < 1) {
				break;
			}
			build.insert(0," ");
		}
		return build.toString();
	}
	
	public String get_word_thousands(int n){
		
		//1000 - 999999
		StringBuilder build = new StringBuilder();
		int second = 0;
		int c = 0;
		while(c < 3){
			second += (n%10)*Math.pow(10, c);
			n /= 10;
			c++;
		}
		build.append(this.get_word_hundreds(n));
		build.append(" thousand ");
		build.append(this.get_word_hundreds(second));
		return build.toString();
		
		
	}
	
	public String get_word_million(int n){
		
		//last is 6
		StringBuilder build = new StringBuilder();
		int second = 0;
		int c = 0;
		while(c < 6){
			second += (n%10)*Math.pow(10, c);
			n /= 10;
			c++;
		}
		build.append(this.get_word_thousands(n));
		build.append(" million ");
		build.append(this.get_word_thousands(second));
		return build.toString();
		
		
	}
	
	public String get_word_billion(int n){
		
		//last is 10
		StringBuilder build = new StringBuilder();
		int second = 0;
		int c = 0;
		while(c < 10){
			second += (n%10)*Math.pow(10, c);
			n /= 10;
			c++;
		}
		build.append(this.get_word_million(n));
		build.append(" billion ");
		build.append(this.get_word_million(second));
		return build.toString();	
		
	}
	
	public String get_word(int n){
		if(n < 1000) {
			return this.get_word_hundreds(n);
		}
		if(n < 1_000_000) {
			return this.get_word_thousands(n);
		}
		if(n < 1_000_000_000){
			return this.get_word_million(n);
		}
		if(n < 1_000_000_000 *1000) {
			return this.get_word_billion(n);
		}
		return "TO BIG.";
	}
	
}
