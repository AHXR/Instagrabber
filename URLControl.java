/*
 * Instagrabber: Download Instagram Pictures
 * Copyright (C) 2017 AR.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

// Imports.
import java.net.URL;

/*
 * URLControl is a class that was supposed to be initially fused with ImageDownload.java.
 * The purpose of this class is to validate and manage the URL that is inserted by the user.
 * 
 * @author AR
 * @version 1.0
 * 
 * public static String cleanURL( String URL )
 * public static Boolean isValidURL( String URL )
 * public static String getExtension( String path )
 */
public class URLControl {
	
	/*
	 * This function will validate the URL that was inserted by the user.
	 * 
 	 *	Instagram URLs are different depending on the user. For example:
	 * 		https://www.instagram.com/p/BXg0wvfhwMr/?taken-by=bigsean
	 * 		https://www.instagram.com/p/BXg0wvfhwMr/
	 * 		https://www.instagram.com/p/BXg0wvfhwMr
	 * 
	 * The main objective here is to end up with the 3rd link mentioned above.
	 * Meaning "taken-by" and the extra slashes are removed.
	 */
	
	public static String cleanURL( String URL ) {
		
		String
			s_new_url = URL;
		
		/*
		 * Here is where "taken-by" is checked. If it is found in
		 * the URL, it is cut off.
		 */
		
		if( s_new_url.contains( "taken-by" ) ) {
			int
				i_count = 0;
			
			for( int i = 0; i < URL.length( ); i ++ ) {
				
				/*
				 * Counting the amount of slashes just to make sure
				 * we're ending up at the right spot here. There are 
				 * usually 5 slashes before "taken-by" is mentioned.
				 */
				
				if( URL.charAt( i ) == '/' )
					i_count ++;
				
				if( i_count == 5 )  {
					s_new_url = s_new_url.substring( 0, i );
					break;
				}
			}		
		}
		
		/*
		 * If the URL ends with a slash, we're going to get rid of it.
		 * Refer to ImageDownload.java for an explanation behind this.
		 */
		
		else if( s_new_url.endsWith( "/" ) ) {
			s_new_url = s_new_url.substring( 0, s_new_url.length() - 1 ); 
		}
		return s_new_url;
	}
	
	/*
	 * isValidURL is a very simple function that will check if the URL
	 * is good to be downloaded.
	 */
	
	public static Boolean isValidURL( String URL ) {
		try {
			
			/*
			 * Usually when a URL isn't valid, an exception will be thrown from java.net.URL.
			 */
			URL url_check = new URL( URL );
			url_check.toURI( );
			
			// DEBUG: system.out.print( url_check.getHost() );
			
			/*
			 * Now we're going to check if the URL is from instagram.com. In the future I might
			 * add other websites. But for now, it's just instagram.com
			 */
			if( url_check.getHost().contains("instagram.com") )
				return true;
			else
				return false;
			
		} catch (Exception e) { 
			return false;
		}
	}

	/*
	public static String getExtension( String path ) {
		String
			s_ext = "";
		
		int 
			idx = path.lastIndexOf( '.' );
		
		if( idx > 0 )
			s_ext = path.substring( idx + 1 );
		
		return s_ext;
	}
	*/
}
