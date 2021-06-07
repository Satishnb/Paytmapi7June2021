package ResponseValidation;

import java.util.HashSet;

public class ResponseData {
	
	public static void validateResponse(HashSet<String> mids, String expectedmId) {
		
		// we used contains method of hashset
		if(mids.contains(expectedmId)) {
			System.out.println("Able to find expected merchant id: "+ expectedmId);

		}
		else
			System.out.println("Not able to find the expected mid:" + expectedmId);

		
	}

}
