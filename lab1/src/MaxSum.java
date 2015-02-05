
public final class MaxSum {
	// globala, lagrar start och slut på sekvensen
	public static int seqStart = 0;
	public static int seqEnd = -1;

	/**
	* contiguous subsequence sum algorithm.
	* seqStart and seqEnd represent the actual best sequence.
	* Version 1
	*/
	public static int maxSubSum1( int[] a ) {
		int maxSum = 0;
		for( int i = 0; i < a.length; i++ )
			for( int j = i; j < a.length; j++ ) {
				int thisSum = 0;
				for( int k = i; k <= j; k++ ) {
					thisSum += a[k];
				}
				if( thisSum > maxSum ) {
					maxSum   = thisSum;
					seqStart = i;
					seqEnd   = j;
				}
			}
		return maxSum;
	}

	// Version 2
	public static int maxSubSum2( int [ ] a ) {
		int maxSum = 0;
		for( int i = 0; i < a.length; i++ ) {
			int thisSum = 0;
			for( int j = i; j < a.length; j++ ) {
				thisSum += a[ j ];
				if( thisSum > maxSum ) {
					maxSum = thisSum;
					seqStart = i;
					seqEnd   = j;
				}
			}
		}
		return maxSum;
	}

	// 1 + 1 + n * (5 + 10) --> 4n + O(n)
	// Version 3
	public static int maxSubSum3( int[] a ) {
		int maxSum  = 0;								// 1
		int thisSum = 0;								// 1
		for( int i = 0, j = 0; 							// 1 + 1
			 j < a.length; j++ ) {	 					// 1 + 2 varje tur
			thisSum += a[ j ];							// 1 + 1 varje tur
			if( thisSum > maxSum ) {					// 1 varje tur
				maxSum = thisSum;						// 1 varje tur
				seqStart = i;							// 1 varje tur
				seqEnd   = j;							// 1 varje tur
			}
			else if( thisSum < 0 ) {					// 1 varje tur
				i = j + 1;								// 1 + 1 varje tur
				thisSum = 0;							// 1 varje tur
			}
		}
		return maxSum;
	}
}