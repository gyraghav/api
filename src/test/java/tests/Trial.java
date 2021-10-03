package tests;

public class Trial {

	public static void main(String[] args) {
		int a[]= {12,23,122,2,-4,-10};
		int res=a[0];
		for(int i=0;i<a.length;i++) {
			res=Math.min(res, a[i]);
		}System.out.println(res);
	}
}
